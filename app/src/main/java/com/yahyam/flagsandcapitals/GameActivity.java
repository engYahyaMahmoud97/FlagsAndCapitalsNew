package com.yahyam.flagsandcapitals;

import static com.yahyam.flagsandcapitals.LoginActivity.MY_PREFS_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.circularreveal.cardview.CircularRevealCardView;


public class GameActivity extends AppCompatActivity {
    CircularRevealCardView goToProfile,add_coin;
    ViewPager viewPager;
    TextView coins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        goToProfile = findViewById(R.id.back_button);
        add_coin = findViewById(R.id.add_coin);
        coins = findViewById(R.id.text_coin);

        coins.setText(tt()+"");
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        add_coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
        goToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameActivity.this, HomeActivity.class));
                finish();
            }
        });



    }
    private int tt() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int tt = prefs.getInt("tt", 0);//"No name defined" is the default value.
        return tt;
    }

}