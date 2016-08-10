package com.ollum.mazecape.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.R;

public class SettingsFragment extends Fragment {

    RelativeLayout relativeLayout;
    SeekBar musicVol, soundVol;
    ToggleButton toggleButtonControl, toggleButtonInverse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout_settings);

        musicVol = (SeekBar) view.findViewById(R.id.seekBar_music);
        musicVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MainActivity.volumeMusic = ((float) progress) / 10;
                if (GameFragment.bgm != null) {
                    GameFragment.bgm.setVolume(GameFragment.volumeBGM, GameFragment.volumeBGM);
                }
                if (MainActivity.menuBGM != null) {
                    MainActivity.menuBGM.setVolume(MainActivity.volumeMusic, MainActivity.volumeMusic);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        musicVol.setProgress((int) (MainActivity.volumeMusic * 10));

        soundVol = (SeekBar) view.findViewById(R.id.seekBar_sound);
        soundVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MainActivity.volumeSound = ((float) progress) / 10;
                if (GameFragment.fire != null) {
                    GameFragment.fire.setVolume(GameFragment.volumeFire, GameFragment.volumeFire);
                }
                if (GameFragment.heartbeat != null) {
                    GameFragment.heartbeat.setVolume(GameFragment.volumeHeart, GameFragment.volumeHeart);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        soundVol.setProgress((int) (MainActivity.volumeSound * 10));

        toggleButtonControl = (ToggleButton) view.findViewById(R.id.toggleButtonControl);
        toggleButtonControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.swipe = true;

                } else {
                    MainActivity.swipe = false;
                }
            }
        });
        toggleButtonControl.setChecked(MainActivity.swipe);

        toggleButtonInverse = (ToggleButton) view.findViewById(R.id.toggleButtonInverse);
        toggleButtonInverse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.inverse = true;
                } else {
                    MainActivity.inverse = false;
                }
            }
        });
        toggleButtonInverse.setChecked(MainActivity.inverse);

        return view;
    }
}
