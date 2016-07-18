package com.ollum.mazecape.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.Adapters.LevelAdapter;
import com.ollum.mazecape.Adapters.MapAdapter;
import com.ollum.mazecape.Arrays.LevelArrays;
import com.ollum.mazecape.Arrays.PlayerArrays;
import com.ollum.mazecape.Classes.OnSwipeTouchListener;
import com.ollum.mazecape.R;

import java.util.ArrayList;

public class GameFragment extends Fragment implements View.OnClickListener {

    public static int width;
    public static int height;
    public static TextView title, stepCounter, timeCounter, posX, posY, starsSum, livesCounter;
    public static MediaPlayer bgm;
    public static MediaPlayer fire;
    public static MediaPlayer heartbeat;
    public static String[][] currentLevel;
    public static int x;
    public static int y;
    public static String scene;
    public static int stepCount = 0;
    public static Point position;
    public static ArrayList<Point> stepsMade;
    public static ArrayList<Point> discovered;
    public static boolean stopTime = false;
    final int VIBRATE_SHORT = 10;
    final int VIBRATE_MEDIUM = 500;
    final int VIBRATE_LONG = 1000;
    RelativeLayout relativeLayout_Container, relativeLayout_UI;
    LinearLayout levelTitle, stats, navigation;
    GridView gridViewLevel, gridViewMap;
    ImageView imageViewPlayer, imageViewDarkness, imageViewSandstorm, compass, needle, mapPosition;
    Button resetLevel, cheatButton;
    ToggleButton controlButton;
    Vibrator vibrator;
    Display display;
    Point size;
    int statusBarHeight;
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
    float volumeBGM = 0.5f;
    float volumeFire = 0;
    float volumeHeart = 0;
    float volumeFX = 1;
    String direction = "up";
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

        stepsMade = new ArrayList<>();
        discovered = new ArrayList<>();

        currentLevel = copyLevel(LevelArrays.LEVEL[MainActivity.level]);
        x = Integer.parseInt(currentLevel[currentLevel.length - 2][0]);
        y = Integer.parseInt(currentLevel[currentLevel.length - 2][1]);
        scene = currentLevel[currentLevel.length - 2][2];

        position = new Point(x, y);
        /*discovered.add(new Point(x - 1, y - 1));
        discovered.add(new Point(x, y - 1));
        discovered.add(new Point(x + 1, y - 1));
        discovered.add(new Point(x - 1, y));
        discovered.add(position);
        discovered.add(new Point(x + 1, y));
        discovered.add(new Point(x - 1, y + 1));
        discovered.add(new Point(x, y + 1));
        discovered.add(new Point(x + 1, y + 1));*/

        stepsMade.add(position);

        display = getActivity().getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        statusBarHeight = getStatusBarHeight();
        width = size.x;
        height = size.y - statusBarHeight;

        relativeLayout_Container = (RelativeLayout) view.findViewById(R.id.relativeLayout_Container);
        relativeLayout_Container.getLayoutParams().height = height + width / 3;
        relativeLayout_Container.getLayoutParams().width = width * 5 / 3;

        relativeLayout_UI = (RelativeLayout) view.findViewById(R.id.relativeLayout_UI);
        relativeLayout_UI.getLayoutParams().width = width;
        relativeLayout_UI.getLayoutParams().height = height;

        levelTitle = (LinearLayout) view.findViewById(R.id.levelTitel);
        levelTitle.getLayoutParams().height = height / 16;

        stats = (LinearLayout) view.findViewById(R.id.stats);
        stats.getLayoutParams().height = height / 16;

        navigation = (LinearLayout) view.findViewById(R.id.navigation);
        navigation.getLayoutParams().height = (int) (height / 3.55555555);

        compass = (ImageView) view.findViewById(R.id.compass);

        needle = (ImageView) view.findViewById(R.id.needle);

        imageViewPlayer = (ImageView) view.findViewById(R.id.imageViewPlayer);
        imageViewPlayer.getLayoutParams().width = width * 5 / 3;
        imageViewPlayer.getLayoutParams().height = width * 5 / 3;
        setMargins(imageViewPlayer, -width / 3, 0, 0, 0);
        if (!scene.equals("s")) {
            imageViewPlayer.setImageResource(getResources().getIdentifier("p" + scene + direction, "drawable", getContext().getPackageName()));
        } else {
            imageViewPlayer.setImageResource(getResources().getIdentifier("p" + "f" + direction, "drawable", getContext().getPackageName()));
        }
        imageViewDarkness = (ImageView) view.findViewById(R.id.imageViewDarkness);
        imageViewDarkness.getLayoutParams().width = width * 5 / 3;
        imageViewDarkness.getLayoutParams().height = width * 5 / 3;
        setMargins(imageViewDarkness, -width / 3, 0, 0, 0);
        imageViewDarkness.setImageResource(PlayerArrays.DARKNESS[darkness]);

        imageViewSandstorm = (ImageView) view.findViewById(R.id.imageViewSandstorm);
        imageViewSandstorm.getLayoutParams().width = width;
        imageViewSandstorm.getLayoutParams().height = width;
        setMargins(imageViewSandstorm, 0, 0, 0, width / 3);
        imageViewSandstorm.setVisibility(View.INVISIBLE);

        gridViewMap = (GridView) view.findViewById(R.id.gridViewMap);

        mapPosition = (ImageView) view.findViewById(R.id.minimapPosition);

        gridViewLevel = (GridView) view.findViewById(R.id.gridViewLevel);

        //3x3 View
        gridViewLevel.getLayoutParams().width = width * 5 / 3;
        gridViewLevel.getLayoutParams().height = width * 5 / 3;
        setMargins(gridViewLevel, -width / 3, 0, 0, 0);

        //5x5 View
        /*gridViewLevel.getLayoutParams().width = width;
        gridViewLevel.getLayoutParams().height = width;
        setMargins(gridViewLevel, 0, 0, 0, width / 3);*/

