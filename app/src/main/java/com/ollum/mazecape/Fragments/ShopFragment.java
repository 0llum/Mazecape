package com.ollum.mazecape.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.R;
import com.ollum.mazecape.util.IabHelper;
import com.ollum.mazecape.util.IabResult;
import com.ollum.mazecape.util.Purchase;
import com.ollum.mazecape.util.SaveGame;

import java.util.HashSet;
import java.util.Set;

import static java.lang.System.currentTimeMillis;

public class ShopFragment extends Fragment implements View.OnClickListener, RewardedVideoAdListener {

    public static LinearLayout rateLayout;
    public static TextView liveTimer, compassUpgrade, mapUpgrade, torchUpgrade, speedUpgrade, livesUpgrade, starsUpgrade, compassLevel, mapLevel, torchLevel, speedLevel, livesLevel, starsLevel;
    Button button1heart, button5hearts, buttonCompass, buttonMap, buttonTorch, buttonSpeed, buttonLives, buttonStars, button100stars, button200stars, button500stars, button1000stars, buttonRate, buttonWatchAd;
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        rateLayout = (LinearLayout) view.findViewById(R.id.linearLayout_rate);
        if (MainActivity.rated) {
            rateLayout.setVisibility(View.GONE);
        }

        liveTimer = (TextView) view.findViewById(R.id.live_timer);
        compassUpgrade = (TextView) view.findViewById(R.id.compass_upgrade);
        mapUpgrade = (TextView) view.findViewById(R.id.map_upgrade);
        torchUpgrade = (TextView) view.findViewById(R.id.torch_upgrade);
        speedUpgrade = (TextView) view.findViewById(R.id.speed_upgrade);
        livesUpgrade = (TextView) view.findViewById(R.id.lives_upgrade);
        starsUpgrade = (TextView) view.findViewById(R.id.stars_upgrade);
        compassLevel = (TextView) view.findViewById(R.id.compass_level);
        mapLevel = (TextView) view.findViewById(R.id.map_level);
        torchLevel = (TextView) view.findViewById(R.id.torch_level);
        speedLevel = (TextView) view.findViewById(R.id.speed_level);
        livesLevel = (TextView) view.findViewById(R.id.lives_level);
        starsLevel = (TextView) view.findViewById(R.id.stars_level);

        button1heart = (Button) view.findViewById(R.id.button_1_heart);
        button1heart.setOnClickListener(this);

        button5hearts = (Button) view.findViewById(R.id.button_5_hearts);
        button5hearts.setOnClickListener(this);

        buttonCompass = (Button) view.findViewById(R.id.button_compass);
        buttonCompass.setOnClickListener(this);

        switch (MainActivity.levelCompass) {
            case 0:
                buttonCompass.setVisibility(View.INVISIBLE);
                compassUpgrade.setText(R.string.upgrade);
                compassLevel.setText("Level 0");
                break;
            case 1:
                buttonCompass.setText("200");
                compassUpgrade.setText(R.string.upgrade);
                compassLevel.setText(getString(R.string.goal));
                break;
            case 2:
                buttonCompass.setVisibility(View.INVISIBLE);
                compassUpgrade.setText(R.string.max);
                compassLevel.setText(getString(R.string.stars));
                break;
        }

        buttonMap = (Button) view.findViewById(R.id.button_map);
        buttonMap.setOnClickListener(this);

        switch (MainActivity.levelMap) {
            case 0:
                buttonMap.setVisibility(View.INVISIBLE);
                mapUpgrade.setText(R.string.upgrade);
                mapLevel.setText("Level 0");
                break;
            case 1:
                buttonMap.setText("200");
                mapUpgrade.setText(R.string.upgrade);
                mapLevel.setText(getString(R.string.steps));
                break;
            case 2:
                buttonMap.setVisibility(View.INVISIBLE);
                mapUpgrade.setText(R.string.max);
                mapLevel.setText(getString(R.string.discovered));
                break;
        }

        buttonTorch = (Button) view.findViewById(R.id.button_torch);
        buttonTorch.setOnClickListener(this);

