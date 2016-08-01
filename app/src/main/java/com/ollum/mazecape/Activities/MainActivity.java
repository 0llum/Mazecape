package com.ollum.mazecape.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.ollum.mazecape.Fragments.GameFragment;
import com.ollum.mazecape.Fragments.HelpFragment;
import com.ollum.mazecape.Fragments.LevelSelectFragment;
import com.ollum.mazecape.Fragments.MainMenuFragment;
import com.ollum.mazecape.Fragments.SettingsFragment;
import com.ollum.mazecape.Fragments.ShopFragment;
import com.ollum.mazecape.Fragments.WorldSelectFragment;
import com.ollum.mazecape.R;
import com.ollum.mazecape.util.IabHelper;
import com.ollum.mazecape.util.IabResult;

import java.util.HashSet;
import java.util.Set;

import static java.lang.System.currentTimeMillis;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static FragmentManager fragmentManager;

    public static int maxLevel = 0;
    public static int maxWorld = 0;
    public static int level = 0;
    public static int world = 0;
    public static int lives = 5;
    public static boolean swipe = true;
    public static HashSet<String> starsList;
    public static int allStars = 0;
    public static int world1Stars = 0;
    public static int world2Stars = 0;
    public static int world3Stars = 0;
    public static int world4Stars = 0;
    public static int[] worldStars;
    public static Display display;
    public static Point size;
    public static int statusBarHeight;
    public static int width;
    public static int height;
    public static RelativeLayout titleBar;
    public static LinearLayout stats, livesLayout, starsLayout, timeLayout, stepsLayout;
    public static TextView title, livesCounter, starsCounter, timeCounter, stepsCounter;
    public static SettingsFragment settingsFragment = new SettingsFragment();
    public static ShopFragment shopFragment = new ShopFragment();
    public static HelpFragment helpFragment = new HelpFragment();
    public static boolean settingsVisible = false;
    public static boolean shopVisible = false;
    public static boolean helpVisible = false;
    public static boolean stopTime = false;
    public static int torches = 0;
    public static IabHelper mHelper;
    public static float volumeMusic;
    public static float volumeSound;
    public static int resetCount = 0;
    public static AdRequest adRequest;
    public static boolean showAds = true;
    public Handler livesHandler;
    public long startMillis;
    public long endMillis;
    public long logOffTime;
    ImageButton settingsButton, helpButton, shopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApCqNdNLtSwn177FZyPyfYbq8sU11JXtug9dUq9JTwqMgIgdCzhaUpIdNtngKSkmaKXCyYPRn8rgCMOBeZYDBmyZavrU1NVZl9u1lUCbtq+AUG9AxgdsIz2BswAsGj4eORV4/6rNOjRswfhQpQAMC0qgwNCNbq/U6WTSzZVHkyC6SEsn3W+A9mfNbYX7lvipEuIlS6pNsZQo0/fXPhHhJ6INAHD4ZRJ/R/FNdkL/kqKkdXmaNe2YDkbSgvgOc+21INw8qb4EztlX8W4bZcEcSt2zWGSfPLbDLkZ21mBkBoM/DdF28xGt8wN7uYgsZpTR6XMlLO/S/pcTYO1x5LT+XwQIDAQAB";

        // compute your public key and store it in base64EncodedPublicKey
        mHelper = new IabHelper(this, base64EncodedPublicKey);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    Log.d("debug", "Problem setting up In-app Billing: " + result);
                }
                // Hooray, IAB is fully set up!
            }
        });

        display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        statusBarHeight = getStatusBarHeight();
        width = size.x;
        height = size.y - statusBarHeight;

        titleBar = (RelativeLayout) findViewById(R.id.titleBar);
        titleBar.getLayoutParams().height = MainActivity.height / 16;

        stats = (LinearLayout) findViewById(R.id.stats);
        stats.getLayoutParams().height = MainActivity.height / 16;

        livesLayout = (LinearLayout) findViewById(R.id.livesLayout);
        starsLayout = (LinearLayout) findViewById(R.id.starsLayout);
        timeLayout = (LinearLayout) findViewById(R.id.timeLayout);
        timeLayout.setVisibility(View.INVISIBLE);
        stepsLayout = (LinearLayout) findViewById(R.id.stepsLayout);
        stepsLayout.setVisibility(View.INVISIBLE);

        title = (TextView) findViewById(R.id.title);
        title.setText("Mazecape");
        livesCounter = (TextView) findViewById(R.id.livesCounter);
        starsCounter = (TextView) findViewById(R.id.starsCounter);
        timeCounter = (TextView) findViewById(R.id.timeCounter);
        stepsCounter = (TextView) findViewById(R.id.stepsCounter);

        settingsButton = (ImageButton) findViewById(R.id.button_settings);
        settingsButton.setOnClickListener(this);

        helpButton = (ImageButton) findViewById(R.id.button_help);
        helpButton.setOnClickListener(this);

        shopButton = (ImageButton) findViewById(R.id.button_shop);
        shopButton.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        MainMenuFragment mainMenuFragment = new MainMenuFragment();
        transaction.replace(R.id.content, mainMenuFragment, "MainMenuFragment");
        transaction.addToBackStack("MainMenuFragment");
        transaction.commit();

        starsList = new HashSet<>();

        MobileAds.initialize(this, "ca-app-pub-7666608930334273~8844493743");
        adRequest = new AdRequest.Builder().build();

        loadGame();
        createHandler();
    }

    @Override
    public void onBackPressed() {
        FragmentManager.BackStackEntry currentFragment = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
        String current = currentFragment.getName();

        if (current.equals("MainMenuFragment")) {
            return;
        } else if (current.equals("WorldSelectFragment")) {
            MainMenuFragment mainMenuFragment = new MainMenuFragment();
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
            transaction.replace(R.id.content, mainMenuFragment, "MainMenuFragment");
            transaction.addToBackStack("MainMenuFragment");
            transaction.commit();
        } else if (current.equals("LevelSelectFragment")) {
            WorldSelectFragment worldSelectFragment = new WorldSelectFragment();
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
            transaction.replace(R.id.content, worldSelectFragment, "WorldSelectFragment");
            transaction.addToBackStack("WorldSelectFragment");
            transaction.commit();
        } else if (current.equals("GameFragment") || current.equals("LevelFragment")) {
            try {
                GameFragment.bgm.pause();
                GameFragment.fire.pause();
                GameFragment.heartbeat.pause();
            } catch (Exception e) {
                e.printStackTrace();
            }

            stopTime = true;

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("");
            builder.setMessage("Do you really want to go back to the level select screen? You will lose all your progress in the current level!");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    resetCount++;
                    livesCounter.setText("" + lives);
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
                    stopTime = false;
                    GameFragment.bgm.start();
                    GameFragment.fire.start();
                    GameFragment.heartbeat.start();
                }
            });
            builder.setCancelable(false);
            builder.create();
            builder.show();
        } else if (current.equals("SettingsFragment")) {
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
            transaction.remove(settingsFragment);
            transaction.commit();
            fragmentManager.popBackStack();
            stopTime = false;
            settingsVisible = false;
        } else if (current.equals("ShopFragment")) {
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
            transaction.remove(shopFragment);
            transaction.commit();
            fragmentManager.popBackStack();
            stopTime = false;
            shopVisible = false;
        } else if (current.equals("HelpFragment")) {
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
            transaction.remove(helpFragment);
            transaction.commit();
            fragmentManager.popBackStack();
            stopTime = false;
            helpVisible = false;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadGame();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) try {
            mHelper.dispose();
        } catch (IabHelper.IabAsyncInProgressException e) {
            e.printStackTrace();
        }
        mHelper = null;
    }

    public void loadGame() {
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);

        level = sharedPreferences.getInt("level", 0);
        world = sharedPreferences.getInt("world", 0);
        lives = sharedPreferences.getInt("lives", 5);
        maxLevel = sharedPreferences.getInt("maxLevel", 0);
        maxWorld = sharedPreferences.getInt("maxWorld", 0);
        allStars = sharedPreferences.getInt("allStars", 0);
        world1Stars = sharedPreferences.getInt("world1Stars", 0);
        world2Stars = sharedPreferences.getInt("world2Stars", 0);
        world3Stars = sharedPreferences.getInt("world3Stars", 0);
        world4Stars = sharedPreferences.getInt("world4Stars", 0);
        torches = sharedPreferences.getInt("torches", 0);
        swipe = sharedPreferences.getBoolean("swipe", true);
        volumeMusic = sharedPreferences.getFloat("volumeMusic", 1);
        volumeSound = sharedPreferences.getFloat("volumeSound", 1);
        logOffTime = sharedPreferences.getLong("logOffTime", 0);
        resetCount = sharedPreferences.getInt("resetCount", 0);
        showAds = sharedPreferences.getBoolean("showAds", true);
        Set<String> set = sharedPreferences.getStringSet("stars", new HashSet<String>());
        starsList.addAll(set);

        worldStars = new int[]{
                world1Stars, world2Stars, world3Stars, world4Stars
        };

        while ((currentTimeMillis() - logOffTime > 300000) && lives < 5) {
            lives++;
            logOffTime += 300000;
        }

        livesCounter.setText("" + lives);
        starsCounter.setText("" + allStars);
    }

    public void saveGame() {
        Set<String> set = new HashSet<String>();
        set.addAll(starsList);
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("level", level);
        editor.putInt("world", world);
        editor.putInt("lives", lives);
        editor.putInt("maxLevel", maxLevel);
        editor.putInt("maxWorld", maxWorld);
        editor.putStringSet("stars", starsList);
        editor.putInt("torches", torches);
        editor.putInt("allStars", allStars);
        editor.putInt("world1Stars", world1Stars);
        editor.putInt("world2Stars", world2Stars);
        editor.putInt("world3Stars", world3Stars);
        editor.putInt("world4Stars", world4Stars);
        editor.putBoolean("swipe", swipe);
        editor.putFloat("volumeMusic", volumeMusic);
        editor.putFloat("volumeSound", volumeSound);
        editor.putLong("logOffTime", currentTimeMillis());
        editor.putInt("resetCount", resetCount);
        editor.putBoolean("showAds", showAds);
        editor.apply();
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
                    if (endMillis - startMillis > 300000) {
                        lives++;
                        startMillis = currentTimeMillis();
                        livesCounter.setText("" + lives);
                    }
                } else {
                    startMillis = currentTimeMillis();
                }
                livesHandler.postDelayed(this, 1000);
            }
        };
        livesHandler.postDelayed(runnable, 0);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_settings:
                if (shopVisible) {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(shopFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    shopVisible = false;
                }
                if (helpVisible) {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(helpFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    helpVisible = false;
                }
                if (!settingsVisible) {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.add(R.id.content, settingsFragment, "SettingsFragment");
                    transaction.addToBackStack("SettingsFragment");
                    transaction.commit();
                    stopTime = true;
                    settingsVisible = true;
                } else {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(settingsFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    settingsVisible = false;
                }
                break;
            case R.id.button_help:
                if (shopVisible) {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(shopFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    shopVisible = false;
                }
                if (settingsVisible) {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(settingsFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    settingsVisible = false;
                }
                if (!helpVisible) {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.add(R.id.content, helpFragment, "HelpFragment");
                    transaction.addToBackStack("HelpFragment");
                    transaction.commit();
                    stopTime = true;
                    helpVisible = true;
                } else {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(helpFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    helpVisible = false;
                }
                break;
            case R.id.button_shop:
                if (helpVisible) {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(helpFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    helpVisible = false;
                }
                if (settingsVisible) {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(settingsFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    settingsVisible = false;
                }
                if (!shopVisible) {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.add(R.id.content, shopFragment, "ShopFragment");
                    transaction.addToBackStack("ShopFragment");
                    transaction.commit();
                    stopTime = true;
                    shopVisible = true;
                } else {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(shopFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    shopVisible = false;
                }
                break;
        }
    }
}
