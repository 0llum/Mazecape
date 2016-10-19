package com.ollum.mazecape.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ollum.mazecape.Activities.MainActivity;

import java.util.HashSet;
import java.util.Set;

import static java.lang.System.currentTimeMillis;

public class SaveGame {
    public static void saveGame(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> starsSet = new HashSet<>();
        starsSet.addAll(MainActivity.starsList);
        Set<String> diarySet = new HashSet<>();
        diarySet.addAll(MainActivity.diaryList);
        editor.putInt("level", MainActivity.level);
        editor.putInt("world", MainActivity.world);
        editor.putInt("lives", MainActivity.lives);
        editor.putInt("levelCompass", MainActivity.levelCompass);
        editor.putInt("levelMap", MainActivity.levelMap);
        editor.putInt("levelTorch", MainActivity.levelTorch);
        editor.putInt("levelSpeed", MainActivity.levelSpeed);
        editor.putInt("levelStars", MainActivity.levelStars);
        editor.putInt("levelLives", MainActivity.levelLives);
        editor.putInt("maxWorld", MainActivity.maxWorld);
        editor.putInt("world1MaxLevel", MainActivity.maxLevel[0]);
        editor.putInt("world2MaxLevel", MainActivity.maxLevel[1]);
        editor.putInt("world3MaxLevel", MainActivity.maxLevel[2]);
        editor.putInt("world4MaxLevel", MainActivity.maxLevel[3]);
        editor.putInt("world5MaxLevel", MainActivity.maxLevel[4]);
        editor.putStringSet("stars", MainActivity.starsList);
        editor.putStringSet("diary", MainActivity.diaryList);
        editor.putInt("torches", MainActivity.torches);
        editor.putInt("allStars", MainActivity.allStars);
        editor.putInt("world1Stars", MainActivity.world1Stars);
        editor.putInt("world2Stars", MainActivity.world2Stars);
        editor.putInt("world3Stars", MainActivity.world3Stars);
        editor.putInt("world4Stars", MainActivity.world4Stars);
        editor.putInt("world5Stars", MainActivity.world5Stars);
        editor.putBoolean("swipe", MainActivity.swipe);
        editor.putBoolean("inverse", MainActivity.inverse);
        editor.putFloat("volumeMusic", MainActivity.volumeMusic);
        editor.putFloat("volumeSound", MainActivity.volumeSound);
        editor.putLong("logOffTime", currentTimeMillis());
        editor.putInt("resetCount", MainActivity.resetCount);
        editor.putBoolean("showAds", MainActivity.showAds);
        editor.putBoolean("rated", MainActivity.rated);
        editor.apply();
    }
}
