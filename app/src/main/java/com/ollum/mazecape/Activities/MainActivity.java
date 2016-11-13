package com.ollum.mazecape.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameUtils;
import com.ollum.mazecape.Fragments.DiaryFragment;
import com.ollum.mazecape.Fragments.EndlessFragment;
import com.ollum.mazecape.Fragments.GameFragment;
import com.ollum.mazecape.Fragments.HelpFragment;
import com.ollum.mazecape.Fragments.LevelEditorFragment;
import com.ollum.mazecape.Fragments.LevelSelectFragment;
import com.ollum.mazecape.Fragments.MainMenuFragment;
import com.ollum.mazecape.Fragments.SettingsFragment;
import com.ollum.mazecape.Fragments.ShopFragment;
import com.ollum.mazecape.Fragments.StoryFragment;
import com.ollum.mazecape.Fragments.TutorialFragment;
import com.ollum.mazecape.Fragments.WorldSelectFragment;
import com.ollum.mazecape.R;
import com.ollum.mazecape.util.IabHelper;
import com.ollum.mazecape.util.IabResult;
import com.ollum.mazecape.util.SharedPreferences;

import java.util.HashSet;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static java.lang.System.currentTimeMillis;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static final int RC_UNUSED = 5001;
    public static final int RC_SIGN_IN = 9001;
    public static FragmentManager fragmentManager;
    public static FrameLayout content;
    public static int maxWorld = 0;
    public static int level = 0;
    public static int world = 0;
    public static int lives = 5;
    public static int levelCompass = 0;
    public static int levelMap = 0;
    public static int levelTorch = 0;
    public static int levelSpeed = 0;
    public static int levelLives = 0;
    public static int levelStars = 0;
    public static boolean swipe = true;
    public static boolean inverse = false;
    public static HashSet<String> starsList;
    public static HashSet<String> diaryList;
    public static int allStars = 0;
    public static int world1Stars = 0;
    public static int world2Stars = 0;
    public static int world3Stars = 0;
    public static int world4Stars = 0;
    public static int world5Stars = 0;
    public static int world1MaxLevel = 0;
    public static int world2MaxLevel = 0;
    public static int world3MaxLevel = 0;
    public static int world4MaxLevel = 0;
    public static int world5MaxLevel = 0;
    public static int[] maxLevel;
    public static int[] worldStars;
    public static Display display;
    public static Point size;
    public static int statusBarHeight;
    public static int width;
    public static int height;
    public static RelativeLayout titleBar;
    public static LinearLayout stats, livesLayout, starsLayout, timeLayout, stepsLayout;
    public static TextView title, livesCounter, starsCounter, timeCounter, stepsCounter;
    public static SettingsFragment settingsFragment = new SettingsFragment();
    public static ShopFragment shopFragment = new ShopFragment();
    public static HelpFragment helpFragment = new HelpFragment();
    public static TutorialFragment tutorialFragment = new TutorialFragment();
    public static DiaryFragment diaryFragment = new DiaryFragment();
    public static boolean settingsVisible = false;
    public static boolean shopVisible = false;
    public static boolean helpVisible = false;
    public static boolean stopTime = false;
    public static boolean rated = false;
    public static boolean liked = false;
    public static int torches = 0;
    public static IabHelper mHelper;
    public static float volumeMusic;
    public static float volumeSound;
    public static int resetCount = 0;
    public static AdRequest adRequest;
    public static boolean showAds = true;
    public static MediaPlayer menuBGM;
    public static RewardedVideoAd mAd;
    public static Button sendButton;
    public static SoundPool soundPool;
    public static int clickID, swoosh1ID, swoosh2ID, liveID, stepID, portalID, swordID, crackID, deathID, monsterID, holeID, starID, trapActiveID, trapInactiveID, winID, upgradeID, diaryID, rotateID;
    public static long logOffTime;
    public static String tutorialTitle, tutorialMessage, tutorialButton;
    public static String diaryTitle, diarySubtitle, diaryMessage, diaryButton;
    public static GoogleApiClient mGoogleApiClient;
    public static boolean firstStart = true;
    public static boolean wantsGPGS = false;
    public static boolean mResolvingConnectionFailure = false;
    public static boolean mAutoStartSignInFlow = true;
    public static boolean achievement50Stars = false;
    public static boolean achievement100Stars = false;
    public static boolean achievement200Stars = false;
    public static boolean achievement500Stars = false;
    public static boolean achievementWorld1Finished = false;
    public static boolean achievementWorld2Finished = false;
    public static boolean achievementWorld3Finished = false;
    public static boolean achievementWorld4Finished = false;
    public static boolean achievementWorld1Completed = false;
    public static boolean achievementWorld2Completed = false;
    public static boolean achievementWorld3Completed = false;
    public static boolean achievementWorld4Completed = false;
    public static boolean achievementChapter1Completed = false;
    public static boolean achievementChapter2Completed = false;
    public static boolean achievementChapter3Completed = false;
    public static boolean achievementChapter4Completed = false;
    public static boolean achievementEngineer = false;
    public static boolean achievementNavigator = false;
    public static boolean achievementTreasureHunter = false;
    public static boolean achievementGuidingLight = false;
    public static boolean achievementSprinter = false;
    public static boolean achievementAddict = false;
    public static boolean achievementGreedy = false;
    public static boolean achievementCreator = false;
    public static boolean achievementSupporter = false;
    public static boolean vibration = true;
    public static int highScore;
    public Handler livesHandler;
    public long startMillis;
    public long endMillis;
    private ImageButton settingsButton, helpButton, shopButton, achievementsButton, leaderboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .build();

        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApCqNdNLtSwn177FZyPyfYbq8sU11JXtug9dUq9JTwqMgIgdCzhaUpIdNtngKSkmaKXCyYPRn8rgCMOBeZYDBmyZavrU1NVZl9u1lUCbtq+AUG9AxgdsIz2BswAsGj4eORV4/6rNOjRswfhQpQAMC0qgwNCNbq/U6WTSzZVHkyC6SEsn3W+A9mfNbYX7lvipEuIlS6pNsZQo0/fXPhHhJ6INAHD4ZRJ/R/FNdkL/kqKkdXmaNe2YDkbSgvgOc+21INw8qb4EztlX8W4bZcEcSt2zWGSfPLbDLkZ21mBkBoM/DdF28xGt8wN7uYgsZpTR6XMlLO/S/pcTYO1x5LT+XwQIDAQAB";

        // compute your public key and store it in base64EncodedPublicKey
        mHelper = new IabHelper(this, base64EncodedPublicKey);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    Log.d("debug", "Problem setting up In-app Billing: " + result);
                }
                // Hooray, IAB is fully set up!
            }
        });

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/bilbo.regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        content = (FrameLayout) findViewById(R.id.content);

        display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        statusBarHeight = getStatusBarHeight();
        width = size.x;
        height = size.y - statusBarHeight;

        titleBar = (RelativeLayout) findViewById(R.id.titleBar);
        titleBar.getLayoutParams().height = MainActivity.height / 16;

        stats = (LinearLayout) findViewById(R.id.stats);
        stats.getLayoutParams().height = MainActivity.height / 16;

        livesLayout = (LinearLayout) findViewById(R.id.livesLayout);
        starsLayout = (LinearLayout) findViewById(R.id.starsLayout);
        timeLayout = (LinearLayout) findViewById(R.id.timeLayout);
        timeLayout.setVisibility(View.INVISIBLE);
        stepsLayout = (LinearLayout) findViewById(R.id.stepsLayout);
        stepsLayout.setVisibility(View.INVISIBLE);

        title = (TextView) findViewById(R.id.title);
        title.setText(R.string.app_name);
        livesCounter = (TextView) findViewById(R.id.livesCounter);
        starsCounter = (TextView) findViewById(R.id.starsCounter);
        timeCounter = (TextView) findViewById(R.id.timeCounter);
        stepsCounter = (TextView) findViewById(R.id.stepsCounter);

        settingsButton = (ImageButton) findViewById(R.id.button_settings);
        settingsButton.setOnClickListener(this);

        helpButton = (ImageButton) findViewById(R.id.button_help);
        helpButton.setOnClickListener(this);

        shopButton = (ImageButton) findViewById(R.id.button_shop);
        shopButton.setOnClickListener(this);

        achievementsButton = (ImageButton) findViewById(R.id.button_achievements);
        achievementsButton.setOnClickListener(this);

        leaderboardButton = (ImageButton) findViewById(R.id.button_leaderboard);
        leaderboardButton.setOnClickListener(this);

        sendButton = (Button) findViewById(R.id.button_send);
        sendButton.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        MainMenuFragment mainMenuFragment = new MainMenuFragment();
        transaction.replace(R.id.content, mainMenuFragment, "MainMenuFragment");
        transaction.addToBackStack("MainMenuFragment");
        transaction.commit();

        starsList = new HashSet<>();
        diaryList = new HashSet<>();

        MobileAds.initialize(this, "ca-app-pub-7666608930334273~8844493743");
        adRequest = new AdRequest.Builder().build();

        mAd = MobileAds.getRewardedVideoAdInstance(this);
        loadRewardedVideoAd();

        SharedPreferences.loadGame(getApplicationContext());
        createHandler();

        /*Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 0, 60000, pendingIntent);
        Log.d("debug", "set up");*/


        if (menuBGM != null) {
            menuBGM.reset();
            menuBGM.release();
        }

        menuBGM = MediaPlayer.create(this, R.raw.menu);
        menuBGM.setLooping(true);
        menuBGM.setVolume(volumeMusic, volumeMusic);
        menuBGM.start();

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        clickID = soundPool.load(this, R.raw.click, 1);
        swoosh1ID = soundPool.load(this, R.raw.swoosh1, 1);
        swoosh2ID = soundPool.load(this, R.raw.swoosh2, 1);
        liveID = soundPool.load(this, R.raw.live, 1);
        stepID = soundPool.load(this, R.raw.sand1, 1);
        portalID = soundPool.load(this, R.raw.portal, 1);
        crackID = soundPool.load(this, R.raw.cracking, 1);
        swordID = soundPool.load(this, R.raw.sword, 1);
        monsterID = soundPool.load(this, R.raw.monster_death, 1);
        holeID = soundPool.load(this, R.raw.hole, 1);
        deathID = soundPool.load(this, R.raw.death, 1);
        starID = soundPool.load(this, R.raw.star, 1);
        trapActiveID = soundPool.load(this, R.raw.trap_activate, 1);
        trapInactiveID = soundPool.load(this, R.raw.trap_deactivate, 1);
        winID = soundPool.load(this, R.raw.win, 1);
        upgradeID = soundPool.load(this, R.raw.upgrade, 1);
        diaryID = soundPool.load(this, R.raw.diary, 1);
        rotateID = soundPool.load(this, R.raw.rotate, 1);

        if (firstStart) {
            firstStart = false;
            SharedPreferences.saveGame(getApplicationContext());
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Google Play Games")
                    .setMessage(R.string.want_gpgs)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            wantsGPGS = true;
                            SharedPreferences.saveGame(getApplicationContext());
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            wantsGPGS = false;
                            SharedPreferences.saveGame(getApplicationContext());
                            dialog.dismiss();
                        }
                    })
                    .setCancelable(false);
            builder.create();
            builder.show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (wantsGPGS) mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    private void loadRewardedVideoAd() {
        mAd.loadAd("ca-app-pub-7666608930334273/7484754549", new AdRequest.Builder().build());
    }

    @Override
    public void onBackPressed() {
        soundPool.play(clickID, volumeSound, volumeSound, 1, 0, 1);

        FragmentManager.BackStackEntry currentFragment = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
        String current = currentFragment.getName();

        if (fragmentManager.getBackStackEntryCount() < 0) {
            MainMenuFragment mainMenuFragment = new MainMenuFragment();
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
            transaction.replace(R.id.content, mainMenuFragment, "MainMenuFragment");
            transaction.addToBackStack("MainMenuFragment");
            transaction.commit();
        } else if (current.equals("MainMenuFragment")) {
            return;
        } else if (current.equals("WorldSelectFragment")) {
            MainMenuFragment mainMenuFragment = new MainMenuFragment();
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
            transaction.replace(R.id.content, mainMenuFragment, "MainMenuFragment");
            transaction.addToBackStack("MainMenuFragment");
            transaction.commit();
        } else if (current.equals("LevelSelectFragment")) {
            WorldSelectFragment worldSelectFragment = new WorldSelectFragment();
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
            transaction.replace(R.id.content, worldSelectFragment, "WorldSelectFragment");
            transaction.addToBackStack("WorldSelectFragment");
            transaction.commit();
        } else if (current.equals("GameFragment") || current.equals("TestLevelFragment")) {
            if (GameFragment.gameBGM != null && GameFragment.gameBGM.isPlaying()) {
                GameFragment.gameBGM.pause();
            }
            if (GameFragment.fireAtmo != null && GameFragment.fireAtmo.isPlaying()) {
                GameFragment.fireAtmo.pause();
            }
            if (GameFragment.heartbeatAtmo != null && GameFragment.heartbeatAtmo.isPlaying()) {
                GameFragment.heartbeatAtmo.pause();
            }

            stopTime = true;

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("");
            builder.setMessage(R.string.level_select_screen);
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    resetCount++;
                    livesCounter.setText("" + lives);
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
            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    dialog.dismiss();
                    stopTime = false;
                    if (GameFragment.gameBGM != null) {
                        GameFragment.gameBGM.start();
                    }
                    if (GameFragment.fireAtmo != null) {
                        GameFragment.fireAtmo.start();
                    }
                    if (GameFragment.heartbeatAtmo != null) {
                        GameFragment.heartbeatAtmo.start();
                    }
                }
            });
            builder.create();
            builder.show();
        } else if (current.equals("EndlessFragment")) {
            if (EndlessFragment.gameBGM != null && EndlessFragment.gameBGM.isPlaying()) {
                EndlessFragment.gameBGM.pause();
            }
            if (EndlessFragment.fireAtmo != null && EndlessFragment.fireAtmo.isPlaying()) {
                EndlessFragment.fireAtmo.pause();
            }
            if (EndlessFragment.heartbeatAtmo != null && EndlessFragment.heartbeatAtmo.isPlaying()) {
                EndlessFragment.heartbeatAtmo.pause();
            }

            stopTime = true;

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("");
            builder.setMessage(R.string.back_to_main_menu);
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    resetCount++;
                    livesCounter.setText("" + lives);

                    EndlessFragment.score = level * 1000 + EndlessFragment.starsCollected * 100 - EndlessFragment.time;

                    if (EndlessFragment.score > MainActivity.highScore) {
                        highScore = EndlessFragment.score;
                    }

                    if (isOnline() && isSignedIn()) {
                        Games.Leaderboards.submitScore(mGoogleApiClient, getString(R.string.leaderboard_endless_mode), EndlessFragment.score);
                    }

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
            });
            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.soundPool.play(MainActivity.clickID, MainActivity.volumeSound, MainActivity.volumeSound, 1, 0, 1);
                    dialog.dismiss();
                    stopTime = false;
                    if (EndlessFragment.gameBGM != null) {
                        EndlessFragment.gameBGM.start();
                    }
                    if (EndlessFragment.fireAtmo != null) {
                        EndlessFragment.fireAtmo.start();
                    }
                    if (EndlessFragment.heartbeatAtmo != null) {
                        EndlessFragment.heartbeatAtmo.start();
                    }
                }
            });
            builder.create();
            builder.show();
        } else if (current.equals("SettingsFragment")) {
            soundPool.play(swoosh2ID, volumeSound, volumeSound, 1, 0, 1);
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
            transaction.remove(settingsFragment);
            transaction.commit();
            fragmentManager.popBackStack();
            stopTime = false;
            settingsVisible = false;
        } else if (current.equals("ShopFragment")) {
            soundPool.play(swoosh2ID, volumeSound, volumeSound, 1, 0, 1);
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
            transaction.remove(shopFragment);
            transaction.commit();
            fragmentManager.popBackStack();
            stopTime = false;
            shopVisible = false;
        } else if (current.equals("HelpFragment")) {
            soundPool.play(swoosh2ID, volumeSound, volumeSound, 1, 0, 1);
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
            transaction.remove(helpFragment);
            transaction.commit();
            fragmentManager.popBackStack();
            stopTime = false;
            helpVisible = false;
        } else if (current.equals("LevelEditorFragment")) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("");
            builder.setMessage(R.string.back_to_main_menu);
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainMenuFragment mainMenuFragment = new MainMenuFragment();
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
                    transaction.replace(R.id.content, mainMenuFragment, "MainMenuFragment");
                    transaction.addToBackStack("MainMenuFragment");
                    transaction.commit();
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
        } else if (current.equals("StoryFragment")) {
            if (StoryFragment.storyBGM != null && StoryFragment.storyBGM.isPlaying()) {
                StoryFragment.storyBGM.pause();
            }

            MainMenuFragment mainMenuFragment = new MainMenuFragment();
            FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
            transaction.replace(R.id.content, mainMenuFragment, "MainMenuFragment");
            transaction.addToBackStack("MainMenuFragment");
            transaction.commit();

            if (MainActivity.menuBGM != null) {
                MainActivity.menuBGM.start();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.saveGame(getApplicationContext());

        if (menuBGM != null && menuBGM.isPlaying()) {
            menuBGM.pause();
            menuBGM.seekTo(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences.loadGame(getApplicationContext());

        FragmentManager.BackStackEntry currentFragment = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
        String current = currentFragment.getName();

        if (menuBGM != null && !current.equals("GameFragment") && !current.equals("EndlessFragment") && !current.equals("TestLevelFragment")) {
            menuBGM.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) try {
            mHelper.dispose();
        } catch (IabHelper.IabAsyncInProgressException e) {
            e.printStackTrace();
        }
        mHelper = null;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void createHandler() {
        startMillis = currentTimeMillis();
        endMillis = currentTimeMillis();
        livesHandler = new android.os.Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                endMillis = currentTimeMillis();
                if (lives < (5 + levelLives)) {
                    if (ShopFragment.liveTimer != null) {
                        ShopFragment.liveTimer.setText(String.format("%d:%02d", (4 - (int) (((endMillis - startMillis) / (1000 * 60)) % 60)), (59 - (int) ((endMillis - startMillis) / 1000) % 60)));
                    }
                    if (endMillis - startMillis > 300000) {
                        lives++;
                        startMillis = currentTimeMillis();
                        livesCounter.setText("" + lives);
                        soundPool.play(liveID, volumeSound, volumeSound, 1, 0, 1);
                    }
                } else {
                    startMillis = currentTimeMillis();
                    if (ShopFragment.liveTimer != null) {
                        ShopFragment.liveTimer.setText("5:00");
                    }
                }
                livesHandler.postDelayed(this, 1000);
            }
        };
        livesHandler.postDelayed(runnable, 0);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        soundPool.play(clickID, volumeSound, volumeSound, 1, 0, 1);
        switch (v.getId()) {
            case R.id.button_settings:
                if (shopVisible) {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(shopFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    shopVisible = false;
                }
                if (helpVisible) {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(helpFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    helpVisible = false;
                }
                if (!settingsVisible) {
                    soundPool.play(swoosh1ID, volumeSound, volumeSound, 1, 0, 1);
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.add(R.id.content, settingsFragment, "SettingsFragment");
                    transaction.addToBackStack("SettingsFragment");
                    transaction.commit();
                    stopTime = true;
                    settingsVisible = true;
                } else {
                    soundPool.play(swoosh2ID, volumeSound, volumeSound, 1, 0, 1);
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(settingsFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    settingsVisible = false;
                }
                break;
            case R.id.button_help:
                if (shopVisible) {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(shopFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    shopVisible = false;
                }
                if (settingsVisible) {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(settingsFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    settingsVisible = false;
                }
                if (!helpVisible) {
                    soundPool.play(swoosh1ID, volumeSound, volumeSound, 1, 0, 1);
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.add(R.id.content, helpFragment, "HelpFragment");
                    transaction.addToBackStack("HelpFragment");
                    transaction.commit();
                    stopTime = true;
                    helpVisible = true;
                } else {
                    soundPool.play(swoosh2ID, volumeSound, volumeSound, 1, 0, 1);
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(helpFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    helpVisible = false;
                }
                break;
            case R.id.button_shop:
                if (helpVisible) {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(helpFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    helpVisible = false;
                }
                if (settingsVisible) {
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(settingsFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    settingsVisible = false;
                }
                if (!shopVisible) {
                    soundPool.play(swoosh1ID, volumeSound, volumeSound, 1, 0, 1);
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.add(R.id.content, shopFragment, "ShopFragment");
                    transaction.addToBackStack("ShopFragment");
                    transaction.commit();
                    stopTime = true;
                    shopVisible = true;
                } else {
                    soundPool.play(swoosh2ID, volumeSound, volumeSound, 1, 0, 1);
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_top, R.anim.top_out);
                    transaction.remove(shopFragment);
                    transaction.commit();
                    fragmentManager.popBackStack();
                    stopTime = false;
                    shopVisible = false;
                }
                break;
            case R.id.button_send:
                StringBuilder builder = new StringBuilder();
                builder.append(LevelEditorFragment.scene + ", " + LevelEditorFragment.columns + ", " + LevelEditorFragment.rows + "\n");
                for (int i = 0; i < LevelEditorFragment.level.length; i++) {
                    builder.append("{");
                    System.out.print("{");
                    for (int k = 0; k < LevelEditorFragment.level[i].length; k++) {
                        builder.append("\"" + LevelEditorFragment.level[i][k] + "\", ");
                        System.out.print("\"" + LevelEditorFragment.level[i][k] + "\", ");
                    }
                    builder.append("},");
                    builder.append("\n");
                }

                String message = builder.toString();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"o.dolgener@googlemail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "New Level");
                i.putExtra(Intent.EXTRA_TEXT, message);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

                achievementCreator = true;

                if (isOnline() && isSignedIn()) {
                    Games.Achievements.unlock(MainActivity.mGoogleApiClient, getString(R.string.achievement_creator));
                }
                break;
            case R.id.button_achievements:
                if (!wantsGPGS) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(this)
                            .setTitle("Google Play Games")
                            .setMessage(R.string.want_gpgs)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    wantsGPGS = true;
                                    if (!isOnline()) {
                                        Toast.makeText(getApplicationContext(), R.string.no_internet, Toast.LENGTH_LONG).show();
                                    } else if (!isSignedIn()) {
                                        mGoogleApiClient.connect();
                                    } else {
                                        startActivityForResult(Games.Achievements.getAchievementsIntent(mGoogleApiClient), RC_UNUSED);
                                    }
                                }
                            })
                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    wantsGPGS = false;
                                    dialog.dismiss();
                                }
                            })
                            .setCancelable(false);
                    builder2.create();
                    builder2.show();
                } else {
                    if (!isOnline()) {
                        Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();
                    } else if (!isSignedIn()) {
                        mGoogleApiClient.connect();
                    } else {
                        startActivityForResult(Games.Achievements.getAchievementsIntent(mGoogleApiClient), RC_UNUSED);
                    }
                }
                break;
            case R.id.button_leaderboard:
                if (!wantsGPGS) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(this)
                            .setTitle("Google Play Games")
                            .setMessage(R.string.want_gpgs)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    wantsGPGS = true;
                                    if (!isOnline()) {
                                        Toast.makeText(getApplicationContext(), R.string.no_internet, Toast.LENGTH_LONG).show();
                                    } else if (!isSignedIn()) {
                                        mGoogleApiClient.connect();
                                    } else {
                                        startActivityForResult(Games.Leaderboards.getLeaderboardIntent(mGoogleApiClient, getString(R.string.leaderboard_endless_mode)), RC_UNUSED);
                                    }
                                }
                            })
                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    wantsGPGS = false;
                                    dialog.dismiss();
                                }
                            })
                            .setCancelable(false);
                    builder2.create();
                    builder2.show();
                } else {
                    if (!isOnline()) {
                        Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();
                    } else if (!isSignedIn()) {
                        mGoogleApiClient.connect();
                    } else {
                        startActivityForResult(Games.Leaderboards.getLeaderboardIntent(mGoogleApiClient, getString(R.string.leaderboard_endless_mode)), RC_UNUSED);
                    }
                }
                break;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d("debug", "GPGS connected");
        checkAchievements();
        Games.Leaderboards.submitScore(mGoogleApiClient, getString(R.string.leaderboard_endless_mode), highScore);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("debug", "GPGS connection suspended: " + i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("debug", "GPGS connection failed: " + connectionResult);
        Log.d("debug", "hasResolution: " + connectionResult.hasResolution());
        /*if (mResolvingError) {
            return;
        } else if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);
            } catch (IntentSender.SendIntentException e) {
                mGoogleApiClient.connect();
            }
        } else {
            Log.d("debug", "Error Code: " + connectionResult.getErrorCode());
            mResolvingError = true;
        }*/

        if (mResolvingConnectionFailure) {
            Log.d("debug", "onConnectionFailed(): already resolving");
            return;
        }

        if (mAutoStartSignInFlow) {
            mAutoStartSignInFlow = false;
            mResolvingConnectionFailure = true;
            if (!BaseGameUtils.resolveConnectionFailure(this, mGoogleApiClient, connectionResult,
                    RC_SIGN_IN, getString(R.string.signin_other_error))) {
                mResolvingConnectionFailure = false;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == RC_SIGN_IN) {
            //mSignInClicked = false;
            mResolvingConnectionFailure = false;
            if (resultCode == RESULT_OK) {
                mGoogleApiClient.connect();
            } else {
                BaseGameUtils.showActivityResultError(this, requestCode, resultCode, R.string.signin_other_error);
            }
        }
    }

    private boolean isSignedIn() {
        return (mGoogleApiClient != null && mGoogleApiClient.isConnected());
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void checkAchievements() {
        if (achievement50Stars)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_collect_50_stars));
        if (achievement100Stars)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_collect_100_stars));
        if (achievement200Stars)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_collect_200_stars));
        if (achievement500Stars)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_collect_500_stars));
        if (achievementWorld1Finished)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_world_1_finished));
        if (achievementWorld2Finished)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_world_2_finished));
        if (achievementWorld3Finished)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_world_3_finished));
        if (achievementWorld4Finished)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_world_4_finished));
        if (achievementWorld1Completed)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_world_1_completed));
        if (achievementWorld2Completed)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_world_2_completed));
        if (achievementWorld3Completed)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_world_3_completed));
        if (achievementWorld4Completed)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_world_4_completed));
        if (achievementChapter1Completed)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_chapter_1_completed));
        if (achievementChapter2Completed)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_chapter_2_completed));
        if (achievementChapter3Completed)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_chapter_3_completed));
        if (achievementChapter4Completed)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_chapter_4_completed));
        if (achievementEngineer)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_engineer));
        if (achievementNavigator)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_navigator));
        if (achievementTreasureHunter)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_treasure_hunter));
        if (achievementGuidingLight)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_guiding_light));
        if (achievementSprinter)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_sprinter));
        if (achievementAddict)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_addict));
        if (achievementGreedy)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_greedy));
        if (achievementCreator)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_creator));
        if (achievementSupporter)
            Games.Achievements.unlock(mGoogleApiClient, getString(R.string.achievement_supporter));
    }
}
