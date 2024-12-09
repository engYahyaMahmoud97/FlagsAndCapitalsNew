/*
package com.example.flagsandcapitals;

import static android.content.Context.MODE_PRIVATE;
import static com.example.flagsandcapitals.LoginActivity.MY_PREFS_NAME;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Random;

public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    ImageView m1,m2,m3;
    private RewardedAd rewardedAd1,rewardedAd2,rewardedAd3;
    private AdView adView1,adView2,adView3,adView4,adView5,adView6,adView7,adView8,adView9;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);


        m1 = view.findViewById(R.id.gift1);
        m2 = view.findViewById(R.id.gift2);
        m3 = view.findViewById(R.id.gift3);
        adView1 = view.findViewById(R.id.adView1);
        adView2 = view.findViewById(R.id.adView2);
        adView3 = view.findViewById(R.id.adView3);
        adView4 = view.findViewById(R.id.adView4);
        adView5 = view.findViewById(R.id.adView5);
        adView6 = view.findViewById(R.id.adView6);


        initialM1();
        initialM2();
        initialM3();
        loadAd1();
        loadAd2();
        loadAd3();
        loadAd4();
        loadAd5();
        loadAd6();

        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAd(rewardedAd1,1);
            }
        });

        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAd(rewardedAd2,2);
            }
        });

        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAd(rewardedAd3,3);
            }
        });
        return view;
    }

    private void loadAd1() {
        MobileAds.initialize(getActivity().getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        adView1.loadAd(adRequest);
    }
    private void loadAd2() {
        MobileAds.initialize(getActivity().getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        adView2.loadAd(adRequest);
    }
    private void loadAd3() {
        MobileAds.initialize(getActivity().getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        adView3.loadAd(adRequest);
    }
    private void loadAd4() {
        MobileAds.initialize(getActivity().getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        adView4.loadAd(adRequest);
    }
    private void loadAd5() {
        MobileAds.initialize(getActivity().getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        adView5.loadAd(adRequest);
    }
    private void loadAd6() {
        MobileAds.initialize(getActivity().getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        adView6.loadAd(adRequest);
    }

    private void showAd(RewardedAd rewardedAd,int a) {
        if (rewardedAd != null) {
            Activity activityContext = getActivity();
            rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    Log.d("TAG", "The user earned the reward.");
                    if (a==1)
                        m1.setBackgroundColor(getResources().getColor(R.color.gift0));
                    if (a==2)
                        m2.setBackgroundColor(getResources().getColor(R.color.gift0));
                    if (a==3)
                        m3.setBackgroundColor(getResources().getColor(R.color.gift0));

                    int a=new Random().nextInt(2 - 1 + 1) + 1;
                    a=tt()+a;
                    tt(a);
                    Toast toast = new Toast(getActivity());
                    toast.setText("+"+a);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            });
        } else {
            Log.d("TAG", "The rewarded ad wasn't ready yet.");
        }
    }

    private void initialM3() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(getActivity().getApplicationContext(), "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d("TAG", loadAdError.toString());
                        rewardedAd1 = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd1 = ad;
                        m1.setBackgroundColor(getResources().getColor(R.color.gift1));
                        Log.d("TAG", "Ad was loaded.");
                    }
                });
    }

    private void initialM2() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(getActivity().getApplicationContext(), "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d("TAG", loadAdError.toString());
                        rewardedAd2 = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd2 = ad;
                        m2.setBackgroundColor(getResources().getColor(R.color.gift1));
                        Log.d("TAG", "Ad was loaded.");
                    }
                });
    }

    private void initialM1() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(getActivity().getApplicationContext(), "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d("TAG", loadAdError.toString());
                        rewardedAd3 = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd3 = ad;
                        m3.setBackgroundColor(getResources().getColor(R.color.gift1));
                        Log.d("TAG", "Ad was loaded.");
                    }
                });
    }

    private void tt(int tt) {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("tt", tt);
        editor.apply();
    }
    private int tt() {
        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int tt = prefs.getInt("tt", 0);//"No name defined" is the default value.
        return tt;
    }
}*/
