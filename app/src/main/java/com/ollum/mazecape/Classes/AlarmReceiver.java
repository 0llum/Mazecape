package com.ollum.mazecape.Classes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ollum.mazecape.Activities.MainActivity;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (MainActivity.lives < 5) {
            MainActivity.lives++;
        }
        Log.d("debug", "Lives: " + MainActivity.lives);
    }
}