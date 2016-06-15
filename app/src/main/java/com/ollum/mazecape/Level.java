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
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class Level extends AppCompatActivity {

    final int VIBRATE_SHORT = 10;
    final int VIBRATE_MEDIUM = 500;
    final int VIBRATE_LONG = 1000;
    RelativeLayout relativeLayout_Container, relativeLayout_UI;
    GridView gridViewLevel, gridViewPlayer, gridViewMap;
    TextView title, stepCounter, timeCounter, posX, posY;
    Button resetLevel, cheatButton;
    ToggleButton controlButton;
    Vibrator vibrator;
    Display display;
    Point size;
    MediaPlayer bgm;
    MediaPlayer fire;
    public static int width;
    public static int height;
    int statusBarHeight;
    int level = 0;
    int x = LevelArrays.LEVEL[level][LevelArrays.LEVEL[level].length - 2][0];
    int y = LevelArrays.LEVEL[level][LevelArrays.LEVEL[level].length - 2][1];
    int darkness = 0;
    int stepCount = 0;
    int time = 0;
    int cheat = 0;
    int score = 0;
    Point position;
    ArrayList<Point> stepsMade;
    public static boolean swipe = false;
    boolean stopTime = false;
    boolean allowInput = true;
    private Handler handler;
    boolean hasSword = false;
    ImageView imgSword;

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
        setContentView(R.layout.level);

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

        gridViewMap = (GridView) findViewById(R.id.gridViewMap);

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

        stepsMade = new ArrayList<>();
        position = new Point(x, y);
        stepsMade.add(position);

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
                    Log.d("debug", "swipe: " + swipe);
                } else {
                    swipe = false;
                    Log.d("debug", "swipe: " + swipe);
                }
            }
        });

        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        bgm = MediaPlayer.create(getApplicationContext(), LevelArrays.LEVEL[level][LevelArrays.LEVEL[level].length - 1][0]);
        bgm.setLooping(true);

        fire = MediaPlayer.create(getApplicationContext(), R.raw.campfire);
        fire.setLooping(true);

        imgSword = (ImageView) findViewById(R.id.imgSword);
        imgSword.setVisibility(View.INVISIBLE);

        relativeLayout_UI.setOnTouchListener(new OnSwipeTouchListener(Level.this) {
            public void onSwipeTop() {
                if (contains(LevelArrays.MOVE_DOWN, LevelArrays.LEVEL[level][y][x])) {
                    if (allowInput == true) {
                        y++;
                        move();

                        //3x3 View
                        startAnimation(0, 0, 0, -width / 3);

                        //5x5 View
                        /*startAnimation(0, 0, 0, -width / 5);*/

                        vibrator.vibrate(VIBRATE_SHORT);
                    }
                } else {
                    vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }

            public void onSwipeRight() {
                if (contains(LevelArrays.MOVE_LEFT, LevelArrays.LEVEL[level][y][x])) {
                    if (allowInput == true) {
                        x--;
                        move();

                        //3x3 View
                        startAnimation(0, width / 3, 0, 0);

                        //5x5 View
                        /*startAnimation(0, width / 5, 0, 0);*/

                        vibrator.vibrate(VIBRATE_SHORT);
                    }
                } else {
                    vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }

            public void onSwipeLeft() {
                if (contains(LevelArrays.MOVE_RIGHT, LevelArrays.LEVEL[level][y][x])) {
                    if (allowInput == true) {
                        x++;
                        move();

                        //3x3 View
                        startAnimation(0, -width / 3, 0, 0);

                        //5x5 View
                        /*startAnimation(0, -width / 5, 0, 0);*/

                        vibrator.vibrate(VIBRATE_SHORT);
                    }
                } else {
                    vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }

            public void onSwipeBottom() {
                if (contains(LevelArrays.MOVE_UP, LevelArrays.LEVEL[level][y][x])) {
                    if (allowInput == true) {
                        y--;
                        move();

                        //3x3 View
                        startAnimation(0, 0, 0, width / 3);

                        //5x5 View
                        /*startAnimation(0, 0, 0, width / 5);*/

                        vibrator.vibrate(VIBRATE_SHORT);
                    }
                } else {
                    vibrator.vibrate(VIBRATE_MEDIUM);
                }
            }
        });
    }

    public void move() {
        stepCount++;
        position = new Point(x, y);
        /*if (position.x == LevelArrays.LEVEL[level][LevelArrays.LEVEL[level].length - 2][0] && position.y == LevelArrays.LEVEL[level][LevelArrays.LEVEL[level].length - 2][1]) {
            Toast.makeText(this, "This is where I started...", Toast.LENGTH_SHORT).show();
        } else if (stepsMade.contains(position)) {
            Toast.makeText(this, "I've been here before...", Toast.LENGTH_SHORT).show();
        }*/
        stepsMade.add(position);
        stepCounter.setText("Steps: " + stepCount);
        posX.setText("posX: " + x);
        posY.setText("posY: " + y);
        playSound(R.raw.sand1);

        if (darkness < 15 && stepCount % LevelArrays.LEVEL[level][LevelArrays.LEVEL[level].length - 2][3] == 0) {
            darkness++;
            gridViewPlayer.setAdapter(new PlayerAdapter(getApplicationContext()));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadGame();
        try {
            bgm.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stopTime = false;
        createHandler();
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveGame();
        try {
            bgm.pause();
            fire.pause();
        } catch (Exception e) {
            e.printStackTrace();
        }
        stopTime = true;
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

    public void checkFire() {
        if (contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y][x])) {
            darkness = 0;
            gridViewPlayer.setAdapter(new PlayerAdapter(getApplicationContext()));
            gridViewLevel.setAdapter(new LevelAdapter(getApplicationContext(), LevelArrays.NORMAL[getIndex(LevelArrays.FIRE, LevelArrays.LEVEL[level][y][x])]));
        }
        if (contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y][x])) {
            fire.setVolume(1, 1);
            if (!fire.isPlaying()) {
                fire.start();
            }
        } else if (contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y-1][x-1]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y-1][x]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y-1][x+1]) ||
                contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y][x-1]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y][x]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y][x+1]) ||
                contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y+1][x-1]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y+1][x]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y+1][x+1])) {
            fire.setVolume(0.66f, 0.66f);
            if (!fire.isPlaying()) {
                fire.start();
            }
        } else if (contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y-2][x-2]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y-2][x-1]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y-2][x]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y-2][x+1]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y-2][x+2]) ||
                contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y-1][x-2]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y-1][x-1]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y-1][x]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y-1][x+1]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y-1][x+2]) ||
                contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y][x-2]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y][x-1]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y][x]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y][x+1]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y][x+2]) ||
                contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y+1][x-2]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y+1][x-1]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y+1][x]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y+1][x+1]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y+1][x+2]) ||
                contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y+2][x-2]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y+2][x-1]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y+2][x]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y+2][x+1]) || contains(LevelArrays.FIRE, LevelArrays.LEVEL[level][y+2][x+2])) {
            fire.setVolume(0.33f, 0.33f);
            if (!fire.isPlaying()) {
                fire.start();
            }
        } else {
            if (fire.isPlaying()) {
                fire.pause();
            }
        }
    }

    public void checkSword() {
        if (contains(LevelArrays.SWORD, LevelArrays.LEVEL[level][y][x])) {
            gridViewLevel.setAdapter(new LevelAdapter(getApplicationContext(), LevelArrays.NORMAL[getIndex(LevelArrays.SWORD, LevelArrays.LEVEL[level][y][x])]));
            LevelArrays.LEVEL[level][y][x] = LevelArrays.NORMAL[getIndex(LevelArrays.SWORD, LevelArrays.LEVEL[level][y][x])];
            hasSword = true;
        }
        if (hasSword) {
            imgSword.setVisibility(View.VISIBLE);
        } else {
            imgSword.setVisibility(View.INVISIBLE);
        }
    }

    public void checkMonster() {
        if (contains(LevelArrays.MONSTER, LevelArrays.LEVEL[level][y][x])) {
            if (!hasSword) {
                gameOver();
            } else {
                gridViewLevel.setAdapter(new LevelAdapter(getApplicationContext(), LevelArrays.NORMAL[getIndex(LevelArrays.MONSTER, LevelArrays.LEVEL[level][y][x])]));
                LevelArrays.LEVEL[level][y][x] = LevelArrays.NORMAL[getIndex(LevelArrays.MONSTER, LevelArrays.LEVEL[level][y][x])];
            }
        }
    }

    public void checkDeath() {
        if (darkness > 14) {
            gameOver();
        }
    }

    public void gameOver() {
        stopTime = true;

        if (bgm.isPlaying()) {
            bgm.stop();
            bgm.release();
        }

        if (fire.isPlaying()) {
            fire.stop();
            fire.release();
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

    public void checkWin() {
        if (contains(LevelArrays.GOAL, LevelArrays.LEVEL[level][y][x])) {
            stopTime = true;
            int minSteps = LevelArrays.LEVEL[level][LevelArrays.LEVEL[level].length - 2][4];
            score = 5000 - (stepCount - minSteps) * 100 + 5000 - ((time * 2 - minSteps) * 100) - cheat * 1000;
            if (score < 0) {
                score = 0;
            } else if (score > 10000) {
                score = 10000;
            }

            if (bgm.isPlaying()) {
                bgm.stop();
                bgm.release();
            }

            if (fire.isPlaying()) {
                fire.stop();
                fire.release();
            }

            playSound(R.raw.win);

            ImageView image = new ImageView(this);
            if (score == 0) {
                image.setImageResource(R.drawable.stars_0);
            } else if (score < 2000) {
                image.setImageResource(R.drawable.stars_1);
            } else if (score < 4000) {
                image.setImageResource(R.drawable.stars_2);
            } else if (score < 6000) {
                image.setImageResource(R.drawable.stars_3);
            } else if (score < 8000) {
                image.setImageResource(R.drawable.stars_4);
            } else if (score <= 10000) {
                image.setImageResource(R.drawable.stars_5);
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(Level.this);
            builder.setTitle("You win!");
            builder.setMessage("Steps: " + stepCount + "\n" + "Time: " + time + "\n" + "Score: " + score + "\n");
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
        } else checkDeath();
    }

    public void playSound(int sound) {
        MediaPlayer stepSound = MediaPlayer.create(getApplicationContext(), sound);
        stepSound.start();
        stepSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }

    public void startAnimation(float fromX, float toX, float fromY, float toY) {
        TranslateAnimation translateAnimation = new TranslateAnimation(fromX, toX, fromY, toY);
        translateAnimation.setDuration(300);
        translateAnimation.setInterpolator(getApplicationContext(), android.R.anim.accelerate_decelerate_interpolator);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                allowInput = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                animation.setDuration(1);
                gridViewLevel.startAnimation(animation);
                gridViewLevel.setAdapter(new LevelAdapter(getApplicationContext(), 0));
                gridViewMap.setAdapter(new MapAdapter(getApplicationContext(), 0));
                checkMonster();
                checkFire();
                checkSword();
                checkWin();
                if (hasSword) {

                }
                allowInput = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        gridViewLevel.startAnimation(translateAnimation);
    }

    public boolean contains(int[] array, int resource) {
        for (int element : array) {
            if (element == resource) {
                return true;
            }
        }
        return false;
    }

    public int getIndex(int[] array, int resource) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == resource) {
                return i;
            }
        }
        return -1;
    }

    public void reset() {
        stepsMade.clear();
        darkness = 0;
        stepCount = 0;
        time = 0;
        cheat = 0;
        stopTime = false;
        x = LevelArrays.LEVEL[level][LevelArrays.LEVEL[level].length - 2][0];
        y = LevelArrays.LEVEL[level][LevelArrays.LEVEL[level].length - 2][1];
        position = new Point(x, y);
        stepsMade.add(position);
        gridViewLevel.setAdapter(new LevelAdapter(getApplicationContext(), 0));
        gridViewMap.setAdapter(new MapAdapter(getApplicationContext(), 0));
        gridViewPlayer.setAdapter(new PlayerAdapter(getApplicationContext()));
        title.setText("Level " + (level + 1));
        stepCounter.setText("Steps: " + stepCount);
        timeCounter.setText("" + time);
        posX.setText("posX: " + x);
        posY.setText("posY: " + y);
        hasSword = false;
        imgSword.setVisibility(View.INVISIBLE);

        bgm = MediaPlayer.create(getApplicationContext(), LevelArrays.LEVEL[level][LevelArrays.LEVEL[level].length - 1][0]);
        bgm.setLooping(true);
        bgm.start();

        fire = MediaPlayer.create(getApplicationContext(), R.raw.campfire);
        fire.setLooping(true);

        createHandler();
    }

    public void resetLevel(View v) {
        try {
            bgm.pause();
            fire.pause();
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
        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (stopTime == false) {
                    time += 1;
                    timeCounter.setText("" + time);
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    private class LevelAdapter extends BaseAdapter {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public LevelAdapter(Context context, int center) {
            inflater = LayoutInflater.from(context);

            items.add(new Item("1-1", LevelArrays.LEVEL[level][y - 2][x - 2]));
            items.add(new Item("1-2", LevelArrays.LEVEL[level][y - 2][x - 1]));
            items.add(new Item("1-3", LevelArrays.LEVEL[level][y - 2][x]));
            items.add(new Item("1-4", LevelArrays.LEVEL[level][y - 2][x + 1]));
            items.add(new Item("1-5", LevelArrays.LEVEL[level][y - 2][x + 2]));
            items.add(new Item("1-1", LevelArrays.LEVEL[level][y - 1][x - 2]));
            items.add(new Item("2-2", LevelArrays.LEVEL[level][y - 1][x - 1]));
            items.add(new Item("2-3", LevelArrays.LEVEL[level][y - 1][x]));
            items.add(new Item("2-4", LevelArrays.LEVEL[level][y - 1][x + 1]));
            items.add(new Item("2-5", LevelArrays.LEVEL[level][y - 1][x + 2]));
            items.add(new Item("3-1", LevelArrays.LEVEL[level][y][x - 2]));
            items.add(new Item("3-2", LevelArrays.LEVEL[level][y][x - 1]));

            if (center == 0) {
                items.add(new Item("3-3", LevelArrays.LEVEL[level][y][x]));
            } else {
                items.add(new Item("3-3", center));
            }

            items.add(new Item("3-4", LevelArrays.LEVEL[level][y][x + 1]));
            items.add(new Item("3-5", LevelArrays.LEVEL[level][y][x + 2]));
            items.add(new Item("4-1", LevelArrays.LEVEL[level][y + 1][x - 2]));
            items.add(new Item("4-2", LevelArrays.LEVEL[level][y + 1][x - 1]));
            items.add(new Item("4-3", LevelArrays.LEVEL[level][y + 1][x]));
            items.add(new Item("4-4", LevelArrays.LEVEL[level][y + 1][x + 1]));
            items.add(new Item("4-5", LevelArrays.LEVEL[level][y + 1][x + 2]));
            items.add(new Item("5-1", LevelArrays.LEVEL[level][y + 2][x - 2]));
            items.add(new Item("5-2", LevelArrays.LEVEL[level][y + 2][x - 1]));
            items.add(new Item("5-3", LevelArrays.LEVEL[level][y + 2][x]));
            items.add(new Item("5-4", LevelArrays.LEVEL[level][y + 2][x + 1]));
            items.add(new Item("5-5", LevelArrays.LEVEL[level][y + 2][x + 2]));
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
            inflater = LayoutInflater.from(context);

            if (stepsMade.contains(new Point(position.x - 3, position.y - 3))) {
                items.add(new Item("1-1", LevelArrays.LEVEL[level][y - 3][x - 3]));
            } else {
                items.add(new Item("1-1", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 2, position.y - 3))) {
                items.add(new Item("1-2", LevelArrays.LEVEL[level][y - 3][x - 2]));
            } else {
                items.add(new Item("1-2", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 1, position.y - 3))) {
                items.add(new Item("1-3", LevelArrays.LEVEL[level][y - 3][x - 1]));
            } else {
                items.add(new Item("1-3", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x, position.y - 3))) {
                items.add(new Item("1-4", LevelArrays.LEVEL[level][y - 3][x]));
            } else {
                items.add(new Item("1-4", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 1, position.y - 3))) {
                items.add(new Item("1-5", LevelArrays.LEVEL[level][y - 3][x + 1]));
            } else {
                items.add(new Item("1-5", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 2, position.y - 3))) {
                items.add(new Item("1-6", LevelArrays.LEVEL[level][y - 3][x + 2]));
            } else {
                items.add(new Item("1-6", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 3, position.y - 3))) {
                items.add(new Item("1-7", LevelArrays.LEVEL[level][y - 3][x + 3]));
            } else {
                items.add(new Item("1-7", R.drawable.blank));
            }

            if (stepsMade.contains(new Point(position.x - 3, position.y - 2))) {
                items.add(new Item("2-1", LevelArrays.LEVEL[level][y - 2][x - 3]));
            } else {
                items.add(new Item("2-1", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 2, position.y - 2))) {
                items.add(new Item("2-2", LevelArrays.LEVEL[level][y - 2][x - 2]));
            } else {
                items.add(new Item("2-2", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 1, position.y - 2))) {
                items.add(new Item("2-3", LevelArrays.LEVEL[level][y - 2][x - 1]));
            } else {
                items.add(new Item("2-3", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x, position.y - 2))) {
                items.add(new Item("2-4", LevelArrays.LEVEL[level][y - 2][x]));
            } else {
                items.add(new Item("2-4", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 1, position.y - 2))) {
                items.add(new Item("2-5", LevelArrays.LEVEL[level][y - 2][x + 1]));
            } else {
                items.add(new Item("2-5", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 2, position.y - 2))) {
                items.add(new Item("2-6", LevelArrays.LEVEL[level][y - 2][x + 2]));
            } else {
                items.add(new Item("2-6", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 3, position.y - 2))) {
                items.add(new Item("2-7", LevelArrays.LEVEL[level][y - 2][x + 3]));
            } else {
                items.add(new Item("2-7", R.drawable.blank));
            }

            if (stepsMade.contains(new Point(position.x - 3, position.y - 1))) {
                items.add(new Item("3-1", LevelArrays.LEVEL[level][y - 1][x - 3]));
            } else {
                items.add(new Item("3-1", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 2, position.y - 1))) {
                items.add(new Item("3-2", LevelArrays.LEVEL[level][y - 1][x - 2]));
            } else {
                items.add(new Item("3-2", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 1, position.y - 1))) {
                items.add(new Item("3-3", LevelArrays.LEVEL[level][y - 1][x - 1]));
            } else {
                items.add(new Item("3-3", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x, position.y - 1))) {
                items.add(new Item("3-4", LevelArrays.LEVEL[level][y - 1][x]));
            } else {
                items.add(new Item("3-4", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 1, position.y - 1))) {
                items.add(new Item("3-5", LevelArrays.LEVEL[level][y - 1][x + 1]));
            } else {
                items.add(new Item("3-5", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 2, position.y - 1))) {
                items.add(new Item("3-6", LevelArrays.LEVEL[level][y - 1][x + 2]));
            } else {
                items.add(new Item("3-6", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 3, position.y - 1))) {
                items.add(new Item("3-7", LevelArrays.LEVEL[level][y - 1][x + 3]));
            } else {
                items.add(new Item("3-7", R.drawable.blank));
            }

            if (stepsMade.contains(new Point(position.x - 3, position.y))) {
                items.add(new Item("4-1", LevelArrays.LEVEL[level][y][x - 3]));
            } else {
                items.add(new Item("4-1", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 2, position.y))) {
                items.add(new Item("4-2", LevelArrays.LEVEL[level][y][x - 2]));
            } else {
                items.add(new Item("4-2", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 1, position.y))) {
                items.add(new Item("4-3", LevelArrays.LEVEL[level][y][x - 1]));
            } else {
                items.add(new Item("4-3", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x, position.y))) {
                items.add(new Item("4-4", LevelArrays.LEVEL[level][y][x]));
            } else {
                items.add(new Item("4-4", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 1, position.y))) {
                items.add(new Item("4-5", LevelArrays.LEVEL[level][y][x + 1]));
            } else {
                items.add(new Item("4-5", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 2, position.y))) {
                items.add(new Item("4-6", LevelArrays.LEVEL[level][y][x + 2]));
            } else {
                items.add(new Item("4-6", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 3, position.y))) {
                items.add(new Item("4-7", LevelArrays.LEVEL[level][y][x + 3]));
            } else {
                items.add(new Item("4-7", R.drawable.blank));
            }

            if (stepsMade.contains(new Point(position.x - 3, position.y + 1))) {
                items.add(new Item("5-1", LevelArrays.LEVEL[level][y + 1][x - 3]));
            } else {
                items.add(new Item("5-1", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 2, position.y + 1))) {
                items.add(new Item("5-2", LevelArrays.LEVEL[level][y + 1][x - 2]));
            } else {
                items.add(new Item("5-2", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 1, position.y + 1))) {
                items.add(new Item("5-3", LevelArrays.LEVEL[level][y + 1][x - 1]));
            } else {
                items.add(new Item("5-3", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x, position.y + 1))) {
                items.add(new Item("5-4", LevelArrays.LEVEL[level][y + 1][x]));
            } else {
                items.add(new Item("5-4", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 1, position.y + 1))) {
                items.add(new Item("5-5", LevelArrays.LEVEL[level][y + 1][x + 1]));
            } else {
                items.add(new Item("5-5", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 2, position.y + 1))) {
                items.add(new Item("5-6", LevelArrays.LEVEL[level][y + 1][x + 2]));
            } else {
                items.add(new Item("5-6", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 3, position.y + 1))) {
                items.add(new Item("5-7", LevelArrays.LEVEL[level][y + 1][x + 3]));
            } else {
                items.add(new Item("5-7", R.drawable.blank));
            }

            if (stepsMade.contains(new Point(position.x - 3, position.y + 2))) {
                items.add(new Item("6-1", LevelArrays.LEVEL[level][y + 2][x - 3]));
            } else {
                items.add(new Item("6-1", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 2, position.y + 2))) {
                items.add(new Item("6-2", LevelArrays.LEVEL[level][y + 2][x - 2]));
            } else {
                items.add(new Item("6-2", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 1, position.y + 2))) {
                items.add(new Item("6-3", LevelArrays.LEVEL[level][y + 2][x - 1]));
            } else {
                items.add(new Item("6-3", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x, position.y + 2))) {
                items.add(new Item("6-4", LevelArrays.LEVEL[level][y + 2][x]));
            } else {
                items.add(new Item("6-4", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 1, position.y + 2))) {
                items.add(new Item("6-5", LevelArrays.LEVEL[level][y + 2][x + 1]));
            } else {
                items.add(new Item("6-5", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 2, position.y + 2))) {
                items.add(new Item("6-6", LevelArrays.LEVEL[level][y + 2][x + 2]));
            } else {
                items.add(new Item("6-6", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 3, position.y + 2))) {
                items.add(new Item("6-7", LevelArrays.LEVEL[level][y + 2][x + 3]));
            } else {
                items.add(new Item("6-7", R.drawable.blank));
            }

            if (stepsMade.contains(new Point(position.x - 3, position.y + 3))) {
                items.add(new Item("7-1", LevelArrays.LEVEL[level][y + 3][x - 3]));
            } else {
                items.add(new Item("7-1", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 2, position.y + 3))) {
                items.add(new Item("7-2", LevelArrays.LEVEL[level][y + 3][x - 2]));
            } else {
                items.add(new Item("7-2", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x - 1, position.y + 3))) {
                items.add(new Item("7-3", LevelArrays.LEVEL[level][y + 3][x - 1]));
            } else {
                items.add(new Item("7-3", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x, position.y + 3))) {
                items.add(new Item("7-4", LevelArrays.LEVEL[level][y + 3][x]));
            } else {
                items.add(new Item("7-4", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 1, position.y + 3))) {
                items.add(new Item("7-5", LevelArrays.LEVEL[level][y + 3][x + 1]));
            } else {
                items.add(new Item("7-5", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 2, position.y + 3))) {
                items.add(new Item("7-6", LevelArrays.LEVEL[level][y + 3][x + 2]));
            } else {
                items.add(new Item("7-6", R.drawable.blank));
            }
            if (stepsMade.contains(new Point(position.x + 3, position.y + 3))) {
                items.add(new Item("7-7", LevelArrays.LEVEL[level][y + 3][x + 3]));
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

            if (LevelArrays.LEVEL[level][LevelArrays.LEVEL[level].length - 2][2] == 0) {
                items.add(new Item("3-3", PlayerArrays.F_PLAYER[darkness][4]));
            } else if (LevelArrays.LEVEL[level][LevelArrays.LEVEL[level].length - 2][2] == 1) {
                items.add(new Item("3-3", PlayerArrays.C_PLAYER[darkness][4]));
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

    public void saveGame() {
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("level", level);
        editor.putInt("x", x);
        editor.putInt("y", y);
        editor.putInt("stepCount", stepCount);
        editor.putInt("time", time);
        editor.putInt("darkness", darkness);
        editor.putInt("cheat", cheat);
        editor.putBoolean("swipe", swipe);
        editor.putBoolean("hasSword", hasSword);
        if (controlButton.isChecked()) {
            editor.putBoolean("isChecked", true);
        } else {
            editor.putBoolean("isChecked", false);
        }
        editor.apply();
    }

    public void loadGame() {
        SharedPreferences sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);

        level = sharedPreferences.getInt("level", 0);
        x = sharedPreferences.getInt("x", LevelArrays.LEVEL[level][LevelArrays.LEVEL[level].length - 2][0]);
        y = sharedPreferences.getInt("y", LevelArrays.LEVEL[level][LevelArrays.LEVEL[level].length - 2][1]);
        stepCount = sharedPreferences.getInt("stepCount", 0);
        time = sharedPreferences.getInt("time", 0);
        darkness = sharedPreferences.getInt("darkness", 0);
        cheat = sharedPreferences.getInt("cheat", 0);
        swipe = sharedPreferences.getBoolean("swipe", false);
        hasSword = sharedPreferences.getBoolean("hasSword", false);

        title.setText("Level " + (level + 1));
        stepCounter.setText("Steps: " + stepCount);
        timeCounter.setText(("" + time));
        posX.setText("posX: " + x);
        posY.setText("posY: " + y);

        gridViewLevel.setAdapter(new LevelAdapter(getApplicationContext(), 0));
        gridViewMap.setAdapter(new MapAdapter(this, 0));
        gridViewPlayer.setAdapter(new PlayerAdapter(getApplicationContext()));
        controlButton.setChecked(sharedPreferences.getBoolean("isChecked", false));
    }
}