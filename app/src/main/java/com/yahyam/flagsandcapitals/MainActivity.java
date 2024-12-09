package com.yahyam.flagsandcapitals;

import static com.yahyam.flagsandcapitals.LoginActivity.MY_PREFS_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yahyam.flagsandcapitals.model.AUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    CircularProgressIndicator progressIndicator;
    TextView progressPercent, textView2;
    int currentProgress = 0;
    FirebaseFirestore db;
    ImageView wifi_image;
    NetworkInfo mWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        progressIndicator = findViewById(R.id.circularProgressIndicator);
        progressPercent = findViewById(R.id.numProgress);
        textView2 = findViewById(R.id.textView2);
        wifi_image = findViewById(R.id.wifi_image);
        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.swiperefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


        refreshData();

    }

    ProgressDialog progressdialog;

    private void showDialogC() {
        progressdialog = new ProgressDialog(getApplicationContext());
        progressdialog.setMessage("Please Wait....");
        progressdialog.show();
    }

    private void refreshData() {

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWifi.isConnected()) {
            wifi_image.setVisibility(View.GONE);
            progressPercent.setVisibility(View.VISIBLE);
            textView2.setVisibility(View.VISIBLE);
            progressIndicator.setVisibility(View.VISIBLE);
            String pubguid = getPubgUid();
            checkLogin(pubguid);
            CountDownTimer countDownTimer = new CountDownTimer(2000, 15) {
                @Override
                public void onTick(long l) {
                    if (currentProgress != 100) {
                        currentProgress = currentProgress + 1;
                        progressIndicator.setProgress(currentProgress);
                        progressPercent.setText(currentProgress + "");
                        progressIndicator.setMax(100);
                    }
                }

                @Override
                public void onFinish() {

                }
            };
            countDownTimer.start();
        } else {
            wifi_image.setVisibility(View.VISIBLE);
            progressPercent.setVisibility(View.GONE);
            textView2.setVisibility(View.GONE);
            progressIndicator.setVisibility(View.GONE);
        }
    }

    private String getPubgUid() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString("pubgUid", "null");//"No name defined" is the default value.
        return name;
    }

    private void checkLogin(String s) {
        try {
            db.collection("users")
                    .document(s)
                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                AUser aUser = task.getResult().toObject(AUser.class);
                                try {
                                    if (aUser.getUsername().isEmpty()) {

                                    }
                                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.putExtra("urlImage", aUser.getImageProfile());
                                    intent.putExtra("pubguid", aUser.getId());
                                    intent.putExtra("username", aUser.getUsername());
                                    intent.putExtra("whatsapp", aUser.getWhatsappNumber());
                                    startActivity(intent);
                                    finish();
                                } catch (NullPointerException e) {
                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }


                            } else {

                                Log.d("ewewew", "Error getting documents: ", task.getException());
                            }
                        }
                    });


        } catch (Exception e) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

    }

}