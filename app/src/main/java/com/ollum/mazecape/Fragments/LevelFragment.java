package com.ollum.mazecape.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.ollum.mazecape.Adapters.TestLevelAdapter;
import com.ollum.mazecape.R;

public class LevelFragment extends Fragment {

    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level, container, false);

        gridView = (GridView) view.findViewById(R.id.gridView);
        gridView.setNumColumns(GameFragment.currentLevel[0].length - 6);
        gridView.setAdapter(new TestLevelAdapter(getContext(), 0));

        return view;
    }

}
