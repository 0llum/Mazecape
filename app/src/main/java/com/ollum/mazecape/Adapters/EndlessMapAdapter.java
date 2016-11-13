package com.ollum.mazecape.Adapters;

import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.Classes.Item;
import com.ollum.mazecape.Fragments.EndlessFragment;
import com.ollum.mazecape.R;

import java.util.ArrayList;
import java.util.List;

public class EndlessMapAdapter extends BaseAdapter {
    private List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;


    public EndlessMapAdapter(Context context, int center) {
        inflater = LayoutInflater.from(context);

        for (int i = -3; i <= 3; i++) {
            for (int k = -3; k <= 3; k++) {
                if (EndlessFragment.stepsMade.contains(new Point(EndlessFragment.position.x + k, EndlessFragment.position.y + i)) && MainActivity.levelMap > 0) {
                    if (EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("c") || EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("b") || EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("d") || EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("x") || EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("v") || EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("l") || EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("q") || EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("z")) {
                        items.add(new Item(i + 4 + "-" + k + 4, context.getResources().getIdentifier("xn" + EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(1, 3), "drawable", context.getPackageName())));
                    } else {
                        items.add(new Item(i + 4 + "-" + k + 4, context.getResources().getIdentifier("x" + EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k], "drawable", context.getPackageName())));
                    }
                } else if (EndlessFragment.discovered.contains(new Point(EndlessFragment.position.x + k, EndlessFragment.position.y + i)) && MainActivity.levelMap > 1) {
                    if (EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("c") || EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("b") || EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("d") || EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("x") || EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("v") || EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("l") || EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("q") || EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(0, 1).equals("z")) {
                        items.add(new Item(i + 4 + "-" + k + 4, context.getResources().getIdentifier("yn" + EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k].substring(1, 3), "drawable", context.getPackageName())));
                    } else {
                        items.add(new Item(i + 4 + "-" + k + 4, context.getResources().getIdentifier("y" + EndlessFragment.currentLevel[EndlessFragment.y + i][EndlessFragment.x + k], "drawable", context.getPackageName())));
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
