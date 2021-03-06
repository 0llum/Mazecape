package com.ollum.mazecape.Utils;

import android.content.Context;
import android.preference.PreferenceManager;

import com.ollum.mazecape.Activities.MainActivity;

import java.util.HashSet;
import java.util.Set;

import static java.lang.System.currentTimeMillis;

public class SharedPreferences {
    public static void saveGame(Context context) {
        android.content.SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("firstStart", MainActivity.firstStart);
        editor.putBoolean("wantsGPGS", MainActivity.wantsGPGS);
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
        editor.putBoolean("vibration", MainActivity.vibration);
        editor.putFloat("volumeMusic", MainActivity.volumeMusic);
        editor.putFloat("volumeSound", MainActivity.volumeSound);
        editor.putLong("logOffTime", currentTimeMillis());
        editor.putInt("resetCount", MainActivity.resetCount);
        editor.putBoolean("showAds", MainActivity.showAds);
        editor.putBoolean("rated", MainActivity.rated);
        editor.putBoolean("liked", MainActivity.liked);
        editor.putBoolean("achievement50Stars", MainActivity.achievement50Stars);
        editor.putBoolean("achievement100Stars", MainActivity.achievement100Stars);
        editor.putBoolean("achievement200Stars", MainActivity.achievement200Stars);
        editor.putBoolean("achievement500Stars", MainActivity.achievement500Stars);
        editor.putBoolean("achievementWorld1Finished", MainActivity.achievementWorld1Finished);
        editor.putBoolean("achievementWorld2Finished", MainActivity.achievementWorld2Finished);
        editor.putBoolean("achievementWorld3Finished", MainActivity.achievementWorld3Finished);
        editor.putBoolean("achievementWorld4Finished", MainActivity.achievementWorld4Finished);
        editor.putBoolean("achievementWorld1Completed", MainActivity.achievementWorld1Completed);
        editor.putBoolean("achievementWorld2Completed", MainActivity.achievementWorld2Completed);
        editor.putBoolean("achievementWorld3Completed", MainActivity.achievementWorld3Completed);
        editor.putBoolean("achievementWorld4Completed", MainActivity.achievementWorld4Completed);
        editor.putBoolean("achievementChapter1Completed", MainActivity.achievementChapter1Completed);
        editor.putBoolean("achievementChapter2Completed", MainActivity.achievementChapter2Completed);
        editor.putBoolean("achievementChapter3Completed", MainActivity.achievementChapter3Completed);
        editor.putBoolean("achievementChapter4Completed", MainActivity.achievementChapter4Completed);
        editor.putBoolean("achievementEngineer", MainActivity.achievementEngineer);
        editor.putBoolean("achievementNavigator", MainActivity.achievementNavigator);
        editor.putBoolean("achievementTreasureHunter", MainActivity.achievementTreasureHunter);
        editor.putBoolean("achievementGuidingLight", MainActivity.achievementGuidingLight);
        editor.putBoolean("achievementSprinter", MainActivity.achievementSprinter);
        editor.putBoolean("achievementAddict", MainActivity.achievementAddict);
        editor.putBoolean("achievementGreedy", MainActivity.achievementGreedy);
        editor.putBoolean("achievementCreator", MainActivity.achievementCreator);
        editor.putBoolean("achievementSupporter", MainActivity.achievementSupporter);
        editor.putInt("highScore", MainActivity.highScore);
        editor.apply();
    }

