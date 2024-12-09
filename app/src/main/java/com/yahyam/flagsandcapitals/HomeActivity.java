package com.yahyam.flagsandcapitals;

import static com.yahyam.flagsandcapitals.LoginActivity.MY_PREFS_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yahyam.flagsandcapitals.model.AUser;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    FirebaseFirestore db;
    CircleImageView circleImageView;
    CircularRevealCardView backButton;
    MaterialButton reset_button, materialButton;
    TextView uidText, usernameText, text_rate_f, text_true, text_false, text33;
    private AdView adView4;
    private boolean isBin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String pubguid = getPubgUid();


        circleImageView = findViewById(R.id.profile_image);
        usernameText = findViewById(R.id.text_username);
        uidText = findViewById(R.id.text_id_pubg);
        backButton = findViewById(R.id.back_button);
        text_rate_f = findViewById(R.id.text_rate_f);
        text_true = findViewById(R.id.text_true);
        text_false = findViewById(R.id.text_false);
        text33 = findViewById(R.id.text33);
        reset_button = findViewById(R.id.reset_button);
        materialButton = findViewById(R.id.materialButton);
        adView4 = findViewById(R.id.adView4);
        db = FirebaseFirestore.getInstance();
        loadAd4();
        checkLogin(pubguid);
        int tt = tt();
        text33.setText(tt + "");
        if (tt>59&&!isBin){
            ttuu(pubguid,60+"");
        }
            //materialButton.setEnabled(true);

        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAns();
            }
        });
        math();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, GameActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


        /*String linkImage = getIntent().getStringExtra("urlImage");
        String uid = getIntent().getStringExtra("pubguid");
        String username = getIntent().getStringExtra("username");

        try {
            if ((!uid.isEmpty()) && (!username.isEmpty())) {
                usernameText.setText(username);
                uidText.setText(uid);
            }
        } catch (Exception e) {

        }
        Picasso.get().load(linkImage).into(circleImageView);*/
    }

    private void ttuu(String s, String s1) {
        db.collection("users").document(s).update("coins", s1)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("ewewew", "DocumentSnapshot successfully updated!");
                        tt(0);
                        showDialogs();
                        text33.setText(0+"");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("ewewew", "Error updating document", e);
                    }
                });
    }

    private void showDialogs() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

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

    private void tt(int tt) {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("tt", tt);
        editor.apply();
    }
    private void math() {
        int[] tf = getAns();
        int truePercent = 0;

        float b = tf[0] + tf[1];
        float a = tf[0];
        truePercent = (int) (a / b * 100);

        text_rate_f.setText(truePercent + "%");
        text_true.setText(tf[0] + "");
        text_false.setText(tf[1] + "");
    }

    private void resetAns() {
        saveAns(0, 0);
        text_rate_f.setText(0 + "%");
        text_true.setText(0 + "");
        text_false.setText(0 + "");
    }

    private void saveAns(int trueAns, int falseAns) {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("trueAns", trueAns);
        editor.putInt("falseAns", falseAns);
        editor.apply();
    }

    private int[] getAns() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int trueAns = prefs.getInt("trueAns", 0);//"No name defined" is the default value.
        int falseAns = prefs.getInt("falseAns", 0);//"No name defined" is the default value.
        return new int[]{trueAns, falseAns};
    }

    private String getPubgUid() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString("pubgUid", "null");//"No name defined" is the default value.
        return name;
    }

    private void checkLogin(String s) {
        db.collection("users").document(s).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                AUser user = documentSnapshot.toObject(AUser.class);
                isBin = user.isBin();
                usernameText.setText(user.getUsername());
                uidText.setText(user.getId());
                Picasso.get().load(user.getImageProfile()).into(circleImageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(HomeActivity.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private int tt() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int tt = prefs.getInt("tt", 0);//"No name defined" is the default value.
        return tt;
    }
    private void loadAd4() {
        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        adView4.loadAd(adRequest);
    }
}