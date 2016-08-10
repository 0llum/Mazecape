package com.ollum.mazecape.Adapters;

import android.content.Context;
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

public class TestLevelAdapter extends BaseAdapter {
    private List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;

    public TestLevelAdapter(Context context, int center) {
        inflater = LayoutInflater.from(context);

        for (int i = 3; i < GameFragment.currentLevel.length - 6; i++) {
            for (int k = 3; k < GameFragment.currentLevel[i].length - 3; k++) {
                items.add(new Item("" + i, context.getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[i][k], "drawable", context.getPackageName())));
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