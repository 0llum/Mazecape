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

import static com.ollum.mazecape.Activities.MainActivity.fragmentManager;
import static com.ollum.mazecape.Activities.MainActivity.stopTime;
import static com.ollum.mazecape.Activities.MainActivity.tutorialFragment;

public class TutorialFragment extends Fragment implements View.OnClickListener {
    Button button;
    TextView title, message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial, container, false);

        title = (TextView) view.findViewById(R.id.tutorial_title);
        title.setText(MainActivity.tutorialTitle);

        message = (TextView) view.findViewById(R.id.tutorial_message);
        message.setText(MainActivity.tutorialMessage);

        button = (Button) view.findViewById(R.id.tutorial_button);
        button.setText(MainActivity.tutorialButton);
        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(tutorialFragment);
        transaction.commit();
        fragmentManager.popBackStack();
        stopTime = false;
    }
}
