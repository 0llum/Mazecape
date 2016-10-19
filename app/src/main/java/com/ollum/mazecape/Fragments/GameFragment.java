package com.ollum.mazecape.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.Adapters.LevelAdapter;
import com.ollum.mazecape.Adapters.MapAdapter;
import com.ollum.mazecape.Arrays.Darkness;
import com.ollum.mazecape.Arrays.Movement;
import com.ollum.mazecape.Arrays.Tiles;
import com.ollum.mazecape.Arrays.Worlds;
import com.ollum.mazecape.Classes.OnSwipeTouchListener;
import com.ollum.mazecape.R;
import com.ollum.mazecape.util.SaveGame;

import java.util.ArrayList;
import java.util.List;

public class GameFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    public static TextView posX, posY;
    public static MediaPlayer bgm, fire, heartbeat;
    public static String[][] currentLevel;
    public static int x;
    public static int y;
    public static String scene;
    public static int steps = 0;
    public static Point position;
    public static ArrayList<Point> stepsMade;
    public static ArrayList<Point> discovered;
    public static List<Integer> items;
    public static float volumeBGM = MainActivity.volumeMusic;
    public static float volumeFire = 0;
    public static float volumeHeart = 0;
    RelativeLayout relativeLayout_Container, relativeLayout_UI, relativeLayoutMap, relativeLayoutHeader;
    LinearLayout navigation;
    GridView gridViewLevel, gridViewMap;
    ImageView imageViewPlayer, imageViewDarkness, imageViewSandstorm, compass, needleGoal, needleStar1, needleStar2, needleStar3, mapPosition, imageStar1, imageStar2, imageStar3;
    Button resetLevel, cheatButton;
    int stars = 0;
    int darkness = 0;
    int time = 0;
    int cheat = 0;
    int score = 0;
    boolean isAnimating = false;
    boolean allowInput = true;
    boolean hasSword = false;
    boolean hasDialog = false;
    ImageView imgSword;
    String direction = "up";
    InterstitialAd mInterstitialAd;
    boolean containsTrap = false;
    boolean trapVisible = false;
    boolean trapActive = false;
    boolean mapRevealed = false;
    int movementSpeed;
    int minSteps;
    LevelAdapter levelAdapter;
    float angleGoalOld;
    float angleGoalNew;
    int gridViewColumns = 3;
    int randStep = 0;
    int step = 0;
    private Handler handler;

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        MainActivity.timeLayout.setVisibility(View.VISIBLE);
        MainActivity.stepsLayout.setVisibility(View.VISIBLE);

        stepsMade = new ArrayList<>();
        discovered = new ArrayList<>();
        items = new ArrayList<Integer>();

        currentLevel = copyLevel(com.ollum.mazecape.Arrays.Worlds.WORLDS[MainActivity.world][MainActivity.level]);
        x = Integer.parseInt(currentLevel[currentLevel.length - 3][0]);
        y = Integer.parseInt(currentLevel[currentLevel.length - 3][1]);
        scene = currentLevel[currentLevel.length - 3][2];

        if (!(currentLevel[currentLevel.length - 1].length == 0)) {
            containsTrap = true;
        } else {
            containsTrap = false;
        }

        position = new Point(x, y);
        discovered.add(new Point(x - 1, y - 1));
        discovered.add(new Point(x, y - 1));
        discovered.add(new Point(x + 1, y - 1));
        discovered.add(new Point(x - 1, y));
        discovered.add(position);
        discovered.add(new Point(x + 1, y));
        discovered.add(new Point(x - 1, y + 1));
        discovered.add(new Point(x, y + 1));
        discovered.add(new Point(x + 1, y + 1));

        stepsMade.add(position);

        relativeLayout_Container = (RelativeLayout) view.findViewById(R.id.relativeLayout_Container);

        relativeLayout_UI = (RelativeLayout) view.findViewById(R.id.relativeLayout_UI);
        relativeLayout_UI.getLayoutParams().width = MainActivity.width;
        relativeLayout_UI.getLayoutParams().height = MainActivity.content.getHeight();

        relativeLayoutMap = (RelativeLayout) view.findViewById(R.id.relative_layout_map);

        relativeLayoutHeader = (RelativeLayout) view.findViewById(R.id.header_black);

        navigation = (LinearLayout) view.findViewById(R.id.navigation);
        navigation.getLayoutParams().height = (int) (MainActivity.height / 3.55555555);

        compass = (ImageView) view.findViewById(R.id.compass);

        needleGoal = (ImageView) view.findViewById(R.id.needleGoal);
        needleStar1 = (ImageView) view.findViewById(R.id.needleStar1);
        needleStar2 = (ImageView) view.findViewById(R.id.needleStar2);
        needleStar3 = (ImageView) view.findViewById(R.id.needleStar3);

        imageViewPlayer = (ImageView) view.findViewById(R.id.imageViewPlayer);

        imageViewDarkness = (ImageView) view.findViewById(R.id.imageViewDarkness);

        imageViewSandstorm = (ImageView) view.findViewById(R.id.imageViewSandstorm);

        gridViewMap = (GridView) view.findViewById(R.id.gridViewMap);
        gridViewMap.setOnItemClickListener(this);

        mapPosition = (ImageView) view.findViewById(R.id.minimapPosition);

        gridViewLevel = (GridView) view.findViewById(R.id.gridViewLevel);

        /*gridViewLevel.setOnTouchListener(new View.OnTouchListener() {
            float dX, dY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        dX = (v.getX() - event.getRawX());
                        dY = (v.getY() - event.getRawY());

                        break;

                    case MotionEvent.ACTION_MOVE:

                        *//*v.animate()
                                .x((event.getRawX() + dX))
                                .y((event.getRawY() + dY))
                                .setDuration(0)
                                .start();*//*
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });*/

        imageStar1 = (ImageView) view.findViewById(R.id.imageStar1);
        imageStar2 = (ImageView) view.findViewById(R.id.imageStar2);
        imageStar3 = (ImageView) view.findViewById(R.id.imageStar3);

        posX = (TextView) view.findViewById(R.id.posX);
        posY = (TextView) view.findViewById(R.id.posY);

        resetLevel = (Button) view.findViewById(R.id.resetLevel);
        resetLevel.setOnClickListener(this);

        cheatButton = (Button) view.findViewById(R.id.cheatButton);
        cheatButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        cheat++;

                        gridViewLevel.getLayoutParams().width = MainActivity.width;
                        gridViewLevel.getLayoutParams().height = MainActivity.width;
                        setMargins(gridViewLevel, 0, 0, 0, MainActivity.width / gridViewColumns);
                        gridViewLevel.setAdapter(new LevelAdapter(getContext(), 0));

                        discovered.add(new Point(x - 2, y - 2));
                        discovered.add(new Point(x - 1, y - 2));
                        discovered.add(new Point(x, y - 2));
                        discovered.add(new Point(x + 1, y - 2));
                        discovered.add(new Point(x + 2, y - 2));
                        discovered.add(new Point(x - 2, y - 1));
                        discovered.add(new Point(x + 2, y - 1));
                        discovered.add(new Point(x - 2, y));
                        discovered.add(new Point(x + 2, y));
                        discovered.add(new Point(x - 2, y + 1));
                        discovered.add(new Point(x + 2, y + 1));
                        discovered.add(new Point(x - 2, y + 2));
                        discovered.add(new Point(x - 1, y + 2));
                        discovered.add(new Point(x, y + 2));
                        discovered.add(new Point(x + 1, y + 2));
                        discovered.add(new Point(x + 2, y + 2));

                        gridViewMap.setAdapter(new MapAdapter(getContext(), 0));

                        break;
                    case MotionEvent.ACTION_UP:
                        gridViewLevel.getLayoutParams().width = MainActivity.width * 5 / gridViewColumns;
                        gridViewLevel.getLayoutParams().height = MainActivity.width * 5 / gridViewColumns;
                        setMargins(gridViewLevel, -MainActivity.width / gridViewColumns, 0, 0, 0);
                        gridViewLevel.setAdapter(new LevelAdapter(getContext(), 0));
                        break;
                }
                return true;
            }
        });

        imgSword = (ImageView) view.findViewById(R.id.imgSword);

        relativeLayout_UI.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            public void onSwipeTop() {
                if (allowInput && contains(Movement.MOVE_DOWN, currentLevel[y][x])) {
                    steps++;
                    moveDown();
                    MainActivity.soundPool.play(MainActivity.stepID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                }
            }

            public void onSwipeRight() {
                if (allowInput && contains(Movement.MOVE_LEFT, currentLevel[y][x])) {
                    steps++;
                    moveLeft();
                    MainActivity.soundPool.play(MainActivity.stepID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                }
            }

            public void onSwipeLeft() {
                if (allowInput && contains(Movement.MOVE_RIGHT, currentLevel[y][x])) {
                    steps++;
                    moveRight();
                    MainActivity.soundPool.play(MainActivity.stepID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                }
            }

            public void onSwipeBottom() {
                if (allowInput && contains(Movement.MOVE_UP, currentLevel[y][x])) {
                    steps++;
                    moveUp();
                    MainActivity.soundPool.play(MainActivity.stepID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                }
            }
        });

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-7666608930334273/3015809346");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                reset();

                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                MainActivity.stopTime = true;
                hasDialog = true;
                builder.setTitle(R.string.ads);
                builder.setMessage(R.string.hate_ads);
                builder.setPositiveButton(R.string.open_shop, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);

                        MainActivity.soundPool.play(MainActivity.swoosh1ID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                        FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                        transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                        transaction.add(R.id.content, MainActivity.shopFragment, "ShopFragment");
                        transaction.addToBackStack("ShopFragment");
                        transaction.commit();
                        MainActivity.shopVisible = true;

                        hasDialog = false;
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton(R.string.like_ads, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                        MainActivity.stopTime = false;
                        hasDialog = false;
                        dialog.dismiss();
                    }
                });
                builder.setCancelable(false);
                builder.create();
                builder.show();
            }
        });

        requestNewInterstitial();

        reset();

        if (MainMenuFragment.devMode) {
            LevelFragment levelFragment = new LevelFragment();
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.right_in, R.anim.left_out);
            transaction.replace(R.id.content, levelFragment, "LevelFragment");
            transaction.addToBackStack("LevelFragment");
            transaction.commit();
        }

        return view;
    }

    private void requestNewInterstitial() {
        //AdRequest adRequest = new AdRequest.Builder().build();
        if (MainActivity.showAds) {
            mInterstitialAd.loadAd(MainActivity.adRequest);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.timeLayout.setVisibility(View.VISIBLE);
        MainActivity.stepsLayout.setVisibility(View.VISIBLE);

        if (bgm != null) {
            bgm.start();
        }

        if (fire != null) {
            fire.start();
        }

        if (heartbeat != null) {
            heartbeat.start();
        }


        if (!(hasDialog || MainActivity.shopVisible || MainActivity.settingsVisible || MainActivity.helpVisible)) {
            MainActivity.stopTime = false;
        }

        createHandler();
    }

    @Override
    public void onPause() {
        super.onPause();
        MainActivity.timeLayout.setVisibility(View.INVISIBLE);
        MainActivity.stepsLayout.setVisibility(View.INVISIBLE);

        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }

        if (bgm != null && bgm.isPlaying()) {
            bgm.pause();
            bgm.seekTo(0);
        }

        if (fire != null && fire.isPlaying()) {
            fire.pause();
            fire.seekTo(0);
        }

        if (heartbeat != null && heartbeat.isPlaying()) {
            heartbeat.pause();
            heartbeat.seekTo(0);
        }

        MainActivity.stopTime = true;
    }

    public void reset() {
        MainActivity.livesCounter.setText("" + (MainActivity.lives));
        MainActivity.starsCounter.setText("" + MainActivity.allStars);

        if (MainActivity.lives > 0) {
            MainActivity.lives--;
        } else {
            MainMenuFragment mainMenuFragment = new MainMenuFragment();
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
            transaction.replace(R.id.content, mainMenuFragment, "MainMenuFragment");
            transaction.addToBackStack("MainMenuFragment");
            transaction.commit();
            if (MainActivity.menuBGM != null) {
                MainActivity.menuBGM.start();
            }
        }

        switch (MainActivity.levelSpeed) {
            case 0:
                movementSpeed = 300;
                break;
            case 1:
                movementSpeed = 250;
                break;
            case 2:
                movementSpeed = 200;
                break;
            case 3:
                movementSpeed = 150;
                break;
        }

        currentLevel = copyLevel(Worlds.WORLDS[MainActivity.world][MainActivity.level]);

        scene = currentLevel[currentLevel.length - 3][2];
        minSteps = Integer.parseInt(currentLevel[currentLevel.length - 3][3]);
        x = Integer.parseInt(currentLevel[currentLevel.length - 3][0]);
        y = Integer.parseInt(currentLevel[currentLevel.length - 3][1]);

        if (scene.equals("m")) {
            movementSpeed *= 2;
        }

        if (!(currentLevel[currentLevel.length - 1].length == 0)) {
            containsTrap = true;
        } else {
            containsTrap = false;
        }

        if (!MainMenuFragment.devMode) {
            int randRotation = 0 + (int) (Math.random() * ((3 - 0) + 1));
            for (int i = 0; i < randRotation; i++) {
                currentLevel = rotateLevel(currentLevel);
            }
        }

        items.clear();

        for (int i = 2; i < GameFragment.currentLevel.length - 5; i++) {
            for (int k = 2; k < GameFragment.currentLevel[i].length - 2; k++) {
                items.add(getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[i][k], "drawable", getContext().getPackageName()));
            }
        }

        angleGoalOld = 0;
        angleGoalNew = 0;

        allowInput = true;
        hasDialog = false;
        discovered.clear();
        stepsMade.clear();
        stars = 0;
        darkness = 0;
        steps = 0;
        time = 0;
        cheat = 0;
        randStep = 5 + (int) (Math.random() * ((15 - 5) + 1));
        direction = "up";
        MainActivity.stopTime = false;
        volumeFire = 0;
        volumeHeart = 0;
        position = new Point(x, y);
        discovered.add(new Point(x - 1, y - 1));
        discovered.add(new Point(x, y - 1));
        discovered.add(new Point(x + 1, y - 1));
        discovered.add(new Point(x - 1, y));
        discovered.add(position);
        discovered.add(new Point(x + 1, y));
        discovered.add(new Point(x - 1, y + 1));
        discovered.add(new Point(x, y + 1));
        discovered.add(new Point(x + 1, y + 1));
        stepsMade.add(position);

        relativeLayout_Container.getLayoutParams().width = MainActivity.width * (currentLevel[0].length - 4) / gridViewColumns;
        relativeLayout_Container.getLayoutParams().height = MainActivity.height - MainActivity.height / 8 + MainActivity.width * (currentLevel.length - 7) / gridViewColumns;

        gridViewLevel.getLayoutParams().width = MainActivity.width * (currentLevel[0].length - 4) / gridViewColumns;
        gridViewLevel.getLayoutParams().height = MainActivity.width * (currentLevel.length - 7) / gridViewColumns;
        gridViewLevel.setNumColumns(currentLevel[0].length - 4);

        switch (gridViewColumns) {
            case 1:
                gridViewLevel.setTranslationY(-MainActivity.width / gridViewColumns * (y - 1));
                gridViewLevel.setTranslationX(-MainActivity.width / gridViewColumns * (x - 2));

                if (!(scene.equals("s") || scene.equals("m"))) {
                    imageViewPlayer.setImageResource(getResources().getIdentifier("p" + scene + direction + "_small", "drawable", getContext().getPackageName()));
                } else {
                    imageViewPlayer.setImageResource(getResources().getIdentifier("p" + "f" + direction + "_small", "drawable", getContext().getPackageName()));
                }
                break;
            case 3:
                gridViewLevel.setTranslationY(-MainActivity.width / gridViewColumns * y);
                gridViewLevel.setTranslationX(-MainActivity.width / gridViewColumns * (x - 3));

                if (!(scene.equals("s") || scene.equals("m"))) {
                    imageViewPlayer.setImageResource(getResources().getIdentifier("p" + scene + direction + "_small", "drawable", getContext().getPackageName()));
                } else {
                    imageViewPlayer.setImageResource(getResources().getIdentifier("p" + "f" + direction + "_small", "drawable", getContext().getPackageName()));
                }
                break;
            case 5:
                gridViewLevel.setTranslationY(-MainActivity.width / gridViewColumns * (y + 1));
                gridViewLevel.setTranslationX(-MainActivity.width / gridViewColumns * (x - 4));

                if (!(scene.equals("s") || scene.equals("m"))) {
                    imageViewPlayer.setImageResource(getResources().getIdentifier("p" + scene + direction + "_big", "drawable", getContext().getPackageName()));
                } else {
                    imageViewPlayer.setImageResource(getResources().getIdentifier("p" + "f" + direction + "_big", "drawable", getContext().getPackageName()));
                }
                break;
        }

        imageViewPlayer.setVisibility(View.VISIBLE);

        levelAdapter = new LevelAdapter(getContext(), 0);

        gridViewLevel.setAdapter(levelAdapter);

        gridViewMap.setAdapter(new MapAdapter(getContext(), 0));

        imageViewDarkness.setImageResource(Darkness.DARKNESS_SMALL[darkness]);
        imageViewSandstorm.setVisibility(View.INVISIBLE);


        if (MainActivity.levelCompass > 0) {
            needleGoal.setVisibility(View.VISIBLE);
            if (MainActivity.levelCompass > 1) {
                needleStar1.setVisibility(View.VISIBLE);
                needleStar2.setVisibility(View.VISIBLE);
                needleStar3.setVisibility(View.VISIBLE);
            } else {
                needleStar1.setVisibility(View.INVISIBLE);
                needleStar2.setVisibility(View.INVISIBLE);
                needleStar3.setVisibility(View.INVISIBLE);
            }
        } else {
            needleGoal.setVisibility(View.INVISIBLE);
            needleStar1.setVisibility(View.INVISIBLE);
            needleStar2.setVisibility(View.INVISIBLE);
            needleStar3.setVisibility(View.INVISIBLE);
        }

        if (MainActivity.levelMap > 0) {
            mapPosition.setVisibility(View.VISIBLE);
        } else {
            mapPosition.setVisibility(View.INVISIBLE);
        }

        setNeedle();
        MainActivity.title.setText(getString(R.string.level) + " " + (MainActivity.world + 1) + " - " + (MainActivity.level + 1));
        MainActivity.stepsCounter.setText("" + steps);
        MainActivity.timeCounter.setText("" + time);
        posX.setText("posX: " + x);
        posY.setText("posY: " + y);
        hasSword = false;
        mapRevealed = false;
        imgSword.setVisibility(View.INVISIBLE);

        if (bgm != null) {
            bgm.reset();
            bgm.release();
        }

        switch (scene) {
            case "f":
                bgm = MediaPlayer.create(getContext(), R.raw.forest);
                break;
            case "c":
                bgm = MediaPlayer.create(getContext(), R.raw.cave);
                break;
            case "s":
                bgm = MediaPlayer.create(getContext(), R.raw.snow);
                break;
            case "d":
                bgm = MediaPlayer.create(getContext(), R.raw.desert);
                break;
            case "m":
                bgm = MediaPlayer.create(getContext(), R.raw.moon);
                break;
        }

        if (bgm != null) {
            bgm.setLooping(true);
            bgm.setVolume(MainActivity.volumeMusic, MainActivity.volumeMusic);
            bgm.start();
        }

        if (fire != null) {
            fire.reset();
            fire.release();
        }

        fire = MediaPlayer.create(getContext(), R.raw.campfire);
        fire.setLooping(true);
        fire.setVolume(volumeFire, volumeFire);
        fire.start();

        if (heartbeat != null) {
            heartbeat.reset();
            heartbeat.release();
        }

        heartbeat = MediaPlayer.create(getContext(), R.raw.heartbeat_breathing);
        heartbeat.setLooping(true);
        heartbeat.setVolume(volumeHeart, volumeHeart);
        heartbeat.start();

        imageStar1.setImageResource(R.drawable.star_empty);
        imageStar2.setImageResource(R.drawable.star_empty);
        imageStar3.setImageResource(R.drawable.star_empty);

        SaveGame.saveGame(getContext());
        checkDialog();

        Log.d("debug", "heigth with statusbar: " + MainActivity.size.y);
        Log.d("debug", "height without statusbar: " + MainActivity.height);
        Log.d("debug", "rel. Layout Container height: " + relativeLayout_Container.getHeight());
        Log.d("debug", "UI Container height: " + relativeLayout_UI.getHeight());
        Log.d("debug", "content height: " + MainActivity.content.getHeight());
    }

    public void move() {
        allowInput = false;

        switch (gridViewColumns) {
            case 1:
                if (!(scene.equals("s") || scene.equals("m"))) {
                    imageViewPlayer.setImageResource(getResources().getIdentifier("p" + scene + direction + "_small", "drawable", getContext().getPackageName()));
                } else {
                    imageViewPlayer.setImageResource(getResources().getIdentifier("p" + "f" + direction + "_small", "drawable", getContext().getPackageName()));
                }
                break;
            case 3:
                if (!(scene.equals("s") || scene.equals("m"))) {
                    imageViewPlayer.setImageResource(getResources().getIdentifier("p" + scene + direction + "_small", "drawable", getContext().getPackageName()));
                } else {
                    imageViewPlayer.setImageResource(getResources().getIdentifier("p" + "f" + direction + "_small", "drawable", getContext().getPackageName()));
                }
                break;
            case 5:
                if (!(scene.equals("s") || scene.equals("m"))) {
                    imageViewPlayer.setImageResource(getResources().getIdentifier("p" + scene + direction + "_big", "drawable", getContext().getPackageName()));
                } else {
                    imageViewPlayer.setImageResource(getResources().getIdentifier("p" + "f" + direction + "_big", "drawable", getContext().getPackageName()));
                }
                break;
        }

        position = new Point(x, y);

        /*if (!mapRevealed) {
            discovered.clear();
        }*/
        discovered.add(new Point(x - 1, y - 1));
        discovered.add(new Point(x, y - 1));
        discovered.add(new Point(x + 1, y - 1));
        discovered.add(new Point(x - 1, y));
        discovered.add(position);
        discovered.add(new Point(x + 1, y));
        discovered.add(new Point(x - 1, y + 1));
        discovered.add(new Point(x, y + 1));
        discovered.add(new Point(x + 1, y + 1));
        stepsMade.add(position);
        MainActivity.stepsCounter.setText("" + steps);
        posX.setText("posX: " + x);
        posY.setText("posY: " + y);

        if (scene.equals("c") && darkness < 15 && steps % ((MainActivity.levelTorch + 2) * 2) == 0) {
            darkness++;
            imageViewDarkness.setImageResource(Darkness.DARKNESS_SMALL[darkness]);
        }

        if (containsTrap) {
            for (int i = 0; i < currentLevel[currentLevel.length - 1].length; i++) {
                String str = currentLevel[currentLevel.length - 1][i];
                String[] result = str.split(",");
                int trapX = Integer.parseInt(result[0]);
                int trapY = Integer.parseInt(result[1]);
                if ((x - 1 == trapX && y - 1 == trapY) || (x == trapX && y - 1 == trapY) || (x + 1 == trapX && y - 1 == trapY) ||
                        (x - 1 == trapX && y == trapY) || (x == trapX && y == trapY) || (x + 1 == trapX && y == trapY) ||
                        (x - 1 == trapX && y + 1 == trapY) || (x == trapX && y + 1 == trapY) || (x + 1 == trapX && y + 1 == trapY)) {
                    trapVisible = true;
                    return;
                } else {
                    trapVisible = false;
                }
            }
        }
    }

    public void moveDown() {
        if (contains(Movement.MOVE_DOWN, currentLevel[y][x])) {
            direction = "dn";
            checkCrack();
            y++;
            move();

            startAnimation(0, 0, 0, -MainActivity.width / gridViewColumns);
            TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, -gridViewMap.getHeight() / 7);
            translateAnimation.setDuration(movementSpeed);
            translateAnimation.setInterpolator(getContext(), android.R.anim.linear_interpolator);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                    animation.setDuration(1);
                    gridViewMap.startAnimation(animation);
                    gridViewMap.setAdapter(new MapAdapter(getContext(), 0));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            gridViewMap.startAnimation(translateAnimation);
        }
    }

    public void moveUp() {
        if (contains(Movement.MOVE_UP, currentLevel[y][x])) {
            direction = "up";
            checkCrack();
            y--;
            move();

            startAnimation(0, 0, 0, MainActivity.width / gridViewColumns);
            TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, gridViewMap.getHeight() / 7);
            translateAnimation.setDuration(movementSpeed);
            translateAnimation.setInterpolator(getContext(), android.R.anim.linear_interpolator);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                    animation.setDuration(1);
                    gridViewMap.startAnimation(animation);
                    gridViewMap.setAdapter(new MapAdapter(getContext(), 0));


                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            gridViewMap.startAnimation(translateAnimation);
        }
    }

    public void moveLeft() {
        if (contains(Movement.MOVE_LEFT, currentLevel[y][x])) {
            direction = "lt";
            checkCrack();
            x--;
            move();

            startAnimation(0, MainActivity.width / gridViewColumns, 0, 0);
            TranslateAnimation translateAnimation = new TranslateAnimation(0, gridViewMap.getWidth() / 7, 0, 0);
            translateAnimation.setDuration(movementSpeed);
            translateAnimation.setInterpolator(getContext(), android.R.anim.linear_interpolator);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                    animation.setDuration(1);
                    gridViewMap.startAnimation(animation);
                    gridViewMap.setAdapter(new MapAdapter(getContext(), 0));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            gridViewMap.startAnimation(translateAnimation);
        }
    }

    public void moveRight() {
        if (contains(Movement.MOVE_RIGHT, currentLevel[y][x])) {
            direction = "rt";
            checkCrack();
            x++;
            move();

            startAnimation(0, -MainActivity.width / gridViewColumns, 0, 0);
            TranslateAnimation translateAnimation = new TranslateAnimation(0, -gridViewMap.getWidth() / 7, 0, 0);
            translateAnimation.setDuration(movementSpeed);
            translateAnimation.setInterpolator(getContext(), android.R.anim.linear_interpolator);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                    animation.setDuration(1);
                    gridViewMap.startAnimation(animation);
                    gridViewMap.setAdapter(new MapAdapter(getContext(), 0));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            gridViewMap.startAnimation(translateAnimation);
        }
    }

    public boolean checkFire() {
        if (contains(Tiles.FIRE, currentLevel[y][x])) {
            darkness = 0;
            imageViewDarkness.setImageResource(Darkness.DARKNESS_SMALL[darkness]);

            volumeFire = MainActivity.volumeSound;
            if (fire != null) {
                fire.setVolume(volumeFire, volumeFire);
            }
            return true;
        } else if (contains(Tiles.FIRE, currentLevel[y - 1][x - 1]) || contains(Tiles.FIRE, currentLevel[y - 1][x]) || contains(Tiles.FIRE, currentLevel[y - 1][x + 1]) ||
                contains(Tiles.FIRE, currentLevel[y][x - 1]) || contains(Tiles.FIRE, currentLevel[y][x]) || contains(Tiles.FIRE, currentLevel[y][x + 1]) ||
                contains(Tiles.FIRE, currentLevel[y + 1][x - 1]) || contains(Tiles.FIRE, currentLevel[y + 1][x]) || contains(Tiles.FIRE, currentLevel[y + 1][x + 1])) {
            volumeFire = MainActivity.volumeSound * 0.5f;
            if (fire != null) {
                fire.setVolume(volumeFire, volumeFire);
            }
            return false;
        } /*else if (contains(Tiles.FIRE, currentLevel[y - 2][x - 2]) || contains(Tiles.FIRE, currentLevel[y - 2][x - 1]) || contains(Tiles.FIRE, currentLevel[y - 2][x]) || contains(Tiles.FIRE, currentLevel[y - 2][x + 1]) || contains(Tiles.FIRE, currentLevel[y - 2][x + 2]) ||
                contains(Tiles.FIRE, currentLevel[y - 1][x - 2]) || contains(Tiles.FIRE, currentLevel[y - 1][x - 1]) || contains(Tiles.FIRE, currentLevel[y - 1][x]) || contains(Tiles.FIRE, currentLevel[y - 1][x + 1]) || contains(Tiles.FIRE, currentLevel[y - 1][x + 2]) ||
                contains(Tiles.FIRE, currentLevel[y][x - 2]) || contains(Tiles.FIRE, currentLevel[y][x - 1]) || contains(Tiles.FIRE, currentLevel[y][x]) || contains(Tiles.FIRE, currentLevel[y][x + 1]) || contains(Tiles.FIRE, currentLevel[y][x + 2]) ||
                contains(Tiles.FIRE, currentLevel[y + 1][x - 2]) || contains(Tiles.FIRE, currentLevel[y + 1][x - 1]) || contains(Tiles.FIRE, currentLevel[y + 1][x]) || contains(Tiles.FIRE, currentLevel[y + 1][x + 1]) || contains(Tiles.FIRE, currentLevel[y + 1][x + 2]) ||
                contains(Tiles.FIRE, currentLevel[y + 2][x - 2]) || contains(Tiles.FIRE, currentLevel[y + 2][x - 1]) || contains(Tiles.FIRE, currentLevel[y + 2][x]) || contains(Tiles.FIRE, currentLevel[y + 2][x + 1]) || contains(Tiles.FIRE, currentLevel[y + 2][x + 2])) {
            volumeFire = MainActivity.volumeSound * 0.33f;
            if (fire != null) {
                fire.setVolume(volumeFire, volumeFire);
            }
            return false;
        }*/ else {
            volumeFire = 0;
            if (fire != null) {
                fire.setVolume(volumeFire, volumeFire);
            }
        }
        return false;
    }

    public boolean checkSword() {
        if (contains(Tiles.WEAPON, currentLevel[y][x])) {
            currentLevel[y][x] = Tiles.NORMAL[getIndex(Tiles.WEAPON, currentLevel[y][x])];
            items.set((y - 2) * (currentLevel[0].length - 4) + x - 2, getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[y][x], "drawable", getContext().getPackageName()));
            levelAdapter.notifyDataSetChanged();

            hasSword = true;
            MainActivity.soundPool.play(MainActivity.swordID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
            if (hasSword) {
                imgSword.setVisibility(View.VISIBLE);
            } else {
                imgSword.setVisibility(View.INVISIBLE);
            }
            return true;
        } else {
            if (hasSword) {
                imgSword.setVisibility(View.VISIBLE);
            } else {
                imgSword.setVisibility(View.INVISIBLE);
            }
        }
        return false;
    }

    public boolean checkPortal() {
        if (contains(Tiles.PORTAL, currentLevel[y][x])) {
            allowInput = false;
            int oldX = x;
            int oldY = y;
            for (int i = 0; i < currentLevel[currentLevel.length - 2].length; i++) {
                String str = currentLevel[currentLevel.length - 2][i];
                String[] result = str.split(",");
                String[] coordinates;
                if ((x == Integer.parseInt(result[0])) && (y == Integer.parseInt(result[1]))) {
                    if (i % 2 == 0) {
                        coordinates = currentLevel[currentLevel.length - 2][i + 1].split(",");
                    } else {
                        coordinates = currentLevel[currentLevel.length - 2][i - 1].split(",");
                    }
                    x = Integer.parseInt(coordinates[0]);
                    y = Integer.parseInt(coordinates[1]);
                    position = new Point(x, y);
                    /*if (!mapRevealed) {
                        discovered.clear();
                    }*/
                    discovered.add(new Point(x - 1, y - 1));
                    discovered.add(new Point(x, y - 1));
                    discovered.add(new Point(x + 1, y - 1));
                    discovered.add(new Point(x - 1, y));
                    discovered.add(position);
                    discovered.add(new Point(x + 1, y));
                    discovered.add(new Point(x - 1, y + 1));
                    discovered.add(new Point(x, y + 1));
                    discovered.add(new Point(x + 1, y + 1));
                    stepsMade.add(position);
                    MainActivity.soundPool.play(MainActivity.portalID, MainActivity.volumeSound * 1.5f, MainActivity.volumeSound * 1.5f, 1, 0, 1);

                    float difX = (MainActivity.width / gridViewColumns) * (oldX - x);
                    float difY = (MainActivity.width / gridViewColumns) * (oldY - y);

                    TranslateAnimation animation = new TranslateAnimation(0.0f, difX, 0.0f, difY);
                    animation.setDuration((long) (Math.sqrt(Math.pow(oldX - x, 2) + Math.pow(oldY - y, 2)) * movementSpeed));
                    animation.setInterpolator(getContext(), android.R.anim.accelerate_decelerate_interpolator);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            allowInput = false;
                            imageViewPlayer.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            allowInput = true;
                            imageViewPlayer.setVisibility(View.VISIBLE);

                            switch (gridViewColumns) {
                                case 1:
                                    gridViewLevel.setTranslationY(-MainActivity.width / gridViewColumns * (y - 1));
                                    gridViewLevel.setTranslationX(-MainActivity.width / gridViewColumns * (x - 2));
                                    break;
                                case 3:
                                    gridViewLevel.setTranslationY(-MainActivity.width / gridViewColumns * y);
                                    gridViewLevel.setTranslationX(-MainActivity.width / gridViewColumns * (x - 3));
                                    break;
                                case 5:
                                    gridViewLevel.setTranslationY(-MainActivity.width / gridViewColumns * (y + 1));
                                    gridViewLevel.setTranslationX(-MainActivity.width / gridViewColumns * (x - 4));
                                    break;
                            }

                            animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                            animation.setDuration(1);
                            gridViewLevel.startAnimation(animation);
                            gridViewMap.setAdapter(new MapAdapter(getContext(), 0));
                            setNeedle();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    gridViewLevel.startAnimation(animation);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkMonster() {
        if (contains(Tiles.MONSTER, currentLevel[y][x])) {
            if (!hasSword) {
                gameOver();
            } else {
                allowInput = true;
                currentLevel[y][x] = Tiles.BLOOD[getIndex(Tiles.MONSTER, currentLevel[y][x])];
                items.set((y - 2) * (currentLevel[0].length - 4) + x - 2, getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[y][x], "drawable", getContext().getPackageName()));
                levelAdapter.notifyDataSetChanged();
                hasSword = false;
                imgSword.setVisibility(View.INVISIBLE);

                MainActivity.soundPool.play(MainActivity.monsterID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
            }
            return true;
        }
        return false;
    }

    public boolean checkTrap() {
        if (contains(Tiles.TRAP_ACTIVE, currentLevel[y][x]) && !hasDialog) {
            gameOver();
            return true;
        }
        return false;
    }

    public boolean checkBridge() {
        if (contains(Tiles.BRIDGE, currentLevel[y][x])) {
            MainActivity.soundPool.play(MainActivity.crackID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);

            Handler bridgeHandler = new Handler();
            bridgeHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (contains(Tiles.BRIDGE, currentLevel[y][x])) {
                        MainActivity.soundPool.play(MainActivity.holeID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                        gameOver();
                    }
                }
            }, 1000);
            return true;
        }
        return false;
    }

    public boolean checkCrack() {
        if (contains(Tiles.CRACK, currentLevel[y][x])) {
            currentLevel[y][x] = Tiles.HOLE[getIndex(Tiles.CRACK, currentLevel[y][x])];
            items.set((y - 2) * (currentLevel[0].length - 4) + x - 2, getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[y][x], "drawable", getContext().getPackageName()));
            levelAdapter.notifyDataSetChanged();
            MainActivity.soundPool.play(MainActivity.holeID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
            return true;
        }
        return false;
    }

    public boolean checkHole() {
        if (contains(Tiles.HOLE, currentLevel[y][x]) && !hasDialog) {
            gameOver();
            return true;
        }
        return false;
    }

    public boolean checkDarkness() {
        if (darkness > 14 && !hasDialog) {
            gameOver();
            return true;
        } else if (darkness < 12) {
            volumeBGM = MainActivity.volumeMusic;
            if (bgm != null) {
                bgm.setVolume(volumeBGM, volumeBGM);
            }
            volumeHeart = 0;
            if (heartbeat != null) {
                heartbeat.setVolume(volumeHeart, volumeHeart);
            }
        }

        switch (darkness) {
            case 12:
                volumeBGM = MainActivity.volumeMusic * 0.66f;
                if (bgm != null) {
                    bgm.setVolume(volumeBGM, volumeBGM);
                }
                volumeHeart = MainActivity.volumeSound * 0.33f;
                if (heartbeat != null) {
                    heartbeat.setVolume(volumeHeart, volumeHeart);
                }
                break;
            case 13:
                volumeBGM = MainActivity.volumeMusic * 0.33f;
                if (bgm != null) {
                    bgm.setVolume(volumeBGM, volumeBGM);
                }
                volumeHeart = MainActivity.volumeSound * 0.66f;
                if (heartbeat != null) {
                    heartbeat.setVolume(volumeHeart, volumeHeart);
                }
                break;
            case 14:
                volumeBGM = 0;
                if (bgm != null) {
                    bgm.setVolume(volumeBGM, volumeBGM);
                }
                volumeHeart = 1;
                if (heartbeat != null) {
                    heartbeat.setVolume(volumeHeart, volumeHeart);
                }
                break;
        }
        return false;
    }

    public boolean checkStar() {
        if (contains(Tiles.STAR, currentLevel[y][x])) {
            currentLevel[y][x] = Tiles.NORMAL[getIndex(Tiles.STAR, currentLevel[y][x])];

            items.set((y - 2) * (currentLevel[0].length - 4) + x - 2, getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[y][x], "drawable", getContext().getPackageName()));
            levelAdapter.notifyDataSetChanged();

            stars++;
            MainActivity.soundPool.play(MainActivity.starID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);

            switch (stars) {
                case 0:
                    imageStar1.setImageResource(R.drawable.star_empty);
                    imageStar2.setImageResource(R.drawable.star_empty);
                    imageStar3.setImageResource(R.drawable.star_empty);
                    break;
                case 1:
                    imageStar1.setImageResource(R.drawable.star);
                    imageStar2.setImageResource(R.drawable.star_empty);
                    imageStar3.setImageResource(R.drawable.star_empty);
                    break;
                case 2:
                    imageStar1.setImageResource(R.drawable.star);
                    imageStar2.setImageResource(R.drawable.star);
                    imageStar3.setImageResource(R.drawable.star_empty);
                    break;
                case 3:
                    imageStar1.setImageResource(R.drawable.star);
                    imageStar2.setImageResource(R.drawable.star);
                    imageStar3.setImageResource(R.drawable.star);
                    break;
            }
            return true;
        }
        return false;
    }

    public boolean checkDiary() {
        if (contains(Tiles.DIARY, currentLevel[y][x])) {
            currentLevel[y][x] = Tiles.NORMAL[getIndex(Tiles.DIARY, currentLevel[y][x])];

            items.set((y - 2) * (currentLevel[0].length - 4) + x - 2, getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[y][x], "drawable", getContext().getPackageName()));
            levelAdapter.notifyDataSetChanged();

            MainActivity.diaryList.add(MainActivity.world + ", " + (MainActivity.level + 1));

            MainActivity.soundPool.play(MainActivity.diaryID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);

            MainActivity.stopTime = true;
            hasDialog = true;

            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            switch (MainActivity.world) {
                case 0:
                    switch (MainActivity.level + 1) {
                        case 3:
                            builder.setTitle(R.string.chapter_1);
                            builder.setMessage(R.string.chapter_1_page_1);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 6:
                            builder.setTitle(R.string.chapter_1);
                            builder.setMessage(R.string.chapter_1_page_2);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 9:
                            builder.setTitle(R.string.chapter_1);
                            builder.setMessage(R.string.chapter_1_page_3);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 12:
                            builder.setTitle(R.string.chapter_1);
                            builder.setMessage(R.string.chapter_1_page_4);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 15:
                            builder.setTitle(R.string.chapter_1);
                            builder.setMessage(R.string.chapter_1_page_5);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 18:
                            builder.setTitle(R.string.chapter_1);
                            builder.setMessage(R.string.chapter_1_page_6);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 21:
                            builder.setTitle(R.string.chapter_1);
                            builder.setMessage(R.string.chapter_1_page_7);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 24:
                            builder.setTitle(R.string.chapter_1);
                            builder.setMessage(R.string.chapter_1_page_8);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 27:
                            builder.setTitle(R.string.chapter_1);
                            builder.setMessage(R.string.chapter_1_page_9);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 30:
                            builder.setTitle(R.string.chapter_1);
                            builder.setMessage(R.string.chapter_1_page_10);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                    }
                    break;
                case 1:
                    switch (MainActivity.level + 1) {
                        case 3:
                            builder.setTitle(R.string.chapter_2);
                            builder.setMessage(R.string.chapter_2_page_1);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 6:
                            builder.setTitle(R.string.chapter_2);
                            builder.setMessage(R.string.chapter_2_page_2);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 9:
                            builder.setTitle(R.string.chapter_2);
                            builder.setMessage(R.string.chapter_2_page_3);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 12:
                            builder.setTitle(R.string.chapter_2);
                            builder.setMessage(R.string.chapter_2_page_4);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 15:
                            builder.setTitle(R.string.chapter_2);
                            builder.setMessage(R.string.chapter_2_page_5);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 18:
                            builder.setTitle(R.string.chapter_2);
                            builder.setMessage(R.string.chapter_2_page_6);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 21:
                            builder.setTitle(R.string.chapter_2);
                            builder.setMessage(R.string.chapter_2_page_7);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 24:
                            builder.setTitle(R.string.chapter_2);
                            builder.setMessage(R.string.chapter_2_page_8);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 27:
                            builder.setTitle(R.string.chapter_2);
                            builder.setMessage(R.string.chapter_2_page_9);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 30:
                            builder.setTitle(R.string.chapter_2);
                            builder.setMessage(R.string.chapter_2_page_10);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                    }
                    break;
                case 2:
                    switch (MainActivity.level + 1) {
                        case 3:
                            builder.setTitle(R.string.chapter_3);
                            builder.setMessage(R.string.chapter_3_page_1);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 6:
                            builder.setTitle(R.string.chapter_3);
                            builder.setMessage(R.string.chapter_3_page_2);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 9:
                            builder.setTitle(R.string.chapter_3);
                            builder.setMessage(R.string.chapter_3_page_3);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 12:
                            builder.setTitle(R.string.chapter_3);
                            builder.setMessage(R.string.chapter_3_page_4);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 15:
                            builder.setTitle(R.string.chapter_3);
                            builder.setMessage(R.string.chapter_3_page_5);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 18:
                            builder.setTitle(R.string.chapter_3);
                            builder.setMessage(R.string.chapter_3_page_6);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 21:
                            builder.setTitle(R.string.chapter_3);
                            builder.setMessage(R.string.chapter_3_page_7);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 24:
                            builder.setTitle(R.string.chapter_3);
                            builder.setMessage(R.string.chapter_3_page_8);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 27:
                            builder.setTitle(R.string.chapter_3);
                            builder.setMessage(R.string.chapter_3_page_9);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 30:
                            builder.setTitle(R.string.chapter_3);
                            builder.setMessage(R.string.chapter_3_page_10);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                    }
                    break;
                case 3:
                    switch (MainActivity.level + 1) {
                        case 3:
                            builder.setTitle(R.string.chapter_4);
                            builder.setMessage(R.string.chapter_4_page_1);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 6:
                            builder.setTitle(R.string.chapter_4);
                            builder.setMessage(R.string.chapter_4_page_2);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 9:
                            builder.setTitle(R.string.chapter_4);
                            builder.setMessage(R.string.chapter_4_page_3);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 12:
                            builder.setTitle(R.string.chapter_4);
                            builder.setMessage(R.string.chapter_4_page_4);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 15:
                            builder.setTitle(R.string.chapter_4);
                            builder.setMessage(R.string.chapter_4_page_5);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 18:
                            builder.setTitle(R.string.chapter_4);
                            builder.setMessage(R.string.chapter_4_page_6);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 21:
                            builder.setTitle(R.string.chapter_4);
                            builder.setMessage(R.string.chapter_4_page_7);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 24:
                            builder.setTitle(R.string.chapter_4);
                            builder.setMessage(R.string.chapter_4_page_8);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 27:
                            builder.setTitle(R.string.chapter_4);
                            builder.setMessage(R.string.chapter_4_page_9);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                        case 30:
                            builder.setTitle(R.string.chapter_4);
                            builder.setMessage(R.string.chapter_4_page_10);
                            builder.setPositiveButton(R.string.interesting, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    MainActivity.stopTime = false;
                                    hasDialog = false;
                                    dialog.dismiss();
                                }
                            });
                            builder.setCancelable(false);
                            builder.create();
                            builder.show();
                            break;
                    }
                    break;
            }

            return true;
        }
        return false;
    }

    public void gameOver() {
        allowInput = false;
        MainActivity.stopTime = true;
        hasDialog = true;
        MainActivity.soundPool.play(MainActivity.deathID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);

        if (bgm != null && bgm.isPlaying()) {
            bgm.pause();
        }

        if (fire != null && fire.isPlaying()) {
            fire.pause();
        }

        if (heartbeat != null && heartbeat.isPlaying()) {
            heartbeat.pause();
        }

        if (darkness > 14) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(getString(R.string.die_torch));
            if (MainActivity.torches > 0) {
                builder.setPositiveButton(getString(R.string.use_torch) + ": " + MainActivity.torches, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                        allowInput = true;
                        darkness = 0;
                        MainActivity.torches--;
                        dialog.dismiss();
                        MainActivity.stopTime = false;
                        imageViewDarkness.setImageResource(Darkness.DARKNESS_SMALL[darkness]);
                        if (bgm != null) {
                            bgm.start();
                        }

                        if (fire != null) {
                            fire.start();
                        }

                        if (heartbeat != null) {
                            heartbeat.start();
                        }
                        SaveGame.saveGame(getContext());
                    }
                });
            } else {
                builder.setPositiveButton(getString(R.string.buy_torch) + ": 10 " + getString(R.string.stars), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                        if (MainActivity.allStars >= 10) {
                            MainActivity.allStars -= 10;
                            MainActivity.starsCounter.setText("" + MainActivity.allStars);
                            allowInput = true;
                            darkness = 0;
                            dialog.dismiss();
                            MainActivity.stopTime = false;
                            imageViewDarkness.setImageResource(Darkness.DARKNESS_SMALL[darkness]);
                            if (bgm != null) {
                                bgm.start();
                            }

                            if (fire != null) {
                                fire.start();
                            }

                            if (heartbeat != null) {
                                heartbeat.start();
                            }
                            SaveGame.saveGame(getContext());
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
            }
            builder.setNegativeButton(R.string.no_thanks, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    MainActivity.resetCount++;
                    dialog.dismiss();
                    MainActivity.livesCounter.setText("" + MainActivity.lives);

                    ImageView image2 = new ImageView(getContext());
                    ImageView image3 = new ImageView(getContext());
                    switch (MainActivity.lives) {
                        case 0:
                            image2.setImageResource(R.drawable.hearts_0);
                            image3.setImageResource(R.drawable.hearts_0);
                            break;
                        case 1:
                            image2.setImageResource(R.drawable.hearts_1);
                            image3.setImageResource(R.drawable.hearts_0);
                            break;
                        case 2:
                            image2.setImageResource(R.drawable.hearts_2);
                            image3.setImageResource(R.drawable.hearts_0);
                            break;
                        case 3:
                            image2.setImageResource(R.drawable.hearts_3);
                            image3.setImageResource(R.drawable.hearts_0);
                            break;
                        case 4:
                            image2.setImageResource(R.drawable.hearts_4);
                            image3.setImageResource(R.drawable.hearts_0);
                            break;
                        case 5:
                            image2.setImageResource(R.drawable.hearts_5);
                            image3.setImageResource(R.drawable.hearts_0);
                            break;
                        case 6:
                            image2.setImageResource(R.drawable.hearts_5);
                            image3.setImageResource(R.drawable.hearts_1);
                            break;
                        case 7:
                            image2.setImageResource(R.drawable.hearts_5);
                            image3.setImageResource(R.drawable.hearts_2);
                            break;
                        case 8:
                            image2.setImageResource(R.drawable.hearts_5);
                            image3.setImageResource(R.drawable.hearts_3);
                            break;
                        case 9:
                            image2.setImageResource(R.drawable.hearts_5);
                            image3.setImageResource(R.drawable.hearts_4);
                            break;
                        case 10:
                            image2.setImageResource(R.drawable.hearts_5);
                            image3.setImageResource(R.drawable.hearts_5);
                            break;
                    }

                    LinearLayout linearLayout = new LinearLayout(getContext());
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    linearLayout.addView(image2);
                    linearLayout.addView(image3);

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle(R.string.you_died);
                    builder.setMessage(getString(R.string.you_died_after) + ": " + steps + " " + getString(R.string.die_steps));
                    builder.setView(linearLayout);
                    builder.setCancelable(false);
                    if (MainActivity.lives > 0) {
                        builder.setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                SaveGame.saveGame(getContext());
                                if (mInterstitialAd.isLoaded() && MainActivity.resetCount % 3 == 0) {
                                    mInterstitialAd.show();
                                } else {
                                    reset();
                                }
                            }
                        });
                    }
                    builder.setNegativeButton(R.string.level_select, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                            LevelSelectFragment levelSelectFragment = new LevelSelectFragment();
                            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                            transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
                            transaction.replace(R.id.content, levelSelectFragment, "LevelSelectFragment");
                            transaction.addToBackStack("LevelSelectFragment");
                            transaction.commit();
                            if (MainActivity.menuBGM != null) {
                                MainActivity.menuBGM.start();
                            }
                            SaveGame.saveGame(getContext());
                        }
                    });
                    builder.show();
                }
            });
            builder.setCancelable(false);
            builder.create();
            builder.show();
        } else {
            MainActivity.resetCount++;
            MainActivity.livesCounter.setText("" + MainActivity.lives);
            ImageView image2 = new ImageView(getContext());
            ImageView image3 = new ImageView(getContext());
            switch (MainActivity.lives) {
                case 0:
                    image2.setImageResource(R.drawable.hearts_0);
                    image3.setImageResource(R.drawable.hearts_0);
                    break;
                case 1:
                    image2.setImageResource(R.drawable.hearts_1);
                    image3.setImageResource(R.drawable.hearts_0);
                    break;
                case 2:
                    image2.setImageResource(R.drawable.hearts_2);
                    image3.setImageResource(R.drawable.hearts_0);
                    break;
                case 3:
                    image2.setImageResource(R.drawable.hearts_3);
                    image3.setImageResource(R.drawable.hearts_0);
                    break;
                case 4:
                    image2.setImageResource(R.drawable.hearts_4);
                    image3.setImageResource(R.drawable.hearts_0);
                    break;
                case 5:
                    image2.setImageResource(R.drawable.hearts_5);
                    image3.setImageResource(R.drawable.hearts_0);
                    break;
                case 6:
                    image2.setImageResource(R.drawable.hearts_5);
                    image3.setImageResource(R.drawable.hearts_1);
                    break;
                case 7:
                    image2.setImageResource(R.drawable.hearts_5);
                    image3.setImageResource(R.drawable.hearts_2);
                    break;
                case 8:
                    image2.setImageResource(R.drawable.hearts_5);
                    image3.setImageResource(R.drawable.hearts_3);
                    break;
                case 9:
                    image2.setImageResource(R.drawable.hearts_5);
                    image3.setImageResource(R.drawable.hearts_4);
                    break;
                case 10:
                    image2.setImageResource(R.drawable.hearts_5);
                    image3.setImageResource(R.drawable.hearts_5);
                    break;
            }

            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(image2);
            linearLayout.addView(image3);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(R.string.you_died);
            builder.setMessage(getString(R.string.you_died_after) + ": " + steps + " " + getString(R.string.die_steps));
            builder.setView(linearLayout);
            builder.setCancelable(false);
            if (MainActivity.lives > 0) {
                builder.setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                        SaveGame.saveGame(getContext());
                        if (mInterstitialAd.isLoaded() && MainActivity.resetCount % 3 == 0) {
                            mInterstitialAd.show();
                        } else {
                            reset();
                        }
                    }
                });
            }
            builder.setNegativeButton(R.string.level_select, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    LevelSelectFragment levelSelectFragment = new LevelSelectFragment();
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
                    transaction.replace(R.id.content, levelSelectFragment, "LevelSelectFragment");
                    transaction.addToBackStack("LevelSelectFragment");
                    transaction.commit();
                    if (MainActivity.menuBGM != null) {
                        MainActivity.menuBGM.start();
                    }
                    SaveGame.saveGame(getContext());
                }
            });
            builder.show();
        }
    }

    public boolean checkWin() {
        if (contains(Tiles.GOAL, currentLevel[y][x])) {
            imageViewPlayer.setVisibility(View.INVISIBLE);
            MainActivity.resetCount++;
            if (MainActivity.lives < (5 + MainActivity.levelLives)) {
                MainActivity.lives++;
            }

            MainActivity.stopTime = true;
            hasDialog = true;

            if ((MainActivity.level < com.ollum.mazecape.Arrays.Worlds.WORLDS[MainActivity.world].length - 1) && (MainActivity.level + 1 > MainActivity.maxLevel[MainActivity.world])) {
                MainActivity.maxLevel[MainActivity.world] = MainActivity.level + 1;
            }

            /*else if ((MainActivity.level == Worlds.WORLDS[MainActivity.world].length - 1) && (MainActivity.world + 1 > MainActivity.maxWorld) && (MainActivity.world < Worlds.WORLDS.length - 1)) {
                MainActivity.maxWorld = MainActivity.world + 1;
            }*/

            score = stars;

            if (steps - minSteps <= 0) {
                score++;
            }

            if (time - minSteps <= 0) {
                score++;
            }

            score = score - cheat;

            if (score < 0) {
                score = 0;
            }

            ImageView image = new ImageView(getContext());
            switch (score) {
                case 0:
                    image.setImageResource(R.drawable.stars_0);
                    break;
                case 1:
                    if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 5)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 4)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 3)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 2)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 1)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else {
                        MainActivity.allStars = MainActivity.allStars + (1 * (MainActivity.levelStars + 1));
                        switch (MainActivity.world) {
                            case 0:
                                MainActivity.world1Stars = MainActivity.world1Stars + 1;
                                break;
                            case 1:
                                MainActivity.world2Stars = MainActivity.world2Stars + 1;
                                break;
                            case 2:
                                MainActivity.world3Stars = MainActivity.world3Stars + 1;
                                break;
                            case 3:
                                MainActivity.world4Stars = MainActivity.world4Stars + 1;
                                break;
                            case 4:
                                MainActivity.world5Stars = MainActivity.world5Stars + 1;
                                break;
                        }
                    }
                    image.setImageResource(R.drawable.stars_1);
                    break;
                case 2:
                    if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 5)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 4)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 3)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 2)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 1)) {
                        MainActivity.allStars = MainActivity.allStars + (1 * (MainActivity.levelStars + 1));
                        switch (MainActivity.world) {
                            case 0:
                                MainActivity.world1Stars = MainActivity.world1Stars + 1;
                                break;
                            case 1:
                                MainActivity.world2Stars = MainActivity.world2Stars + 1;
                                break;
                            case 2:
                                MainActivity.world3Stars = MainActivity.world3Stars + 1;
                                break;
                            case 3:
                                MainActivity.world4Stars = MainActivity.world4Stars + 1;
                                break;
                            case 4:
                                MainActivity.world5Stars = MainActivity.world5Stars + 1;
                                break;
                        }
                    } else {
                        MainActivity.allStars = MainActivity.allStars + (2 * (MainActivity.levelStars + 1));
                        switch (MainActivity.world) {
                            case 0:
                                MainActivity.world1Stars = MainActivity.world1Stars + 2;
                                break;
                            case 1:
                                MainActivity.world2Stars = MainActivity.world2Stars + 2;
                                break;
                            case 2:
                                MainActivity.world3Stars = MainActivity.world3Stars + 2;
                                break;
                            case 3:
                                MainActivity.world4Stars = MainActivity.world4Stars + 2;
                                break;
                            case 4:
                                MainActivity.world5Stars = MainActivity.world5Stars + 2;
                                break;
                        }
                    }
                    image.setImageResource(R.drawable.stars_2);
                    break;
                case 3:
                    if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 5)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 4)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 3)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 2)) {
                        MainActivity.allStars = MainActivity.allStars + (1 * (MainActivity.levelStars + 1));
                        switch (MainActivity.world) {
                            case 0:
                                MainActivity.world1Stars = MainActivity.world1Stars + 1;
                                break;
                            case 1:
                                MainActivity.world2Stars = MainActivity.world2Stars + 1;
                                break;
                            case 2:
                                MainActivity.world3Stars = MainActivity.world3Stars + 1;
                                break;
                            case 3:
                                MainActivity.world4Stars = MainActivity.world4Stars + 1;
                                break;
                            case 4:
                                MainActivity.world5Stars = MainActivity.world5Stars + 1;
                                break;
                        }
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 1)) {
                        MainActivity.allStars = MainActivity.allStars + (2 * (MainActivity.levelStars + 1));
                        switch (MainActivity.world) {
                            case 0:
                                MainActivity.world1Stars = MainActivity.world1Stars + 2;
                                break;
                            case 1:
                                MainActivity.world2Stars = MainActivity.world2Stars + 2;
                                break;
                            case 2:
                                MainActivity.world3Stars = MainActivity.world3Stars + 2;
                                break;
                            case 3:
                                MainActivity.world4Stars = MainActivity.world4Stars + 2;
                                break;
                            case 4:
                                MainActivity.world5Stars = MainActivity.world5Stars + 2;
                                break;
                        }
                    } else {
                        MainActivity.allStars = MainActivity.allStars + (3 * (MainActivity.levelStars + 1));
                        switch (MainActivity.world) {
                            case 0:
                                MainActivity.world1Stars = MainActivity.world1Stars + 3;
                                break;
                            case 1:
                                MainActivity.world2Stars = MainActivity.world2Stars + 3;
                                break;
                            case 2:
                                MainActivity.world3Stars = MainActivity.world3Stars + 3;
                                break;
                            case 3:
                                MainActivity.world4Stars = MainActivity.world4Stars + 3;
                                break;
                            case 4:
                                MainActivity.world5Stars = MainActivity.world5Stars + 3;
                                break;
                        }
                    }
                    image.setImageResource(R.drawable.stars_3);
                    break;
                case 4:
                    if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 5)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 4)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 3)) {
                        MainActivity.allStars = MainActivity.allStars + (1 * (MainActivity.levelStars + 1));
                        switch (MainActivity.world) {
                            case 0:
                                MainActivity.world1Stars = MainActivity.world1Stars + 1;
                                break;
                            case 1:
                                MainActivity.world2Stars = MainActivity.world2Stars + 1;
                                break;
                            case 2:
                                MainActivity.world3Stars = MainActivity.world3Stars + 1;
                                break;
                            case 3:
                                MainActivity.world4Stars = MainActivity.world4Stars + 1;
                                break;
                            case 4:
                                MainActivity.world5Stars = MainActivity.world5Stars + 1;
                                break;
                        }
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 2)) {
                        MainActivity.allStars = MainActivity.allStars + (2 * (MainActivity.levelStars + 1));
                        switch (MainActivity.world) {
                            case 0:
                                MainActivity.world1Stars = MainActivity.world1Stars + 2;
                                break;
                            case 1:
                                MainActivity.world2Stars = MainActivity.world2Stars + 2;
                                break;
                            case 2:
                                MainActivity.world3Stars = MainActivity.world3Stars + 2;
                                break;
                            case 3:
                                MainActivity.world4Stars = MainActivity.world4Stars + 2;
                                break;
                            case 4:
                                MainActivity.world5Stars = MainActivity.world5Stars + 2;
                                break;
                        }
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 1)) {
                        MainActivity.allStars = MainActivity.allStars + (3 * (MainActivity.levelStars + 1));
                        switch (MainActivity.world) {
                            case 0:
                                MainActivity.world1Stars = MainActivity.world1Stars + 3;
                                break;
                            case 1:
                                MainActivity.world2Stars = MainActivity.world2Stars + 3;
                                break;
                            case 2:
                                MainActivity.world3Stars = MainActivity.world3Stars + 3;
                                break;
                            case 3:
                                MainActivity.world4Stars = MainActivity.world4Stars + 3;
                                break;
                            case 4:
                                MainActivity.world5Stars = MainActivity.world5Stars + 3;
                                break;
                        }
                    } else {
                        MainActivity.allStars = MainActivity.allStars + (4 * (MainActivity.levelStars + 1));
                        switch (MainActivity.world) {
                            case 0:
                                MainActivity.world1Stars = MainActivity.world1Stars + 4;
                                break;
                            case 1:
                                MainActivity.world2Stars = MainActivity.world2Stars + 4;
                                break;
                            case 2:
                                MainActivity.world3Stars = MainActivity.world3Stars + 4;
                                break;
                            case 3:
                                MainActivity.world4Stars = MainActivity.world4Stars + 4;
                                break;
                            case 4:
                                MainActivity.world5Stars = MainActivity.world5Stars + 4;
                                break;
                        }
                    }
                    image.setImageResource(R.drawable.stars_4);
                    break;
                case 5:
                    if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 5)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 4)) {
                        MainActivity.allStars = MainActivity.allStars + (1 * (MainActivity.levelStars + 1));
                        switch (MainActivity.world) {
                            case 0:
                                MainActivity.world1Stars = MainActivity.world1Stars + 1;
                                break;
                            case 1:
                                MainActivity.world2Stars = MainActivity.world2Stars + 1;
                                break;
                            case 2:
                                MainActivity.world3Stars = MainActivity.world3Stars + 1;
                                break;
                            case 3:
                                MainActivity.world4Stars = MainActivity.world4Stars + 1;
                                break;
                            case 4:
                                MainActivity.world5Stars = MainActivity.world5Stars + 1;
                                break;
                        }
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 3)) {
                        MainActivity.allStars = MainActivity.allStars + (2 * (MainActivity.levelStars + 1));
                        switch (MainActivity.world) {
                            case 0:
                                MainActivity.world1Stars = MainActivity.world1Stars + 2;
                                break;
                            case 1:
                                MainActivity.world2Stars = MainActivity.world2Stars + 2;
                                break;
                            case 2:
                                MainActivity.world3Stars = MainActivity.world3Stars + 2;
                                break;
                            case 3:
                                MainActivity.world4Stars = MainActivity.world4Stars + 2;
                                break;
                            case 4:
                                MainActivity.world5Stars = MainActivity.world5Stars + 2;
                                break;
                        }
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 2)) {
                        MainActivity.allStars = MainActivity.allStars + (3 * (MainActivity.levelStars + 1));
                        switch (MainActivity.world) {
                            case 0:
                                MainActivity.world1Stars = MainActivity.world1Stars + 3;
                                break;
                            case 1:
                                MainActivity.world2Stars = MainActivity.world2Stars + 3;
                                break;
                            case 2:
                                MainActivity.world3Stars = MainActivity.world3Stars + 3;
                                break;
                            case 3:
                                MainActivity.world4Stars = MainActivity.world4Stars + 3;
                                break;
                            case 4:
                                MainActivity.world5Stars = MainActivity.world5Stars + 3;
                                break;
                        }
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 1)) {
                        MainActivity.allStars = MainActivity.allStars + (4 * (MainActivity.levelStars + 1));
                        switch (MainActivity.world) {
                            case 0:
                                MainActivity.world1Stars = MainActivity.world1Stars + 4;
                                break;
                            case 1:
                                MainActivity.world2Stars = MainActivity.world2Stars + 4;
                                break;
                            case 2:
                                MainActivity.world3Stars = MainActivity.world3Stars + 4;
                                break;
                            case 3:
                                MainActivity.world4Stars = MainActivity.world4Stars + 4;
                                break;
                            case 4:
                                MainActivity.world5Stars = MainActivity.world5Stars + 4;
                                break;
                        }
                    } else {
                        MainActivity.allStars = MainActivity.allStars + (5 * (MainActivity.levelStars + 1));
                        switch (MainActivity.world) {
                            case 0:
                                MainActivity.world1Stars = MainActivity.world1Stars + 5;
                                break;
                            case 1:
                                MainActivity.world2Stars = MainActivity.world2Stars + 5;
                                break;
                            case 2:
                                MainActivity.world3Stars = MainActivity.world3Stars + 5;
                                break;
                            case 3:
                                MainActivity.world4Stars = MainActivity.world4Stars + 5;
                                break;
                            case 4:
                                MainActivity.world5Stars = MainActivity.world5Stars + 5;
                                break;
                        }
                    }
                    image.setImageResource(R.drawable.stars_5);
                    break;
            }

            MainActivity.worldStars = new int[]{
                    MainActivity.world1Stars, MainActivity.world2Stars, MainActivity.world3Stars, MainActivity.world4Stars, MainActivity.world5Stars
            };

            //unlock new world when enough stars collected
            int collectedStars = 0;
            for (int i = 0; i < MainActivity.worldStars.length; i++) {
                collectedStars += MainActivity.worldStars[i];
            }

            if (collectedStars >= (MainActivity.maxWorld + 1) * 50 && MainActivity.maxWorld < Worlds.WORLDS.length - 1) {
                MainActivity.maxWorld++;
                MainActivity.soundPool.play(MainActivity.upgradeID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                Toast.makeText(getContext(), R.string.new_world, Toast.LENGTH_LONG).show();
            }

            /*if (collectedStars >= 50) {
                Games.Achievements.unlock(MainActivity.mGoogleApiClient, getString(R.string.achievement_collect_50_stars));
            }*/

            /*ImageView image2 = new ImageView(getContext());
            switch (MainActivity.lives) {
                case 0:
                    image2.setImageResource(R.drawable.hearts_0);
                    break;
                case 1:
                    image2.setImageResource(R.drawable.hearts_1);
                    break;
                case 2:
                    image2.setImageResource(R.drawable.hearts_2);
                    break;
                case 3:
                    image2.setImageResource(R.drawable.hearts_3);
                    break;
                case 4:
                    image2.setImageResource(R.drawable.hearts_4);
                    break;
                case 5:
                    image2.setImageResource(R.drawable.hearts_5);
                    break;
            }*/

            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(image);
            //linearLayout.addView(image2);

            MainActivity.starsCounter.setText("" + MainActivity.allStars);
            MainActivity.starsList.add(MainActivity.world + ", " + MainActivity.level + ", " + score);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(R.string.you_win);
            builder.setMessage(getString(R.string.steps) + ": " + steps + " / " + minSteps + "\n" + getString(R.string.time) + ": " + time);
            builder.setView(linearLayout);
            builder.setCancelable(false);
            if (MainActivity.level < com.ollum.mazecape.Arrays.Worlds.WORLDS[MainActivity.world].length - 1) {
                builder.setPositiveButton(R.string.next_level, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                        MainActivity.level++;
                        if (mInterstitialAd.isLoaded() && MainActivity.resetCount % 3 == 0) {
                            mInterstitialAd.show();
                        } else {
                            reset();
                        }
                    }
                });
            } else {
                builder.setPositiveButton(R.string.next_world, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                        MainActivity.level = 0;
                        MainActivity.world++;
                        WorldSelectFragment worldSelectFragment = new WorldSelectFragment();
                        FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                        transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
                        transaction.replace(R.id.content, worldSelectFragment, "WorldSelectFragment");
                        transaction.addToBackStack("WorldSelectFragment");
                        transaction.commit();
                        if (MainActivity.menuBGM != null) {
                            MainActivity.menuBGM.start();
                        }
                    }
                });
            }
            builder.setNeutralButton(R.string.level_select, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    LevelSelectFragment levelSelectFragment = new LevelSelectFragment();
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
                    transaction.replace(R.id.content, levelSelectFragment, "LevelSelectFragment");
                    transaction.addToBackStack("LevelSelectFragment");
                    transaction.commit();
                    if (MainActivity.menuBGM != null) {
                        MainActivity.menuBGM.start();
                    }
                }
            });
            builder.setNegativeButton(R.string.replay, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    if (mInterstitialAd.isLoaded() && MainActivity.resetCount % 3 == 0) {
                        mInterstitialAd.show();
                    } else {
                        reset();
                    }
                }
            });
            builder.show();

            if (bgm != null && bgm.isPlaying()) {
                bgm.pause();
            }

            if (fire != null && fire.isPlaying()) {
                fire.pause();
            }

            if (heartbeat != null && heartbeat.isPlaying()) {
                heartbeat.pause();
            }

            MainActivity.soundPool.play(MainActivity.winID, MainActivity.volumeSound / 2, MainActivity.volumeSound / 2, 1, 0, 1);
            SaveGame.saveGame(getContext());
            return true;
        }
        return false;
    }

    public void checkDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        if (MainActivity.maxWorld == MainActivity.world) {
            if (MainActivity.maxLevel[MainActivity.world] == MainActivity.level) {
                switch (MainActivity.world) {
                    case 0:
                        switch (MainActivity.level) {
                            case 0:
                                MainActivity.stopTime = true;
                                hasDialog = true;
                                builder.setTitle(R.string.welcome_to_mazecape);
                                builder.setMessage(R.string.welcome_dialog);
                                builder.setPositiveButton(R.string.lets_get_started, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                        MainActivity.stopTime = false;
                                        hasDialog = false;
                                        dialog.dismiss();
                                    }
                                });
                                builder.setCancelable(false);
                                builder.create();
                                builder.show();
                                break;
                            case 4:
                                MainActivity.stopTime = true;
                                hasDialog = true;
                                builder.setTitle(R.string.moster);
                                builder.setMessage(R.string.monster_dialog);
                                builder.setPositiveButton(R.string.i_can_handle_that, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                        MainActivity.stopTime = false;
                                        hasDialog = false;
                                        dialog.dismiss();
                                    }
                                });
                                builder.setCancelable(false);
                                builder.create();
                                builder.show();
                                break;
                            case 6:
                                MainActivity.stopTime = true;
                                hasDialog = true;
                                builder.setTitle(R.string.portal);
                                builder.setMessage(R.string.portal_dialog);
                                builder.setPositiveButton(R.string.awesome, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                        MainActivity.stopTime = false;
                                        hasDialog = false;
                                        dialog.dismiss();
                                    }
                                });
                                builder.setCancelable(false);
                                builder.create();
                                builder.show();
                                break;
                        }
                        break;
                    case 1:
                        switch (MainActivity.level) {
                            case 0:
                                MainActivity.stopTime = true;
                                hasDialog = true;
                                builder.setTitle(R.string.cave);
                                builder.setMessage(R.string.cave_dialog);
                                builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                        MainActivity.stopTime = false;
                                        hasDialog = false;
                                        dialog.dismiss();
                                    }
                                });
                                builder.setCancelable(false);
                                builder.create();
                                builder.show();
                                break;
                        }
                        break;
                    case 2:
                        switch (MainActivity.level) {
                            case 0:
                                MainActivity.stopTime = true;
                                hasDialog = true;
                                builder.setTitle(R.string.snow);
                                builder.setMessage(R.string.snow_dialog);
                                builder.setPositiveButton(R.string.alright, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                        MainActivity.stopTime = false;
                                        hasDialog = false;
                                        dialog.dismiss();
                                    }
                                });
                                builder.setCancelable(false);
                                builder.create();
                                builder.show();
                                break;
                        }
                        break;
                    case 3:
                        switch (MainActivity.level) {
                            case 0:
                                MainActivity.stopTime = true;
                                hasDialog = true;
                                builder.setTitle(R.string.desert);
                                builder.setMessage(R.string.desert_dialog);
                                builder.setPositiveButton(R.string.lets_not_get_lost, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                        MainActivity.stopTime = false;
                                        hasDialog = false;
                                        dialog.dismiss();
                                    }
                                });
                                builder.setCancelable(false);
                                builder.create();
                                builder.show();
                                break;
                        }
                        break;
                }
            }
        }
    }

    public String[][] rotateLevel(String[][] oldArray) {
        /*System.out.println("oldArray");
        for (int i = 0; i < oldArray.length; i++) {
            for (int k = 0; k < oldArray[i].length; k++) {
                System.out.print(oldArray[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------");*/

        //rotate level by 90 degrees cw
        String[][] newArray = new String[oldArray[0].length + 3][oldArray.length - 3];
        for (int i = 0; i < oldArray.length - 3; i++) {
            for (int k = 0; k < oldArray[i].length; k++) {
                switch (oldArray[i][k].substring(1, 3)) {
                    case "lt":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "up";
                        break;
                    case "rt":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "dn";
                        break;
                    case "up":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "rt";
                        break;
                    case "dn":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "lt";
                        break;
                    case "lr":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "ud";
                        break;
                    case "ud":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "lr";
                        break;
                    case "ur":
                        if (oldArray[i][k].substring(0, 1).equals("j")) {
                            newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "rd";
                        } else {
                            newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "dr";
                        }
                        break;
                    case "ul":
                        if (oldArray[i][k].substring(0, 1).equals("j")) {
                            newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "ru";
                        } else {
                            newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "ur";
                        }
                        break;
                    case "dr":
                        if (oldArray[i][k].substring(0, 1).equals("j")) {
                            newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "ld";
                        } else {
                            newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "dl";
                        }
                        break;
                    case "dl":
                        if (oldArray[i][k].substring(0, 1).equals("j")) {
                            newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "lu";
                        } else {
                            newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "ul";
                        }
                        break;
                    case "ru":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "dr";
                        break;
                    case "lu":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "ur";
                        break;
                    case "rd":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "dl";
                        break;
                    case "ld":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "ul";
                        break;
                    case "tu":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "tr";
                        break;
                    case "td":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "tl";
                        break;
                    case "tl":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "tu";
                        break;
                    case "tr":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "td";
                        break;
                    case "cs":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "cs";
                        break;
                    case "cd":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "cl";
                        break;
                    case "cl":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "cu";
                        break;
                    case "cu":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "cr";
                        break;
                    case "cr":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "cd";
                        break;
                    case "xx":
                        newArray[k][oldArray.length - 4 - i] = oldArray[i][k].substring(0, 1) + "xx";
                        break;
                }
            }
        }

        //keep instructions
        newArray[newArray.length - 3] = oldArray[oldArray.length - 3].clone();
        newArray[newArray.length - 2] = oldArray[oldArray.length - 2].clone();
        newArray[newArray.length - 1] = oldArray[oldArray.length - 1].clone();

        //rotate position
        int newX = x;
        int newY = y;
        newY = x;
        newX = newArray[0].length - 1 - y;
        x = newX;
        y = newY;
        position = new Point(x, y);

        //rotate steps made
        for (int i = 0; i < stepsMade.size(); i++) {
            newX = stepsMade.get(i).x;
            newY = stepsMade.get(i).y;
            newY = stepsMade.get(i).x;
            newX = newArray[0].length - 1 - stepsMade.get(i).y;
            stepsMade.set(i, new Point(newX, newY));
        }

        //rotate discovered paths
        for (int i = 0; i < discovered.size(); i++) {
            newX = discovered.get(i).x;
            newY = discovered.get(i).y;
            newY = discovered.get(i).x;
            newX = newArray[0].length - 1 - discovered.get(i).y;
            discovered.set(i, new Point(newX, newY));
        }

        //rotate portals
        for (int i = 0; i < newArray[newArray.length - 2].length; i++) {
            String str = newArray[newArray.length - 2][i];
            String[] result = str.split(",");
            newY = Integer.parseInt(result[0]);
            newX = newArray[0].length - 1 - Integer.parseInt(result[1]);
            newArray[newArray.length - 2][i] = newX + "," + newY;
        }

        //rotate traps
        for (int i = 0; i < newArray[newArray.length - 1].length; i++) {
            String str = newArray[newArray.length - 1][i];
            String[] result = str.split(",");
            newY = Integer.parseInt(result[0]);
            newX = newArray[0].length - 1 - Integer.parseInt(result[1]);
            newArray[newArray.length - 1][i] = newX + "," + newY;
        }

        /*System.out.println("oldArray");
        for (int i = 0; i < newArray.length; i++) {
            for (int k = 0; k < newArray[i].length; k++) {
                System.out.print(newArray[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------");*/

        return newArray;
    }

    public void setNeedle() {
        int goalX = 0;
        int goalY = 0;
        int star1X = 0;
        int star1Y = 0;
        int star2X = 0;
        int star2Y = 0;
        int star3X = 0;
        int star3Y = 0;

        float y;
        float x;
        float angleRad;
        float angleDeg;

        final float angleStar1;
        final float angleStar2;
        final float angleStar3;

        if (MainActivity.levelCompass > 0) {
            for (int i = 0; i < currentLevel.length - 3; i++) {
                for (int k = 0; k < currentLevel[i].length; k++) {
                    if (contains(Tiles.GOAL, currentLevel[i][k])) {
                        goalX = k;
                        goalY = i;
                        break;
                    }
                }
            }

            angleGoalOld = angleGoalNew;
            y = goalY - position.y;
            x = goalX - position.x;
            angleRad = (float) Math.atan2(y, x);
            angleDeg = (float) Math.toDegrees(angleRad);
            angleGoalNew = angleDeg + 90;

            float angleCalc = angleGoalNew - angleGoalOld;

            if (angleCalc > 180) {
                angleCalc = angleCalc - 360;
            } else if (angleCalc < -180) {
                angleCalc = angleCalc + 360;
            }

            /*Log.d("debug", "old: " + angleGoalOld);
            Log.d("debug", "new: " + angleGoalNew);
            Log.d("debug", "calc: " + angleGoalNew);*/

            if (!(y == 0 && x == 0)) {
                RotateAnimation rotateAnimation = new RotateAnimation(0, angleCalc, compass.getWidth() / 2, compass.getHeight() / 2);
                rotateAnimation.setDuration(movementSpeed - 50);
                rotateAnimation.setInterpolator(getContext(), android.R.anim.overshoot_interpolator);
                rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                        animation.setDuration(1);
                        needleGoal.startAnimation(animation);
                        needleGoal.setRotation(angleGoalNew);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                needleGoal.startAnimation(rotateAnimation);
            }

            if (MainActivity.levelCompass > 1) {
                for (int i = 0; i < currentLevel.length - 3; i++) {
                    for (int k = 0; k < currentLevel[i].length; k++) {
                        if (contains(Tiles.STAR, currentLevel[i][k])) {
                            star1X = k;
                            star1Y = i;
                            break;
                        }
                    }
                }

                for (int i = 0; i < currentLevel.length - 3; i++) {
                    for (int k = 0; k < currentLevel[i].length; k++) {
                        if (contains(Tiles.STAR, currentLevel[i][k]) && !(i == star1Y && k == star1X)) {
                            star2X = k;
                            star2Y = i;
                            break;
                        }
                    }
                }

                for (int i = 0; i < currentLevel.length - 3; i++) {
                    for (int k = 0; k < currentLevel[i].length; k++) {
                        if (contains(Tiles.STAR, currentLevel[i][k]) && !(i == star1Y && k == star1X) && !(i == star2Y && k == star2X)) {
                            star3X = k;
                            star3Y = i;
                            break;
                        }
                    }
                }

                if (!(star1X == 0 && star1Y == 0)) {
                    y = star1Y - position.y;
                    x = star1X - position.x;
                    angleRad = (float) Math.atan2(y, x);
                    angleDeg = (float) Math.toDegrees(angleRad);
                    angleStar1 = angleDeg + 90;

                    if (!(y == 0 && x == 0)) {
                        needleStar1.setVisibility(View.VISIBLE);
                        needleStar1.setRotation(angleStar1);
                    }
                } else {
                    needleStar1.setVisibility(View.INVISIBLE);
                }

                if (!(star2X == 0 && star2Y == 0)) {
                    y = star2Y - position.y;
                    x = star2X - position.x;
                    angleRad = (float) Math.atan2(y, x);
                    angleDeg = (float) Math.toDegrees(angleRad);
                    angleStar2 = angleDeg + 90;

                    if (!(y == 0 && x == 0)) {
                        needleStar2.setVisibility(View.VISIBLE);
                        needleStar2.setRotation(angleStar2);
                    }
                } else {
                    needleStar2.setVisibility(View.INVISIBLE);
                }

                if (!(star3X == 0 && star3Y == 0)) {
                    y = star3Y - position.y;
                    x = star3X - position.x;
                    angleRad = (float) Math.atan2(y, x);
                    angleDeg = (float) Math.toDegrees(angleRad);
                    angleStar3 = angleDeg + 90;

                    if (!(y == 0 && x == 0)) {
                        needleStar3.setVisibility(View.VISIBLE);
                        needleStar3.setRotation(angleStar3);
                    }
                } else {
                    needleStar3.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    public void startAnimation(float fromX, float toX, float fromY, float toY) {
        allowInput = false;
        final TranslateAnimation translateAnimation = new TranslateAnimation(fromX, toX, fromY, toY);
        translateAnimation.setDuration(movementSpeed);
        if (scene.equals("s")) {
            translateAnimation.setInterpolator(getContext(), android.R.anim.linear_interpolator);
        } else {
            translateAnimation.setInterpolator(getContext(), android.R.anim.accelerate_decelerate_interpolator);
        }
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimating = true;
                setNeedle();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (contains(Tiles.LOOKOUT, currentLevel[y][x])) {
                    gridViewColumns = 5;
                } else if (contains(Tiles.FOG, currentLevel[y][x])) {
                    gridViewColumns = 1;
                } else {
                    gridViewColumns = 3;
                }

                relativeLayout_Container.getLayoutParams().width = MainActivity.width * (currentLevel[0].length - 4) / gridViewColumns;
                relativeLayout_Container.getLayoutParams().height = MainActivity.height - MainActivity.height / 8 + MainActivity.width * (currentLevel.length - 7) / gridViewColumns;

                gridViewLevel.getLayoutParams().width = MainActivity.width * (currentLevel[0].length - 4) / gridViewColumns;
                gridViewLevel.getLayoutParams().height = MainActivity.width * (currentLevel.length - 7) / gridViewColumns;
                gridViewLevel.setNumColumns(currentLevel[0].length - 4);

                switch (gridViewColumns) {
                    case 1:
                        gridViewLevel.setTranslationY(-MainActivity.width / gridViewColumns * (y - 1));
                        gridViewLevel.setTranslationX(-MainActivity.width / gridViewColumns * (x - 2));

                        if (!(scene.equals("s") || scene.equals("m"))) {
                            imageViewPlayer.setImageResource(getResources().getIdentifier("p" + scene + direction + "_small", "drawable", getContext().getPackageName()));
                        } else {
                            imageViewPlayer.setImageResource(getResources().getIdentifier("p" + "f" + direction + "_small", "drawable", getContext().getPackageName()));
                        }
                        break;
                    case 3:
                        gridViewLevel.setTranslationY(-MainActivity.width / gridViewColumns * y);
                        gridViewLevel.setTranslationX(-MainActivity.width / gridViewColumns * (x - 3));

                        if (!(scene.equals("s") || scene.equals("m"))) {
                            imageViewPlayer.setImageResource(getResources().getIdentifier("p" + scene + direction + "_small", "drawable", getContext().getPackageName()));
                        } else {
                            imageViewPlayer.setImageResource(getResources().getIdentifier("p" + "f" + direction + "_small", "drawable", getContext().getPackageName()));
                        }
                        break;
                    case 5:
                        gridViewLevel.setTranslationY(-MainActivity.width / gridViewColumns * (y + 1));
                        gridViewLevel.setTranslationX(-MainActivity.width / gridViewColumns * (x - 4));

                        if (!(scene.equals("s") || scene.equals("m"))) {
                            imageViewPlayer.setImageResource(getResources().getIdentifier("p" + scene + direction + "_big", "drawable", getContext().getPackageName()));
                        } else {
                            imageViewPlayer.setImageResource(getResources().getIdentifier("p" + "f" + direction + "_big", "drawable", getContext().getPackageName()));
                        }
                        break;
                }

                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                animation.setDuration(1);
                gridViewLevel.startAnimation(animation);

                if (!checkDarkness()) {
                    if (!checkTrap()) {
                        if (!checkHole()) {
                            if (!checkMonster()) {
                                if (!checkWin()) {
                                    checkFire();
                                    checkSword();
                                    checkStar();
                                    checkDiary();
                                    checkPortal();
                                    checkBridge();
                                    if (contains(Tiles.CRACK, currentLevel[y][x])) {
                                        MainActivity.soundPool.play(MainActivity.crackID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                                    }
                                    step++;
                                    if (scene.equals("d") && !contains(Tiles.TRAP_ACTIVE, currentLevel[y][x]) && !contains(Tiles.TRAP_INACTIVE, currentLevel[y][x])) {
                                        if (step % randStep == 0) {
                                            step = 0;
                                            randStep = 5 + (int) (Math.random() * ((15 - 5) + 1));
                                            allowInput = false;
                                            int randRotation = 1 + (int) (Math.random() * ((3 - 1) + 1));
                                            for (int i = 0; i < randRotation; i++) {
                                                currentLevel = rotateLevel(currentLevel);
                                            }
                                            RotateAnimation rotateAnimation = new RotateAnimation(0, 360, MainActivity.width / 2, MainActivity.width / 2);
                                            rotateAnimation.setDuration(1000);
                                            rotateAnimation.setInterpolator(getContext(), android.R.anim.linear_interpolator);
                                            rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                                                @Override
                                                public void onAnimationStart(Animation animation) {
                                                    allowInput = false;
                                                    imageViewSandstorm.setVisibility(View.VISIBLE);
                                                }

                                                @Override
                                                public void onAnimationEnd(Animation animation) {
                                                    allowInput = true;
                                                    imageViewSandstorm.setVisibility(View.INVISIBLE);
                                                }

                                                @Override
                                                public void onAnimationRepeat(Animation animation) {

                                                }
                                            });
                                            imageViewSandstorm.startAnimation(rotateAnimation);

                                            items.clear();
                                            for (int i = 2; i < GameFragment.currentLevel.length - 5; i++) {
                                                for (int k = 2; k < GameFragment.currentLevel[i].length - 2; k++) {
                                                    items.add(getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[i][k], "drawable", getContext().getPackageName()));
                                                }
                                            }

                                            relativeLayout_Container.getLayoutParams().height = MainActivity.height - MainActivity.height / 8 + MainActivity.width * (currentLevel.length - 7) / gridViewColumns;
                                            relativeLayout_Container.getLayoutParams().width = MainActivity.width * (currentLevel[0].length - 4) / gridViewColumns;

                                            gridViewLevel.getLayoutParams().width = MainActivity.width * (currentLevel[0].length - 4) / gridViewColumns;
                                            gridViewLevel.getLayoutParams().height = MainActivity.width * (currentLevel.length - 7) / gridViewColumns;
                                            gridViewLevel.setNumColumns(currentLevel[0].length - 4);

                                            switch (gridViewColumns) {
                                                case 1:
                                                    gridViewLevel.setTranslationY(-MainActivity.width / gridViewColumns * (y - 1));
                                                    gridViewLevel.setTranslationX(-MainActivity.width / gridViewColumns * (x - 2));
                                                    break;
                                                case 3:
                                                    gridViewLevel.setTranslationY(-MainActivity.width / gridViewColumns * y);
                                                    gridViewLevel.setTranslationX(-MainActivity.width / gridViewColumns * (x - 3));
                                                    break;
                                                case 5:
                                                    gridViewLevel.setTranslationY(-MainActivity.width / gridViewColumns * (y + 1));
                                                    gridViewLevel.setTranslationX(-MainActivity.width / gridViewColumns * (x - 4));
                                                    break;
                                            }

                                            levelAdapter.notifyDataSetChanged();
                                            gridViewMap.setAdapter(new MapAdapter(getContext(), 0));
                                        } else {
                                            allowInput = true;
                                        }
                                    } else {
                                        allowInput = true;
                                    }
                                }
                            }
                        }
                    }
                }

                isAnimating = false;
                //allowInput = true;

                //Slippery Ground
                if (scene.equals("s")) {
                    switch (direction) {
                        case "up":
                            moveUp();
                            break;
                        case "dn":
                            moveDown();
                            break;
                        case "lt":
                            moveLeft();
                            break;
                        case "rt":
                            moveRight();
                            break;
                    }
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        gridViewLevel.startAnimation(translateAnimation);
    }

    public boolean contains(String[] array, String resource) {
        for (String element : array) {
            if (element.equals(resource)) {
                return true;
            }
        }
        return false;
    }

    public int getIndex(String[] array, String resource) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(resource)) {
                return i;
            }
        }
        return -1;
    }

    public String[][] copyLevel(String[][] oldArray) {
        String[][] newArray = new String[oldArray.length][];
        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i].clone();
        }
        return newArray;
    }

    public void resetLevel() {
        if (bgm != null && bgm.isPlaying()) {
            bgm.pause();
        }
        if (fire != null && fire.isPlaying()) {
            fire.pause();
        }
        if (heartbeat != null && heartbeat.isPlaying()) {
            heartbeat.pause();
        }

        MainActivity.stopTime = true;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.reset_level);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                MainActivity.resetCount++;
                if (mInterstitialAd.isLoaded() && MainActivity.resetCount % 3 == 0) {
                    mInterstitialAd.show();
                } else {
                    reset();
                }
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                MainActivity.stopTime = false;
                if (bgm != null) {
                    bgm.start();
                }
                if (fire != null) {
                    fire.start();
                }
                if (heartbeat != null) {
                    heartbeat.start();
                }
            }
        });
        builder.setNeutralButton(R.string.level_select, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                MainActivity.resetCount++;
                MainActivity.livesCounter.setText("" + MainActivity.lives);
                LevelSelectFragment levelSelectFragment = new LevelSelectFragment();
                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
                transaction.replace(R.id.content, levelSelectFragment, "LevelSelectFragment");
                transaction.addToBackStack("LevelSelectFragment");
                transaction.commit();
                if (MainActivity.menuBGM != null) {
                    MainActivity.menuBGM.start();
                }
            }
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    public void createHandler() {
        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                if (!MainActivity.stopTime) {
                    time += 1;
                    MainActivity.timeCounter.setText("" + time);

                    //Time-Based changes like traps
                    if (containsTrap) {
                        if ((time + 2) % 3 == 0) {
                            for (int i = 0; i < currentLevel[currentLevel.length - 1].length; i++) {
                                String str = currentLevel[currentLevel.length - 1][i];
                                String[] result = str.split(",");
                                int trapX = Integer.parseInt(result[0]);
                                int trapY = Integer.parseInt(result[1]);
                                if (contains(Tiles.TRAP_ACTIVE, currentLevel[trapY][trapX])) {
                                    currentLevel[trapY][trapX] = Tiles.TRAP_INACTIVE[getIndex(Tiles.TRAP_ACTIVE, currentLevel[trapY][trapX])];
                                    items.set((trapY - 2) * (currentLevel[0].length - 4) + trapX - 2, getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[trapY][trapX], "drawable", getContext().getPackageName()));
                                    levelAdapter.notifyDataSetChanged();
                                    trapActive = false;
                                    checkTrap();
                                }
                            }
                            if (!trapActive && trapVisible) {
                                MainActivity.soundPool.play(MainActivity.trapInactiveID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                            }
                        } else if (time % 3 == 0) {
                            for (int i = 0; i < currentLevel[currentLevel.length - 1].length; i++) {
                                String str = currentLevel[currentLevel.length - 1][i];
                                String[] result = str.split(",");
                                int trapX = Integer.parseInt(result[0]);
                                int trapY = Integer.parseInt(result[1]);
                                if (contains(Tiles.TRAP_INACTIVE, currentLevel[trapY][trapX])) {
                                    currentLevel[trapY][trapX] = Tiles.TRAP_ACTIVE[getIndex(Tiles.TRAP_INACTIVE, currentLevel[trapY][trapX])];
                                    items.set((trapY - 2) * (currentLevel[0].length - 4) + trapX - 2, getResources().getIdentifier(GameFragment.scene + GameFragment.currentLevel[trapY][trapX], "drawable", getContext().getPackageName()));
                                    levelAdapter.notifyDataSetChanged();
                                    trapActive = true;
                                    checkTrap();
                                }
                            }
                            if (trapActive && trapVisible) {
                                MainActivity.soundPool.play(MainActivity.trapActiveID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                            }
                        }
                    }

                    if (scene.equals("f") && (time % ((MainActivity.levelTorch + 2) * 2)) == 0) {
                        darkness++;
                        imageViewDarkness.setImageResource(Darkness.DARKNESS_SMALL[darkness]);
                        checkDarkness();
                    } else if ((scene.equals("s") || scene.equals("d")) && (time % ((MainActivity.levelTorch + 2) * 4)) == 0) {
                        darkness++;
                        imageViewDarkness.setImageResource(Darkness.DARKNESS_SMALL[darkness]);
                        checkDarkness();
                    }
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    @Override
    public void onClick(View v) {
        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
        switch (v.getId()) {
            case R.id.resetLevel:
                resetLevel();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.reveal_map);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                if (MainActivity.allStars >= 20) {
                    MainActivity.allStars -= 20;
                    for (int i = 0; i < currentLevel.length - 3; i++) {
                        for (int k = 0; k < currentLevel[i].length - 1; k++) {
                            Point point = new Point(k, i);
                            discovered.add(point);
                            mapRevealed = true;
                        }
                    }
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    gridViewMap.setAdapter(new MapAdapter(getContext(), 0));
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
                MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }
}
