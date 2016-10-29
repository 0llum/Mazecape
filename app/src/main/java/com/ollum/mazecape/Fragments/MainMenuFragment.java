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
    Button playButton, levelEditorButton, storyButton, shopButton, helpButton;
    CheckBox checkBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        MainActivity.title.setText(R.string.app_name);

        playButton = (Button) view.findViewById(R.id.button_play);
        playButton.setOnClickListener(this);

        levelEditorButton = (Button) view.findViewById(R.id.button_level_editor);
        levelEditorButton.setOnClickListener(this);

        storyButton = (Button) view.findViewById(R.id.button_story);
        storyButton.setOnClickListener(this);

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
        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
        switch (v.getId()) {
            case R.id.button_play:
                WorldSelectFragment worldSelectFragment = new WorldSelectFragment();
                FragmentTransaction transaction2 = MainActivity.fragmentManager.beginTransaction();
                transaction2.setCustomAnimations(R.anim.right_in, R.anim.left_out);
                transaction2.replace(R.id.content, worldSelectFragment, "WorldSelectFragment");
                transaction2.addToBackStack("WorldSelectFragment");
                transaction2.commit();
                break;
            case R.id.button_story:
                StoryFragment storyFragment = new StoryFragment();
                FragmentTransaction transaction6 = MainActivity.fragmentManager.beginTransaction();
                transaction6.setCustomAnimations(R.anim.right_in, R.anim.left_out);
                transaction6.replace(R.id.content, storyFragment, "StoryFragment");
                transaction6.addToBackStack("StoryFragment");
                transaction6.commit();

                if (MainActivity.menuBGM != null) {
                    MainActivity.menuBGM.pause();
                    MainActivity.menuBGM.seekTo(0);
                }
                break;
            case R.id.button_shop:
                MainActivity.soundPool.play(MainActivity.swoosh1ID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                transaction.add(R.id.content, MainActivity.shopFragment, "ShopFragment");
                transaction.addToBackStack("ShopFragment");
                transaction.commit();
                MainActivity.shopVisible = true;
                break;
            case R.id.button_help:
                MainActivity.soundPool.play(MainActivity.swoosh1ID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                FragmentTransaction transaction4 = MainActivity.fragmentManager.beginTransaction();
                transaction4.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                transaction4.add(R.id.content, MainActivity.helpFragment, "HelpFragment");
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
                break;
        }
    }
}