        switch (MainActivity.levelTorch) {
            case 0:
                buttonTorch.setText("50");
                torchUpgrade.setText(R.string.upgrade);
                torchLevel.setText("1:00 min");
                break;
            case 1:
                buttonTorch.setText("100");
                torchUpgrade.setText(R.string.upgrade);
                torchLevel.setText("1:30 min");
                break;
            case 2:
                buttonTorch.setText("150");
                torchUpgrade.setText(R.string.upgrade);
                torchLevel.setText("2:00 min");
                break;
            case 3:
                buttonTorch.setText("200");
                torchUpgrade.setText(R.string.upgrade);
                torchLevel.setText("2:30 min");
                break;
            case 4:
                buttonTorch.setVisibility(View.INVISIBLE);
                torchUpgrade.setText(R.string.max);
                torchLevel.setText("3:00 min");
                break;
        }

        buttonSpeed = (Button) view.findViewById(R.id.button_speed);
        buttonSpeed.setOnClickListener(this);

        switch (MainActivity.levelSpeed) {
            case 0:
                buttonSpeed.setText("50");
                speedUpgrade.setText(R.string.upgrade);
                speedLevel.setText("100%");
                break;
            case 1:
                buttonSpeed.setText("100");
                speedUpgrade.setText(R.string.upgrade);
                speedLevel.setText("133%");
                break;
            case 2:
                buttonSpeed.setText("150");
                speedUpgrade.setText(R.string.upgrade);
                speedLevel.setText("166%");
                break;
            case 3:
                buttonSpeed.setVisibility(View.INVISIBLE);
                speedUpgrade.setText(R.string.max);
                speedLevel.setText("200%");
                break;
        }

        buttonLives = (Button) view.findViewById(R.id.button_lives);
        buttonLives.setOnClickListener(this);

        switch (MainActivity.levelLives) {
            case 0:
                buttonLives.setText("50");
                livesUpgrade.setText(R.string.upgrade);
                livesLevel.setText("5 " + getString(R.string.lives));
                break;
            case 1:
                buttonLives.setText("100");
                livesUpgrade.setText(R.string.upgrade);
                livesLevel.setText("6 " + getString(R.string.lives));
                break;
            case 2:
                buttonLives.setText("150");
                livesUpgrade.setText(R.string.upgrade);
                livesLevel.setText("7 " + getString(R.string.lives));
                break;
            case 3:
                buttonLives.setText("200");
                livesUpgrade.setText(R.string.upgrade);
                livesLevel.setText("8 " + getString(R.string.lives));
                break;
            case 4:
                buttonLives.setText("250");
                livesUpgrade.setText(R.string.upgrade);
                livesLevel.setText("9 " + getString(R.string.lives));
                break;
            case 5:
                buttonLives.setVisibility(View.INVISIBLE);
                livesUpgrade.setText(R.string.max);
                livesLevel.setText("10 " + getString(R.string.lives));
                break;
        }

        buttonStars = (Button) view.findViewById(R.id.button_stars);
        buttonStars.setOnClickListener(this);

        switch (MainActivity.levelStars) {
            case 0:
                buttonStars.setText("100");
                starsUpgrade.setText(R.string.upgrade);
                starsLevel.setText("x1");
                break;
            case 1:
                buttonStars.setText("200");
                starsUpgrade.setText(R.string.upgrade);
                starsLevel.setText("x2");
                break;
            case 2:
                buttonStars.setVisibility(View.INVISIBLE);
                starsUpgrade.setText(R.string.max);
                starsLevel.setText("x3");
                break;
        }

        button100stars = (Button) view.findViewById(R.id.button_100_stars);
        button100stars.setOnClickListener(this);

        button200stars = (Button) view.findViewById(R.id.button_200_stars);
        button200stars.setOnClickListener(this);

        button500stars = (Button) view.findViewById(R.id.button_500_stars);
        button500stars.setOnClickListener(this);

        button1000stars = (Button) view.findViewById(R.id.button_1000_stars);
        button1000stars.setOnClickListener(this);

        buttonRate = (Button) view.findViewById(R.id.button_rate);
        buttonRate.setOnClickListener(this);

        buttonWatchAd = (Button) view.findViewById(R.id.button_watch_ad);
        buttonWatchAd.setOnClickListener(this);

        mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
            @Override
            public void onIabPurchaseFinished(IabResult result, Purchase info) {
                if (result.isFailure()) {
                    Toast.makeText(getContext(), R.string.purchase_error, Toast.LENGTH_LONG).show();
                } else if (info.getSku().equals("100_stars")) {
                    MainActivity.showAds = false;
                    MainActivity.allStars += 100;
                    MainActivity.soundPool.play(MainActivity.starID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    Toast.makeText(getContext(), R.string.purchase_successful, Toast.LENGTH_LONG).show();
                    SaveGame.saveGame(getContext());;
                } else if (info.getSku().equals("200_stars")) {
                    MainActivity.showAds = false;
                    MainActivity.allStars += 200;
                    MainActivity.soundPool.play(MainActivity.starID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    Toast.makeText(getContext(), R.string.purchase_successful, Toast.LENGTH_LONG).show();
                    SaveGame.saveGame(getContext());;
                } else if (info.getSku().equals("500_stars")) {
                    MainActivity.showAds = false;
                    MainActivity.allStars += 500;
                    MainActivity.soundPool.play(MainActivity.starID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    Toast.makeText(getContext(), R.string.purchase_successful, Toast.LENGTH_LONG).show();
                    SaveGame.saveGame(getContext());;
                } else if (info.getSku().equals("1000_stars")) {
                    MainActivity.showAds = false;
                    MainActivity.allStars += 1000;
                    MainActivity.soundPool.play(MainActivity.starID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    Toast.makeText(getContext(), R.string.purchase_successful, Toast.LENGTH_LONG).show();
                    SaveGame.saveGame(getContext());;
                }
            }
        };

        //MainActivity.mAd.setRewardedVideoAdListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);

        switch (v.getId()) {
            case R.id.button_1_heart:
                if (MainActivity.allStars >= 5) {
                    if (MainActivity.lives < (5 + MainActivity.levelLives)) {
                        MainActivity.allStars -= 5;
                        MainActivity.lives++;
                        MainActivity.starsCounter.setText("" + MainActivity.allStars);
                        MainActivity.livesCounter.setText("" + MainActivity.lives);
                        Toast.makeText(getContext(), R.string.purchase_successful, Toast.LENGTH_LONG).show();
                        MainActivity.soundPool.play(MainActivity.liveID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                        SaveGame.saveGame(getContext());;
                    } else {
                        Toast.makeText(getContext(), R.string.full_health, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), R.string.not_enough_stars, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_5_hearts:
                if (MainActivity.allStars >= 20) {
                    if (MainActivity.lives < (5 + MainActivity.levelLives)) {
                        MainActivity.allStars -= 20;
                        for (int i = 0; i < 5; i++) {
                            if (MainActivity.lives < (5 + MainActivity.levelLives)) {
                                MainActivity.lives++;
                            }
                        }
                        MainActivity.starsCounter.setText("" + MainActivity.allStars);
                        MainActivity.livesCounter.setText("" + MainActivity.lives);
                        Toast.makeText(getContext(), R.string.purchase_successful, Toast.LENGTH_LONG).show();
                        MainActivity.soundPool.play(MainActivity.liveID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                        SaveGame.saveGame(getContext());;
                    } else {
                        Toast.makeText(getContext(), R.string.full_health, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), R.string.not_enough_stars, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_compass:
                if (MainActivity.allStars >= Integer.parseInt(buttonCompass.getText().toString())) {
                    MainActivity.allStars -= Integer.parseInt(buttonCompass.getText().toString());
                    MainActivity.levelCompass++;
                    MainActivity.soundPool.play(MainActivity.upgradeID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    switch (MainActivity.levelCompass) {
                        case 0:
                            buttonCompass.setVisibility(View.INVISIBLE);
                            compassUpgrade.setText(R.string.upgrade);
                            compassLevel.setText("Level 0");
                            break;
                        case 1:
                            buttonCompass.setText("200");
                            compassUpgrade.setText(R.string.upgrade);
                            compassLevel.setText(getString(R.string.goal));
                        case 2:
                            buttonCompass.setVisibility(View.INVISIBLE);
                            compassUpgrade.setText(R.string.max);
                            compassLevel.setText(getString(R.string.stars));
                            break;
                    }
                    Toast.makeText(getContext(), R.string.purchase_successful, Toast.LENGTH_LONG).show();
                    SaveGame.saveGame(getContext());;
                } else {
                    Toast.makeText(getContext(), R.string.not_enough_stars, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_map:
                if (MainActivity.allStars >= Integer.parseInt(buttonMap.getText().toString())) {
                    MainActivity.allStars -= Integer.parseInt(buttonMap.getText().toString());
                    MainActivity.levelMap++;
                    MainActivity.soundPool.play(MainActivity.upgradeID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    switch (MainActivity.levelMap) {
                        case 0:
                            buttonMap.setVisibility(View.INVISIBLE);
                            mapUpgrade.setText(R.string.upgrade);
                            mapLevel.setText("Level 0");
                            break;
                        case 1:
                            buttonMap.setText("200");
                            mapUpgrade.setText(R.string.upgrade);
                            mapLevel.setText(getString(R.string.steps));
                            break;
                        case 2:
                            buttonMap.setVisibility(View.INVISIBLE);
                            mapUpgrade.setText(R.string.max);
                            mapLevel.setText(getString(R.string.discovered));
                            break;
                    }
                    Toast.makeText(getContext(), R.string.purchase_successful, Toast.LENGTH_LONG).show();
                    SaveGame.saveGame(getContext());;
                } else {
                    Toast.makeText(getContext(), R.string.not_enough_stars, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_torch:
                if (MainActivity.allStars >= Integer.parseInt(buttonTorch.getText().toString())) {
                    MainActivity.allStars -= Integer.parseInt(buttonTorch.getText().toString());
                    MainActivity.levelTorch++;
                    MainActivity.soundPool.play(MainActivity.upgradeID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    switch (MainActivity.levelTorch) {
                        case 0:
                            buttonTorch.setText("50");
                            torchUpgrade.setText(R.string.upgrade);
                            torchLevel.setText("1:00 min");
                            break;
                        case 1:
                            buttonTorch.setText("100");
                            torchUpgrade.setText(R.string.upgrade);
                            torchLevel.setText("1:30 min");
                            break;
                        case 2:
                            buttonTorch.setText("150");
                            torchUpgrade.setText(R.string.upgrade);
                            torchLevel.setText("2:00 min");
                            break;
                        case 3:
                            buttonTorch.setText("200");
                            torchUpgrade.setText(R.string.upgrade);
                            torchLevel.setText("2:30 min");
                            break;
                        case 4:
                            buttonTorch.setVisibility(View.INVISIBLE);
                            torchUpgrade.setText(R.string.max);
                            torchLevel.setText("3:00 min");
                            break;
                    }
                    Toast.makeText(getContext(), R.string.purchase_successful, Toast.LENGTH_LONG).show();
                    SaveGame.saveGame(getContext());;
                } else {
                    Toast.makeText(getContext(), R.string.not_enough_stars, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_speed:
                if (MainActivity.allStars >= Integer.parseInt(buttonSpeed.getText().toString())) {
                    MainActivity.allStars -= Integer.parseInt(buttonSpeed.getText().toString());
                    MainActivity.levelSpeed++;
                    MainActivity.soundPool.play(MainActivity.upgradeID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    switch (MainActivity.levelSpeed) {
                        case 0:
                            buttonSpeed.setText("50");
                            speedUpgrade.setText(R.string.upgrade);
                            speedLevel.setText("100%");
                            break;
                        case 1:
                            buttonSpeed.setText("100");
                            speedUpgrade.setText(R.string.upgrade);
                            speedLevel.setText("133%");
                            break;
                        case 2:
                            buttonSpeed.setText("150");
                            speedUpgrade.setText(R.string.upgrade);
                            speedLevel.setText("166%");
                            break;
                        case 3:
                            buttonSpeed.setVisibility(View.INVISIBLE);
                            speedUpgrade.setText(R.string.max);
                            speedLevel.setText("200%");
                            break;
                    }
                    Toast.makeText(getContext(), R.string.purchase_successful, Toast.LENGTH_LONG).show();
                    SaveGame.saveGame(getContext());;
                } else {
                    Toast.makeText(getContext(), R.string.not_enough_stars, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_lives:
                if (MainActivity.allStars >= Integer.parseInt(buttonLives.getText().toString())) {
                    MainActivity.allStars -= Integer.parseInt(buttonLives.getText().toString());
                    MainActivity.levelLives++;
                    MainActivity.soundPool.play(MainActivity.upgradeID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    switch (MainActivity.levelLives) {
                        case 0:
                            buttonLives.setText("50");
                            livesUpgrade.setText(R.string.upgrade);
                            livesLevel.setText("5 " + getString(R.string.lives));
                            break;
                        case 1:
                            buttonLives.setText("100");
                            livesUpgrade.setText(R.string.upgrade);
                            livesLevel.setText("6 " + getString(R.string.lives));
                            break;
                        case 2:
                            buttonLives.setText("150");
                            livesUpgrade.setText(R.string.upgrade);
                            livesLevel.setText("7 " + getString(R.string.lives));
                            break;
                        case 3:
                            buttonLives.setText("200");
                            livesUpgrade.setText(R.string.upgrade);
                            livesLevel.setText("8 " + getString(R.string.lives));
                            break;
                        case 4:
                            buttonLives.setText("250");
                            livesUpgrade.setText(R.string.upgrade);
                            livesLevel.setText("9 " + getString(R.string.lives));
                            break;
                        case 5:
                            buttonLives.setVisibility(View.INVISIBLE);
                            livesUpgrade.setText(R.string.max);
                            livesLevel.setText("10 " + getString(R.string.lives));
                            break;
                    }
                    Toast.makeText(getContext(), R.string.purchase_successful, Toast.LENGTH_LONG).show();
                    SaveGame.saveGame(getContext());;
                } else {
                    Toast.makeText(getContext(), R.string.not_enough_stars, Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.button_stars:
                if (MainActivity.allStars >= Integer.parseInt(buttonStars.getText().toString())) {
                    MainActivity.allStars -= Integer.parseInt(buttonStars.getText().toString());
                    MainActivity.levelStars++;
                    MainActivity.soundPool.play(MainActivity.upgradeID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    switch (MainActivity.levelStars) {
                        case 0:
                            buttonStars.setText("100");
                            starsUpgrade.setText(R.string.upgrade);
                            starsLevel.setText("x1");
                            break;
                        case 1:
                            buttonStars.setText("200");
                            starsUpgrade.setText(R.string.upgrade);
                            starsLevel.setText("x2");
                            break;
                        case 2:
                            buttonStars.setVisibility(View.INVISIBLE);
                            starsUpgrade.setText(R.string.max);
                            starsLevel.setText("x3");
                            break;
                    }
                    Toast.makeText(getContext(), R.string.purchase_successful, Toast.LENGTH_LONG).show();
                    SaveGame.saveGame(getContext());;
                } else {
                    Toast.makeText(getContext(), R.string.not_enough_stars, Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_100_stars:
                try {
                    MainActivity.mHelper.launchPurchaseFlow(getActivity(), "100_stars", 10001, mPurchaseFinishedListener, "");
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_200_stars:
                try {
                    MainActivity.mHelper.launchPurchaseFlow(getActivity(), "200_stars", 10001, mPurchaseFinishedListener, "");
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_500_stars:
                try {
                    MainActivity.mHelper.launchPurchaseFlow(getActivity(), "500_stars", 10001, mPurchaseFinishedListener, "");
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_1000_stars:
                try {
                    MainActivity.mHelper.launchPurchaseFlow(getActivity(), "1000_stars", 10001, mPurchaseFinishedListener, "");
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_rate:
                if (isOnline()) {
                    MainActivity.allStars += 50;
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    MainActivity.rated = true;
                    rateLayout.setVisibility(View.GONE);

                    final String appPackageName = getActivity().getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }

                    SaveGame.saveGame(getContext());;
                } else {
                    Toast.makeText(getContext(), R.string.no_internet, Toast.LENGTH_LONG).show();
                }
                break;
            /*case R.id.button_watch_ad:
                if (MainActivity.mAd.isLoaded()) {
                    MainActivity.mAd.show();
                }
                break;*/
        }
    }

    @Override
    public void onRewarded(RewardItem reward) {
        //Toast.makeText(getContext(), "onRewarded! currency: " + reward.getType() + "  amount: " + reward.getAmount(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        //Toast.makeText(getContext(), "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        //Toast.makeText(getContext(), "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        //Toast.makeText(getContext(), "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        //Toast.makeText(getContext(), "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        //Toast.makeText(getContext(), "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        //Toast.makeText(getContext(), "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
