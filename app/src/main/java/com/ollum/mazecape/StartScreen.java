package com.ollum.mazecape;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class StartScreen extends AppCompatActivity implements View.OnClickListener {

    static HashSet<String> starsList;
    static int maxLevel = 0;
    static int level = 0;
    static int lives = 5;
    Button continueButton, levelSelectButton, helpButton;
    ImageButton settingsButton;
    TextView livesCounter;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        continueButton = (Button) findViewById(R.id.button_continue);
        continueButton.setOnClickListener(this);

        levelSelectButton = (Button) findViewById(R.id.button_level_select);
        levelSelectButton.setOnClickListener(this);

        helpButton = (Button) findViewById(R.id.button_help);
        helpButton.setOnClickListener(this);

        settingsButton = (ImageButton) findViewById(R.id.button_settings);
        settingsButton.setOnClickListener(this);

        livesCounter = (TextView) findViewById(R.id.lives);

        starsList = new HashSet<>();

        loadGame();
        createHandler();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_continue:
                if (lives > 0) {
                    startActivity(new Intent(this, Level.class));
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
                break;
            case R.id.button_level_select:
                startActivity(new Intent(this, LevelSelect.class));
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.button_help:
                break;
            case R.id.button_settings:
                break;
        }
    }

    public void loadGame() {
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);

        level = sharedPreferences.getInt("level", 0);
        lives = sharedPreferences.getInt("lives", 5);
        maxLevel = sharedPreferences.getInt("maxLevel", 0);
        Set<String> set = sharedPreferences.getStringSet("stars", new HashSet<String>());
        starsList.addAll(set);

        livesCounter.setText("Lives: " + lives);
    }

    public void createHandler() {
        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (lives < 5) {
                    lives++;
                    livesCounter.setText("Lives: " + lives);
                    try {
                        Level.livesCounter.setText("Lives: " + lives);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                handler.postDelayed(this, 300000);
            }
        };
        handler.postDelayed(runnable, 0);
    }
}
