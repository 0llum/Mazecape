package com.ollum.mazecape.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ollum.mazecape.Activities.MainActivity;

import java.util.HashSet;
import java.util.Set;

import static java.lang.System.currentTimeMillis;

public class LoadGame {
    public static void loadGame(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        MainActivity.level = sharedPreferences.getInt("level", 0);
        MainActivity.world = sharedPreferences.getInt("world", 0);
        MainActivity.lives = sharedPreferences.getInt("lives", 5);
        MainActivity.levelCompass = sharedPreferences.getInt("levelCompass", 1);
        MainActivity.levelMap = sharedPreferences.getInt("levelMap", 1);
        MainActivity.levelTorch = sharedPreferences.getInt("levelTorch", 0);
        MainActivity.levelSpeed = sharedPreferences.getInt("levelSpeed", 0);
        MainActivity.levelStars = sharedPreferences.getInt("levelStars", 0);
        MainActivity.levelLives = sharedPreferences.getInt("levelLives", 0);
        MainActivity.maxWorld = sharedPreferences.getInt("maxWorld", 3);
        if (MainActivity.maxWorld > 4) {
            MainActivity.maxWorld = 4;
        }
        MainActivity.world1MaxLevel = sharedPreferences.getInt("world1MaxLevel", 0);
        MainActivity.world2MaxLevel = sharedPreferences.getInt("world2MaxLevel", 0);
        MainActivity.world3MaxLevel = sharedPreferences.getInt("world3MaxLevel", 0);
        MainActivity.world4MaxLevel = sharedPreferences.getInt("world4MaxLevel", 0);
        MainActivity.world5MaxLevel = sharedPreferences.getInt("world5MaxLevel", 0);
        MainActivity.allStars = sharedPreferences.getInt("allStars", 0);
        MainActivity.world1Stars = sharedPreferences.getInt("world1Stars", 0);
        MainActivity.world2Stars = sharedPreferences.getInt("world2Stars", 0);
        MainActivity.world3Stars = sharedPreferences.getInt("world3Stars", 0);
        MainActivity.world4Stars = sharedPreferences.getInt("world4Stars", 0);
        MainActivity.world5Stars = sharedPreferences.getInt("world5Stars", 0);
        MainActivity.torches = sharedPreferences.getInt("torches", 0);
        MainActivity.swipe = sharedPreferences.getBoolean("swipe", true);
        MainActivity.inverse = sharedPreferences.getBoolean("inverse", false);
        MainActivity.volumeMusic = sharedPreferences.getFloat("volumeMusic", 1);
        MainActivity.volumeSound = sharedPreferences.getFloat("volumeSound", 1);
        MainActivity.logOffTime = sharedPreferences.getLong("logOffTime", 0);
        MainActivity.resetCount = sharedPreferences.getInt("resetCount", 0);
        MainActivity.showAds = sharedPreferences.getBoolean("showAds", true);
        MainActivity.rated = sharedPreferences.getBoolean("rated", false);
        Set<String> set = sharedPreferences.getStringSet("stars", new HashSet<String>());
        MainActivity.starsList.addAll(set);

        MainActivity.worldStars = new int[]{
                MainActivity.world1Stars, MainActivity.world2Stars, MainActivity.world3Stars, MainActivity.world4Stars, MainActivity.world5Stars
        };

        MainActivity.maxLevel = new int[]{
                MainActivity.world1MaxLevel, MainActivity.world2MaxLevel, MainActivity.world3MaxLevel, MainActivity.world4MaxLevel, MainActivity.world5MaxLevel
        };

        while ((currentTimeMillis() - MainActivity.logOffTime > 300000) && MainActivity.lives < (5 + MainActivity.levelLives)) {
            MainActivity.lives++;
            MainActivity.logOffTime += 300000;
        }

        MainActivity.livesCounter.setText("" + MainActivity.lives);
        MainActivity.starsCounter.setText("" + MainActivity.allStars);
    }
}
