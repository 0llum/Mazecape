package com.ollum.mazecape.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ollum.mazecape.R;

public class HelpFragment extends Fragment {

    LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        linearLayout = (LinearLayout) view.findViewById(R.id.linear_layout_help);

        return view;
    }

}
