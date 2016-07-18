package com.ollum.mazecape.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.R;

public class MainMenuFragment extends Fragment implements View.OnClickListener {

    public static TextView livesCounter;
    Button continueButton, levelSelectButton, helpButton;
    ImageButton settingsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        continueButton = (Button) view.findViewById(R.id.button_continue);
        continueButton.setOnClickListener(this);

        if (MainActivity.maxLevel == 0) {
            continueButton.setText("New Game");
        } else {
            continueButton.setText("Continue");
        }

        levelSelectButton = (Button) view.findViewById(R.id.button_level_select);
        levelSelectButton.setOnClickListener(this);

        helpButton = (Button) view.findViewById(R.id.button_help);
        helpButton.setOnClickListener(this);

        settingsButton = (ImageButton) view.findViewById(R.id.button_settings);
        settingsButton.setOnClickListener(this);

        livesCounter = (TextView) view.findViewById(R.id.lives);
        livesCounter.setText("Lives: " + MainActivity.lives);

        MobileAds.initialize(getContext(), "ca-app-pub-7666608930334273~8844493743");

        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_continue:
                if (MainActivity.lives > 0) {
                    MainActivity.level = MainActivity.maxLevel;
                    GameFragment gameFragment = new GameFragment();
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out);
                    transaction.replace(R.id.content, gameFragment, "GameFragment");
                    transaction.addToBackStack("GameFragment");
                    transaction.commit();
                }
                break;
            case R.id.button_level_select:
                LevelSelectFragment levelSelectFragment = new LevelSelectFragment();
                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out);
                transaction.replace(R.id.content, levelSelectFragment, "LevelSelectFragment");
                transaction.addToBackStack("LevelSelectFragment");
                transaction.commit();
                break;
            case R.id.button_help:
                break;
            case R.id.button_settings:
                break;
        }
    }
}
