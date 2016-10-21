package com.ollum.mazecape.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.ollum.mazecape.Arrays.Worlds;
import com.ollum.mazecape.R;
import com.ollum.mazecape.util.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class WorldSelectFragment extends Fragment {

    GridView gridViewWorlds;
    int collectedStars;
    int counter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_world_select, container, false);

        MainActivity.title.setText(R.string.world_select);

        gridViewWorlds = (GridView) view.findViewById(R.id.gridViewWorlds);
        gridViewWorlds.setAdapter(new WorldsAdapter(getContext(), 0));
        gridViewWorlds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
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
                    builder.setMessage(R.string.buy_world_50);
                    builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (MainActivity.allStars >= 50) {
                                MainActivity.allStars -= 50;
                                MainActivity.starsCounter.setText("" + MainActivity.allStars);
                                MainActivity.maxWorld++;
                                MainActivity.soundPool.play(MainActivity.upgradeID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                gridViewWorlds.setAdapter(new WorldsAdapter(getContext(), 0));
                                gridViewWorlds.setSelection(MainActivity.maxWorld);
                                SharedPreferences.saveGame(getContext());
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
                    builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create();
                    builder.show();
                } else if (position - 2 == MainActivity.maxWorld) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(R.string.buy_world_100);
                    builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (MainActivity.allStars >= 100) {
                                MainActivity.allStars -= 100;
                                MainActivity.starsCounter.setText("" + MainActivity.allStars);
                                MainActivity.maxWorld += 2;
                                MainActivity.soundPool.play(MainActivity.upgradeID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                gridViewWorlds.setAdapter(new WorldsAdapter(getContext(), 0));
                                gridViewWorlds.setSelection(MainActivity.maxWorld);
                                SharedPreferences.saveGame(getContext());
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
                    builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create();
                    builder.show();
                } else if (position - 3 == MainActivity.maxWorld) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(R.string.buy_world_150);
                    builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (MainActivity.allStars >= 150) {
                                MainActivity.allStars -= 150;
                                MainActivity.starsCounter.setText("" + MainActivity.allStars);
                                MainActivity.maxWorld += 3;
                                MainActivity.soundPool.play(MainActivity.upgradeID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                gridViewWorlds.setAdapter(new WorldsAdapter(getContext(), 0));
                                gridViewWorlds.setSelection(MainActivity.maxWorld);
                                SharedPreferences.saveGame(getContext());
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
                    builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create();
                    builder.show();
                } else if (position - 4 == MainActivity.maxWorld) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(R.string.buy_world_200);
                    builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (MainActivity.allStars >= 200) {
                                MainActivity.allStars -= 200;
                                MainActivity.starsCounter.setText("" + MainActivity.allStars);
                                MainActivity.maxWorld += 4;
                                MainActivity.soundPool.play(MainActivity.upgradeID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                gridViewWorlds.setAdapter(new WorldsAdapter(getContext(), 0));
                                gridViewWorlds.setSelection(MainActivity.maxWorld);
                                SharedPreferences.saveGame(getContext());
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
                    builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
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

        for (int i = 0; i < MainActivity.worldStars.length; i++) {
            collectedStars += MainActivity.worldStars[i];
        }

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
                    //items.add(new Item("4", getResources().getIdentifier("m" + "blk", "drawable", getContext().getPackageName()), 5));
                    break;
                case 1:
                    items.add(new Item("0", getResources().getIdentifier("f" + "xxx", "drawable", getContext().getPackageName()), 1));
                    items.add(new Item("1", getResources().getIdentifier("c" + "xxx", "drawable", getContext().getPackageName()), 2));
                    items.add(new Item("2", getResources().getIdentifier("s" + "blk", "drawable", getContext().getPackageName()), 3));
                    items.add(new Item("3", getResources().getIdentifier("d" + "blk", "drawable", getContext().getPackageName()), 4));
                    //items.add(new Item("4", getResources().getIdentifier("m" + "blk", "drawable", getContext().getPackageName()), 5));
                    break;
                case 2:
                    items.add(new Item("0", getResources().getIdentifier("f" + "xxx", "drawable", getContext().getPackageName()), 1));
                    items.add(new Item("1", getResources().getIdentifier("c" + "xxx", "drawable", getContext().getPackageName()), 2));
                    items.add(new Item("2", getResources().getIdentifier("s" + "xxx", "drawable", getContext().getPackageName()), 3));
                    items.add(new Item("3", getResources().getIdentifier("d" + "blk", "drawable", getContext().getPackageName()), 4));
                    //items.add(new Item("4", getResources().getIdentifier("m" + "blk", "drawable", getContext().getPackageName()), 5));
                    break;
                case 3:
                    items.add(new Item("0", getResources().getIdentifier("f" + "xxx", "drawable", getContext().getPackageName()), 1));
                    items.add(new Item("1", getResources().getIdentifier("c" + "xxx", "drawable", getContext().getPackageName()), 2));
                    items.add(new Item("2", getResources().getIdentifier("s" + "xxx", "drawable", getContext().getPackageName()), 3));
                    items.add(new Item("3", getResources().getIdentifier("d" + "xxx", "drawable", getContext().getPackageName()), 4));
                    //items.add(new Item("4", getResources().getIdentifier("m" + "blk", "drawable", getContext().getPackageName()), 5));
                    break;
                /*case 4:
                    items.add(new Item("0", getResources().getIdentifier("f" + "xxx", "drawable", getContext().getPackageName()), 1));
                    items.add(new Item("1", getResources().getIdentifier("c" + "xxx", "drawable", getContext().getPackageName()), 2));
                    items.add(new Item("2", getResources().getIdentifier("s" + "xxx", "drawable", getContext().getPackageName()), 3));
                    items.add(new Item("3", getResources().getIdentifier("d" + "xxx", "drawable", getContext().getPackageName()), 4));
                    items.add(new Item("4", getResources().getIdentifier("m" + "xxx", "drawable", getContext().getPackageName()), 5));
                    break;*/
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
            ImageView picture, unlockStar;
            TextView world, score, unlock;

            if (v == null) {
                v = inflater.inflate(R.layout.gridview_world_select, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                v.setTag(R.id.text, v.findViewById(R.id.text));
                v.setTag(R.id.score, v.findViewById(R.id.score));
                v.setTag(R.id.world, v.findViewById(R.id.world));
                v.setTag(R.id.unlock, v.findViewById(R.id.unlock));
                v.setTag(R.id.unlock_star, v.findViewById(R.id.unlock_star));
            }

            picture = (ImageView) v.getTag(R.id.picture);
            score = (TextView) v.getTag(R.id.score);
            world = (TextView) v.getTag(R.id.world);
            unlock = (TextView) v.getTag(R.id.unlock);
            unlockStar = (ImageView) v.getTag(R.id.unlock_star);

            Item item = (Item) getItem(position);

            picture.setImageResource(item.drawableId);

            world.setText("" + (position + 1));

            if (collectedStars >= position * 50 || MainActivity.maxWorld >= position) {
                unlockStar.setVisibility(View.INVISIBLE);
                unlock.setVisibility(View.INVISIBLE);
            } else {
                unlockStar.setVisibility(View.VISIBLE);
                unlock.setVisibility(View.VISIBLE);
                unlock.setText("" + collectedStars + " / " + (50 * position));
            }

            score.setText("" + MainActivity.worldStars[position] + " / " + Worlds.WORLDS[position].length * 5);

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
