package com.ollum.mazecape.Adapters;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.Classes.Item;
import com.ollum.mazecape.Fragments.GameFragment;
import com.ollum.mazecape.R;

import java.util.ArrayList;
import java.util.List;

public class MapAdapter extends BaseAdapter {
    private List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;


    public MapAdapter(Context context, int center) {
        inflater = LayoutInflater.from(context);

        for (int i = -3; i <= 3; i++) {
            for (int k = -3; k <= 3; k++) {
                if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + k, GameFragment.position.y + i)) && MainActivity.levelMap > 0) {
                    if (GameFragment.currentLevel[GameFragment.y + i][GameFragment.x + k].substring(0, 1).equals("c") || GameFragment.currentLevel[GameFragment.y + i][GameFragment.x + k].substring(0, 1).equals("b")) {
                        items.add(new Item(i + 4 + "-" + k + 4, context.getResources().getIdentifier("mn" + GameFragment.currentLevel[GameFragment.y + i][GameFragment.x + k].substring(1, 3), "drawable", context.getPackageName())));
                        Log.d("debug", "mn" + GameFragment.currentLevel[GameFragment.y + i][GameFragment.x + k].substring(1, 3));
                    } else {
                        items.add(new Item(i + 4 + "-" + k + 4, context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + i][GameFragment.x + k], "drawable", context.getPackageName())));
                    }
                } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + k, GameFragment.position.y + i)) && MainActivity.levelMap > 1) {
                    if (GameFragment.currentLevel[GameFragment.y + i][GameFragment.x + k].substring(0, 1).equals("c") || GameFragment.currentLevel[GameFragment.y + i][GameFragment.x + k].substring(0, 1).equals("b")) {
                        items.add(new Item(i + 4 + "-" + k + 4, context.getResources().getIdentifier("nn" + GameFragment.currentLevel[GameFragment.y + i][GameFragment.x + k].substring(1, 3), "drawable", context.getPackageName())));
                        Log.d("debug", "nn" + GameFragment.currentLevel[GameFragment.y + i][GameFragment.x + k].substring(1, 3));
                    } else {
                        items.add(new Item(i + 4 + "-" + k + 4, context.getResources().getIdentifier("n" + GameFragment.currentLevel[GameFragment.y + i][GameFragment.x + k], "drawable", context.getPackageName())));
                    }
                } else {
                    items.add(new Item(i + 4 + "-" + k + 4, R.drawable.blank));
                }
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;

        if (v == null) {
            v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);

        Item item = (Item) getItem(i);

        picture.setImageResource(item.drawableId);

        return v;
    }
}
