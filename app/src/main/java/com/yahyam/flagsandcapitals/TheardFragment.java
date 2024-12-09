package com.yahyam.flagsandcapitals;

import static android.content.Context.MODE_PRIVATE;
import static com.yahyam.flagsandcapitals.LoginActivity.MY_PREFS_NAME;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.yahyam.flagsandcapitals.model.AUser;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;


public class TheardFragment extends Fragment {


    private boolean isBin;

    public TheardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    MaterialButton m1, m2, m3, m4, m5, m6;
    private RewardedAd rewardedAd1, rewardedAd2, rewardedAd3, rewardedAd4, rewardedAd5, rewardedAd6;
    private AdView adView1, adView2, adView3;
    private FirebaseFirestore db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theard, container, false);

        db = FirebaseFirestore.getInstance();

        m1 = view.findViewById(R.id.gift1);
        m2 = view.findViewById(R.id.gift2);
        m3 = view.findViewById(R.id.gift3);
        m4 = view.findViewById(R.id.gift4);
        m5 = view.findViewById(R.id.gift5);
        m6 = view.findViewById(R.id.gift6);
        adView1 = view.findViewById(R.id.adView1);
        adView2 = view.findViewById(R.id.adView2);
        adView3 = view.findViewById(R.id.adView3);
        sa = getActivity().findViewById(R.id.text_coin);
        initialM1();
        initialM2();
        initialM3();
        initialM4();
        initialM5();
        initialM6();
        loadAd1();
        loadAd2();
        loadAd3();
        /*final Runnable r = new Runnable() {
            public void run() {
                initialM1();
                initialM2();
                initialM3();
                initialM4();
                initialM5();
                initialM6();
                loadAd1();
                loadAd2();
                loadAd3();
            }
        };*/

        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAd(rewardedAd1, 1);
            }
        });

        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAd(rewardedAd2, 2);
            }
        });

        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAd(rewardedAd3, 3);
            }
        });

        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAd(rewardedAd4, 4);
            }
        });

        m5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAd(rewardedAd5, 5);
            }
        });

        m6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAd(rewardedAd6, 6);
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


    private void showAd(RewardedAd rewardedAd, int a) {
        if (rewardedAd != null) {
            Activity activityContext = getActivity();
            rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    Log.d("TAG", "The user earned the reward.");
                    int b = 0;
                    switch (a){
                        case 1:
                            m1.setEnabled(false);
                            b = 2;
                            break;
                        case 2:
                            m2.setEnabled(false);
                            b = 1;
                            break;
                        case 3:
                            m3.setEnabled(false);
                            b = 2;
                            break;
                        case 4:
                            m4.setEnabled(false);
                            b = 1;
                            break;
                        case 5:
                            m5.setEnabled(false);
                            b = 2;
                            break;
                        case 6:
                            m6.setEnabled(false);
                            b = 1;
                            break;
                    }
                    ucl(b);
                    b = tt() + b;
                    tt(b);
                    if (b>59&&!isBin){
                        ttuu(getPubgUid(),60+"");
                    }else {
                        sa.setText(b+"");
                        Toast.makeText(activityContext, "" + b, Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            Log.d("TAG", "The rewarded ad wasn't ready yet.");
        }
    }
    TextView sa;
    private void ttuu(String s, String s1) {
        db.collection("users").document(s).update("coins", s1)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("ewewew", "DocumentSnapshot successfully updated!");
                        tt(0);
                        showDialogs();
                        sa.setText(0+"");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@androidx.annotation.NonNull Exception e) {
                        Log.w("ewewew", "Error updating document", e);
                    }
                });
    }

    private void showDialogs() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    private void ucl(int s1) {
        db.collection("users").document(getPubgUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@androidx.annotation.NonNull Task<DocumentSnapshot> task) {
                AUser a = task.getResult().toObject(AUser.class);
                isBin = a.isBin();
                int o = Integer.parseInt(a.getClk())+s1;
                db.collection("users").document(getPubgUid()).update("clk", ""+o)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("ewewew", "DocumentSnapshot successfully updated!");

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@androidx.annotation.NonNull Exception e) {
                                Log.w("ewewew", "Error updating document", e);
                            }
                        });
            }
        });

    }
    private String getPubgUid() {
        SharedPreferences prefs = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString("pubgUid", "null");//"No name defined" is the default value.
        return name;
    }
    private void initialM6() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(getActivity().getApplicationContext(), "ca-app-pub-8008323357438881/7105495364",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d("TAG", loadAdError.toString());
                        rewardedAd6 = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd6 = ad;
                        m6.setEnabled(true);
                        Log.d("TAG", "Ad was loaded.");
                    }
                });
    }

    private void initialM5() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(getActivity().getApplicationContext(), "ca-app-pub-8008323357438881/9731658702",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d("TAG", loadAdError.toString());
                        rewardedAd5 = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd5 = ad;
                        m5.setEnabled(true);
                        Log.d("TAG", "Ad was loaded.");
                    }
                });
    }

    private void initialM4() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(getActivity().getApplicationContext(), "ca-app-pub-8008323357438881/5245618780",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d("TAG", loadAdError.toString());
                        rewardedAd4 = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd4 = ad;
                        m4.setEnabled(true);
                        Log.d("TAG", "Ad was loaded.");
                    }
                });
    }

    private void initialM3() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(getActivity().getApplicationContext(), "ca-app-pub-8008323357438881/8254925500",
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
                        m3.setEnabled(true);
                        Log.d("TAG", "Ad was loaded.");
                    }
                });
    }

    private void initialM2() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(getActivity().getApplicationContext(), "ca-app-pub-8008323357438881/6011905542",
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
                        m2.setEnabled(true);
                        Log.d("TAG", "Ad was loaded.");
                    }
                });
    }

    private void initialM1() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(getActivity().getApplicationContext(), "ca-app-pub-8008323357438881/8810407397",
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
                        m1.setEnabled(true);
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
}