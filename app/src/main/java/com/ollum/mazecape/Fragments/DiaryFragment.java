package com.ollum.mazecape.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.R;

import static com.ollum.mazecape.Activities.MainActivity.diaryFragment;
import static com.ollum.mazecape.Activities.MainActivity.fragmentManager;
import static com.ollum.mazecape.Activities.MainActivity.stopTime;

public class DiaryFragment extends Fragment implements View.OnClickListener {
    Button button;
    TextView title, subtitle, message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary, container, false);

        title = (TextView) view.findViewById(R.id.diary_title);
        title.setText(MainActivity.diaryTitle);

        subtitle = (TextView) view.findViewById(R.id.diary_subtitle);
        subtitle.setText(MainActivity.diarySubtitle);

        message = (TextView) view.findViewById(R.id.diary_message);
        message.setText(MainActivity.diaryMessage);

        button = (Button) view.findViewById(R.id.diary_button);
        button.setText(MainActivity.diaryButton);
        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        MainActivity.soundPool.play(MainActivity.diaryID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(diaryFragment);
        transaction.commit();
        fragmentManager.popBackStack();
        stopTime = false;
    }
}
