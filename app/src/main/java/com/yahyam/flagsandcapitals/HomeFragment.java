package com.yahyam.flagsandcapitals;

import static android.content.Context.MODE_PRIVATE;
import static com.yahyam.flagsandcapitals.LoginActivity.MY_PREFS_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

import java.util.Random;

public class HomeFragment extends Fragment {
    String[] countryArr = new String[]{"afghanistan", "albania", "algeria", "andorra", "angola", "anguilla", "argentina", "armenia", "aruba", "australia", "austria", "azerbaijan", "bahamas", "bahrain", "bangladesh", "barbados", "belarus", "belgium", "belize", "benin", "bermuda", "bhutan", "bolivia", "botswana", "brazil", "brunei", "bulgaria", "burkina_faso", "burundi", "cambodia", "cameroon", "canada", "canary_islands", "cape_verde", "cayman_islands", "chad", "chile", "china", "colombia", "comoros", "costa_rica", "croatia", "cuba", "curacao", "cyprus", "czech_republic", "denmark", "djibouti", "dominica", "dominican_republic", "east_timor", "ecuador", "egypt", "el_salvador", "england", "equatorial_guinea", "eritrea", "estonia", "ethiopia", "european_union", "finland", "france", "french_polynesia", "gabon", "gambia", "georgia", "germany", "ghana", "gibraltar", "greece", "greenland", "grenada", "guatemala", "guinea", "guinea_bissau", "haiti", "hawaii", "honduras", "hong_kong", "hungary", "iceland", "india", "indonesia", "iran", "iraq", "ireland", "italy", "ivory_coast", "jamaica", "japan", "jersey", "jordan", "kazakhstan", "kenya", "kiribati", "kosovo", "kwait", "kyrgyzstan", "laos", "latvia", "lebanon", "lesotho", "liberia", "libya", "liechtenstein", "lithuania", "luxembourg", "macao", "madagascar", "malawi", "maldives", "mali", "malta", "marshall_island", "mauritania", "mauritius", "mexico", "moldova", "monaco", "mongolia", "montenegro", "morocco", "mozambique", "myanmar", "namibia", "nato", "nauru", "nepal", "netherlands", "new_zealand", "nicaragua", "niger", "nigeria", "north_korea", "northern_cyprus", "norway", "oman", "pakistan", "palau", "palestine", "panama", "papua_new_guinea", "paraguay", "peru", "philippines", "poland", "portugal", "puerto_rico", "qatar", "republic_of_macedonia", "romania", "russia", "rwanda", "samoa", "san_marino", "saudi_arabia", "scotland", "senegal", "serbia", "seychelles", "sierra_leone", "singapore", "slovakia", "slovenia", "solomon_islands", "somalia", "somaliland", "south_africa", "south_korea", "south_sudan", "spain", "sri_lanka", "sudan", "suriname", "swaziland", "sweden", "switzerland", "syria", "taiwan", "tajikistan", "tanzania", "thailand", "togo", "tunisia", "turkey", "turkmenistan", "uganda", "ukraine", "united_arab_emirates", "united_kingdom", "united_nations", "united_states", "uruguay", "uzbekistan", "vanuatu", "vatican_city", "venezuela", "vietnam", "wales", "yemen", "zambia", "zimbabwe"};
    MaterialCardView card1, card2, card3, card4;
    ImageView flag1, flag2, flag3, flag4 , true_image,false_image,true1,true2,true3,true4;
    CircularRevealCardView question;
    int trueAn,falseAn;
    TextView nameFlag;
    int sizeArr = countryArr.length;
    int trueAns;

    // TODO: Rename parameter arguments, choose names that match
    Animation am;
    private AdView adView5;

    void animationTrue(){
        true_image.setVisibility(View.VISIBLE);
        am = AnimationUtils.loadAnimation(getActivity(), R.anim.animation);
        true_image.startAnimation(am);

    }
    void animationFalse(){
        false_image.setVisibility(View.VISIBLE);
        am = AnimationUtils.loadAnimation(getActivity(), R.anim.animation);
        false_image.startAnimation(am);

    }
    String[] flags = new String[4];

