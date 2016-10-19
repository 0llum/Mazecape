package com.ollum.mazecape.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ResetGame {
    public static void resetGame(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        SaveGame.saveGame(context);
        LoadGame.loadGame(context);
    }
}
