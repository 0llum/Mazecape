package com.ollum.mazecape;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LevelSelect extends AppCompatActivity {

    GridView gridViewLevels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_select);

        gridViewLevels = (GridView) findViewById(R.id.gridViewLevels);
        gridViewLevels.setAdapter(new LevelsAdapter(this, 0));
        gridViewLevels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position <= StartScreen.maxLevel) {
                    StartScreen.level = position;
                    if (StartScreen.lives > 0) {
                        startActivity(new Intent(getApplicationContext(), Level.class));
                        overridePendingTransition(R.anim.right_in, R.anim.left_out);
                    }
                }
            }
        });
        gridViewLevels.smoothScrollToPositionFromTop(StartScreen.maxLevel, 0, 500);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, StartScreen.class));
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    private class LevelsAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public LevelsAdapter(Context context, int center) {
            inflater = LayoutInflater.from(context);

            for (int i = 0; i < LevelArrays.LEVEL.length; i++) {
                String scene = LevelArrays.LEVEL[i][LevelArrays.LEVEL[i].length - 2][2];
                if (i <= StartScreen.maxLevel) {
                    items.add(new Item("" + i, getResources().getIdentifier(scene + "xxx", "drawable", getPackageName()), i + 1));
                } else {
                    items.add(new Item("" + i, getResources().getIdentifier(scene + "blk", "drawable", getPackageName()), i + 1));
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
            if (StartScreen.starsList.contains(position + ", " + 5)) {
                score.setImageResource(R.drawable.stars_5);
            } else if (StartScreen.starsList.contains(position + ", " + 4)) {
                score.setImageResource(R.drawable.stars_4);
            } else if (StartScreen.starsList.contains(position + ", " + 3)) {
                score.setImageResource(R.drawable.stars_3);
            } else if (StartScreen.starsList.contains(position + ", " + 2)) {
                score.setImageResource(R.drawable.stars_2);
            } else if (StartScreen.starsList.contains(position + ", " + 1)) {
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
