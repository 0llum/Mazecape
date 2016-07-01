package com.ollum.mazecape;

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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class Level extends AppCompatActivity {

    public static int width;
    public static int height;
    public static boolean swipe = false;
    final int VIBRATE_SHORT = 10;
    final int VIBRATE_MEDIUM = 500;
    final int VIBRATE_LONG = 1000;
    RelativeLayout relativeLayout_Container, relativeLayout_UI;
    GridView gridViewLevel, gridViewPlayer, gridViewMap;
    ImageView compass, needle, mapPosition;
    TextView title, stepCounter, timeCounter, posX, posY;
    Button resetLevel, cheatButton;
    ToggleButton controlButton;
    Vibrator vibrator;
    Display display;
    Point size;
    MediaPlayer bgm;
    MediaPlayer fire;
    MediaPlayer heartbeat;
    int statusBarHeight;
    int level = 0;
    String[][] currentLevel;
    int x;
    int y;
    String scene;
    int stars = 0;
    int darkness = 0;
    int stepCount = 0;
    int time = 0;
    int cheat = 0;
    int score = 0;
    Point position;
    ArrayList<Point> stepsMade;
    ArrayList<Point> discovered;
    boolean isAnimating = false;
    boolean stopTime = false;
    boolean allowInput = true;
    boolean hasSword = false;
    ImageView imgSword;
    float volumeBGM = 0.5f;
    float volumeFire = 0;
    float volumeHeart = 0;
    float volumeFX = 1;
    String direction = "";
    private Handler handler;

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("debug", "onCreate");
        setContentView(R.layout.level);

        stepsMade = new ArrayList<>();
        discovered = new ArrayList<>();

        currentLevel = copyLevel(LevelArrays.LEVEL[level]);
        x = Integer.parseInt(currentLevel[currentLevel.length - 2][0]);
        y = Integer.parseInt(currentLevel[currentLevel.length - 2][1]);
        scene = currentLevel[currentLevel.length - 2][2];

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

        display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        statusBarHeight = getStatusBarHeight();
        width = size.x;
        height = size.y - statusBarHeight;

        relativeLayout_Container = (RelativeLayout) findViewById(R.id.relativeLayout_Container);
        relativeLayout_Container.getLayoutParams().height = height + width / 3;
        relativeLayout_Container.getLayoutParams().width = width * 5 / 3;

        relativeLayout_UI = (RelativeLayout) findViewById(R.id.relativeLayout_UI);
        relativeLayout_UI.getLayoutParams().width = width;
        relativeLayout_UI.getLayoutParams().height = height;

        compass = (ImageView) findViewById(R.id.compass);
        compass.getLayoutParams().width = width / 2;
        compass.getLayoutParams().height = width / 2;

        needle = (ImageView) findViewById(R.id.needle);
        needle.getLayoutParams().width = width / 2;
        needle.getLayoutParams().height = width / 2;

        gridViewMap = (GridView) findViewById(R.id.gridViewMap);
        gridViewMap.getLayoutParams().width = width / 2;
        gridViewMap.getLayoutParams().height = width / 2;

        mapPosition = (ImageView) findViewById(R.id.minimapPosition);
        mapPosition.setX(gridViewMap.getLayoutParams().width / 10);
        mapPosition.setY(gridViewMap.getLayoutParams().height / 10);
        Log.d("debug", "mapPosition: " + mapPosition.getX() + ", " + mapPosition.getY());

        gridViewLevel = (GridView) findViewById(R.id.gridViewLevel);

        //3x3 View
        gridViewLevel.getLayoutParams().width = width * 5 / 3;
        gridViewLevel.getLayoutParams().height = width * 5 / 3;
        setMargins(gridViewLevel, -width / 3, 0, 0, 0);

        //5x5 View
        /*gridViewLevel.getLayoutParams().width = width;
        gridViewLevel.getLayoutParams().height = width;
        setMargins(gridViewLevel, 0, 0, 0, width / 3);*/

        gridViewLevel.setAdapter(new LevelAdapter(this, 0));

        gridViewPlayer = (GridView) findViewById(R.id.gridViewPlayer);
        gridViewPlayer.getLayoutParams().width = width * 5 / 3;
        gridViewPlayer.getLayoutParams().height = width * 5 / 3;
        setMargins(gridViewPlayer, -width / 3, 0, 0, 0);
        gridViewPlayer.setAdapter(new PlayerAdapter(this));

        title = (TextView) findViewById(R.id.title);
        title.setText("Level " + (level + 1));

        stepCounter = (TextView) findViewById(R.id.stepCounter);
        stepCounter.setText("Steps: " + stepCount);

        timeCounter = (TextView) findViewById(R.id.timeCounter);
        timeCounter.setText(("" + time));

        posX = (TextView) findViewById(R.id.posX);
        posY = (TextView) findViewById(R.id.posY);

        posX.setText("posX: " + x);
        posY.setText("posY: " + y);

        resetLevel = (Button) findViewById(R.id.resetLevel);

        cheatButton = (Button) findViewById(R.id.cheatButton);
        cheatButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        cheat++;

                        gridViewLevel.getLayoutParams().width = width;
                        gridViewLevel.getLayoutParams().height = width;
                        setMargins(gridViewLevel, 0, 0, 0, width / 3);
                        gridViewLevel.setAdapter(new LevelAdapter(getApplicationContext(), 0));

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

                        gridViewMap.setAdapter(new MapAdapter(getApplicationContext(), 0));

                        break;
                    case MotionEvent.ACTION_UP:
                        gridViewLevel.getLayoutParams().width = width * 5 / 3;
                        gridViewLevel.getLayoutParams().height = width * 5 / 3;
                        setMargins(gridViewLevel, -width / 3, 0, 0, 0);
                        gridViewLevel.setAdapter(new LevelAdapter(getApplicationContext(), 0));
                        break;
                }
                return true;
            }
        });

        controlButton = (ToggleButton) findViewById(R.id.controlButton);
        controlButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    swipe = true;
                } else {
                    swipe = false;
                }
            }
        });

        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        switch (scene) {
            case "f":
                bgm = MediaPlayer.create(getApplicationContext(), R.raw.overworld);
                break;
            case "c":
                bgm = MediaPlayer.create(getApplicationContext(), R.raw.grand_cave);
                break;
            case "s":
                bgm = MediaPlayer.create(getApplicationContext(), R.raw.overworld);
                break;
            case "d":
                bgm = MediaPlayer.create(getApplicationContext(), R.raw.overworld);
                break;
        }
        bgm.setLooping(true);

        fire = MediaPlayer.create(getApplicationContext(), R.raw.campfire);
        fire.setLooping(true);

        heartbeat = MediaPlayer.create(getApplicationContext(), R.raw.heartbeat_breathing);
        heartbeat.setLooping(true);

        imgSword = (ImageView) findViewById(R.id.imgSword);
        imgSword.setVisibility(View.INVISIBLE);

        relativeLayout_UI.setOnTouchListener(new OnSwipeTouchListener(Level.this) {
            public void onSwipeTop() {
                if (allowInput && contains(LevelArrays.MOVE_DOWN, currentLevel[y][x])) {
                    stepCount++;
                    moveDown();
                    playSound(R.raw.sand1, volumeFX);
                    vibrator.vibrate(VIBRATE_SHORT);
                } else {
                    vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }

            public void onSwipeRight() {
                if (allowInput && contains(LevelArrays.MOVE_LEFT, currentLevel[y][x])) {
                    stepCount++;
                    moveLeft();
                    playSound(R.raw.sand1, volumeFX);
                    vibrator.vibrate(VIBRATE_SHORT);
                } else {
                    vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }

            public void onSwipeLeft() {
                if (allowInput && contains(LevelArrays.MOVE_RIGHT, currentLevel[y][x])) {
                    stepCount++;
                    moveRight();
                    playSound(R.raw.sand1, volumeFX);
                    vibrator.vibrate(VIBRATE_SHORT);
                } else {
                    vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }

            public void onSwipeBottom() {
                if (allowInput && contains(LevelArrays.MOVE_UP, currentLevel[y][x])) {
                    stepCount++;
                    moveUp();
                    playSound(R.raw.sand1, volumeFX);
                    vibrator.vibrate(VIBRATE_SHORT);
                } else {
                    vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }
        });

        loadGame();
        createHandler();
    }

    public void reset() {
        Log.d("debug", "reset");
        currentLevel = copyLevel(LevelArrays.LEVEL[level]);
        x = Integer.parseInt(currentLevel[currentLevel.length - 2][0]);
        y = Integer.parseInt(currentLevel[currentLevel.length - 2][1]);
        scene = currentLevel[currentLevel.length - 2][2];

        discovered.clear();
        stepsMade.clear();
        stars = 0;
        darkness = 0;
        stepCount = 0;
        time = 0;
        cheat = 0;
        stopTime = false;
        volumeBGM = 0.5f;
        volumeFire = 0;
        volumeHeart = 0;
        volumeFX = 1;
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

        gridViewLevel.setAdapter(new LevelAdapter(getApplicationContext(), 0));
        gridViewMap.setAdapter(new MapAdapter(getApplicationContext(), 0));
        gridViewPlayer.setAdapter(new PlayerAdapter(getApplicationContext()));
        setNeedle();
        title.setText("Level " + (level + 1));
        stepCounter.setText("Steps: " + stepCount);
        timeCounter.setText("" + time);
        posX.setText("posX: " + x);
        posY.setText("posY: " + y);
        hasSword = false;
        imgSword.setVisibility(View.INVISIBLE);

        switch (scene) {
            case "f":
                bgm = MediaPlayer.create(getApplicationContext(), R.raw.overworld);
                break;
            case "c":
                bgm = MediaPlayer.create(getApplicationContext(), R.raw.grand_cave);
                break;
            case "s":
                bgm = MediaPlayer.create(getApplicationContext(), R.raw.overworld);
                break;
            case "d":
                bgm = MediaPlayer.create(getApplicationContext(), R.raw.overworld);
                break;
        }

        bgm.setLooping(true);
        bgm.setVolume(volumeBGM, volumeBGM);
        bgm.start();

        fire = MediaPlayer.create(getApplicationContext(), R.raw.campfire);
        fire.setLooping(true);
        fire.setVolume(volumeFire, volumeFire);
        fire.start();

        heartbeat = MediaPlayer.create(getApplicationContext(), R.raw.heartbeat_breathing);
        heartbeat.setLooping(true);
        heartbeat.setVolume(volumeHeart, volumeHeart);
        heartbeat.start();
    }

    public void move() {
        Log.d("debug", "move");
        allowInput = false;

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
        stepCounter.setText("Steps: " + stepCount);
        posX.setText("posX: " + x);
        posY.setText("posY: " + y);

        if (darkness < 15 && stepCount % Integer.parseInt(currentLevel[currentLevel.length - 2][3]) == 0) {
            darkness++;
            gridViewPlayer.setAdapter(new PlayerAdapter(getApplicationContext()));
        }
    }

    public void moveDown() {
        if (contains(LevelArrays.MOVE_DOWN, currentLevel[y][x])) {
            direction = "down";
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
            direction = "left";
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
            direction = "right";
            x++;
            move();

            //3x3 View
            startAnimation(0, -width / 3, 0, 0);

            //5x5 View
            /*startAnimation(0, -width / 5, 0, 0);*/
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("debug", "onResume");
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
        stopTime = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug", "onPause");
        saveGame();
        try {
            bgm.pause();
            fire.pause();
            heartbeat.pause();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stopTime = true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("debug", "onConfigurationChanged");
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            relativeLayout_Container.getLayoutParams().height = height + width / 3;
            relativeLayout_Container.getLayoutParams().width = width * 5 / 3;

            relativeLayout_UI.getLayoutParams().width = width;
            relativeLayout_UI.getLayoutParams().height = height;

            gridViewLevel.getLayoutParams().width = width * 5 / 3;
            gridViewLevel.getLayoutParams().height = width * 5 / 3;
            setMargins(gridViewLevel, -width / 3, 0, 0, 0);

            gridViewPlayer.getLayoutParams().width = width * 5 / 3;
            gridViewPlayer.getLayoutParams().height = width * 5 / 3;
            setMargins(gridViewPlayer, -width / 3, 0, 0, 0);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            relativeLayout_Container.getLayoutParams().height = width + height / 3;
            relativeLayout_Container.getLayoutParams().width = height * 5 / 3;

            relativeLayout_UI.getLayoutParams().width = height;
            relativeLayout_UI.getLayoutParams().height = width;

            gridViewLevel.getLayoutParams().width = height * 5 / 3;
            gridViewLevel.getLayoutParams().height = height * 5 / 3;
            setMargins(gridViewLevel, 0, -height / 3, 0, 0);

            gridViewPlayer.getLayoutParams().width = height * 5 / 3;
            gridViewPlayer.getLayoutParams().height = height * 5 / 3;
            setMargins(gridViewPlayer, 0, -height / 3, 0, 0);
        }

        gridViewLevel.setAdapter(new LevelAdapter(this, 0));
        gridViewMap.setAdapter(new MapAdapter(this, 0));
        gridViewPlayer.setAdapter(new PlayerAdapter(this));
    }

    public boolean checkFire() {
        Log.d("debug", "checkFire");
        if (contains(LevelArrays.FIRE, currentLevel[y][x])) {
            darkness = 0;
            gridViewPlayer.setAdapter(new PlayerAdapter(getApplicationContext()));
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
        Log.d("debug", "checkSword");
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
        Log.d("debug", "checkPortal");
        /*if (contains(LevelArrays.PORTAL, currentLevel[y][x])) {
            for (int i = 0; i < currentLevel.length - 1; i++) {
                for (int k = 0; k < currentLevel[i].length; k++) {
                    if (contains(LevelArrays.PORTAL, currentLevel[i][k]) && !(i == y && k == x)) {
                        x = k;
                        y = i;
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
                        playSound(R.raw.portal, volumeFX);
                        return true;
                    }
                }
            }
        }*/

        for (int i = 0; i < currentLevel[currentLevel.length - 1].length; i++) {
            String str = currentLevel[currentLevel.length - 1][i];
            String[] result = str.split(",");
            if ((x == Integer.parseInt(result[0])) && (y == Integer.parseInt(result[1]))) {
                if (i % 2 == 0) {
                    Log.d("debug", currentLevel[currentLevel.length - 1][i + 1]);
                    String[] even = currentLevel[currentLevel.length - 1][i + 1].split(",");
                    x = Integer.parseInt(even[0]);
                    y = Integer.parseInt(even[1]);
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
                    playSound(R.raw.portal, volumeFX);
                    return true;
                } else {
                    Log.d("debug", currentLevel[currentLevel.length - 1][i - 1]);
                    String[] odd = currentLevel[currentLevel.length - 1][i - 1].split(",");
                    x = Integer.parseInt(odd[0]);
                    y = Integer.parseInt(odd[1]);
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
                    playSound(R.raw.portal, volumeFX);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkMonster() {
        Log.d("debug", "checkMonster");
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
        Log.d("debug", "checkTrap");
        if (contains(LevelArrays.TRAP, currentLevel[y][x])) {
            gameOver();
            return true;
        }
        return false;
    }

    public boolean checkDarkness() {
        Log.d("debug", "checkDarkness");
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
        Log.d("debug", "checkStar");
        if (contains(LevelArrays.STAR, currentLevel[y][x])) {
            currentLevel[y][x] = LevelArrays.NORMAL[getIndex(LevelArrays.STAR, currentLevel[y][x])];
            stars++;
            playSound(R.raw.star, volumeFX);
            return true;
        }
        return false;
    }

    public void gameOver() {
        Log.d("debug", "gameOver");
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


        AlertDialog.Builder builder = new AlertDialog.Builder(Level.this);
        builder.setTitle("You died!");
        builder.setMessage("You died after: " + stepCount + " steps.");
        builder.setCancelable(false);
        builder.setPositiveButton("Replay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reset();
            }
        });
        builder.setNegativeButton("Previous", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                level--;
                reset();
            }
        });
        builder.show();
    }

    public boolean checkWin() {
        Log.d("debug", "checkWin");
        if (contains(LevelArrays.GOAL, currentLevel[y][x])) {
            stopTime = true;
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

            ImageView image = new ImageView(this);
            switch (score) {
                case 0:
                    image.setImageResource(R.drawable.stars_0);
                    break;
                case 1:
                    image.setImageResource(R.drawable.stars_1);
                    break;
                case 2:
                    image.setImageResource(R.drawable.stars_2);
                    break;
                case 3:
                    image.setImageResource(R.drawable.stars_3);
                    break;
                case 4:
                    image.setImageResource(R.drawable.stars_4);
                    break;
                case 5:
                    image.setImageResource(R.drawable.stars_5);
                    break;
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(Level.this);
            builder.setTitle("You win!");
            builder.setMessage("Steps: " + stepCount + "\n" + "Time: " + time + "\n");
            builder.setView(image);
            builder.setCancelable(false);
            if (level < LevelArrays.LEVEL.length - 1) {
                builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        level++;
                        reset();
                    }
                });
            }
            if (level > 0) {
                builder.setNeutralButton("Previous", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        level--;
                        reset();
                    }
                });
            }
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

    public void setNeedle() {
        Log.d("debug", "setNeedle");
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
        Log.d("debug", "playSound");
        MediaPlayer stepSound = MediaPlayer.create(getApplicationContext(), sound);
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
        Log.d("debug", "startAnimation");
        TranslateAnimation translateAnimation = new TranslateAnimation(fromX, toX, fromY, toY);
        translateAnimation.setDuration(200);
        translateAnimation.setInterpolator(getApplicationContext(), android.R.anim.linear_interpolator);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                allowInput = false;
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!checkDarkness()) {
                    if (!checkTrap()) {
                        if (!checkMonster()) {
                            if (!checkWin()) {
                                checkFire();
                                checkSword();
                                checkStar();
                                checkPortal();
                            }
                        }
                    }
                }

                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                animation.setDuration(1);
                gridViewLevel.startAnimation(animation);
                gridViewLevel.setAdapter(new LevelAdapter(getApplicationContext(), 0));
                gridViewMap.setAdapter(new MapAdapter(getApplicationContext(), 0));
                setNeedle();
                allowInput = true;
                isAnimating = false;

                //Slippery Ground
                if (scene.equals("s")) {
                    switch (direction) {
                        case "up":
                            Log.d("debug", "up");
                            moveUp();
                            break;
                        case "down":
                            Log.d("debug", "down");
                            moveDown();
                            break;
                        case "left":
                            Log.d("debug", "left");
                            moveLeft();
                            break;
                        case "right":
                            Log.d("debug", "right");
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

    public void resetLevel(View v) {
        Log.d("debug", "resetLevel");
        try {
            bgm.pause();
            fire.pause();
            heartbeat.pause();
        } catch (Exception e) {
            e.printStackTrace();
        }

        stopTime = true;

        AlertDialog.Builder builder = new AlertDialog.Builder(Level.this);
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
                createHandler();
            }
        });
        builder.setNeutralButton("Back to level 1", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                level = 0;
                reset();
            }
        });
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
        Log.d("debug", "createHandler");
        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                boolean containsTrap = false;
                if (!stopTime) {
                    time += 1;
                    timeCounter.setText("" + time);
                    handler.postDelayed(this, 1000);

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
                        gridViewLevel.setAdapter(new LevelAdapter(getApplicationContext(), 0));
                        gridViewMap.setAdapter(new MapAdapter(getApplicationContext(), 0));
                        checkTrap();
                    }
                } else {
                    createHandler();
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    public void saveGame() {
        Log.d("debug", "saveGame");
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("level", level);
        editor.putBoolean("swipe", swipe);
        if (controlButton.isChecked()) {
            editor.putBoolean("isChecked", true);
        } else {
            editor.putBoolean("isChecked", false);
        }
        editor.apply();
    }

    public void loadGame() {
        Log.d("debug", "loadGame");
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);

        level = sharedPreferences.getInt("level", 0);
        swipe = sharedPreferences.getBoolean("swipe", false);
        controlButton.setChecked(sharedPreferences.getBoolean("isChecked", false));

        reset();
    }

    private class LevelAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public LevelAdapter(Context context, int center) {
            Log.d("debug", "LevelAdapter");
            inflater = LayoutInflater.from(context);

            items.add(new Item("1-1", getResources().getIdentifier(scene + currentLevel[y - 2][x - 2], "drawable", getPackageName())));
            items.add(new Item("1-2", getResources().getIdentifier(scene + currentLevel[y - 2][x - 1], "drawable", getPackageName())));
            items.add(new Item("1-3", getResources().getIdentifier(scene + currentLevel[y - 2][x], "drawable", getPackageName())));
            items.add(new Item("1-4", getResources().getIdentifier(scene + currentLevel[y - 2][x + 1], "drawable", getPackageName())));
            items.add(new Item("1-5", getResources().getIdentifier(scene + currentLevel[y - 2][x + 2], "drawable", getPackageName())));
            items.add(new Item("1-1", getResources().getIdentifier(scene + currentLevel[y - 1][x - 2], "drawable", getPackageName())));
            items.add(new Item("2-2", getResources().getIdentifier(scene + currentLevel[y - 1][x - 1], "drawable", getPackageName())));
            items.add(new Item("2-3", getResources().getIdentifier(scene + currentLevel[y - 1][x], "drawable", getPackageName())));
            items.add(new Item("2-4", getResources().getIdentifier(scene + currentLevel[y - 1][x + 1], "drawable", getPackageName())));
            items.add(new Item("2-5", getResources().getIdentifier(scene + currentLevel[y - 1][x + 2], "drawable", getPackageName())));
            items.add(new Item("3-1", getResources().getIdentifier(scene + currentLevel[y][x - 2], "drawable", getPackageName())));
            items.add(new Item("3-2", getResources().getIdentifier(scene + currentLevel[y][x - 1], "drawable", getPackageName())));

            if (center == 0) {
                items.add(new Item("3-3", getResources().getIdentifier(scene + currentLevel[y][x], "drawable", getPackageName())));
            } else {
                items.add(new Item("3-3", center));
            }

            items.add(new Item("3-4", getResources().getIdentifier(scene + currentLevel[y][x + 1], "drawable", getPackageName())));
            items.add(new Item("3-5", getResources().getIdentifier(scene + currentLevel[y][x + 2], "drawable", getPackageName())));
            items.add(new Item("4-1", getResources().getIdentifier(scene + currentLevel[y + 1][x - 2], "drawable", getPackageName())));
            items.add(new Item("4-2", getResources().getIdentifier(scene + currentLevel[y + 1][x - 1], "drawable", getPackageName())));
            items.add(new Item("4-3", getResources().getIdentifier(scene + currentLevel[y + 1][x], "drawable", getPackageName())));
            items.add(new Item("4-4", getResources().getIdentifier(scene + currentLevel[y + 1][x + 1], "drawable", getPackageName())));
            items.add(new Item("4-5", getResources().getIdentifier(scene + currentLevel[y + 1][x + 2], "drawable", getPackageName())));
            items.add(new Item("5-1", getResources().getIdentifier(scene + currentLevel[y + 2][x - 2], "drawable", getPackageName())));
            items.add(new Item("5-2", getResources().getIdentifier(scene + currentLevel[y + 2][x - 1], "drawable", getPackageName())));
            items.add(new Item("5-3", getResources().getIdentifier(scene + currentLevel[y + 2][x], "drawable", getPackageName())));
            items.add(new Item("5-4", getResources().getIdentifier(scene + currentLevel[y + 2][x + 1], "drawable", getPackageName())));
            items.add(new Item("5-5", getResources().getIdentifier(scene + currentLevel[y + 2][x + 2], "drawable", getPackageName())));
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

        private class Item {
            final String name;
            final int drawableId;

            Item(String name, int drawableId) {
                this.name = name;
                this.drawableId = drawableId;
            }
        }
    }

    private class MapAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public MapAdapter(Context context, int center) {
            Log.d("debug", "MapAdapter");
            inflater = LayoutInflater.from(context);

            if (stepsMade.contains(new Point(position.x - 3, position.y - 3))) {
                items.add(new Item("1-1", getResources().getIdentifier("m" + currentLevel[y - 3][x - 3], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 3, position.y - 3))) {
                items.add(new Item("1-1", getResources().getIdentifier("m" + currentLevel[y - 3][x - 3], "drawable", getPackageName())));
            } else {
                items.add(new Item("1-1", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 2, position.y - 3))) {
                items.add(new Item("1-2", getResources().getIdentifier("m" + currentLevel[y - 3][x - 2], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 2, position.y - 3))) {
                items.add(new Item("1-2", getResources().getIdentifier("m" + currentLevel[y - 3][x - 2], "drawable", getPackageName())));
            } else {
                items.add(new Item("1-2", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 1, position.y - 3))) {
                items.add(new Item("1-3", getResources().getIdentifier("m" + currentLevel[y - 3][x - 1], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 1, position.y - 3))) {
                items.add(new Item("1-3", getResources().getIdentifier("m" + currentLevel[y - 3][x - 1], "drawable", getPackageName())));
            } else {
                items.add(new Item("1-3", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x, position.y - 3))) {
                items.add(new Item("1-4", getResources().getIdentifier("m" + currentLevel[y - 3][x], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x, position.y - 3))) {
                items.add(new Item("1-4", getResources().getIdentifier("m" + currentLevel[y - 3][x], "drawable", getPackageName())));
            } else {
                items.add(new Item("1-4", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 1, position.y - 3))) {
                items.add(new Item("1-5", getResources().getIdentifier("m" + currentLevel[y - 3][x + 1], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 1, position.y - 3))) {
                items.add(new Item("1-5", getResources().getIdentifier("m" + currentLevel[y - 3][x + 1], "drawable", getPackageName())));
            } else {
                items.add(new Item("1-5", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 2, position.y - 3))) {
                items.add(new Item("1-6", getResources().getIdentifier("m" + currentLevel[y - 3][x + 2], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 2, position.y - 3))) {
                items.add(new Item("1-6", getResources().getIdentifier("m" + currentLevel[y - 3][x + 2], "drawable", getPackageName())));
            } else {
                items.add(new Item("1-6", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 3, position.y - 3))) {
                items.add(new Item("1-7", getResources().getIdentifier("m" + currentLevel[y - 3][x + 3], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 3, position.y - 3))) {
                items.add(new Item("1-7", getResources().getIdentifier("m" + currentLevel[y - 3][x + 3], "drawable", getPackageName())));
            } else {
                items.add(new Item("1-7", R.drawable.blank));
            }

            if (stepsMade.contains(new Point(position.x - 3, position.y - 2))) {
                items.add(new Item("2-1", getResources().getIdentifier("m" + currentLevel[y - 2][x - 3], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 3, position.y - 2))) {
                items.add(new Item("2-1", getResources().getIdentifier("m" + currentLevel[y - 2][x - 3], "drawable", getPackageName())));
            } else {
                items.add(new Item("2-1", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 2, position.y - 2))) {
                items.add(new Item("2-2", getResources().getIdentifier("m" + currentLevel[y - 2][x - 2], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 2, position.y - 2))) {
                items.add(new Item("2-2", getResources().getIdentifier("m" + currentLevel[y - 2][x - 2], "drawable", getPackageName())));
            } else {
                items.add(new Item("2-2", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 1, position.y - 2))) {
                items.add(new Item("2-3", getResources().getIdentifier("m" + currentLevel[y - 2][x - 1], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 1, position.y - 2))) {
                items.add(new Item("2-3", getResources().getIdentifier("m" + currentLevel[y - 2][x - 1], "drawable", getPackageName())));
            } else {
                items.add(new Item("2-3", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x, position.y - 2))) {
                items.add(new Item("2-4", getResources().getIdentifier("m" + currentLevel[y - 2][x], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x, position.y - 2))) {
                items.add(new Item("2-4", getResources().getIdentifier("m" + currentLevel[y - 2][x], "drawable", getPackageName())));
            } else {
                items.add(new Item("2-4", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 1, position.y - 2))) {
                items.add(new Item("2-5", getResources().getIdentifier("m" + currentLevel[y - 2][x + 1], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 1, position.y - 2))) {
                items.add(new Item("2-5", getResources().getIdentifier("m" + currentLevel[y - 2][x + 1], "drawable", getPackageName())));
            } else {
                items.add(new Item("2-5", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 2, position.y - 2))) {
                items.add(new Item("2-6", getResources().getIdentifier("m" + currentLevel[y - 2][x + 2], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 2, position.y - 2))) {
                items.add(new Item("2-6", getResources().getIdentifier("m" + currentLevel[y - 2][x + 2], "drawable", getPackageName())));
            } else {
                items.add(new Item("2-6", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 3, position.y - 2))) {
                items.add(new Item("2-7", getResources().getIdentifier("m" + currentLevel[y - 2][x + 3], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 3, position.y - 2))) {
                items.add(new Item("2-7", getResources().getIdentifier("m" + currentLevel[y - 2][x + 3], "drawable", getPackageName())));
            } else {
                items.add(new Item("2-7", R.drawable.blank));
            }

            if (stepsMade.contains(new Point(position.x - 3, position.y - 1))) {
                items.add(new Item("3-1", getResources().getIdentifier("m" + currentLevel[y - 1][x - 3], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 3, position.y - 1))) {
                items.add(new Item("3-1", getResources().getIdentifier("m" + currentLevel[y - 1][x - 3], "drawable", getPackageName())));
            } else {
                items.add(new Item("3-1", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 2, position.y - 1))) {
                items.add(new Item("3-2", getResources().getIdentifier("m" + currentLevel[y - 1][x - 2], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 2, position.y - 1))) {
                items.add(new Item("3-2", getResources().getIdentifier("m" + currentLevel[y - 1][x - 2], "drawable", getPackageName())));
            } else {
                items.add(new Item("3-2", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 1, position.y - 1))) {
                items.add(new Item("3-3", getResources().getIdentifier("m" + currentLevel[y - 1][x - 1], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 1, position.y - 1))) {
                items.add(new Item("3-3", getResources().getIdentifier("m" + currentLevel[y - 1][x - 1], "drawable", getPackageName())));
            } else {
                items.add(new Item("3-3", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x, position.y - 1))) {
                items.add(new Item("3-4", getResources().getIdentifier("m" + currentLevel[y - 1][x], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x, position.y - 1))) {
                items.add(new Item("3-4", getResources().getIdentifier("m" + currentLevel[y - 1][x], "drawable", getPackageName())));
            } else {
                items.add(new Item("3-4", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 1, position.y - 1))) {
                items.add(new Item("3-5", getResources().getIdentifier("m" + currentLevel[y - 1][x + 1], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 1, position.y - 1))) {
                items.add(new Item("3-5", getResources().getIdentifier("m" + currentLevel[y - 1][x + 1], "drawable", getPackageName())));
            } else {
                items.add(new Item("3-5", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 2, position.y - 1))) {
                items.add(new Item("3-6", getResources().getIdentifier("m" + currentLevel[y - 1][x + 2], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 2, position.y - 1))) {
                items.add(new Item("3-6", getResources().getIdentifier("m" + currentLevel[y - 1][x + 2], "drawable", getPackageName())));
            } else {
                items.add(new Item("3-6", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 3, position.y - 1))) {
                items.add(new Item("3-7", getResources().getIdentifier("m" + currentLevel[y - 1][x + 3], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 3, position.y - 1))) {
                items.add(new Item("3-7", getResources().getIdentifier("m" + currentLevel[y - 1][x + 3], "drawable", getPackageName())));
            } else {
                items.add(new Item("3-7", R.drawable.blank));
            }

            if (stepsMade.contains(new Point(position.x - 3, position.y))) {
                items.add(new Item("4-1", getResources().getIdentifier("m" + currentLevel[y][x - 3], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 3, position.y))) {
                items.add(new Item("4-1", getResources().getIdentifier("m" + currentLevel[y][x - 3], "drawable", getPackageName())));
            } else {
                items.add(new Item("4-1", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 2, position.y))) {
                items.add(new Item("4-2", getResources().getIdentifier("m" + currentLevel[y][x - 2], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 2, position.y))) {
                items.add(new Item("4-2", getResources().getIdentifier("m" + currentLevel[y][x - 2], "drawable", getPackageName())));
            } else {
                items.add(new Item("4-2", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 1, position.y))) {
                items.add(new Item("4-3", getResources().getIdentifier("m" + currentLevel[y][x - 1], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 1, position.y))) {
                items.add(new Item("4-3", getResources().getIdentifier("m" + currentLevel[y][x - 1], "drawable", getPackageName())));
            } else {
                items.add(new Item("4-3", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x, position.y))) {
                items.add(new Item("4-4", getResources().getIdentifier("m" + currentLevel[y][x], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x, position.y))) {
                items.add(new Item("4-4", getResources().getIdentifier("m" + currentLevel[y][x], "drawable", getPackageName())));
            } else {
                items.add(new Item("4-4", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 1, position.y))) {
                items.add(new Item("4-5", getResources().getIdentifier("m" + currentLevel[y][x + 1], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 1, position.y))) {
                items.add(new Item("4-5", getResources().getIdentifier("m" + currentLevel[y][x + 1], "drawable", getPackageName())));
            } else {
                items.add(new Item("4-5", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 2, position.y))) {
                items.add(new Item("4-6", getResources().getIdentifier("m" + currentLevel[y][x + 2], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 2, position.y))) {
                items.add(new Item("4-6", getResources().getIdentifier("m" + currentLevel[y][x + 2], "drawable", getPackageName())));
            } else {
                items.add(new Item("4-6", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 3, position.y))) {
                items.add(new Item("4-7", getResources().getIdentifier("m" + currentLevel[y][x + 3], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 3, position.y))) {
                items.add(new Item("4-7", getResources().getIdentifier("m" + currentLevel[y][x + 3], "drawable", getPackageName())));
            } else {
                items.add(new Item("4-7", R.drawable.blank));
            }

            if (stepsMade.contains(new Point(position.x - 3, position.y + 1))) {
                items.add(new Item("5-1", getResources().getIdentifier("m" + currentLevel[y + 1][x - 3], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 3, position.y + 1))) {
                items.add(new Item("5-1", getResources().getIdentifier("m" + currentLevel[y + 1][x - 3], "drawable", getPackageName())));
            } else {
                items.add(new Item("5-1", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 2, position.y + 1))) {
                items.add(new Item("5-2", getResources().getIdentifier("m" + currentLevel[y + 1][x - 2], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 2, position.y + 1))) {
                items.add(new Item("5-2", getResources().getIdentifier("m" + currentLevel[y + 1][x - 2], "drawable", getPackageName())));
            } else {
                items.add(new Item("5-2", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 1, position.y + 1))) {
                items.add(new Item("5-3", getResources().getIdentifier("m" + currentLevel[y + 1][x - 1], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 1, position.y + 1))) {
                items.add(new Item("5-3", getResources().getIdentifier("m" + currentLevel[y + 1][x - 1], "drawable", getPackageName())));
            } else {
                items.add(new Item("5-3", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x, position.y + 1))) {
                items.add(new Item("5-4", getResources().getIdentifier("m" + currentLevel[y + 1][x], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x, position.y + 1))) {
                items.add(new Item("5-4", getResources().getIdentifier("m" + currentLevel[y + 1][x], "drawable", getPackageName())));
            } else {
                items.add(new Item("5-4", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 1, position.y + 1))) {
                items.add(new Item("5-5", getResources().getIdentifier("m" + currentLevel[y + 1][x + 1], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 1, position.y + 1))) {
                items.add(new Item("5-5", getResources().getIdentifier("m" + currentLevel[y + 1][x + 1], "drawable", getPackageName())));
            } else {
                items.add(new Item("5-5", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 2, position.y + 1))) {
                items.add(new Item("5-6", getResources().getIdentifier("m" + currentLevel[y + 1][x + 2], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 2, position.y + 1))) {
                items.add(new Item("5-6", getResources().getIdentifier("m" + currentLevel[y + 1][x + 2], "drawable", getPackageName())));
            } else {
                items.add(new Item("5-6", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 3, position.y + 1))) {
                items.add(new Item("5-7", getResources().getIdentifier("m" + currentLevel[y + 1][x + 3], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 3, position.y + 1))) {
                items.add(new Item("5-7", getResources().getIdentifier("m" + currentLevel[y + 1][x + 3], "drawable", getPackageName())));
            } else {
                items.add(new Item("5-7", R.drawable.blank));
            }

            if (stepsMade.contains(new Point(position.x - 3, position.y + 2))) {
                items.add(new Item("6-1", getResources().getIdentifier("m" + currentLevel[y + 2][x - 3], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 3, position.y + 2))) {
                items.add(new Item("6-1", getResources().getIdentifier("m" + currentLevel[y + 2][x - 3], "drawable", getPackageName())));
            } else {
                items.add(new Item("6-1", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 2, position.y + 2))) {
                items.add(new Item("6-2", getResources().getIdentifier("m" + currentLevel[y + 2][x - 2], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 2, position.y + 2))) {
                items.add(new Item("6-2", getResources().getIdentifier("m" + currentLevel[y + 2][x - 2], "drawable", getPackageName())));
            } else {
                items.add(new Item("6-2", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 1, position.y + 2))) {
                items.add(new Item("6-3", getResources().getIdentifier("m" + currentLevel[y + 2][x - 1], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 1, position.y + 2))) {
                items.add(new Item("6-3", getResources().getIdentifier("m" + currentLevel[y + 2][x - 1], "drawable", getPackageName())));
            } else {
                items.add(new Item("6-3", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x, position.y + 2))) {
                items.add(new Item("6-4", getResources().getIdentifier("m" + currentLevel[y + 2][x], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x, position.y + 2))) {
                items.add(new Item("6-4", getResources().getIdentifier("m" + currentLevel[y + 2][x], "drawable", getPackageName())));
            } else {
                items.add(new Item("6-4", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 1, position.y + 2))) {
                items.add(new Item("6-5", getResources().getIdentifier("m" + currentLevel[y + 2][x + 1], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 1, position.y + 2))) {
                items.add(new Item("6-5", getResources().getIdentifier("m" + currentLevel[y + 2][x + 1], "drawable", getPackageName())));
            } else {
                items.add(new Item("6-5", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 2, position.y + 2))) {
                items.add(new Item("6-6", getResources().getIdentifier("m" + currentLevel[y + 2][x + 2], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 2, position.y + 2))) {
                items.add(new Item("6-6", getResources().getIdentifier("m" + currentLevel[y + 2][x + 2], "drawable", getPackageName())));
            } else {
                items.add(new Item("6-6", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 3, position.y + 2))) {
                items.add(new Item("6-7", getResources().getIdentifier("m" + currentLevel[y + 2][x + 3], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 3, position.y + 2))) {
                items.add(new Item("6-7", getResources().getIdentifier("m" + currentLevel[y + 2][x + 3], "drawable", getPackageName())));
            } else {
                items.add(new Item("6-7", R.drawable.blank));
            }

            if (stepsMade.contains(new Point(position.x - 3, position.y + 3))) {
                items.add(new Item("7-1", getResources().getIdentifier("m" + currentLevel[y + 3][x - 3], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 3, position.y + 3))) {
                items.add(new Item("7-1", getResources().getIdentifier("m" + currentLevel[y + 3][x - 3], "drawable", getPackageName())));
            } else {
                items.add(new Item("7-1", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 2, position.y + 3))) {
                items.add(new Item("7-2", getResources().getIdentifier("m" + currentLevel[y + 3][x - 2], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 2, position.y + 3))) {
                items.add(new Item("7-2", getResources().getIdentifier("m" + currentLevel[y + 3][x - 2], "drawable", getPackageName())));
            } else {
                items.add(new Item("7-2", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 1, position.y + 3))) {
                items.add(new Item("7-3", getResources().getIdentifier("m" + currentLevel[y + 3][x - 1], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x - 1, position.y + 3))) {
                items.add(new Item("7-3", getResources().getIdentifier("m" + currentLevel[y + 3][x - 1], "drawable", getPackageName())));
            } else {
                items.add(new Item("7-3", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x, position.y + 3))) {
                items.add(new Item("7-4", getResources().getIdentifier("m" + currentLevel[y + 3][x], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x, position.y + 3))) {
                items.add(new Item("7-4", getResources().getIdentifier("m" + currentLevel[y + 3][x], "drawable", getPackageName())));
            } else {
                items.add(new Item("7-4", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 1, position.y + 3))) {
                items.add(new Item("7-5", getResources().getIdentifier("m" + currentLevel[y + 3][x + 1], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 1, position.y + 3))) {
                items.add(new Item("7-5", getResources().getIdentifier("m" + currentLevel[y + 3][x + 1], "drawable", getPackageName())));
            } else {
                items.add(new Item("7-5", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 2, position.y + 3))) {
                items.add(new Item("7-6", getResources().getIdentifier("m" + currentLevel[y + 3][x + 2], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 2, position.y + 3))) {
                items.add(new Item("7-6", getResources().getIdentifier("m" + currentLevel[y + 3][x + 2], "drawable", getPackageName())));
            } else {
                items.add(new Item("7-6", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 3, position.y + 3))) {
                items.add(new Item("7-7", getResources().getIdentifier("m" + currentLevel[y + 3][x + 3], "drawable", getPackageName())));
            } else if (discovered.contains(new Point(position.x + 3, position.y + 3))) {
                items.add(new Item("7-7", getResources().getIdentifier("m" + currentLevel[y + 3][x + 3], "drawable", getPackageName())));
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

        private class Item {
            final String name;
            final int drawableId;

            Item(String name, int drawableId) {
                this.name = name;
                this.drawableId = drawableId;
            }
        }
    }

    private class PlayerAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public PlayerAdapter(Context context) {
            Log.d("debug", "PlayerAdapter");
            inflater = LayoutInflater.from(context);

            items.add(new Item("1-1", R.drawable.black));
            items.add(new Item("1-2", R.drawable.black));
            items.add(new Item("1-3", R.drawable.black));
            items.add(new Item("1-4", R.drawable.black));
            items.add(new Item("1-5", R.drawable.black));
            items.add(new Item("1-1", R.drawable.black));
            items.add(new Item("2-2", PlayerArrays.F_PLAYER[darkness][0]));
            items.add(new Item("2-3", PlayerArrays.F_PLAYER[darkness][1]));
            items.add(new Item("2-4", PlayerArrays.F_PLAYER[darkness][2]));
            items.add(new Item("2-5", R.drawable.black));
            items.add(new Item("3-1", R.drawable.black));
            items.add(new Item("3-2", PlayerArrays.F_PLAYER[darkness][3]));

            switch (scene) {
                case "f":
                    items.add(new Item("3-3", PlayerArrays.F_PLAYER[darkness][4]));
                    break;
                case "c":
                    items.add(new Item("3-3", PlayerArrays.C_PLAYER[darkness][4]));
                    break;
                case "s":
                    items.add(new Item("3-3", PlayerArrays.F_PLAYER[darkness][4]));
                    break;
                case "d":
                    items.add(new Item("3-3", PlayerArrays.F_PLAYER[darkness][4]));
                    break;
            }

            items.add(new Item("3-4", PlayerArrays.F_PLAYER[darkness][5]));
            items.add(new Item("3-5", R.drawable.black));
            items.add(new Item("4-1", R.drawable.black));
            items.add(new Item("4-2", PlayerArrays.F_PLAYER[darkness][6]));
            items.add(new Item("4-3", PlayerArrays.F_PLAYER[darkness][7]));
            items.add(new Item("4-4", PlayerArrays.F_PLAYER[darkness][8]));
            items.add(new Item("4-5", R.drawable.black));
            items.add(new Item("5-1", R.drawable.black));
            items.add(new Item("5-2", R.drawable.black));
            items.add(new Item("5-3", R.drawable.black));
            items.add(new Item("5-4", R.drawable.black));
            items.add(new Item("5-5", R.drawable.black));
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

        private class Item {
            final String name;
            final int drawableId;

            Item(String name, int drawableId) {
                this.name = name;
                this.drawableId = drawableId;
            }
        }
    }
}