    public static void loadGame(Context context) {
        android.content.SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        MainActivity.firstStart = sharedPreferences.getBoolean("firstStart", true);
        MainActivity.wantsGPGS = sharedPreferences.getBoolean("wantsGPGS", false);
        MainActivity.level = sharedPreferences.getInt("level", 0);
        MainActivity.world = sharedPreferences.getInt("world", 0);
        MainActivity.lives = sharedPreferences.getInt("lives", 5);
        MainActivity.levelCompass = sharedPreferences.getInt("levelCompass", 1);
        MainActivity.levelMap = sharedPreferences.getInt("levelMap", 1);
        MainActivity.levelTorch = sharedPreferences.getInt("levelTorch", 0);
        MainActivity.levelSpeed = sharedPreferences.getInt("levelSpeed", 0);
        MainActivity.levelStars = sharedPreferences.getInt("levelStars", 0);
        MainActivity.levelLives = sharedPreferences.getInt("levelLives", 0);
        MainActivity.maxWorld = sharedPreferences.getInt("maxWorld", 0);
        if (MainActivity.maxWorld > 3) {
            MainActivity.maxWorld = 3;
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
        MainActivity.vibration = sharedPreferences.getBoolean("vibration", true);
        MainActivity.volumeMusic = sharedPreferences.getFloat("volumeMusic", 1);
        MainActivity.volumeSound = sharedPreferences.getFloat("volumeSound", 1);
        MainActivity.logOffTime = sharedPreferences.getLong("logOffTime", 0);
        MainActivity.resetCount = sharedPreferences.getInt("resetCount", 0);
        MainActivity.showAds = sharedPreferences.getBoolean("showAds", true);
        MainActivity.rated = sharedPreferences.getBoolean("rated", false);
        MainActivity.liked = sharedPreferences.getBoolean("liked", false);
        Set<String> starsSet = sharedPreferences.getStringSet("stars", new HashSet<String>());
        MainActivity.starsList.addAll(starsSet);
        Set<String> diarySet = sharedPreferences.getStringSet("diary", new HashSet<String>());
        MainActivity.diaryList.addAll(diarySet);
        MainActivity.achievement50Stars = sharedPreferences.getBoolean("achievement50Stars", false);
        MainActivity.achievement100Stars = sharedPreferences.getBoolean("achievement100Stars", false);
        MainActivity.achievement200Stars = sharedPreferences.getBoolean("achievement200Stars", false);
        MainActivity.achievement500Stars = sharedPreferences.getBoolean("achievement500Stars", false);
        MainActivity.achievementWorld1Finished = sharedPreferences.getBoolean("achievementWorld1Finished", false);
        MainActivity.achievementWorld2Finished = sharedPreferences.getBoolean("achievementWorld2Finished", false);
        MainActivity.achievementWorld3Finished = sharedPreferences.getBoolean("achievementWorld3Finished", false);
        MainActivity.achievementWorld4Finished = sharedPreferences.getBoolean("achievementWorld4Finished", false);
        MainActivity.achievementWorld1Completed = sharedPreferences.getBoolean("achievementWorld1Completed", false);
        MainActivity.achievementWorld2Completed = sharedPreferences.getBoolean("achievementWorld2Completed", false);
        MainActivity.achievementWorld3Completed = sharedPreferences.getBoolean("achievementWorld3Completed", false);
        MainActivity.achievementWorld4Completed = sharedPreferences.getBoolean("achievementWorld4Completed", false);
        MainActivity.achievementChapter1Completed = sharedPreferences.getBoolean("achievementChapter1Completed", false);
        MainActivity.achievementChapter2Completed = sharedPreferences.getBoolean("achievementChapter2Completed", false);
        MainActivity.achievementChapter3Completed = sharedPreferences.getBoolean("achievementChapter3Completed", false);
        MainActivity.achievementChapter4Completed = sharedPreferences.getBoolean("achievementChapter4Completed", false);
        MainActivity.achievementEngineer = sharedPreferences.getBoolean("achievementEngineer", false);
        MainActivity.achievementNavigator = sharedPreferences.getBoolean("achievementNavigator", false);
        MainActivity.achievementTreasureHunter = sharedPreferences.getBoolean("achievementTreasureHunter", false);
        MainActivity.achievementGuidingLight = sharedPreferences.getBoolean("achievementGuidingLight", false);
        MainActivity.achievementSprinter = sharedPreferences.getBoolean("achievementSprinter", false);
        MainActivity.achievementAddict = sharedPreferences.getBoolean("achievementAddict", false);
        MainActivity.achievementGreedy = sharedPreferences.getBoolean("achievementGreedy", false);
        MainActivity.achievementCreator = sharedPreferences.getBoolean("achievementCreator", false);
        MainActivity.achievementSupporter = sharedPreferences.getBoolean("achievementSupporter", false);
        MainActivity.highScore = sharedPreferences.getInt("highScore", 0);

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

    public static void resetGame(Context context) {
        android.content.SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();

        MainActivity.starsList.clear();
        MainActivity.diaryList.clear();
        editor.putInt("level", 0);
        editor.putInt("world", 0);
        editor.putInt("lives", 5);
        editor.putInt("levelCompass", 1);
        editor.putInt("levelMap", 1);
        editor.putInt("levelTorch", 0);
        editor.putInt("levelSpeed", 0);
        editor.putInt("levelStars", 0);
        editor.putInt("levelLives", 0);
        editor.putInt("maxWorld", 0);
        editor.putInt("world1MaxLevel", 0);
        editor.putInt("world2MaxLevel", 0);
        editor.putInt("world3MaxLevel", 0);
        editor.putInt("world4MaxLevel", 0);
        editor.putInt("world5MaxLevel", 0);
        editor.putStringSet("stars", MainActivity.starsList);
        editor.putStringSet("diary", MainActivity.diaryList);
        editor.putInt("torches", 0);
        editor.putInt("allStars", 0);
        editor.putInt("world1Stars", 0);
        editor.putInt("world2Stars", 0);
        editor.putInt("world3Stars", 0);
        editor.putInt("world4Stars", 0);
        editor.putInt("world5Stars", 0);
        editor.putBoolean("rated", false);
        editor.putBoolean("liked", false);
        editor.apply();
    }
}
