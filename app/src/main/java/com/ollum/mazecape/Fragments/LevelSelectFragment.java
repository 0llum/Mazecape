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
import android.widget.Toast;

import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.Arrays.Worlds;
import com.ollum.mazecape.R;

import java.util.ArrayList;
import java.util.List;

public class LevelSelectFragment extends Fragment {

    GridView gridViewLevels;
    String[][][] currentWorld;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level_select, container, false);

        MainActivity.title.setText(R.string.level_select);

        currentWorld = Worlds.WORLDS[MainActivity.world];

        gridViewLevels = (GridView) view.findViewById(R.id.gridViewLevels);
        gridViewLevels.setAdapter(new LevelsAdapter(getContext(), 0));
        gridViewLevels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);

                if (MainMenuFragment.devMode) {
                    MainActivity.level = position;
                    GameFragment gameFragment = new GameFragment();
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out);
                    transaction.replace(R.id.content, gameFragment, "GameFragment");
                    transaction.addToBackStack("GameFragment");
                    transaction.commit();
                    if (MainActivity.menuBGM != null) {
                        MainActivity.menuBGM.pause();
                        MainActivity.menuBGM.seekTo(0);
                    }
                } else {
                    if (position <= MainActivity.maxLevel[MainActivity.world]) {
                        MainActivity.level = position;
                        if (MainActivity.lives > 0) {
                            GameFragment gameFragment = new GameFragment();
                            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                            transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out);
                            transaction.replace(R.id.content, gameFragment, "GameFragment");
                            transaction.addToBackStack("GameFragment");
                            transaction.commit();
                            if (MainActivity.menuBGM != null) {
                                MainActivity.menuBGM.pause();
                                MainActivity.menuBGM.seekTo(0);
                            }
                        } else {
                            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                            transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                            transaction.add(R.id.content, MainActivity.shopFragment, "ShopFragment");
                            transaction.addToBackStack("ShopFragment");
                            transaction.commit();
                            MainActivity.shopVisible = true;
                        }
                    } else {
                        Toast.makeText(getContext(), R.string.unlock_level, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        gridViewLevels.setSelection(MainActivity.maxLevel[MainActivity.world]);

        return view;
    }

    private class LevelsAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public LevelsAdapter(Context context, int center) {
            inflater = LayoutInflater.from(context);

            for (int i = 0; i < currentWorld.length; i++) {
                String scene = currentWorld[i][currentWorld[i].length - 4][2];
                if (i <= MainActivity.maxLevel[MainActivity.world]) {
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
                v.setTag(R.id.world, v.findViewById(R.id.world));
            }

            picture = (ImageView) v.getTag(R.id.picture);
            score = (ImageView) v.getTag(R.id.score);
            level = (TextView) v.getTag(R.id.world);

            Item item = (Item) getItem(position);

            picture.setImageResource(item.drawableId);
            if (MainActivity.starsList.contains(MainActivity.world + ", " + position + ", " + 5)) {
                score.setImageResource(R.drawable.stars_5);
            } else if (MainActivity.starsList.contains(MainActivity.world + ", " + position + ", " + 4)) {
                score.setImageResource(R.drawable.stars_4);
            } else if (MainActivity.starsList.contains(MainActivity.world + ", " + position + ", " + 3)) {
                score.setImageResource(R.drawable.stars_3);
            } else if (MainActivity.starsList.contains(MainActivity.world + ", " + position + ", " + 2)) {
                score.setImageResource(R.drawable.stars_2);
            } else if (MainActivity.starsList.contains(MainActivity.world + ", " + position + ", " + 1)) {
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
