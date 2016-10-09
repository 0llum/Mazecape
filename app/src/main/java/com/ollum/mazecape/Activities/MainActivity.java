package com.ollum.mazecape.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
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
import com.ollum.mazecape.Fragments.GameFragment;
import com.ollum.mazecape.Fragments.HelpFragment;
import com.ollum.mazecape.Fragments.LevelEditorFragment;
import com.ollum.mazecape.Fragments.LevelSelectFragment;
import com.ollum.mazecape.Fragments.MainMenuFragment;
import com.ollum.mazecape.Fragments.SettingsFragment;
import com.ollum.mazecape.Fragments.ShopFragment;
import com.ollum.mazecape.Fragments.WorldSelectFragment;
import com.ollum.mazecape.R;
import com.ollum.mazecape.util.IabHelper;
import com.ollum.mazecape.util.IabResult;
import com.ollum.mazecape.util.LoadGame;
import com.ollum.mazecape.util.SaveGame;

import java.util.HashSet;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static java.lang.System.currentTimeMillis;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
    public static boolean settingsVisible = false;
    public static boolean shopVisible = false;
    public static boolean helpVisible = false;
    public static boolean stopTime = false;
    public static boolean rated = false;
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
    public static int clickID, swoosh1ID, swoosh2ID, liveID, stepID, portalID, swordID, crackID, deathID, monsterID, holeID, starID, trapActiveID, trapInactiveID, winID, upgradeID;
    public static long logOffTime;
    public Handler livesHandler;
    public long startMillis;
    public long endMillis;
    ImageButton settingsButton, helpButton, shopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        .setDefaultFontPath("fonts/mail_ray_stuff.ttf")
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

        sendButton = (Button) findViewById(R.id.button_send);
        sendButton.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        MainMenuFragment mainMenuFragment = new MainMenuFragment();
        transaction.replace(R.id.content, mainMenuFragment, "MainMenuFragment");
        transaction.addToBackStack("MainMenuFragment");
        transaction.commit();

        starsList = new HashSet<>();

        MobileAds.initialize(this, "ca-app-pub-7666608930334273~8844493743");
        adRequest = new AdRequest.Builder().build();

        mAd = MobileAds.getRewardedVideoAdInstance(this);
        loadRewardedVideoAd();

        LoadGame.loadGame(getApplicationContext());
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
        } else if (current.equals("GameFragment") || current.equals("LevelFragment")) {
            if (GameFragment.bgm != null && GameFragment.bgm.isPlaying()) {
                GameFragment.bgm.pause();
            }
            if (GameFragment.fire != null && GameFragment.fire.isPlaying()) {
                GameFragment.fire.pause();
            }
            if (GameFragment.heartbeat != null && GameFragment.heartbeat.isPlaying()) {
                GameFragment.heartbeat.pause();
            }

            stopTime = true;

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("");
            builder.setMessage(R.string.level_select_screen);
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    resetCount++;
                    livesCounter.setText("" + lives);
                    LevelSelectFragment levelSelectFragment = new LevelSelectFragment();
                    FragmentTransaction transaction = MainActivity.fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
                    transaction.replace(R.id.content, levelSelectFragment, "LevelSelectFragment");
                    transaction.addToBackStack("LevelSelectFragment");
                    transaction.commit();
                }
            });
            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    stopTime = false;
                    if (GameFragment.bgm != null) {
                        GameFragment.bgm.start();
                    }
                    if (GameFragment.fire != null) {
                        GameFragment.fire.start();
                    }
                    if (GameFragment.heartbeat != null) {
                        GameFragment.heartbeat.start();
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
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SaveGame.saveGame(getApplicationContext());

        if (menuBGM != null && menuBGM.isPlaying()) {
            menuBGM.pause();
            menuBGM.seekTo(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoadGame.loadGame(getApplicationContext());

        FragmentManager.BackStackEntry currentFragment = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
        String current = currentFragment.getName();

        if (menuBGM != null && !current.equals("GameFragment") || current.equals("LevelFragment")) {
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
                break;
        }
    }
}
