package com.ollum.mazecape.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.ollum.mazecape.Fragments.GameFragment;
import com.ollum.mazecape.Fragments.LevelSelectFragment;
import com.ollum.mazecape.Fragments.MainMenuFragment;
import com.ollum.mazecape.R;

import java.util.HashSet;
import java.util.Set;

import static java.lang.System.currentTimeMillis;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;

    public static int maxLevel = 0;
    public static int level = 0;
    public static int lives = 5;
    public static HashSet<String> starsList;
    public Handler livesHandler;
    public long startMillis;
    public long endMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        MainMenuFragment mainMenuFragment = new MainMenuFragment();
        transaction.replace(R.id.content, mainMenuFragment, "MainMenuFragment");
        transaction.addToBackStack("MainMenuFragment");
        transaction.commit();

        starsList = new HashSet<>();

        loadGame();
        createHandler();
    }

    @Override
    public void onBackPressed() {
        FragmentManager.BackStackEntry currentFragment = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
        String current = currentFragment.getName();

        if (current.equals("MainMenuFragment")) {
            return;
        } else if (current.equals("LevelSelectFragment")) {
            MainMenuFragment mainMenuFragment = new MainMenuFragment();
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
            transaction.replace(R.id.content, mainMenuFragment, "MainMenuFragment");
            transaction.addToBackStack("MainMenuFragment");
            transaction.commit();
        } else if (current.equals("GameFragment")) {
            try {
                GameFragment.bgm.pause();
                GameFragment.fire.pause();
                GameFragment.heartbeat.pause();
            } catch (Exception e) {
                e.printStackTrace();
            }

            GameFragment.stopTime = true;

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("");
            builder.setMessage("Do you really want to go back to the level select screen? You will lose all your progress in the current level!");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    LevelSelectFragment levelSelectFragment = new LevelSelectFragment();
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
                    transaction.replace(R.id.content, levelSelectFragment, "LevelSelectFragment");
                    transaction.addToBackStack("LevelSelectFragment");
                    transaction.commit();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    GameFragment.stopTime = false;
                    GameFragment.bgm.start();
                    GameFragment.fire.start();
                    GameFragment.heartbeat.start();
                }
            });
            builder.setCancelable(false);
            builder.create();
            builder.show();
        } else {
            super.onBackPressed();
        }
    }

    public void loadGame() {
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);

        level = sharedPreferences.getInt("level", 0);
        lives = sharedPreferences.getInt("lives", 5);
        maxLevel = sharedPreferences.getInt("maxLevel", 0);
        Set<String> set = sharedPreferences.getStringSet("stars", new HashSet<String>());
        starsList.addAll(set);
    }

    public void saveGame() {

    }

    public void createHandler() {
        startMillis = currentTimeMillis();
        endMillis = currentTimeMillis();
        livesHandler = new android.os.Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                endMillis = currentTimeMillis();
                if (lives < 5) {
                    if (endMillis - startMillis > 60000) {
                        lives++;
                        startMillis = currentTimeMillis();
                        try {
                            MainMenuFragment.livesCounter.setText("Lives: " + lives);
                            GameFragment.livesCounter.setText("Lives: " + lives);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    startMillis = currentTimeMillis();
                }
                livesHandler.postDelayed(this, 1000);
            }
        };
        livesHandler.postDelayed(runnable, 0);
    }
}
