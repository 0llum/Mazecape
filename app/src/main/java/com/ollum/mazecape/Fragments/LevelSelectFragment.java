package com.ollum.mazecape.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.Arrays.LevelArrays;
import com.ollum.mazecape.R;

import java.util.ArrayList;
import java.util.List;

public class LevelSelectFragment extends Fragment {

    GridView gridViewLevels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level_select, container, false);

        gridViewLevels = (GridView) view.findViewById(R.id.gridViewLevels);
        gridViewLevels.setAdapter(new LevelsAdapter(getContext(), 0));
        gridViewLevels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position <= MainActivity.maxLevel) {
                    MainActivity.level = position;
                    if (MainActivity.lives > 0) {
                        GameFragment gameFragment = new GameFragment();
                        FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                        transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out);
                        transaction.replace(R.id.content, gameFragment, "GameFragment");
                        transaction.addToBackStack("GameFragment");
                        transaction.commit();
                    }
                }
            }
        });

        gridViewLevels.setSelection(MainActivity.maxLevel);

        return view;
    }

    private class LevelsAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public LevelsAdapter(Context context, int center) {
            inflater = LayoutInflater.from(context);

            for (int i = 0; i < LevelArrays.LEVEL.length; i++) {
                String scene = LevelArrays.LEVEL[i][LevelArrays.LEVEL[i].length - 2][2];
                if (i <= MainActivity.maxLevel) {
                    items.add(new Item("" + i, getResources().getIdentifier(scene + "xxx", "drawable", getContext().getPackageName()), i + 1));
                } else {
                    items.add(new Item("" + i, getResources().getIdentifier(scene + "blk", "drawable", getContext().getPackageName()), i + 1));
                }
            }
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return items.get(i).drawableId;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            View v = view;
            ImageView picture, score;
            TextView level;

            if (v == null) {
                v = inflater.inflate(R.layout.gridview_level_select, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                v.setTag(R.id.text, v.findViewById(R.id.text));
                v.setTag(R.id.score, v.findViewById(R.id.score));
                v.setTag(R.id.level, v.findViewById(R.id.level));
            }

            picture = (ImageView) v.getTag(R.id.picture);
            score = (ImageView) v.getTag(R.id.score);
            level = (TextView) v.getTag(R.id.level);

            Item item = (Item) getItem(position);

            picture.setImageResource(item.drawableId);
            if (MainActivity.starsList.contains(position + ", " + 5)) {
                score.setImageResource(R.drawable.stars_5);
            } else if (MainActivity.starsList.contains(position + ", " + 4)) {
                score.setImageResource(R.drawable.stars_4);
            } else if (MainActivity.starsList.contains(position + ", " + 3)) {
                score.setImageResource(R.drawable.stars_3);
            } else if (MainActivity.starsList.contains(position + ", " + 2)) {
                score.setImageResource(R.drawable.stars_2);
            } else if (MainActivity.starsList.contains(position + ", " + 1)) {
                score.setImageResource(R.drawable.stars_1);
            } else {
                score.setImageResource(R.drawable.stars_0);
            }

            level.setText("" + (position + 1));

            return v;
        }

        private class Item {
            final String name;
            final int drawableId, level;

            Item(String name, int drawableId, int level) {
                this.name = name;
                this.drawableId = drawableId;
                this.level = level;
            }
        }
    }

}