    private String[] getFourFlag() {
        if (isQue){
            true1.setVisibility(View.GONE);
            true2.setVisibility(View.GONE);
            true3.setVisibility(View.GONE);
            true4.setVisibility(View.GONE);
            isQue = false;
        }
        Random random2 = new Random();
        trueAns = random2.nextInt(4);
        flags = new String[4];
        boolean s = true;
        int index = 0;
        int[] randomArr = new int[4];
        Random random = new Random();
        while (s) {
            if (index < 4) {
                /*int low = 0;
                int high = 4;
                int randomNumber = random.nextInt(high-low) + low;*/
                int randomNumber = random.nextInt(sizeArr);
                if (index == 0) {
                    randomArr[0] = randomNumber;
                    index++;
                } else {
                    switch (index) {
                        case 1:
                            if (randomNumber != randomArr[0]) {
                                randomArr[1] = randomNumber;
                                index++;
                            }
                            break;
                        case 2:
                            if (randomNumber != randomArr[0] && randomNumber != randomArr[1]) {
                                randomArr[2] = randomNumber;
                                index++;
                            }
                            break;
                        case 3:
                            if (randomNumber != randomArr[0] && randomNumber != randomArr[1] && randomNumber != randomArr[2]) {
                                randomArr[3] = randomNumber;
                                index++;
                            }
                            break;
                    }
                }
            } else s = false;
        }
        int resourceId = this.getResources().getIdentifier(countryArr[randomArr[trueAns]], "string", getActivity().getPackageName());
        String s1 = getString(resourceId);
        nameFlag.setText(s1);


        flag1.setImageResource(getImageResourceId(getActivity(), countryArr[randomArr[0]]));
        flag2.setImageResource(getImageResourceId(getActivity(), countryArr[randomArr[1]]));
        flag3.setImageResource(getImageResourceId(getActivity(), countryArr[randomArr[2]]));
        flag4.setImageResource(getImageResourceId(getActivity(), countryArr[randomArr[3]]));

        return flags;
    }

    public static int getImageResourceId(Context context, String name) {
        try {
            return context.getResources().getIdentifier(name, "drawable",
                    context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    private void saveAns(int trueAns,int falseAns) {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("trueAns", trueAns);
        editor.putInt("falseAns", falseAns);
        editor.apply();
    }
    private int[] getAns() {
        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int trueAns = prefs.getInt("trueAns", 0);//"No name defined" is the default value.
        int falseAns = prefs.getInt("falseAns", 0);//"No name defined" is the default value.
        return new int[]{trueAns,falseAns};
    }

    @Override
    public void onPause() {
        super.onPause();
        int[] tfAns =getAns();
        saveAns(tfAns[0]+trueAn,tfAns[1]+falseAn);
        trueAn = 0;
        falseAn = 0;
    }
    private void tt(int tt) {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("tt", tt);
        editor.apply();
    }
    private boolean isQue = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        question = view.findViewById(R.id.question);
        true1 = view.findViewById(R.id.true1);
        true2 = view.findViewById(R.id.true2);
        true3 = view.findViewById(R.id.true3);
        true4 = view.findViewById(R.id.true4);
        flag1 = view.findViewById(R.id.flag1);
        flag2 = view.findViewById(R.id.flag2);
        flag3 = view.findViewById(R.id.flag3);
        flag4 = view.findViewById(R.id.flag4);
        card1 = view.findViewById(R.id.card1);
        card2 = view.findViewById(R.id.card2);
        card3 = view.findViewById(R.id.card3);
        card4 = view.findViewById(R.id.card4);
        nameFlag = view.findViewById(R.id.name_flag);
        true_image = view.findViewById(R.id.true_image);
        false_image = view.findViewById(R.id.false_image);
        adView5 = view.findViewById(R.id.adView5);
        loadAd5();
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double d = tt();
                if (tt()>0){
                    isQue = true;
                    switch (trueAns){
                        case 0:
                            true1.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            true2.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            true3.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            true4.setVisibility(View.VISIBLE);
                            break;
                    }
                    tt((int) (d-1));
                    TextView sa = getActivity().findViewById(R.id.text_coin);
                    sa.setText((int) (d-1)+"");
                }else {
                    String s = getResources().getString(R.string.no_coins);
                    Toast.makeText(getContext(), ""+s, Toast.LENGTH_SHORT).show();
                }

            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trueAns == 0) {
                    trueAn++;
                    animationTrue();
                    getFourFlag();
                }else {
                    falseAn++;
                    animationFalse();
                    getFourFlag();
                }
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trueAns == 1) {
                    trueAn++;
                    animationTrue();
                    getFourFlag();
                }else {
                    falseAn++;
                    animationFalse();
                    getFourFlag();
                }
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trueAns == 2) {
                    trueAn++;
                    animationTrue();
                    getFourFlag();
                }else {
                    falseAn++;
                    animationFalse();
                    getFourFlag();
                }
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trueAns == 3) {
                    trueAn++;
                    animationTrue();
                    getFourFlag();
                }else {
                    falseAn++;
                    animationFalse();
                    getFourFlag();
                }
            }
        });
        getFourFlag();
        return view;
    }
    private int tt() {
        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int tt = prefs.getInt("tt", 0);//"No name defined" is the default value.
        return tt;
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

}