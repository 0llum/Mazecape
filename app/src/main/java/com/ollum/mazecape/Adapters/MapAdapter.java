package com.ollum.mazecape.Adapters;

import android.content.Context;
import android.graphics.Point;
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

public class MapAdapter extends BaseAdapter {
    private List<Item> items = new ArrayList<Item>();
    private LayoutInflater inflater;


    public MapAdapter(Context context, int center) {
        Log.d("debug", "MapAdapter");
        inflater = LayoutInflater.from(context);

        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 3, GameFragment.position.y - 3))) {
            items.add(new Item("1-1", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 3][GameFragment.x - 3], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 3, GameFragment.position.y - 3))) {
            items.add(new Item("1-1", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 3][GameFragment.x - 3], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("1-1", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 2, GameFragment.position.y - 3))) {
            items.add(new Item("1-2", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 3][GameFragment.x - 2], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 2, GameFragment.position.y - 3))) {
            items.add(new Item("1-2", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 3][GameFragment.x - 2], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("1-2", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 1, GameFragment.position.y - 3))) {
            items.add(new Item("1-3", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 3][GameFragment.x - 1], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 1, GameFragment.position.y - 3))) {
            items.add(new Item("1-3", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 3][GameFragment.x - 1], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("1-3", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x, GameFragment.position.y - 3))) {
            items.add(new Item("1-4", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 3][GameFragment.x], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x, GameFragment.position.y - 3))) {
            items.add(new Item("1-4", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 3][GameFragment.x], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("1-4", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 1, GameFragment.position.y - 3))) {
            items.add(new Item("1-5", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 3][GameFragment.x + 1], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 1, GameFragment.position.y - 3))) {
            items.add(new Item("1-5", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 3][GameFragment.x + 1], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("1-5", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 2, GameFragment.position.y - 3))) {
            items.add(new Item("1-6", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 3][GameFragment.x + 2], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 2, GameFragment.position.y - 3))) {
            items.add(new Item("1-6", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 3][GameFragment.x + 2], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("1-6", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 3, GameFragment.position.y - 3))) {
            items.add(new Item("1-7", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 3][GameFragment.x + 3], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 3, GameFragment.position.y - 3))) {
            items.add(new Item("1-7", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 3][GameFragment.x + 3], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("1-7", R.drawable.blank));
        }

        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 3, GameFragment.position.y - 2))) {
            items.add(new Item("2-1", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x - 3], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 3, GameFragment.position.y - 2))) {
            items.add(new Item("2-1", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x - 3], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("2-1", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 2, GameFragment.position.y - 2))) {
            items.add(new Item("2-2", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x - 2], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 2, GameFragment.position.y - 2))) {
            items.add(new Item("2-2", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x - 2], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("2-2", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 1, GameFragment.position.y - 2))) {
            items.add(new Item("2-3", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x - 1], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 1, GameFragment.position.y - 2))) {
            items.add(new Item("2-3", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x - 1], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("2-3", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x, GameFragment.position.y - 2))) {
            items.add(new Item("2-4", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x, GameFragment.position.y - 2))) {
            items.add(new Item("2-4", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("2-4", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 1, GameFragment.position.y - 2))) {
            items.add(new Item("2-5", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x + 1], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 1, GameFragment.position.y - 2))) {
            items.add(new Item("2-5", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x + 1], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("2-5", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 2, GameFragment.position.y - 2))) {
            items.add(new Item("2-6", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x + 2], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 2, GameFragment.position.y - 2))) {
            items.add(new Item("2-6", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x + 2], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("2-6", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 3, GameFragment.position.y - 2))) {
            items.add(new Item("2-7", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x + 3], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 3, GameFragment.position.y - 2))) {
            items.add(new Item("2-7", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 2][GameFragment.x + 3], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("2-7", R.drawable.blank));
        }

        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 3, GameFragment.position.y - 1))) {
            items.add(new Item("3-1", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x - 3], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 3, GameFragment.position.y - 1))) {
            items.add(new Item("3-1", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x - 3], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("3-1", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 2, GameFragment.position.y - 1))) {
            items.add(new Item("3-2", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x - 2], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 2, GameFragment.position.y - 1))) {
            items.add(new Item("3-2", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x - 2], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("3-2", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 1, GameFragment.position.y - 1))) {
            items.add(new Item("3-3", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x - 1], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 1, GameFragment.position.y - 1))) {
            items.add(new Item("3-3", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x - 1], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("3-3", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x, GameFragment.position.y - 1))) {
            items.add(new Item("3-4", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x, GameFragment.position.y - 1))) {
            items.add(new Item("3-4", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("3-4", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 1, GameFragment.position.y - 1))) {
            items.add(new Item("3-5", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x + 1], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 1, GameFragment.position.y - 1))) {
            items.add(new Item("3-5", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x + 1], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("3-5", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 2, GameFragment.position.y - 1))) {
            items.add(new Item("3-6", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x + 2], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 2, GameFragment.position.y - 1))) {
            items.add(new Item("3-6", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x + 2], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("3-6", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 3, GameFragment.position.y - 1))) {
            items.add(new Item("3-7", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x + 3], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 3, GameFragment.position.y - 1))) {
            items.add(new Item("3-7", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y - 1][GameFragment.x + 3], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("3-7", R.drawable.blank));
        }

        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 3, GameFragment.position.y))) {
            items.add(new Item("4-1", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y][GameFragment.x - 3], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 3, GameFragment.position.y))) {
            items.add(new Item("4-1", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y][GameFragment.x - 3], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("4-1", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 2, GameFragment.position.y))) {
            items.add(new Item("4-2", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y][GameFragment.x - 2], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 2, GameFragment.position.y))) {
            items.add(new Item("4-2", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y][GameFragment.x - 2], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("4-2", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 1, GameFragment.position.y))) {
            items.add(new Item("4-3", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y][GameFragment.x - 1], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 1, GameFragment.position.y))) {
            items.add(new Item("4-3", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y][GameFragment.x - 1], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("4-3", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x, GameFragment.position.y))) {
            items.add(new Item("4-4", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y][GameFragment.x], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x, GameFragment.position.y))) {
            items.add(new Item("4-4", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y][GameFragment.x], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("4-4", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 1, GameFragment.position.y))) {
            items.add(new Item("4-5", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y][GameFragment.x + 1], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 1, GameFragment.position.y))) {
            items.add(new Item("4-5", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y][GameFragment.x + 1], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("4-5", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 2, GameFragment.position.y))) {
            items.add(new Item("4-6", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y][GameFragment.x + 2], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 2, GameFragment.position.y))) {
            items.add(new Item("4-6", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y][GameFragment.x + 2], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("4-6", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 3, GameFragment.position.y))) {
            items.add(new Item("4-7", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y][GameFragment.x + 3], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 3, GameFragment.position.y))) {
            items.add(new Item("4-7", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y][GameFragment.x + 3], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("4-7", R.drawable.blank));
        }

        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 3, GameFragment.position.y + 1))) {
            items.add(new Item("5-1", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x - 3], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 3, GameFragment.position.y + 1))) {
            items.add(new Item("5-1", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x - 3], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("5-1", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 2, GameFragment.position.y + 1))) {
            items.add(new Item("5-2", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x - 2], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 2, GameFragment.position.y + 1))) {
            items.add(new Item("5-2", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x - 2], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("5-2", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 1, GameFragment.position.y + 1))) {
            items.add(new Item("5-3", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x - 1], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 1, GameFragment.position.y + 1))) {
            items.add(new Item("5-3", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x - 1], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("5-3", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x, GameFragment.position.y + 1))) {
            items.add(new Item("5-4", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x, GameFragment.position.y + 1))) {
            items.add(new Item("5-4", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("5-4", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 1, GameFragment.position.y + 1))) {
            items.add(new Item("5-5", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x + 1], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 1, GameFragment.position.y + 1))) {
            items.add(new Item("5-5", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x + 1], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("5-5", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 2, GameFragment.position.y + 1))) {
            items.add(new Item("5-6", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x + 2], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 2, GameFragment.position.y + 1))) {
            items.add(new Item("5-6", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x + 2], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("5-6", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 3, GameFragment.position.y + 1))) {
            items.add(new Item("5-7", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x + 3], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 3, GameFragment.position.y + 1))) {
            items.add(new Item("5-7", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 1][GameFragment.x + 3], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("5-7", R.drawable.blank));
        }

        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 3, GameFragment.position.y + 2))) {
            items.add(new Item("6-1", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x - 3], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 3, GameFragment.position.y + 2))) {
            items.add(new Item("6-1", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x - 3], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("6-1", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 2, GameFragment.position.y + 2))) {
            items.add(new Item("6-2", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x - 2], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 2, GameFragment.position.y + 2))) {
            items.add(new Item("6-2", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x - 2], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("6-2", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 1, GameFragment.position.y + 2))) {
            items.add(new Item("6-3", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x - 1], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 1, GameFragment.position.y + 2))) {
            items.add(new Item("6-3", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x - 1], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("6-3", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x, GameFragment.position.y + 2))) {
            items.add(new Item("6-4", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x, GameFragment.position.y + 2))) {
            items.add(new Item("6-4", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("6-4", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 1, GameFragment.position.y + 2))) {
            items.add(new Item("6-5", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x + 1], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 1, GameFragment.position.y + 2))) {
            items.add(new Item("6-5", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x + 1], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("6-5", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 2, GameFragment.position.y + 2))) {
            items.add(new Item("6-6", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x + 2], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 2, GameFragment.position.y + 2))) {
            items.add(new Item("6-6", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x + 2], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("6-6", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 3, GameFragment.position.y + 2))) {
            items.add(new Item("6-7", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x + 3], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 3, GameFragment.position.y + 2))) {
            items.add(new Item("6-7", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 2][GameFragment.x + 3], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("6-7", R.drawable.blank));
        }

        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 3, GameFragment.position.y + 3))) {
            items.add(new Item("7-1", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 3][GameFragment.x - 3], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 3, GameFragment.position.y + 3))) {
            items.add(new Item("7-1", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 3][GameFragment.x - 3], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("7-1", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 2, GameFragment.position.y + 3))) {
            items.add(new Item("7-2", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 3][GameFragment.x - 2], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 2, GameFragment.position.y + 3))) {
            items.add(new Item("7-2", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 3][GameFragment.x - 2], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("7-2", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x - 1, GameFragment.position.y + 3))) {
            items.add(new Item("7-3", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 3][GameFragment.x - 1], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x - 1, GameFragment.position.y + 3))) {
            items.add(new Item("7-3", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 3][GameFragment.x - 1], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("7-3", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x, GameFragment.position.y + 3))) {
            items.add(new Item("7-4", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 3][GameFragment.x], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x, GameFragment.position.y + 3))) {
            items.add(new Item("7-4", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 3][GameFragment.x], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("7-4", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 1, GameFragment.position.y + 3))) {
            items.add(new Item("7-5", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 3][GameFragment.x + 1], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 1, GameFragment.position.y + 3))) {
            items.add(new Item("7-5", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 3][GameFragment.x + 1], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("7-5", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 2, GameFragment.position.y + 3))) {
            items.add(new Item("7-6", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 3][GameFragment.x + 2], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 2, GameFragment.position.y + 3))) {
            items.add(new Item("7-6", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 3][GameFragment.x + 2], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("7-6", R.drawable.blank));
        }
        if (GameFragment.stepsMade.contains(new Point(GameFragment.position.x + 3, GameFragment.position.y + 3))) {
            items.add(new Item("7-7", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 3][GameFragment.x + 3], "drawable", context.getPackageName())));
        } else if (GameFragment.discovered.contains(new Point(GameFragment.position.x + 3, GameFragment.position.y + 3))) {
            items.add(new Item("7-7", context.getResources().getIdentifier("m" + GameFragment.currentLevel[GameFragment.y + 3][GameFragment.x + 3], "drawable", context.getPackageName())));
        } else {
            items.add(new Item("7-7", R.drawable.blank));
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
