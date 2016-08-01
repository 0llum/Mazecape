package com.ollum.mazecape.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ollum.mazecape.Activities.MainActivity;
import com.ollum.mazecape.R;
import com.ollum.mazecape.util.IabHelper;
import com.ollum.mazecape.util.IabResult;
import com.ollum.mazecape.util.Purchase;

public class ShopFragment extends Fragment implements View.OnClickListener {

    RelativeLayout relativeLayout;
    Button button1heart, button5hearts, button100stars, button200stars, button500stars, button1000stars;
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_layout_shop);

        button1heart = (Button) view.findViewById(R.id.button_1_heart);
        button1heart.setOnClickListener(this);

        button5hearts = (Button) view.findViewById(R.id.button_5_hearts);
        button5hearts.setOnClickListener(this);

        button100stars = (Button) view.findViewById(R.id.button_100_stars);
        button100stars.setOnClickListener(this);

        button200stars = (Button) view.findViewById(R.id.button_200_stars);
        button200stars.setOnClickListener(this);

        button500stars = (Button) view.findViewById(R.id.button_500_stars);
        button500stars.setOnClickListener(this);

        button1000stars = (Button) view.findViewById(R.id.button_1000_stars);
        button1000stars.setOnClickListener(this);

        mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
            @Override
            public void onIabPurchaseFinished(IabResult result, Purchase info) {
                if (result.isFailure()) {
                    Toast.makeText(getContext(), "There was an error, please try again!", Toast.LENGTH_LONG).show();
                } else if (info.getSku().equals("100_stars")) {
                    MainActivity.showAds = false;
                    MainActivity.allStars += 100;
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    Toast.makeText(getContext(), "Purchase successfully!", Toast.LENGTH_LONG).show();
                } else if (info.getSku().equals("200_stars")) {
                    MainActivity.showAds = false;
                    MainActivity.allStars += 200;
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    Toast.makeText(getContext(), "Purchase successfully!", Toast.LENGTH_LONG).show();
                } else if (info.getSku().equals("500_stars")) {
                    MainActivity.showAds = false;
                    MainActivity.allStars += 500;
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    Toast.makeText(getContext(), "Purchase successfully!", Toast.LENGTH_LONG).show();
                } else if (info.getSku().equals("1000_stars")) {
                    MainActivity.showAds = false;
                    MainActivity.allStars += 1000;
                    MainActivity.starsCounter.setText("" + MainActivity.allStars);
                    Toast.makeText(getContext(), "Purchase successfully!", Toast.LENGTH_LONG).show();
                }
            }
        };

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1_heart:
                if (MainActivity.allStars >= 5) {
                    if (MainActivity.lives < 5) {
                        MainActivity.allStars -= 5;
                        MainActivity.lives++;
                        MainActivity.starsCounter.setText("" + MainActivity.allStars);
                        MainActivity.livesCounter.setText("" + MainActivity.lives);
                        Toast.makeText(getContext(), "Purchase successfully!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "You already are at 5 lives!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Not enough Stars!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_5_hearts:
                if (MainActivity.allStars >= 20) {
                    if (MainActivity.lives < 5) {
                        MainActivity.allStars -= 20;
                        while (MainActivity.lives < 5) {
                            MainActivity.lives++;
                        }
                        MainActivity.starsCounter.setText("" + MainActivity.allStars);
                        MainActivity.livesCounter.setText("" + MainActivity.lives);
                        Toast.makeText(getContext(), "Purchase successfully!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "You already are at 5 lives!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Not enough Stars!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_100_stars:
                try {
                    MainActivity.mHelper.launchPurchaseFlow(getActivity(), "100_stars", 10001, mPurchaseFinishedListener, "");
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_200_stars:
                try {
                    MainActivity.mHelper.launchPurchaseFlow(getActivity(), "200_stars", 10001, mPurchaseFinishedListener, "");
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_500_stars:
                try {
                    MainActivity.mHelper.launchPurchaseFlow(getActivity(), "500_stars", 10001, mPurchaseFinishedListener, "");
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button_1000_stars:
                try {
                    MainActivity.mHelper.launchPurchaseFlow(getActivity(), "1000_stars", 10001, mPurchaseFinishedListener, "");
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


}
