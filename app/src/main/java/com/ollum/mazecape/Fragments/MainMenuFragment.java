package com.ollum.mazecape.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.gms.ads.AdView;
import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.R;

public class MainMenuFragment extends Fragment implements View.OnClickListener {

    public static boolean devMode = false;
    Button continueButton, levelSelectButton, levelEditorButton, shopButton, helpButton;
    CheckBox checkBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        MainActivity.title.setText("Mazecape");

        continueButton = (Button) view.findViewById(R.id.button_continue);
        continueButton.setOnClickListener(this);

        if (MainActivity.maxLevel == 0) {
            continueButton.setText("New Game");
        } else {
            continueButton.setText("Continue");
        }

        levelSelectButton = (Button) view.findViewById(R.id.button_level_select);
        levelSelectButton.setOnClickListener(this);

        levelEditorButton = (Button) view.findViewById(R.id.button_level_editor);
        levelEditorButton.setOnClickListener(this);

        shopButton = (Button) view.findViewById(R.id.button_shop);
        shopButton.setOnClickListener(this);

        helpButton = (Button) view.findViewById(R.id.button_help);
        helpButton.setOnClickListener(this);

        checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        checkBox.setChecked(devMode);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                devMode = isChecked;
            }
        });

        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().build();
        if (MainActivity.showAds) {
            mAdView.loadAd(MainActivity.adRequest);
        }

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
                } else {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.add(R.id.content, MainActivity.shopFragment, "ShopFragment");
                    transaction.addToBackStack("ShopFragment");
                    transaction.commit();
                    MainActivity.stopTime = true;
                    MainActivity.shopVisible = true;
                }
                break;
            case R.id.button_level_select:
                /*LevelSelectFragment levelSelectFragment = new LevelSelectFragment();
                FragmentTransaction transaction2 = MainActivity.fragmentManager.beginTransaction();
                transaction2.setCustomAnimations(R.anim.right_in, R.anim.left_out);
                transaction2.replace(R.id.content, levelSelectFragment, "LevelSelectFragment");
                transaction2.addToBackStack("LevelSelectFragment");
                transaction2.commit();*/

                WorldSelectFragment worldSelectFragment = new WorldSelectFragment();
                FragmentTransaction transaction2 = MainActivity.fragmentManager.beginTransaction();
                transaction2.setCustomAnimations(R.anim.right_in, R.anim.left_out);
                transaction2.replace(R.id.content, worldSelectFragment, "WorldSelectFragment");
                transaction2.addToBackStack("WorldSelectFragment");
                transaction2.commit();
                break;
            case R.id.button_shop:
                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                transaction.add(R.id.content, MainActivity.shopFragment, "ShopFragment");
                transaction.addToBackStack("ShopFragment");
                transaction.commit();
                MainActivity.shopVisible = true;
                break;
            case R.id.button_help:
                HelpFragment helpFragment = new HelpFragment();
                FragmentTransaction transaction4 = MainActivity.fragmentManager.beginTransaction();
                transaction4.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                transaction4.add(R.id.content, helpFragment, "HelpFragment");
                transaction4.addToBackStack("HelpFragment");
                transaction4.commit();
                MainActivity.helpVisible = true;
                break;
            case R.id.button_level_editor:
                LevelEditorFragment levelEditorFragment = new LevelEditorFragment();
                FragmentTransaction transaction5 = MainActivity.fragmentManager.beginTransaction();
                transaction5.setCustomAnimations(R.anim.right_in, R.anim.left_out);
                transaction5.replace(R.id.content, levelEditorFragment, "LevelEditorFragment");
                transaction5.addToBackStack("LevelEditorFragment");
                transaction5.commit();
        }
    }
}