        gridViewLevel.setAdapter(new LevelAdapter(getContext(), 0));

        title = (TextView) view.findViewById(R.id.title);
        title.setText("Level " + (MainActivity.level + 1));

        stepCounter = (TextView) view.findViewById(R.id.stepCounter);
        stepCounter.setText("" + stepCount);

        timeCounter = (TextView) view.findViewById(R.id.timeCounter);
        timeCounter.setText(("" + time));

        starsSum = (TextView) view.findViewById(R.id.stars);
        starsSum.setText("" + MainActivity.allStars);

        livesCounter = (TextView) view.findViewById(R.id.lives);

        posX = (TextView) view.findViewById(R.id.posX);
        posY = (TextView) view.findViewById(R.id.posY);

        posX.setText("posX: " + x);
        posY.setText("posY: " + y);

        resetLevel = (Button) view.findViewById(R.id.resetLevel);
        resetLevel.setOnClickListener(this);

        cheatButton = (Button) view.findViewById(R.id.cheatButton);
        cheatButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        cheat++;

                        gridViewLevel.getLayoutParams().width = width;
                        gridViewLevel.getLayoutParams().height = width;
                        setMargins(gridViewLevel, 0, 0, 0, width / 3);
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
                        gridViewLevel.getLayoutParams().width = width * 5 / 3;
                        gridViewLevel.getLayoutParams().height = width * 5 / 3;
                        setMargins(gridViewLevel, -width / 3, 0, 0, 0);
                        gridViewLevel.setAdapter(new LevelAdapter(getContext(), 0));
                        break;
                }
                return true;
            }
        });

        controlButton = (ToggleButton) view.findViewById(R.id.controlButton);
        controlButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.swipe = true;
                } else {
                    MainActivity.swipe = false;
                }
            }
        });

        vibrator = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);

        switch (scene) {
            case "f":
                bgm = MediaPlayer.create(getContext(), R.raw.overworld);
                break;
            case "c":
                bgm = MediaPlayer.create(getContext(), R.raw.grand_cave);
                break;
            case "s":
                bgm = MediaPlayer.create(getContext(), R.raw.overworld);
                break;
            case "d":
                bgm = MediaPlayer.create(getContext(), R.raw.overworld);
                break;
        }
        bgm.setLooping(true);

        fire = MediaPlayer.create(getContext(), R.raw.campfire);
        fire.setLooping(true);

        heartbeat = MediaPlayer.create(getContext(), R.raw.heartbeat_breathing);
        heartbeat.setLooping(true);

        imgSword = (ImageView) view.findViewById(R.id.imgSword);
        imgSword.setVisibility(View.INVISIBLE);

        relativeLayout_UI.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            public void onSwipeTop() {
                if (allowInput && contains(LevelArrays.MOVE_DOWN, currentLevel[y][x])) {
                    stepCount++;
                    moveDown();
                    playSound(R.raw.sand1, volumeFX);
                    //vibrator.vibrate(VIBRATE_SHORT);
                } else {
                    //vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }

            public void onSwipeRight() {
                if (allowInput && contains(LevelArrays.MOVE_LEFT, currentLevel[y][x])) {
                    stepCount++;
                    moveLeft();
                    playSound(R.raw.sand1, volumeFX);
                    //vibrator.vibrate(VIBRATE_SHORT);
                } else {
                    //vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }

            public void onSwipeLeft() {
                if (allowInput && contains(LevelArrays.MOVE_RIGHT, currentLevel[y][x])) {
                    stepCount++;
                    moveRight();
                    playSound(R.raw.sand1, volumeFX);
                    //vibrator.vibrate(VIBRATE_SHORT);
                } else {
                    //vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }

            public void onSwipeBottom() {
                if (allowInput && contains(LevelArrays.MOVE_UP, currentLevel[y][x])) {
                    stepCount++;
                    moveUp();
                    playSound(R.raw.sand1, volumeFX);
                    //vibrator.vibrate(VIBRATE_SHORT);
                } else {
                    //vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }
        });

        loadGame();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            bgm.setVolume(volumeBGM, volumeBGM);
            bgm.start();
            fire.setVolume(volumeFire, volumeFire);
            fire.start();
            heartbeat.setVolume(volumeHeart, volumeHeart);
            heartbeat.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!hasDialog) {
            stopTime = false;
        }
        createHandler();
    }

    @Override
    public void onPause() {
        super.onPause();
        saveGame();
        handler.removeCallbacksAndMessages(null);
        try {
            bgm.pause();
            fire.pause();
            heartbeat.pause();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stopTime = true;
    }

    public void reset() {
        livesCounter.setText("" + (MainActivity.lives));
        starsSum.setText("" + MainActivity.allStars);

        if (MainActivity.lives > 0) {
            MainActivity.lives--;
        } else {
            MainMenuFragment mainMenuFragment = new MainMenuFragment();
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
            transaction.replace(R.id.content, mainMenuFragment, "MainMenuFragment");
            transaction.addToBackStack("MainMenuFragment");
            transaction.commit();
        }

        System.out.println(x + ", " + y);
        currentLevel = copyLevel(LevelArrays.LEVEL[MainActivity.level]);

        scene = currentLevel[currentLevel.length - 2][2];
        x = Integer.parseInt(currentLevel[currentLevel.length - 2][0]);
        y = Integer.parseInt(currentLevel[currentLevel.length - 2][1]);

        int randRotation = 0 + (int) (Math.random() * ((3 - 0) + 1));
        for (int i = 0; i < randRotation; i++) {
            currentLevel = rotateLevel(currentLevel);
        }

        System.out.println(x + ", " + y);

        discovered.clear();
        stepsMade.clear();
        stars = 0;
        darkness = 0;
        stepCount = 0;
        time = 0;
        cheat = 0;
        direction = "up";
        stopTime = false;
        volumeBGM = 0.5f;
        volumeFire = 0;
        volumeHeart = 0;
        volumeFX = 1;
        position = new Point(x, y);
        /*discovered.add(new Point(x - 1, y - 1));
        discovered.add(new Point(x, y - 1));
        discovered.add(new Point(x + 1, y - 1));
        discovered.add(new Point(x - 1, y));
        discovered.add(position);
        discovered.add(new Point(x + 1, y));
        discovered.add(new Point(x - 1, y + 1));
        discovered.add(new Point(x, y + 1));
        discovered.add(new Point(x + 1, y + 1));*/
        stepsMade.add(position);

        gridViewLevel.setAdapter(new LevelAdapter(getContext(), 0));
        gridViewMap.setAdapter(new MapAdapter(getContext(), 0));
        imageViewDarkness.setImageResource(PlayerArrays.DARKNESS[darkness]);
        if (!scene.equals("s")) {
            imageViewPlayer.setImageResource(getResources().getIdentifier("p" + scene + direction, "drawable", getContext().getPackageName()));
        } else {
            imageViewPlayer.setImageResource(getResources().getIdentifier("p" + "f" + direction, "drawable", getContext().getPackageName()));
        }

        setNeedle();
        title.setText("Level " + (MainActivity.level + 1));
        stepCounter.setText("" + stepCount);
        timeCounter.setText("" + time);
        posX.setText("posX: " + x);
        posY.setText("posY: " + y);
        hasSword = false;
        imgSword.setVisibility(View.INVISIBLE);

        switch (scene) {
            case "f":
                bgm = MediaPlayer.create(getContext(), R.raw.overworld);
                break;
            case "c":
                bgm = MediaPlayer.create(getContext(), R.raw.grand_cave);
                break;
            case "s":
                bgm = MediaPlayer.create(getContext(), R.raw.overworld);
                break;
            case "d":
                bgm = MediaPlayer.create(getContext(), R.raw.overworld);
                break;
        }

        bgm.setLooping(true);
        bgm.setVolume(volumeBGM, volumeBGM);
        bgm.start();

        fire = MediaPlayer.create(getContext(), R.raw.campfire);
        fire.setLooping(true);
        fire.setVolume(volumeFire, volumeFire);
        fire.start();

        heartbeat = MediaPlayer.create(getContext(), R.raw.heartbeat_breathing);
        heartbeat.setLooping(true);
        heartbeat.setVolume(volumeHeart, volumeHeart);
        heartbeat.start();

        checkDialog();
    }

    public void move() {
        allowInput = false;

        if (!scene.equals("s")) {
            imageViewPlayer.setImageResource(getResources().getIdentifier("p" + scene + direction, "drawable", getContext().getPackageName()));
        } else {
            imageViewPlayer.setImageResource(getResources().getIdentifier("p" + "f" + direction, "drawable", getContext().getPackageName()));
        }

        position = new Point(x, y);

        /*discovered.add(new Point(x - 1, y - 1));
        discovered.add(new Point(x, y - 1));
        discovered.add(new Point(x + 1, y - 1));
        discovered.add(new Point(x - 1, y));
        discovered.add(position);
        discovered.add(new Point(x + 1, y));
        discovered.add(new Point(x - 1, y + 1));
        discovered.add(new Point(x, y + 1));
        discovered.add(new Point(x + 1, y + 1));*/
        stepsMade.add(position);
        stepCounter.setText("" + stepCount);
        posX.setText("posX: " + x);
        posY.setText("posY: " + y);

        if (scene.equals("c") && darkness < 15 && stepCount % Integer.parseInt(currentLevel[currentLevel.length - 2][3]) == 0) {
            darkness++;
            imageViewDarkness.setImageResource(PlayerArrays.DARKNESS[darkness]);
        }
    }

    public void moveDown() {
        if (contains(LevelArrays.MOVE_DOWN, currentLevel[y][x])) {
            direction = "dn";
            y++;
            move();

            //3x3 View
            startAnimation(0, 0, 0, -width / 3);

            //5x5 View
            /*startAnimation(0, 0, 0, -width / 5);*/
        }
    }

    public void moveUp() {
        if (contains(LevelArrays.MOVE_UP, currentLevel[y][x])) {
            direction = "up";
            y--;
            move();

            //3x3 View
            startAnimation(0, 0, 0, width / 3);

            //5x5 View
            /*startAnimation(0, 0, 0, width / 5);*/
        }
    }

    public void moveLeft() {
        if (contains(LevelArrays.MOVE_LEFT, currentLevel[y][x])) {
            direction = "lt";
            x--;
            move();

            //3x3 View
            startAnimation(0, width / 3, 0, 0);

            //5x5 View
            /*startAnimation(0, width / 5, 0, 0);*/
        }
    }

    public void moveRight() {
        if (contains(LevelArrays.MOVE_RIGHT, currentLevel[y][x])) {
            direction = "rt";
            x++;
            move();

            //3x3 View
            startAnimation(0, -width / 3, 0, 0);

            //5x5 View
            /*startAnimation(0, -width / 5, 0, 0);*/
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            relativeLayout_Container.getLayoutParams().height = height + width / 3;
            relativeLayout_Container.getLayoutParams().width = width * 5 / 3;

            relativeLayout_UI.getLayoutParams().width = width;
            relativeLayout_UI.getLayoutParams().height = height;

            gridViewLevel.getLayoutParams().width = width * 5 / 3;
            gridViewLevel.getLayoutParams().height = width * 5 / 3;
            setMargins(gridViewLevel, -width / 3, 0, 0, 0);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            relativeLayout_Container.getLayoutParams().height = width + height / 3;
            relativeLayout_Container.getLayoutParams().width = height * 5 / 3;

            relativeLayout_UI.getLayoutParams().width = height;
            relativeLayout_UI.getLayoutParams().height = width;

            gridViewLevel.getLayoutParams().width = height * 5 / 3;
            gridViewLevel.getLayoutParams().height = height * 5 / 3;
            setMargins(gridViewLevel, 0, -height / 3, 0, 0);
        }

        gridViewLevel.setAdapter(new LevelAdapter(getContext(), 0));
        gridViewMap.setAdapter(new MapAdapter(getContext(), 0));
        imageViewDarkness.setImageResource(PlayerArrays.DARKNESS[darkness]);
    }

    public boolean checkFire() {
        if (contains(LevelArrays.FIRE, currentLevel[y][x])) {
            darkness = 0;
            imageViewDarkness.setImageResource(PlayerArrays.DARKNESS[darkness]);
            volumeFire = 1;
            fire.setVolume(volumeFire, volumeFire);
            if (!fire.isPlaying()) {
                fire.start();
            }
            return true;
        } else if (contains(LevelArrays.FIRE, currentLevel[y - 1][x - 1]) || contains(LevelArrays.FIRE, currentLevel[y - 1][x]) || contains(LevelArrays.FIRE, currentLevel[y - 1][x + 1]) ||
                contains(LevelArrays.FIRE, currentLevel[y][x - 1]) || contains(LevelArrays.FIRE, currentLevel[y][x]) || contains(LevelArrays.FIRE, currentLevel[y][x + 1]) ||
                contains(LevelArrays.FIRE, currentLevel[y + 1][x - 1]) || contains(LevelArrays.FIRE, currentLevel[y + 1][x]) || contains(LevelArrays.FIRE, currentLevel[y + 1][x + 1])) {
            volumeFire = 0.66f;
            fire.setVolume(volumeFire, volumeFire);
            if (!fire.isPlaying()) {
                fire.start();
            }
            return false;
        } else if (contains(LevelArrays.FIRE, currentLevel[y - 2][x - 2]) || contains(LevelArrays.FIRE, currentLevel[y - 2][x - 1]) || contains(LevelArrays.FIRE, currentLevel[y - 2][x]) || contains(LevelArrays.FIRE, currentLevel[y - 2][x + 1]) || contains(LevelArrays.FIRE, currentLevel[y - 2][x + 2]) ||
                contains(LevelArrays.FIRE, currentLevel[y - 1][x - 2]) || contains(LevelArrays.FIRE, currentLevel[y - 1][x - 1]) || contains(LevelArrays.FIRE, currentLevel[y - 1][x]) || contains(LevelArrays.FIRE, currentLevel[y - 1][x + 1]) || contains(LevelArrays.FIRE, currentLevel[y - 1][x + 2]) ||
                contains(LevelArrays.FIRE, currentLevel[y][x - 2]) || contains(LevelArrays.FIRE, currentLevel[y][x - 1]) || contains(LevelArrays.FIRE, currentLevel[y][x]) || contains(LevelArrays.FIRE, currentLevel[y][x + 1]) || contains(LevelArrays.FIRE, currentLevel[y][x + 2]) ||
                contains(LevelArrays.FIRE, currentLevel[y + 1][x - 2]) || contains(LevelArrays.FIRE, currentLevel[y + 1][x - 1]) || contains(LevelArrays.FIRE, currentLevel[y + 1][x]) || contains(LevelArrays.FIRE, currentLevel[y + 1][x + 1]) || contains(LevelArrays.FIRE, currentLevel[y + 1][x + 2]) ||
                contains(LevelArrays.FIRE, currentLevel[y + 2][x - 2]) || contains(LevelArrays.FIRE, currentLevel[y + 2][x - 1]) || contains(LevelArrays.FIRE, currentLevel[y + 2][x]) || contains(LevelArrays.FIRE, currentLevel[y + 2][x + 1]) || contains(LevelArrays.FIRE, currentLevel[y + 2][x + 2])) {
            volumeFire = 0.33f;
            fire.setVolume(volumeFire, volumeFire);
            if (!fire.isPlaying()) {
                fire.start();
            }
            return false;
        } else {
            volumeFire = 0;
            fire.setVolume(volumeFire, volumeFire);
        }
        return false;
    }

    public boolean checkSword() {
        if (contains(LevelArrays.SWORD, currentLevel[y][x])) {
            currentLevel[y][x] = LevelArrays.NORMAL[getIndex(LevelArrays.SWORD, currentLevel[y][x])];
            hasSword = true;
            playSound(R.raw.sword, volumeFX);
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
        for (int i = 0; i < currentLevel[currentLevel.length - 1].length; i++) {
            String str = currentLevel[currentLevel.length - 1][i];
            String[] result = str.split(",");
            if ((x == Integer.parseInt(result[0])) && (y == Integer.parseInt(result[1]))) {
                if (i % 2 == 0) {
                    String[] even = currentLevel[currentLevel.length - 1][i + 1].split(",");
                    x = Integer.parseInt(even[0]);
                    y = Integer.parseInt(even[1]);
                    position = new Point(x, y);
                    /*discovered.add(new Point(x - 1, y - 1));
                    discovered.add(new Point(x, y - 1));
                    discovered.add(new Point(x + 1, y - 1));
                    discovered.add(new Point(x - 1, y));
                    discovered.add(position);
                    discovered.add(new Point(x + 1, y));
                    discovered.add(new Point(x - 1, y + 1));
                    discovered.add(new Point(x, y + 1));
                    discovered.add(new Point(x + 1, y + 1));*/
                    stepsMade.add(position);
                    playSound(R.raw.portal, volumeFX);
                    return true;
                } else {
                    String[] odd = currentLevel[currentLevel.length - 1][i - 1].split(",");
                    x = Integer.parseInt(odd[0]);
                    y = Integer.parseInt(odd[1]);
                    position = new Point(x, y);
                    /*discovered.add(new Point(x - 1, y - 1));
                    discovered.add(new Point(x, y - 1));
                    discovered.add(new Point(x + 1, y - 1));
                    discovered.add(new Point(x - 1, y));
                    discovered.add(position);
                    discovered.add(new Point(x + 1, y));
                    discovered.add(new Point(x - 1, y + 1));
                    discovered.add(new Point(x, y + 1));
                    discovered.add(new Point(x + 1, y + 1));*/
                    stepsMade.add(position);
                    playSound(R.raw.portal, volumeFX);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkMonster() {
        if (contains(LevelArrays.MONSTER, currentLevel[y][x])) {
            if (!hasSword) {
                gameOver();
            } else {
                currentLevel[y][x] = LevelArrays.NORMAL[getIndex(LevelArrays.MONSTER, currentLevel[y][x])];
                playSound(R.raw.monster_death, volumeFX);
            }
            return true;
        }
        return false;
    }

    public boolean checkTrap() {
        if (contains(LevelArrays.TRAP, currentLevel[y][x])) {
            gameOver();
            return true;
        }
        return false;
    }

    public boolean checkDarkness() {
        if (darkness > 14) {
            gameOver();
            return true;
        } else if (darkness < 12) {
            volumeBGM = 0.5f;
            bgm.setVolume(volumeBGM, volumeBGM);
            volumeHeart = 0;
            heartbeat.setVolume(volumeHeart, volumeHeart);
            if (!heartbeat.isPlaying()) {
                heartbeat.start();
            }
        }

        switch (darkness) {
            case 12:
                volumeHeart = 0.33f;
                heartbeat.setVolume(volumeHeart, volumeHeart);
                volumeBGM = 0.4f;
                bgm.setVolume(volumeBGM, volumeBGM);
                if (!heartbeat.isPlaying()) {
                    heartbeat.start();
                }
                break;
            case 13:
                volumeHeart = 0.66f;
                heartbeat.setVolume(volumeHeart, volumeHeart);
                volumeBGM = 0.2f;
                bgm.setVolume(volumeBGM, volumeBGM);
                if (!heartbeat.isPlaying()) {
                    heartbeat.start();
                }
                break;
            case 14:
                volumeHeart = 1;
                heartbeat.setVolume(volumeHeart, volumeHeart);
                volumeBGM = 0;
                bgm.setVolume(volumeBGM, volumeBGM);
                if (!heartbeat.isPlaying()) {
                    heartbeat.start();
                }
                break;
        }
        return false;
    }

    public boolean checkStar() {
        if (contains(LevelArrays.STAR, currentLevel[y][x])) {
            currentLevel[y][x] = LevelArrays.NORMAL[getIndex(LevelArrays.STAR, currentLevel[y][x])];
            stars++;
            playSound(R.raw.star, volumeFX);
            return true;
        }
        return false;
    }

    public void gameOver() {
        stopTime = true;
        playSound(R.raw.death, volumeFX);

        try {
            if (bgm.isPlaying()) {
                bgm.stop();
            }

            if (fire.isPlaying()) {
                fire.stop();
            }

            if (heartbeat.isPlaying()) {
                heartbeat.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageView image2 = new ImageView(getContext());
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
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("You died!");
        builder.setMessage("You died after: " + stepCount + " steps.");
        builder.setView(image2);
        builder.setCancelable(false);
        if (MainActivity.lives > 0) {
            builder.setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    reset();
                }
            });
        }
        builder.setNegativeButton("Level Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LevelSelectFragment levelSelectFragment = new LevelSelectFragment();
                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
                transaction.replace(R.id.content, levelSelectFragment, "LevelSelectFragment");
                transaction.addToBackStack("LevelSelectFragment");
                transaction.commit();
            }
        });
        builder.show();
    }

    public boolean checkWin() {
        if (contains(LevelArrays.GOAL, currentLevel[y][x])) {
            if (MainActivity.lives < 5) {
                MainActivity.lives++;
            }

            stopTime = true;

            if (MainActivity.level + 1 > MainActivity.maxLevel) {
                MainActivity.maxLevel = MainActivity.level + 1;
            }

            int minSteps = Integer.parseInt(currentLevel[currentLevel.length - 2][4]);

            score = stars;

            if (stepCount - minSteps <= 0) {
                score++;
            }

            if (time - minSteps * 5 <= 0) {
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
                    if (MainActivity.starsList.contains(MainActivity.level + ", " + 5)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 4)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 3)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 2)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 1)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else {
                        MainActivity.allStars = MainActivity.allStars + 1;
                    }
                    image.setImageResource(R.drawable.stars_1);
                    break;
                case 2:
                    if (MainActivity.starsList.contains(MainActivity.level + ", " + 5)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 4)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 3)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 2)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 1)) {
                        MainActivity.allStars = MainActivity.allStars + 1;
                    } else {
                        MainActivity.allStars = MainActivity.allStars + 2;
                    }
                    image.setImageResource(R.drawable.stars_2);
                    break;
                case 3:
                    if (MainActivity.starsList.contains(MainActivity.level + ", " + 5)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 4)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 3)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 2)) {
                        MainActivity.allStars = MainActivity.allStars + 1;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 1)) {
                        MainActivity.allStars = MainActivity.allStars + 2;
                    } else {
                        MainActivity.allStars = MainActivity.allStars + 3;
                    }
                    image.setImageResource(R.drawable.stars_3);
                    break;
                case 4:
                    if (MainActivity.starsList.contains(MainActivity.level + ", " + 5)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 4)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 3)) {
                        MainActivity.allStars = MainActivity.allStars + 1;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 2)) {
                        MainActivity.allStars = MainActivity.allStars + 2;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 1)) {
                        MainActivity.allStars = MainActivity.allStars + 3;
                    } else {
                        MainActivity.allStars = MainActivity.allStars + 4;
                    }
                    image.setImageResource(R.drawable.stars_4);
                    break;
                case 5:
                    if (MainActivity.starsList.contains(MainActivity.level + ", " + 5)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 4)) {
                        MainActivity.allStars = MainActivity.allStars + 1;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 3)) {
                        MainActivity.allStars = MainActivity.allStars + 2;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 2)) {
                        MainActivity.allStars = MainActivity.allStars + 3;
                    } else if (MainActivity.starsList.contains(MainActivity.level + ", " + 1)) {
                        MainActivity.allStars = MainActivity.allStars + 4;
                    } else {
                        MainActivity.allStars = MainActivity.allStars + 5;
                    }
                    image.setImageResource(R.drawable.stars_5);
                    break;
            }

            ImageView image2 = new ImageView(getContext());
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
            }

            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.addView(image);
            linearLayout.addView(image2);

            starsSum.setText("" + MainActivity.allStars);
            MainActivity.starsList.add(MainActivity.level + ", " + score);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("You win!");
            builder.setMessage("Steps: " + stepCount + "\n" + "Time: " + time + "\n");
            builder.setView(linearLayout);
            builder.setCancelable(false);
            if (MainActivity.level < LevelArrays.LEVEL.length - 1) {
                builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.level++;
                        reset();
                    }
                });
            }
            builder.setNeutralButton("Level Select", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    LevelSelectFragment levelSelectFragment = new LevelSelectFragment();
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
                    transaction.replace(R.id.content, levelSelectFragment, "LevelSelectFragment");
                    transaction.addToBackStack("LevelSelectFragment");
                    transaction.commit();
                }
            });
            builder.setNegativeButton("Replay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    reset();
                }
            });
            builder.show();

            if (bgm.isPlaying()) {
                bgm.stop();
            }

            if (fire.isPlaying()) {
                fire.stop();
            }

            if (heartbeat.isPlaying()) {
                heartbeat.stop();
            }

            playSound(R.raw.win, volumeFX / 2);
            return true;
        }
        return false;
    }

    public void checkDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        if (MainActivity.maxLevel == MainActivity.level) {
            switch (MainActivity.level) {
                case 0:
                    stopTime = true;
                    hasDialog = true;
                    builder.setTitle("Welcome to Mazecape");
                    builder.setMessage("You find yourself lost in a terrifying maze. You need to get back home before sunset. " +
                            "Move around by swiping or change the input mode to touch at the bottom of the screen. " +
                            "At each stage you will find 3 stars that will help you to proceed later on. Good luck!");
                    builder.setPositiveButton("Let's get started!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            stopTime = false;
                            dialog.dismiss();
                        }
                    });
                    builder.setCancelable(false);
                    builder.create();
                    builder.show();
                    break;
                case 3:
                    stopTime = true;
                    hasDialog = true;
                    builder.setTitle("Monster");
                    builder.setMessage("Oh no a monster is blocking your path. You must find a sword first in order to proceed.");
                    builder.setPositiveButton("I can handle that!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            stopTime = false;
                            dialog.dismiss();
                        }
                    });
                    builder.setCancelable(false);
                    builder.create();
                    builder.show();
                    break;
                case 5:
                    stopTime = true;
                    hasDialog = true;
                    builder.setTitle("Portal");
                    builder.setMessage("Look, there's a portal somewhere. You have to find it. It will take you to unreachable places!");
                    builder.setPositiveButton("Awesome!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            stopTime = false;
                            dialog.dismiss();
                        }
                    });
                    builder.setCancelable(false);
                    builder.create();
                    builder.show();
                    break;
                case 7:
                    stopTime = true;
                    hasDialog = true;
                    builder.setTitle("Cave");
                    builder.setMessage("You entered a grand cave. The deeper you go the darker it gets. So watch your steps and try to find a campfire.");
                    builder.setPositiveButton("Sure!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            stopTime = false;
                            dialog.dismiss();
                        }
                    });
                    builder.setCancelable(false);
                    builder.create();
                    builder.show();
                    break;
                case 8:
                    stopTime = true;
                    hasDialog = true;
                    builder.setTitle("Snow");
                    builder.setMessage("It got cold. The path is frozen and slippery. Watch out!");
                    builder.setPositiveButton("Alright!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            stopTime = false;
                            dialog.dismiss();
                        }
                    });
                    builder.setCancelable(false);
                    builder.create();
                    builder.show();
                    break;
                case 10:
                    stopTime = true;
                    hasDialog = true;
                    builder.setTitle("Desert");
                    builder.setMessage("You entered a wide desert. Try not not to lose your orientation and watch out for sandstorms!");
                    builder.setPositiveButton("Let's not get lost!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            stopTime = false;
                            dialog.dismiss();
                        }
                    });
                    builder.setCancelable(false);
                    builder.create();
                    builder.show();
                    break;
            }
        }
    }

    public String[][] rotateLevel(String[][] oldArray) {
        String[][] newArray = new String[oldArray[0].length + 2][oldArray.length - 2];
        for (int i = 0; i < oldArray.length - 2; i++) {
            for (int k = 0; k < oldArray[i].length; k++) {
                switch (oldArray[i][k].substring(1, 3)) {
                    case "lt":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "up";
                        break;
                    case "rt":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "dn";
                        break;
                    case "up":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "rt";
                        break;
                    case "dn":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "lt";
                        break;
                    case "lr":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "ud";
                        break;
                    case "ud":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "lr";
                        break;
                    case "ur":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "dr";
                        break;
                    case "ul":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "ur";
                        break;
                    case "dr":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "dl";
                        break;
                    case "dl":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "ul";
                        break;
                    case "tu":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "tr";
                        break;
                    case "td":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "tl";
                        break;
                    case "tl":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "tu";
                        break;
                    case "tr":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "td";
                        break;
                    case "cr":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "cr";
                        break;
                    case "xx":
                        newArray[k][oldArray.length - 3 - i] = oldArray[i][k].substring(0, 1) + "xx";
                        break;
                }
            }
        }
        newArray[newArray.length - 2] = oldArray[oldArray.length - 2].clone();
        newArray[newArray.length - 1] = oldArray[oldArray.length - 1].clone();

        int newX = x;
        int newY = y;
        newY = x;
        newX = newArray[0].length - 1 - y;
        x = newX;
        y = newY;

        for (int i = 0; i < stepsMade.size(); i++) {
            newX = stepsMade.get(i).x;
            newY = stepsMade.get(i).y;
            newY = stepsMade.get(i).x;
            newX = newArray[0].length - 1 - stepsMade.get(i).y;
            stepsMade.get(i).x = newX;
            stepsMade.get(i).y = newY;
        }

        for (int i = 0; i < discovered.size(); i++) {
            newX = discovered.get(i).x;
            newY = discovered.get(i).y;
            newY = discovered.get(i).x;
            newX = newArray[0].length - 1 - discovered.get(i).y;
            discovered.get(i).x = newX;
            discovered.get(i).y = newY;
        }

        for (int i = 0; i < newArray[newArray.length - 1].length; i++) {
            String str = newArray[newArray.length - 1][i];
            String[] result = str.split(",");
            newY = Integer.parseInt(result[0]);
            newX = newArray[0].length - 1 - Integer.parseInt(result[1]);
            newArray[newArray.length - 1][i] = newX + "," + newY;
        }

        return newArray;
    }

    public void setNeedle() {
        int goalX = 0;
        int goalY = 0;
        for (int i = 0; i < currentLevel.length - 2; i++) {
            for (int k = 0; k < currentLevel[i].length; k++) {
                if (contains(LevelArrays.GOAL, currentLevel[i][k])) {
                    goalX = k;
                    goalY = i;
                }
            }
        }
        float y = goalY - position.y;
        float x = goalX - position.x;
        float angleRad = (float) Math.atan2(y, x);
        float angleDeg = (float) Math.toDegrees(angleRad);
        float angle = angleDeg + 90;

        if (!(y == 0 && x == 0)) {
            needle.setRotation(angle);
        }
    }

    public void playSound(int sound, float volume) {
        MediaPlayer stepSound = MediaPlayer.create(getContext(), sound);
        stepSound.setVolume(volume, volume);
        stepSound.start();
        stepSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }

    public void startAnimation(float fromX, float toX, float fromY, float toY) {
        final TranslateAnimation translateAnimation = new TranslateAnimation(fromX, toX, fromY, toY);
        translateAnimation.setDuration(200);
        translateAnimation.setInterpolator(getContext(), android.R.anim.linear_interpolator);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                allowInput = false;
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                allowInput = true;
                if (!checkDarkness()) {
                    if (!checkTrap()) {
                        if (!checkMonster()) {
                            if (!checkWin()) {
                                checkFire();
                                checkSword();
                                checkStar();
                                checkPortal();
                                if (scene.equals("d")) {
                                    int randStep = 5 + (int) (Math.random() * ((10 - 5) + 1));
                                    if (stepCount % randStep == 0) {
                                        int randRotation = 1 + (int) (Math.random() * ((3 - 1) + 1));
                                        for (int i = 0; i < randRotation; i++) {
                                            currentLevel = rotateLevel(currentLevel);
                                        }
                                        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, width / 2, width / 2);
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
                                    }
                                }
                            }
                        }
                    }
                }

                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                animation.setDuration(1);
                gridViewLevel.startAnimation(animation);
                gridViewLevel.setAdapter(new LevelAdapter(getContext(), 0));
                gridViewMap.setAdapter(new MapAdapter(getContext(), 0));
                setNeedle();
                isAnimating = false;

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
        try {
            bgm.pause();
            fire.pause();
            heartbeat.pause();
        } catch (Exception e) {
            e.printStackTrace();
        }

        stopTime = true;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Do you really want to reset level?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reset();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                stopTime = false;
                bgm.start();
                fire.start();
                heartbeat.start();
            }
        });
        builder.setNeutralButton("Level Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LevelSelectFragment levelSelectFragment = new LevelSelectFragment();
                FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
                transaction.replace(R.id.content, levelSelectFragment, "LevelSelectFragment");
                transaction.addToBackStack("LevelSelectFragment");
                transaction.commit();
            }
        });
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void createHandler() {
        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                boolean containsTrap = false;
                if (!stopTime) {
                    time += 1;
                    timeCounter.setText("" + time);

                    //Time-Based changes like traps
                    for (int i = 0; i < currentLevel.length - 2; i++) {
                        for (int k = 0; k < currentLevel[i].length; k++) {
                            if (contains(LevelArrays.TRAP, currentLevel[i][k])) {
                                if (contains(LevelArrays.TRAP, currentLevel[y][x])) {
                                    playSound(R.raw.trap_deactivate, volumeFX);
                                    containsTrap = true;
                                } else if (contains(LevelArrays.TRAP, currentLevel[y - 1][x - 1]) || contains(LevelArrays.TRAP, currentLevel[y - 1][x]) || contains(LevelArrays.TRAP, currentLevel[y - 1][x + 1]) ||
                                        contains(LevelArrays.TRAP, currentLevel[y][x - 1]) || contains(LevelArrays.TRAP, currentLevel[y][x]) || contains(LevelArrays.TRAP, currentLevel[y][x + 1]) ||
                                        contains(LevelArrays.TRAP, currentLevel[y + 1][x - 1]) || contains(LevelArrays.TRAP, currentLevel[y + 1][x]) || contains(LevelArrays.TRAP, currentLevel[y + 1][x + 1])) {
                                    playSound(R.raw.trap_deactivate, volumeFX * 2 / 3);
                                    containsTrap = true;
                                } else if (contains(LevelArrays.TRAP, currentLevel[y - 2][x - 2]) || contains(LevelArrays.TRAP, currentLevel[y - 2][x - 1]) || contains(LevelArrays.TRAP, currentLevel[y - 2][x]) || contains(LevelArrays.TRAP, currentLevel[y - 2][x + 1]) || contains(LevelArrays.TRAP, currentLevel[y - 2][x + 2]) ||
                                        contains(LevelArrays.TRAP, currentLevel[y - 1][x - 2]) || contains(LevelArrays.TRAP, currentLevel[y - 1][x - 1]) || contains(LevelArrays.TRAP, currentLevel[y - 1][x]) || contains(LevelArrays.TRAP, currentLevel[y - 1][x + 1]) || contains(LevelArrays.TRAP, currentLevel[y - 1][x + 2]) ||
                                        contains(LevelArrays.TRAP, currentLevel[y][x - 2]) || contains(LevelArrays.TRAP, currentLevel[y][x - 1]) || contains(LevelArrays.TRAP, currentLevel[y][x]) || contains(LevelArrays.TRAP, currentLevel[y][x + 1]) || contains(LevelArrays.TRAP, currentLevel[y][x + 2]) ||
                                        contains(LevelArrays.TRAP, currentLevel[y + 1][x - 2]) || contains(LevelArrays.TRAP, currentLevel[y + 1][x - 1]) || contains(LevelArrays.TRAP, currentLevel[y + 1][x]) || contains(LevelArrays.TRAP, currentLevel[y + 1][x + 1]) || contains(LevelArrays.TRAP, currentLevel[y + 1][x + 2]) ||
                                        contains(LevelArrays.TRAP, currentLevel[y + 2][x - 2]) || contains(LevelArrays.TRAP, currentLevel[y + 2][x - 1]) || contains(LevelArrays.TRAP, currentLevel[y + 2][x]) || contains(LevelArrays.TRAP, currentLevel[y + 2][x + 1]) || contains(LevelArrays.TRAP, currentLevel[y + 2][x + 2])) {
                                    playSound(R.raw.trap_deactivate, volumeFX / 3);
                                    containsTrap = true;
                                }
                                currentLevel[i][k] = LevelArrays.HOLES[getIndex(LevelArrays.TRAP, currentLevel[i][k])];
                            } else if (contains(LevelArrays.HOLES, currentLevel[i][k])) {
                                if (contains(LevelArrays.HOLES, currentLevel[y][x])) {
                                    playSound(R.raw.trap_activate, volumeFX);
                                    containsTrap = true;
                                } else if (contains(LevelArrays.HOLES, currentLevel[y - 1][x - 1]) || contains(LevelArrays.HOLES, currentLevel[y - 1][x]) || contains(LevelArrays.HOLES, currentLevel[y - 1][x + 1]) ||
                                        contains(LevelArrays.HOLES, currentLevel[y][x - 1]) || contains(LevelArrays.HOLES, currentLevel[y][x]) || contains(LevelArrays.HOLES, currentLevel[y][x + 1]) ||
                                        contains(LevelArrays.HOLES, currentLevel[y + 1][x - 1]) || contains(LevelArrays.HOLES, currentLevel[y + 1][x]) || contains(LevelArrays.HOLES, currentLevel[y + 1][x + 1])) {
                                    playSound(R.raw.trap_activate, volumeFX * 2 / 3);
                                    containsTrap = true;
                                } else if (contains(LevelArrays.HOLES, currentLevel[y - 2][x - 2]) || contains(LevelArrays.HOLES, currentLevel[y - 2][x - 1]) || contains(LevelArrays.HOLES, currentLevel[y - 2][x]) || contains(LevelArrays.HOLES, currentLevel[y - 2][x + 1]) || contains(LevelArrays.HOLES, currentLevel[y - 2][x + 2]) ||
                                        contains(LevelArrays.HOLES, currentLevel[y - 1][x - 2]) || contains(LevelArrays.HOLES, currentLevel[y - 1][x - 1]) || contains(LevelArrays.HOLES, currentLevel[y - 1][x]) || contains(LevelArrays.HOLES, currentLevel[y - 1][x + 1]) || contains(LevelArrays.HOLES, currentLevel[y - 1][x + 2]) ||
                                        contains(LevelArrays.HOLES, currentLevel[y][x - 2]) || contains(LevelArrays.HOLES, currentLevel[y][x - 1]) || contains(LevelArrays.HOLES, currentLevel[y][x]) || contains(LevelArrays.HOLES, currentLevel[y][x + 1]) || contains(LevelArrays.HOLES, currentLevel[y][x + 2]) ||
                                        contains(LevelArrays.HOLES, currentLevel[y + 1][x - 2]) || contains(LevelArrays.HOLES, currentLevel[y + 1][x - 1]) || contains(LevelArrays.HOLES, currentLevel[y + 1][x]) || contains(LevelArrays.HOLES, currentLevel[y + 1][x + 1]) || contains(LevelArrays.HOLES, currentLevel[y + 1][x + 2]) ||
                                        contains(LevelArrays.HOLES, currentLevel[y + 2][x - 2]) || contains(LevelArrays.HOLES, currentLevel[y + 2][x - 1]) || contains(LevelArrays.HOLES, currentLevel[y + 2][x]) || contains(LevelArrays.HOLES, currentLevel[y + 2][x + 1]) || contains(LevelArrays.HOLES, currentLevel[y + 2][x + 2])) {
                                    playSound(R.raw.trap_activate, volumeFX / 3);
                                    containsTrap = true;
                                }
                                currentLevel[i][k] = LevelArrays.TRAP[getIndex(LevelArrays.HOLES, currentLevel[i][k])];
                            }
                        }
                    }
                    if (!isAnimating && containsTrap) {
                        gridViewLevel.setAdapter(new LevelAdapter(getContext(), 0));
                        gridViewMap.setAdapter(new MapAdapter(getContext(), 0));
                        checkTrap();
                    }
                    if (scene.equals("f") && (time % 4 == 0)) {
                        darkness++;
                        imageViewDarkness.setImageResource(PlayerArrays.DARKNESS[darkness]);
                        checkDarkness();
                    }
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    public void saveGame() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (controlButton.isChecked()) {
            editor.putBoolean("isChecked", true);
        } else {
            editor.putBoolean("isChecked", false);
        }
        editor.apply();
    }

    public void loadGame() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);

        controlButton.setChecked(sharedPreferences.getBoolean("isChecked", true));

        reset();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resetLevel:
                resetLevel();
                break;
        }
    }
}
