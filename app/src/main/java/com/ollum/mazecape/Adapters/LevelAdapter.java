package com.ollum.mazecape.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ollum.mazecape.Classes.Item;
import com.ollum.mazecape.Fragments.GameFragment;
import com.ollum.mazecape.R;

import java.util.ArrayList;
import java.util.List;

public class LevelAdapter extends BaseAdapter {
    private List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;

    public LevelAdapter(Context context, int center) {
        Log.d("debug", "LevelAdapter");
        inflater = LayoutInflater.from(context);

        items.add(new Item("1-1", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x - 2], "drawable", context.getPackageName())));
        items.add(new Item("1-2", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x - 1], "drawable", context.getPackageName())));
        items.add(new Item("1-3", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x], "drawable", context.getPackageName())));
        items.add(new Item("1-4", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x + 1], "drawable", context.getPackageName())));
        items.add(new Item("1-5", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x + 2], "drawable", context.getPackageName())));
        items.add(new Item("1-1", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x - 2], "drawable", context.getPackageName())));
        items.add(new Item("2-2", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x - 1], "drawable", context.getPackageName())));
        items.add(new Item("2-3", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x], "drawable", context.getPackageName())));
        items.add(new Item("2-4", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x + 1], "drawable", context.getPackageName())));
        items.add(new Item("2-5", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x + 2], "drawable", context.getPackageName())));
        items.add(new Item("3-1", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y][GameFragment.x - 2], "drawable", context.getPackageName())));
        items.add(new Item("3-2", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y][GameFragment.x - 1], "drawable", context.getPackageName())));

        if (center == 0) {
            items.add(new Item("3-3", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y][GameFragment.x], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("3-3", center));
        }

        items.add(new Item("3-4", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y][GameFragment.x + 1], "drawable", context.getPackageName())));
        items.add(new Item("3-5", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y][GameFragment.x + 2], "drawable", context.getPackageName())));
        items.add(new Item("4-1", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x - 2], "drawable", context.getPackageName())));
        items.add(new Item("4-2", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x - 1], "drawable", context.getPackageName())));
        items.add(new Item("4-3", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x], "drawable", context.getPackageName())));
        items.add(new Item("4-4", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x + 1], "drawable", context.getPackageName())));
        items.add(new Item("4-5", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x + 2], "drawable", context.getPackageName())));
        items.add(new Item("5-1", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x - 2], "drawable", context.getPackageName())));
        items.add(new Item("5-2", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x - 1], "drawable", context.getPackageName())));
        items.add(new Item("5-3", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x], "drawable", context.getPackageName())));
        items.add(new Item("5-4", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x + 1], "drawable", context.getPackageName())));
        items.add(new Item("5-5", context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x + 2], "drawable", context.getPackageName())));
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
