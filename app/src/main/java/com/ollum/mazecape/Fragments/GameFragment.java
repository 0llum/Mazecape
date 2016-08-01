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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.Adapters.LevelAdapter;
import com.ollum.mazecape.Adapters.MapAdapter;
import com.ollum.mazecape.Arrays.LevelArrays;
import com.ollum.mazecape.Arrays.PlayerArrays;
import com.ollum.mazecape.Classes.OnSwipeTouchListener;
import com.ollum.mazecape.R;

import java.util.ArrayList;

public class GameFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    public static TextView posX, posY;
    public static MediaPlayer bgm;
    public static MediaPlayer fire;
    public static MediaPlayer heartbeat;
    public static String[][] currentLevel;
    public static int x;
    public static int y;
    public static String scene;
    public static int steps = 0;
    public static Point position;
    public static ArrayList<Point> stepsMade;
    public static ArrayList<Point> discovered;
    public static float volumeBGM = MainActivity.volumeMusic;
    public static float volumeFX = MainActivity.volumeSound;
    public static float volumeFire = 0;
    public static float volumeHeart = 0;
    RelativeLayout relativeLayout_Container, relativeLayout_UI, relativeLayoutMap;
    LinearLayout navigation;
    GridView gridViewLevel, gridViewMap;
    ImageView imageViewPlayer, imageViewDarkness, imageViewSandstorm, compass, needle, mapPosition, imageStar1, imageStar2, imageStar3;
    Button resetLevel, cheatButton;
    ToggleButton controlButton;
    Vibrator vibrator;
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

        currentLevel = copyLevel(LevelArrays.WORLDS[MainActivity.world][MainActivity.level]);
        x = Integer.parseInt(currentLevel[currentLevel.length - 3][0]);
        y = Integer.parseInt(currentLevel[currentLevel.length - 3][1]);
        scene = currentLevel[currentLevel.length - 3][2];

        for (int i = 0; i < currentLevel.length - 2; i++) {
            for (int k = 0; k < currentLevel[i].length; k++) {
                if (contains(LevelArrays.TRAP_ACTIVE, currentLevel[i][k]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[i][k])) {
                    containsTrap = true;
                }
            }
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

        relativeLayout_Container = (RelativeLayout) view.findViewById(R.id.relativeLayout_Container);
        relativeLayout_Container.getLayoutParams().height = MainActivity.height + MainActivity.width / 3 - MainActivity.height / 8;
        relativeLayout_Container.getLayoutParams().width = MainActivity.width * 5 / 3;

        relativeLayout_UI = (RelativeLayout) view.findViewById(R.id.relativeLayout_UI);
        relativeLayout_UI.getLayoutParams().width = MainActivity.width;
        relativeLayout_UI.getLayoutParams().height = MainActivity.height - MainActivity.height / 8;

        relativeLayoutMap = (RelativeLayout) view.findViewById(R.id.relative_layout_map);

        navigation = (LinearLayout) view.findViewById(R.id.navigation);
        navigation.getLayoutParams().height = (int) (MainActivity.height / 3.55555555);

        compass = (ImageView) view.findViewById(R.id.compass);

        needle = (ImageView) view.findViewById(R.id.needle);

        imageViewPlayer = (ImageView) view.findViewById(R.id.imageViewPlayer);
        imageViewPlayer.getLayoutParams().width = MainActivity.width * 5 / 3;
        imageViewPlayer.getLayoutParams().height = MainActivity.width * 5 / 3;
        setMargins(imageViewPlayer, -MainActivity.width / 3, 0, 0, 0);
        if (!scene.equals("s")) {
            imageViewPlayer.setImageResource(getResources().getIdentifier("p" + scene + direction, "drawable", getContext().getPackageName()));
        } else {
            imageViewPlayer.setImageResource(getResources().getIdentifier("p" + "f" + direction, "drawable", getContext().getPackageName()));
        }
        imageViewDarkness = (ImageView) view.findViewById(R.id.imageViewDarkness);
        imageViewDarkness.getLayoutParams().width = MainActivity.width * 5 / 3;
        imageViewDarkness.getLayoutParams().height = MainActivity.width * 5 / 3;
        setMargins(imageViewDarkness, -MainActivity.width / 3, 0, 0, 0);
        imageViewDarkness.setImageResource(PlayerArrays.DARKNESS[darkness]);

        imageViewSandstorm = (ImageView) view.findViewById(R.id.imageViewSandstorm);
        imageViewSandstorm.getLayoutParams().width = MainActivity.width;
        imageViewSandstorm.getLayoutParams().height = MainActivity.width;
        setMargins(imageViewSandstorm, 0, 0, 0, MainActivity.width / 3);
        imageViewSandstorm.setVisibility(View.INVISIBLE);

        gridViewMap = (GridView) view.findViewById(R.id.gridViewMap);
        gridViewMap.setOnItemClickListener(this);

        mapPosition = (ImageView) view.findViewById(R.id.minimapPosition);

        gridViewLevel = (GridView) view.findViewById(R.id.gridViewLevel);

        //3x3 View
        gridViewLevel.getLayoutParams().width = MainActivity.width * 5 / 3;
        gridViewLevel.getLayoutParams().height = MainActivity.width * 5 / 3;
        setMargins(gridViewLevel, -MainActivity.width / 3, 0, 0, 0);

        //5x5 View
        /*gridViewLevel.getLayoutParams().width = MainActivity.width;
        gridViewLevel.getLayoutParams().height = MainActivity.width;
        setMargins(gridViewLevel, 0, 0, 0, MainActivity.width / 3);*/

        gridViewLevel.setAdapter(new LevelAdapter(getContext(), 0));
        gridViewMap.setAdapter(new MapAdapter(getContext(), 0));

        MainActivity.title.setText("Level " + (MainActivity.world + 1) + "-" + (MainActivity.level + 1));
        MainActivity.livesCounter.setText("");
        MainActivity.stepsCounter.setText("" + steps);
        MainActivity.timeCounter.setText(("" + time));
        MainActivity.starsCounter.setText("" + MainActivity.allStars);

        imageStar1 = (ImageView) view.findViewById(R.id.imageStar1);
        imageStar2 = (ImageView) view.findViewById(R.id.imageStar2);
        imageStar3 = (ImageView) view.findViewById(R.id.imageStar3);

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

                        gridViewLevel.getLayoutParams().width = MainActivity.width;
                        gridViewLevel.getLayoutParams().height = MainActivity.width;
                        setMargins(gridViewLevel, 0, 0, 0, MainActivity.width / 3);
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
                        gridViewLevel.getLayoutParams().width = MainActivity.width * 5 / 3;
                        gridViewLevel.getLayoutParams().height = MainActivity.width * 5 / 3;
                        setMargins(gridViewLevel, -MainActivity.width / 3, 0, 0, 0);
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
                    steps++;
                    moveDown();
                    playSound(R.raw.sand1, MainActivity.volumeSound);
                    //vibrator.vibrate(VIBRATE_SHORT);
                } else {
                    //vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }

            public void onSwipeRight() {
                if (allowInput && contains(LevelArrays.MOVE_LEFT, currentLevel[y][x])) {
                    steps++;
                    moveLeft();
                    playSound(R.raw.sand1, MainActivity.volumeSound);
                    //vibrator.vibrate(VIBRATE_SHORT);
                } else {
                    //vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }

            public void onSwipeLeft() {
                if (allowInput && contains(LevelArrays.MOVE_RIGHT, currentLevel[y][x])) {
                    steps++;
                    moveRight();
                    playSound(R.raw.sand1, MainActivity.volumeSound);
                    //vibrator.vibrate(VIBRATE_SHORT);
                } else {
                    //vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }

            public void onSwipeBottom() {
                if (allowInput && contains(LevelArrays.MOVE_UP, currentLevel[y][x])) {
                    steps++;
                    moveUp();
                    playSound(R.raw.sand1, MainActivity.volumeSound);
                    //vibrator.vibrate(VIBRATE_SHORT);
                } else {
                    //vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }
        });

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                reset();
            }
        });

        requestNewInterstitial();

        loadGame();

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
            MainActivity.stopTime = false;
        }
        createHandler();
    }

    @Override
    public void onPause() {
        super.onPause();
        saveGame();
        MainActivity.timeLayout.setVisibility(View.INVISIBLE);
        MainActivity.stepsLayout.setVisibility(View.INVISIBLE);
        handler.removeCallbacksAndMessages(null);
        try {
            bgm.pause();
            fire.pause();
            heartbeat.pause();
        } catch (Exception e) {
            e.printStackTrace();
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
        }

        currentLevel = copyLevel(LevelArrays.WORLDS[MainActivity.world][MainActivity.level]);

        scene = currentLevel[currentLevel.length - 3][2];
        x = Integer.parseInt(currentLevel[currentLevel.length - 3][0]);
        y = Integer.parseInt(currentLevel[currentLevel.length - 3][1]);

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

        discovered.clear();
        stepsMade.clear();
        stars = 0;
        darkness = 0;
        steps = 0;
        time = 0;
        cheat = 0;
        direction = "up";
        MainActivity.stopTime = false;
        volumeFire = 0;
        volumeHeart = 0;
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
        MainActivity.title.setText("Level " + (MainActivity.world + 1) + "-" + (MainActivity.level + 1));
        MainActivity.stepsCounter.setText("" + steps);
        MainActivity.timeCounter.setText("" + time);
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
        bgm.setVolume(MainActivity.volumeMusic, MainActivity.volumeMusic);
        bgm.start();

        fire = MediaPlayer.create(getContext(), R.raw.campfire);
        fire.setLooping(true);
        fire.setVolume(volumeFire, volumeFire);
        fire.start();

        heartbeat = MediaPlayer.create(getContext(), R.raw.heartbeat_breathing);
        heartbeat.setLooping(true);
        heartbeat.setVolume(volumeHeart, volumeHeart);
        heartbeat.start();

        imageStar1.setImageResource(R.drawable.star_empty);
        imageStar2.setImageResource(R.drawable.star_empty);
        imageStar3.setImageResource(R.drawable.star_empty);

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
        MainActivity.stepsCounter.setText("" + steps);
        posX.setText("posX: " + x);
        posY.setText("posY: " + y);

        if (scene.equals("c") && darkness < 15 && steps % Integer.parseInt(currentLevel[currentLevel.length - 3][3]) == 0) {
            darkness++;
            imageViewDarkness.setImageResource(PlayerArrays.DARKNESS[darkness]);
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
        if (contains(LevelArrays.MOVE_DOWN, currentLevel[y][x])) {
            direction = "dn";
            checkCrack();
            y++;
            move();

            //3x3 View
            startAnimation(0, 0, 0, -MainActivity.width / 3);

            //5x5 View
            /*startAnimation(0, 0, 0, -MainActivity.width / 5);*/
        }
    }

    public void moveUp() {
        if (contains(LevelArrays.MOVE_UP, currentLevel[y][x])) {
            direction = "up";
            checkCrack();
            y--;
            move();

            //3x3 View
            startAnimation(0, 0, 0, MainActivity.width / 3);

            //5x5 View
            /*startAnimation(0, 0, 0, MainActivity.width / 5);*/
        }
    }

    public void moveLeft() {
        if (contains(LevelArrays.MOVE_LEFT, currentLevel[y][x])) {
            direction = "lt";
            checkCrack();
            x--;
            move();

            //3x3 View
            startAnimation(0, MainActivity.width / 3, 0, 0);

            //5x5 View
            /*startAnimation(0, MainActivity.width / 5, 0, 0);*/
        }
    }

    public void moveRight() {
        if (contains(LevelArrays.MOVE_RIGHT, currentLevel[y][x])) {
            direction = "rt";
            checkCrack();
            x++;
            move();

            //3x3 View
            startAnimation(0, -MainActivity.width / 3, 0, 0);

            //5x5 View
            /*startAnimation(0, -MainActivity.width / 5, 0, 0);*/
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            relativeLayout_Container.getLayoutParams().height = MainActivity.height + MainActivity.width / 3;
            relativeLayout_Container.getLayoutParams().width = MainActivity.width * 5 / 3;

            relativeLayout_UI.getLayoutParams().width = MainActivity.width;
            relativeLayout_UI.getLayoutParams().height = MainActivity.height;

            gridViewLevel.getLayoutParams().width = MainActivity.width * 5 / 3;
            gridViewLevel.getLayoutParams().height = MainActivity.width * 5 / 3;
            setMargins(gridViewLevel, -MainActivity.width / 3, 0, 0, 0);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            relativeLayout_Container.getLayoutParams().height = MainActivity.width + MainActivity.height / 3;
            relativeLayout_Container.getLayoutParams().width = MainActivity.height * 5 / 3;

            relativeLayout_UI.getLayoutParams().width = MainActivity.height;
            relativeLayout_UI.getLayoutParams().height = MainActivity.width;

            gridViewLevel.getLayoutParams().width = MainActivity.height * 5 / 3;
            gridViewLevel.getLayoutParams().height = MainActivity.height * 5 / 3;
            setMargins(gridViewLevel, 0, -MainActivity.height / 3, 0, 0);
        }

        gridViewLevel.setAdapter(new LevelAdapter(getContext(), 0));
        gridViewMap.setAdapter(new MapAdapter(getContext(), 0));
        imageViewDarkness.setImageResource(PlayerArrays.DARKNESS[darkness]);
    }

    public boolean checkFire() {
        if (contains(LevelArrays.FIRE, currentLevel[y][x])) {
            darkness = 0;
            imageViewDarkness.setImageResource(PlayerArrays.DARKNESS[darkness]);
            volumeFire = MainActivity.volumeSound;
            fire.setVolume(volumeFire, volumeFire);
            if (!fire.isPlaying()) {
                fire.start();
            }
            return true;
        } else if (contains(LevelArrays.FIRE, currentLevel[y - 1][x - 1]) || contains(LevelArrays.FIRE, currentLevel[y - 1][x]) || contains(LevelArrays.FIRE, currentLevel[y - 1][x + 1]) ||
                contains(LevelArrays.FIRE, currentLevel[y][x - 1]) || contains(LevelArrays.FIRE, currentLevel[y][x]) || contains(LevelArrays.FIRE, currentLevel[y][x + 1]) ||
                contains(LevelArrays.FIRE, currentLevel[y + 1][x - 1]) || contains(LevelArrays.FIRE, currentLevel[y + 1][x]) || contains(LevelArrays.FIRE, currentLevel[y + 1][x + 1])) {
            volumeFire = MainActivity.volumeSound * 0.66f;
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
            volumeFire = MainActivity.volumeSound * 0.33f;
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
        if (contains(LevelArrays.WEAPON, currentLevel[y][x])) {
            currentLevel[y][x] = LevelArrays.NORMAL[getIndex(LevelArrays.WEAPON, currentLevel[y][x])];
            hasSword = true;
            playSound(R.raw.sword, MainActivity.volumeSound);
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
        for (int i = 0; i < currentLevel[currentLevel.length - 2].length; i++) {
            String str = currentLevel[currentLevel.length - 2][i];
            String[] result = str.split(",");
            if ((x == Integer.parseInt(result[0])) && (y == Integer.parseInt(result[1]))) {
                if (i % 2 == 0) {
                    String[] even = currentLevel[currentLevel.length - 2][i + 1].split(",");
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
                    playSound(R.raw.portal, MainActivity.volumeSound);
                    return true;
                } else {
                    String[] odd = currentLevel[currentLevel.length - 2][i - 1].split(",");
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
                    playSound(R.raw.portal, MainActivity.volumeSound);
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
                playSound(R.raw.monster_death, MainActivity.volumeSound);
            }
            return true;
        }
        return false;
    }

    public boolean checkTrap() {
        if (contains(LevelArrays.TRAP_ACTIVE, currentLevel[y][x])) {
            gameOver();
            return true;
        }
        return false;
    }

    public boolean checkCrack() {
        if (contains(LevelArrays.CRACK, currentLevel[y][x])) {
            currentLevel[y][x] = LevelArrays.HOLE[getIndex(LevelArrays.CRACK, currentLevel[y][x])];
            return true;
        }
        return false;
    }

    public boolean checkHole() {
        if (contains(LevelArrays.HOLE, currentLevel[y][x])) {
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
            volumeBGM = MainActivity.volumeMusic;
            volumeHeart = 0;
            bgm.setVolume(volumeBGM, volumeBGM);
            heartbeat.setVolume(volumeHeart, volumeHeart);
            if (!heartbeat.isPlaying()) {
                heartbeat.start();
            }
        }

        switch (darkness) {
            case 12:
                volumeBGM = MainActivity.volumeMusic * 0.66f;
                volumeHeart = MainActivity.volumeSound * 0.33f;
                heartbeat.setVolume(volumeHeart, volumeHeart);
                bgm.setVolume(volumeBGM, volumeBGM);
                if (!heartbeat.isPlaying()) {
                    heartbeat.start();
                }
                break;
            case 13:
                volumeBGM = MainActivity.volumeMusic * 0.33f;
                volumeHeart = MainActivity.volumeSound * 0.66f;
                heartbeat.setVolume(volumeHeart, volumeHeart);
                bgm.setVolume(volumeBGM, volumeBGM);
                if (!heartbeat.isPlaying()) {
                    heartbeat.start();
                }
                break;
            case 14:
                volumeBGM = 0;
                volumeHeart = 1;
                heartbeat.setVolume(volumeHeart, volumeHeart);
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
            playSound(R.raw.star, MainActivity.volumeSound);

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

    public void gameOver() {
        MainActivity.stopTime = true;
        playSound(R.raw.death, MainActivity.volumeSound);

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

        if (darkness > 14) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("If I just had a torch...");
            if (MainActivity.torches > 0) {
                builder.setPositiveButton("Use torch: " + MainActivity.torches, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        darkness = 0;
                        MainActivity.torches--;
                        dialog.dismiss();
                        MainActivity.stopTime = false;
                        imageViewDarkness.setImageResource(PlayerArrays.DARKNESS[darkness]);
                    }
                });
            } else {
                builder.setPositiveButton("Buy torch: 20 Stars", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (MainActivity.allStars >= 20) {
                            MainActivity.allStars -= 20;
                            MainActivity.starsCounter.setText("" + MainActivity.allStars);
                            darkness = 0;
                            dialog.dismiss();
                            MainActivity.stopTime = false;
                            imageViewDarkness.setImageResource(PlayerArrays.DARKNESS[darkness]);
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
            builder.setNegativeButton("No, thanks!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.resetCount++;
                    dialog.dismiss();

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
                    builder.setMessage("You died after: " + steps + " steps.");
                    builder.setView(image2);
                    builder.setCancelable(false);
                    if (MainActivity.lives > 0) {
                        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (mInterstitialAd.isLoaded() && MainActivity.resetCount % 3 == 0) {
                                    mInterstitialAd.show();
                                } else {
                                    reset();
                                }
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
            });
            builder.setCancelable(false);
            builder.create();
            builder.show();
        } else {
            MainActivity.resetCount++;
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
            builder.setMessage("You died after: " + steps + " steps.");
            builder.setView(image2);
            builder.setCancelable(false);
            if (MainActivity.lives > 0) {
                builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mInterstitialAd.isLoaded() && MainActivity.resetCount % 3 == 0) {
                            mInterstitialAd.show();
                        } else {
                            reset();
                        }
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
    }

    public boolean checkWin() {
        if (contains(LevelArrays.GOAL, currentLevel[y][x])) {
            MainActivity.resetCount++;
            if (MainActivity.lives < 5) {
                MainActivity.lives++;
            }

            MainActivity.stopTime = true;

            if ((MainActivity.level < LevelArrays.WORLDS[MainActivity.world].length - 1) && (MainActivity.level + 1 > MainActivity.maxLevel)) {
                MainActivity.maxLevel = MainActivity.level + 1;
            } else if ((MainActivity.level == LevelArrays.WORLDS[MainActivity.world].length - 1) && (MainActivity.world + 1 > MainActivity.maxWorld) && (MainActivity.world < LevelArrays.WORLDS.length - 1)) {
                MainActivity.maxWorld = MainActivity.world + 1;
                MainActivity.maxLevel = 0;
            }

            int minSteps = Integer.parseInt(currentLevel[currentLevel.length - 3][4]);

            score = stars;

            if (steps - minSteps <= 0) {
                score++;
            }

            if (time - minSteps * 2 <= 0) {
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
                        MainActivity.allStars = MainActivity.allStars + 1;
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
                        MainActivity.allStars = MainActivity.allStars + 1;
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
                        }
                    } else {
                        MainActivity.allStars = MainActivity.allStars + 2;
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
                        MainActivity.allStars = MainActivity.allStars + 1;
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
                        }
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 1)) {
                        MainActivity.allStars = MainActivity.allStars + 2;
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
                        }
                    } else {
                        MainActivity.allStars = MainActivity.allStars + 3;
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
                        MainActivity.allStars = MainActivity.allStars + 1;
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
                        }
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 2)) {
                        MainActivity.allStars = MainActivity.allStars + 2;
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
                        }
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 1)) {
                        MainActivity.allStars = MainActivity.allStars + 3;
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
                        }
                    } else {
                        MainActivity.allStars = MainActivity.allStars + 4;
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
                        }
                    }
                    image.setImageResource(R.drawable.stars_4);
                    break;
                case 5:
                    if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 5)) {
                        MainActivity.allStars = MainActivity.allStars;
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 4)) {
                        MainActivity.allStars = MainActivity.allStars + 1;
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
                        }
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 3)) {
                        MainActivity.allStars = MainActivity.allStars + 2;
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
                        }
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 2)) {
                        MainActivity.allStars = MainActivity.allStars + 3;
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
                        }
                    } else if (MainActivity.starsList.contains(MainActivity.world + ", " + MainActivity.level + ", " + 1)) {
                        MainActivity.allStars = MainActivity.allStars + 4;
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
                        }
                    } else {
                        MainActivity.allStars = MainActivity.allStars + 5;
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
                        }
                    }
                    image.setImageResource(R.drawable.stars_5);
                    break;
            }

            MainActivity.worldStars = new int[]{
                    MainActivity.world1Stars, MainActivity.world2Stars, MainActivity.world3Stars, MainActivity.world4Stars
            };

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

            MainActivity.starsCounter.setText("" + MainActivity.allStars);
            MainActivity.starsList.add(MainActivity.world + ", " + MainActivity.level + ", " + score);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("You win!");
            builder.setMessage("Steps: " + steps + "\n" + "Time: " + time + "\n");
            builder.setView(linearLayout);
            builder.setCancelable(false);
            if (MainActivity.level < LevelArrays.WORLDS[MainActivity.world].length - 1) {
                builder.setPositiveButton("Next Level", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.level++;
                        if (mInterstitialAd.isLoaded() && MainActivity.resetCount % 3 == 0) {
                            mInterstitialAd.show();
                        } else {
                            reset();
                        }
                    }
                });
            } else {
                builder.setPositiveButton("Next World", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.level = 0;
                        MainActivity.world++;
                        WorldSelectFragment worldSelectFragment = new WorldSelectFragment();
                        FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                        transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
                        transaction.replace(R.id.content, worldSelectFragment, "WorldSelectFragment");
                        transaction.addToBackStack("WorldSelectFragment");
                        transaction.commit();
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
                    if (mInterstitialAd.isLoaded() && MainActivity.resetCount % 3 == 0) {
                        mInterstitialAd.show();
                    } else {
                        reset();
                    }
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

            playSound(R.raw.win, MainActivity.volumeSound / 2);
            return true;
        }
        return false;
    }

    public void checkDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        if (MainActivity.maxWorld == MainActivity.world) {
            if (MainActivity.maxLevel == MainActivity.level) {
                switch (MainActivity.world) {
                    case 0:
                        switch (MainActivity.level) {
                            case 0:
                                MainActivity.stopTime = true;
                                hasDialog = true;
                                builder.setTitle("Welcome to Mazecape");
                                builder.setMessage("You find yourself lost in a terrifying maze. You need to get back home before sunset. " +
                                        "Move around by swiping or change the input mode to touch at the bottom of the screen. " +
                                        "At each stage you will find 3 stars that will help you to proceed later on. Good luck!");
                                builder.setPositiveButton("Let's get started!", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        MainActivity.stopTime = false;
                                        hasDialog = false;
                                        dialog.dismiss();
                                    }
                                });
                                builder.setCancelable(false);
                                builder.create();
                                builder.show();
                                break;
                            case 3:
                                MainActivity.stopTime = true;
                                hasDialog = true;
                                builder.setTitle("Monster");
                                builder.setMessage("Oh no a monster is blocking your path. You must find a sword first in order to proceed.");
                                builder.setPositiveButton("I can handle that!", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        MainActivity.stopTime = false;
                                        hasDialog = false;
                                        dialog.dismiss();
                                    }
                                });
                                builder.setCancelable(false);
                                builder.create();
                                builder.show();
                                break;
                            case 5:
                                MainActivity.stopTime = true;
                                hasDialog = true;
                                builder.setTitle("Portal");
                                builder.setMessage("Look, there's a portal somewhere. You have to find it. It will take you to unreachable places!");
                                builder.setPositiveButton("Awesome!", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
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
                                builder.setTitle("Cave");
                                builder.setMessage("You entered a grand cave. The deeper you go the darker it gets. So watch your steps and try to find a campfire.");
                                builder.setPositiveButton("Sure!", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
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
                                builder.setTitle("Snow");
                                builder.setMessage("It got cold. The path is frozen and slippery. Watch out!");
                                builder.setPositiveButton("Alright!", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
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
                                builder.setTitle("Desert");
                                builder.setMessage("You entered a wide desert. Try not not to lose your orientation and watch out for sandstorms!");
                                builder.setPositiveButton("Let's not get lost!", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
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

        //rotate level by 90 degrees ccw
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

        //rotate steps made
        for (int i = 0; i < stepsMade.size(); i++) {
            newX = stepsMade.get(i).x;
            newY = stepsMade.get(i).y;
            newY = stepsMade.get(i).x;
            newX = newArray[0].length - 1 - stepsMade.get(i).y;
            stepsMade.get(i).x = newX;
            stepsMade.get(i).y = newY;
        }

        //rotate discovered paths
        for (int i = 0; i < discovered.size(); i++) {
            newX = discovered.get(i).x;
            newY = discovered.get(i).y;
            newY = discovered.get(i).x;
            newX = newArray[0].length - 1 - discovered.get(i).y;
            discovered.get(i).x = newX;
            discovered.get(i).y = newY;
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
        for (int i = 0; i < currentLevel.length - 3; i++) {
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
                        if (!checkHole()) {
                            if (!checkMonster()) {
                                if (!checkWin()) {
                                    checkFire();
                                    checkSword();
                                    checkStar();
                                    checkPortal();
                                    if (scene.equals("d")) {
                                        int randStep = 5 + (int) (Math.random() * ((10 - 5) + 1));
                                        if (steps % randStep == 0) {
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
                                        }
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

        MainActivity.stopTime = true;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Do you really want to reset level?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.resetCount++;
                if (mInterstitialAd.isLoaded() && MainActivity.resetCount % 3 == 0) {
                    mInterstitialAd.show();
                } else {
                    reset();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.stopTime = false;
                bgm.start();
                fire.start();
                heartbeat.start();
            }
        });
        builder.setNeutralButton("Level Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.resetCount++;
                MainActivity.livesCounter.setText("" + MainActivity.lives);
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
                    if (trapVisible) {
                        if ((time + 2) % 3 == 0) {
                            for (int i = 0; i < currentLevel[currentLevel.length - 1].length; i++) {
                                String str = currentLevel[currentLevel.length - 1][i];
                                String[] result = str.split(",");
                                int trapX = Integer.parseInt(result[0]);
                                int trapY = Integer.parseInt(result[1]);
                                if (contains(LevelArrays.TRAP_ACTIVE, currentLevel[trapY][trapX])) {
                                    currentLevel[trapY][trapX] = LevelArrays.TRAP_INACTIVE[getIndex(LevelArrays.TRAP_ACTIVE, currentLevel[trapY][trapX])];
                                    trapActive = false;
                                }
                            }
                            if (!trapActive) {
                                playSound(R.raw.trap_deactivate, MainActivity.volumeSound);
                            }
                        } else if (time % 3 == 0) {
                            for (int i = 0; i < currentLevel[currentLevel.length - 1].length; i++) {
                                String str = currentLevel[currentLevel.length - 1][i];
                                String[] result = str.split(",");
                                int trapX = Integer.parseInt(result[0]);
                                int trapY = Integer.parseInt(result[1]);
                                if (contains(LevelArrays.TRAP_INACTIVE, currentLevel[trapY][trapX])) {
                                    currentLevel[trapY][trapX] = LevelArrays.TRAP_ACTIVE[getIndex(LevelArrays.TRAP_INACTIVE, currentLevel[trapY][trapX])];
                                    trapActive = true;
                                }
                            }
                            if (trapActive) {
                                playSound(R.raw.trap_activate, MainActivity.volumeSound);
                            }
                        }




                        /*for (int i = 0; i < currentLevel.length - 3; i++) {
                            for (int k = 0; k < currentLevel[i].length; k++) {
                                if (contains(LevelArrays.TRAP_ACTIVE, currentLevel[i][k]) && (time + 2) % 3 == 0) {
                                    if (contains(LevelArrays.TRAP_ACTIVE, currentLevel[y][x])) {
                                        playSound(R.raw.trap_deactivate, MainActivity.volumeSound);
                                        Log.d("debug", "1");
                                        trapVisible = true;
                                    } else if (contains(LevelArrays.TRAP_ACTIVE, currentLevel[y - 1][x - 1]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y - 1][x]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y - 1][x + 1]) ||
                                            contains(LevelArrays.TRAP_ACTIVE, currentLevel[y][x - 1]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y][x]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y][x + 1]) ||
                                            contains(LevelArrays.TRAP_ACTIVE, currentLevel[y + 1][x - 1]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y + 1][x]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y + 1][x + 1])) {
                                        playSound(R.raw.trap_deactivate, MainActivity.volumeSound * 2 / 3);
                                        Log.d("debug", "2");
                                        trapVisible = true;
                                    }
                                *//*else if (contains(LevelArrays.TRAP_ACTIVE, currentLevel[y - 2][x - 2]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y - 2][x - 1]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y - 2][x]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y - 2][x + 1]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y - 2][x + 2]) ||
                                        contains(LevelArrays.TRAP_ACTIVE, currentLevel[y - 1][x - 2]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y - 1][x - 1]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y - 1][x]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y - 1][x + 1]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y - 1][x + 2]) ||
                                        contains(LevelArrays.TRAP_ACTIVE, currentLevel[y][x - 2]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y][x - 1]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y][x]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y][x + 1]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y][x + 2]) ||
                                        contains(LevelArrays.TRAP_ACTIVE, currentLevel[y + 1][x - 2]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y + 1][x - 1]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y + 1][x]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y + 1][x + 1]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y + 1][x + 2]) ||
                                        contains(LevelArrays.TRAP_ACTIVE, currentLevel[y + 2][x - 2]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y + 2][x - 1]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y + 2][x]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y + 2][x + 1]) || contains(LevelArrays.TRAP_ACTIVE, currentLevel[y + 2][x + 2])) {
                                    playSound(R.raw.trap_deactivate, MainActivity.volumeSound / 3);
                                    trapVisible = true;
                                }*//*
                                    currentLevel[i][k] = LevelArrays.TRAP_INACTIVE[getIndex(LevelArrays.TRAP_ACTIVE, currentLevel[i][k])];
                                } else if (contains(LevelArrays.TRAP_INACTIVE, currentLevel[i][k]) && time % 3 == 0) {
                                    if (contains(LevelArrays.TRAP_INACTIVE, currentLevel[y][x])) {
                                        playSound(R.raw.trap_activate, MainActivity.volumeSound);
                                        Log.d("debug", "3");
                                        trapVisible = true;
                                    } else if (contains(LevelArrays.TRAP_INACTIVE, currentLevel[y - 1][x - 1]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y - 1][x]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y - 1][x + 1]) ||
                                            contains(LevelArrays.TRAP_INACTIVE, currentLevel[y][x - 1]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y][x]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y][x + 1]) ||
                                            contains(LevelArrays.TRAP_INACTIVE, currentLevel[y + 1][x - 1]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y + 1][x]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y + 1][x + 1])) {
                                        playSound(R.raw.trap_activate, MainActivity.volumeSound * 2 / 3);
                                        Log.d("debug", "4");
                                        trapVisible = true;
                                    }
                                *//*else if (contains(LevelArrays.TRAP_INACTIVE, currentLevel[y - 2][x - 2]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y - 2][x - 1]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y - 2][x]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y - 2][x + 1]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y - 2][x + 2]) ||
                                        contains(LevelArrays.TRAP_INACTIVE, currentLevel[y - 1][x - 2]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y - 1][x - 1]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y - 1][x]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y - 1][x + 1]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y - 1][x + 2]) ||
                                        contains(LevelArrays.TRAP_INACTIVE, currentLevel[y][x - 2]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y][x - 1]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y][x]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y][x + 1]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y][x + 2]) ||
                                        contains(LevelArrays.TRAP_INACTIVE, currentLevel[y + 1][x - 2]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y + 1][x - 1]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y + 1][x]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y + 1][x + 1]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y + 1][x + 2]) ||
                                        contains(LevelArrays.TRAP_INACTIVE, currentLevel[y + 2][x - 2]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y + 2][x - 1]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y + 2][x]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y + 2][x + 1]) || contains(LevelArrays.TRAP_INACTIVE, currentLevel[y + 2][x + 2])) {
                                    playSound(R.raw.trap_activate, MainActivity.volumeSound / 3);
                                    trapVisible = true;
                                }*//*
                                    currentLevel[i][k] = LevelArrays.TRAP_ACTIVE[getIndex(LevelArrays.TRAP_INACTIVE, currentLevel[i][k])];
                                }
                            }
                        }*/
                    }
                    if (!isAnimating && trapVisible) {
                        gridViewLevel.setAdapter(new LevelAdapter(getContext(), 0));
                        gridViewMap.setAdapter(new MapAdapter(getContext(), 0));
                        checkTrap();
                    }
                    if (scene.equals("f") && (time % Integer.parseInt(currentLevel[currentLevel.length - 3][3]) == 0)) {
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Do you like to reveal the map? 30 Stars");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (MainActivity.allStars >= 30) {
                    MainActivity.allStars -= 30;
                    for (int i = 0; i < GameFragment.currentLevel.length - 3; i++) {
                        for (int k = 0; k < GameFragment.currentLevel[i].length - 1; k++) {
                            Point point = new Point(k, i);
                            discovered.add(point);
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
