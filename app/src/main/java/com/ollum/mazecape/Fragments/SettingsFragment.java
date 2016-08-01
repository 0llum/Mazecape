package com.ollum.mazecape.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.R;

public class SettingsFragment extends Fragment {

    RelativeLayout relativeLayout;
    SeekBar musicVol, soundVol;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout_settings);

        musicVol = (SeekBar) view.findViewById(R.id.seekBar_music);
        musicVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MainActivity.volumeMusic = ((float) progress) / 10;
                Log.d("debug", "volumeMusic: " + MainActivity.volumeMusic);
                try {
                    GameFragment.bgm.setVolume(GameFragment.volumeBGM, GameFragment.volumeBGM);
                } catch (Exception e) {
                    e.printStackTrace();
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
                Log.d("debug", "volumeSound: " + MainActivity.volumeSound);
                try {
                    GameFragment.fire.setVolume(GameFragment.volumeFire, GameFragment.volumeFire);
                    GameFragment.heartbeat.setVolume(GameFragment.volumeHeart, GameFragment.volumeHeart);
                } catch (Exception e) {
                    e.printStackTrace();
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

        return view;
    }
}
