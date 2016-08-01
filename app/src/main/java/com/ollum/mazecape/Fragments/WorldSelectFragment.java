package com.ollum.mazecape.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
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

public class WorldSelectFragment extends Fragment {

    GridView gridViewWorlds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_world_select, container, false);

        MainActivity.title.setText("World Select");

        gridViewWorlds = (GridView) view.findViewById(R.id.gridViewWorlds);
        gridViewWorlds.setAdapter(new WorldsAdapter(getContext(), 0));
        gridViewWorlds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (MainMenuFragment.devMode) {
                    MainActivity.world = position;
                    LevelSelectFragment levelSelectFragment = new LevelSelectFragment();
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out);
                    transaction.replace(R.id.content, levelSelectFragment, "LevelSelectFragment");
                    transaction.addToBackStack("LevelSelectFragment");
                    transaction.commit();
                } else if (position <= MainActivity.maxWorld) {
                    MainActivity.world = position;
                    LevelSelectFragment levelSelectFragment = new LevelSelectFragment();
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out);
                    transaction.replace(R.id.content, levelSelectFragment, "LevelSelectFragment");
                    transaction.addToBackStack("LevelSelectFragment");
                    transaction.commit();
                } else if (position - 1 == MainActivity.maxWorld) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Would you like to buy this World? 100 Stars");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (MainActivity.allStars >= 100) {
                                MainActivity.allStars -= 100;
                                MainActivity.maxWorld++;
                                MainActivity.maxLevel = 0;
                                dialog.dismiss();
                            } else {
                                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                                transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                                transaction.add(R.id.content, MainActivity.shopFragment, "ShopFragment");
                                transaction.addToBackStack("ShopFragment");
                                transaction.commit();
                                MainActivity.stopTime = true;
                                MainActivity.shopVisible = true;
                            }
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create();
                    builder.show();
                } else if (position - 2 == MainActivity.maxWorld) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Would you like to buy this World? 200 Stars");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (MainActivity.allStars >= 200) {
                                MainActivity.allStars -= 200;
                                MainActivity.maxWorld++;
                                MainActivity.maxLevel = 0;
                                dialog.dismiss();
                            } else {
                                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                                transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                                transaction.add(R.id.content, MainActivity.shopFragment, "ShopFragment");
                                transaction.addToBackStack("ShopFragment");
                                transaction.commit();
                                MainActivity.stopTime = true;
                                MainActivity.shopVisible = true;
                            }
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create();
                    builder.show();
                } else if (position - 3 == MainActivity.maxWorld) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Would you like to buy this World? 300 Stars");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (MainActivity.allStars >= 300) {
                                MainActivity.allStars -= 300;
                                MainActivity.maxWorld++;
                                MainActivity.maxLevel = 0;
                                dialog.dismiss();
                            } else {
                                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                                transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                                transaction.add(R.id.content, MainActivity.shopFragment, "ShopFragment");
                                transaction.addToBackStack("ShopFragment");
                                transaction.commit();
                                MainActivity.stopTime = true;
                                MainActivity.shopVisible = true;
                            }
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create();
                    builder.show();
                }
            }
        });

        gridViewWorlds.setSelection(MainActivity.maxWorld);

        return view;
    }

    private class WorldsAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public WorldsAdapter(Context context, int center) {
            inflater = LayoutInflater.from(context);

            switch (MainActivity.maxWorld) {
                case 0:
                    items.add(new Item("0", getResources().getIdentifier("f" + "xxx", "drawable", getContext().getPackageName()), 1));
                    items.add(new Item("1", getResources().getIdentifier("c" + "blk", "drawable", getContext().getPackageName()), 2));
                    items.add(new Item("2", getResources().getIdentifier("s" + "blk", "drawable", getContext().getPackageName()), 3));
                    items.add(new Item("3", getResources().getIdentifier("d" + "blk", "drawable", getContext().getPackageName()), 4));
                    break;
                case 1:
                    items.add(new Item("0", getResources().getIdentifier("f" + "xxx", "drawable", getContext().getPackageName()), 1));
                    items.add(new Item("1", getResources().getIdentifier("c" + "xxx", "drawable", getContext().getPackageName()), 2));
                    items.add(new Item("2", getResources().getIdentifier("s" + "blk", "drawable", getContext().getPackageName()), 3));
                    items.add(new Item("3", getResources().getIdentifier("d" + "blk", "drawable", getContext().getPackageName()), 4));
                    break;
                case 2:
                    items.add(new Item("0", getResources().getIdentifier("f" + "xxx", "drawable", getContext().getPackageName()), 1));
                    items.add(new Item("1", getResources().getIdentifier("c" + "xxx", "drawable", getContext().getPackageName()), 2));
                    items.add(new Item("2", getResources().getIdentifier("s" + "xxx", "drawable", getContext().getPackageName()), 3));
                    items.add(new Item("3", getResources().getIdentifier("d" + "blk", "drawable", getContext().getPackageName()), 4));
                    break;
                case 3:
                    items.add(new Item("0", getResources().getIdentifier("f" + "xxx", "drawable", getContext().getPackageName()), 1));
                    items.add(new Item("1", getResources().getIdentifier("c" + "xxx", "drawable", getContext().getPackageName()), 2));
                    items.add(new Item("2", getResources().getIdentifier("s" + "xxx", "drawable", getContext().getPackageName()), 3));
                    items.add(new Item("3", getResources().getIdentifier("d" + "xxx", "drawable", getContext().getPackageName()), 4));
                    break;
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
            ImageView picture;
            TextView world, score;

            if (v == null) {
                v = inflater.inflate(R.layout.gridview_world_select, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                v.setTag(R.id.text, v.findViewById(R.id.text));
                v.setTag(R.id.score, v.findViewById(R.id.score));
                v.setTag(R.id.level, v.findViewById(R.id.level));
            }

            picture = (ImageView) v.getTag(R.id.picture);
            score = (TextView) v.getTag(R.id.score);
            world = (TextView) v.getTag(R.id.level);

            Item item = (Item) getItem(position);

            picture.setImageResource(item.drawableId);

            world.setText("" + (position + 1));
            world.setTextColor(Color.BLACK);

            score.setText("" + MainActivity.worldStars[position] + " / " + LevelArrays.WORLDS[position].length * 5);

            return v;
        }

        private class Item {
            final String name;
            final int drawableId, world;

            Item(String name, int drawableId, int world) {
                this.name = name;
                this.drawableId = drawableId;
                this.world = world;
            }
        }
    }

}