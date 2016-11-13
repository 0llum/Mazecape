package com.ollum.mazecape.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.R;
import com.ollum.mazecape.util.SharedPreferences;

public class SettingsFragment extends Fragment {

    RelativeLayout relativeLayout;
    SeekBar musicVol, soundVol;
    ToggleButton toggleButtonControl, toggleButtonInverse;
    Button resetButton;
    TextView textViewVersion;
    CheckBox vibrationCheckbox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout_settings);

        textViewVersion = (TextView) view.findViewById(R.id.settings_version);
        try {
            textViewVersion.setText(getString(R.string.version) + ": " + getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            textViewVersion.setText("");
        }

        musicVol = (SeekBar) view.findViewById(R.id.seekBar_music);
        musicVol.setProgress((int) (MainActivity.volumeMusic * 10));
        musicVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MainActivity.volumeMusic = ((float) progress) / 10;
                if (GameFragment.gameBGM != null) {
                    GameFragment.gameBGM.setVolume(GameFragment.volumeBGM, GameFragment.volumeBGM);
                }
                if (MainActivity.menuBGM != null) {
                    MainActivity.menuBGM.setVolume(MainActivity.volumeMusic, MainActivity.volumeMusic);
                }
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        soundVol = (SeekBar) view.findViewById(R.id.seekBar_sound);
        soundVol.setProgress((int) (MainActivity.volumeSound * 10));
        soundVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MainActivity.volumeSound = ((float) progress) / 10;
                if (GameFragment.fireAtmo != null) {
                    GameFragment.fireAtmo.setVolume(GameFragment.volumeFire, GameFragment.volumeFire);
                }
                if (GameFragment.heartbeatAtmo != null) {
                    GameFragment.heartbeatAtmo.setVolume(GameFragment.volumeHeart, GameFragment.volumeHeart);
                }
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        vibrationCheckbox = (CheckBox) view.findViewById(R.id.checkBox_vibration);
        vibrationCheckbox.setChecked(MainActivity.vibration);
        vibrationCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.vibration = isChecked;
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
            }
        });

        toggleButtonControl = (ToggleButton) view.findViewById(R.id.toggleButtonControl);
        toggleButtonControl.setChecked(MainActivity.swipe);
        toggleButtonControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                if (isChecked) {
                    MainActivity.swipe = true;
                } else {
                    MainActivity.swipe = false;
                }
            }
        });

        toggleButtonInverse = (ToggleButton) view.findViewById(R.id.toggleButtonInverse);
        toggleButtonInverse.setChecked(MainActivity.inverse);
        toggleButtonInverse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                if (isChecked) {
                    MainActivity.inverse = true;
                } else {
                    MainActivity.inverse = false;
                }
            }
        });

        resetButton = (Button) view.findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(R.string.reset_game);
                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                        SharedPreferences.resetGame(getContext());
                        SharedPreferences.loadGame(getContext());
                    }
                });
                builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });

        return view;
    }
}
