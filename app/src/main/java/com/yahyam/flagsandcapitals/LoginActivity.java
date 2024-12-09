package com.yahyam.flagsandcapitals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yahyam.flagsandcapitals.model.AUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 100;
    public static final String MY_PREFS_NAME = "PublicShared";
    TextInputEditText username, whatsapp, id;
    ImageView addPerson;
    LinearLayout editImage;
    CircleImageView imagePerson;
    FirebaseFirestore db;
    MaterialButton signupButton;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        whatsapp = findViewById(R.id.numberWhats);
        id = findViewById(R.id.number_id);
        imagePerson = findViewById(R.id.profile_image);
        addPerson = findViewById(R.id.add_image);
        editImage = findViewById(R.id.edit_image);
        signupButton = findViewById(R.id.button_signup);
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });
        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.requireNonNull(username.getText()).toString().isEmpty()) {
                    username.setError(getString(R.string.errorusername));
                } else if (whatsapp.getText().toString().isEmpty()) {
                    whatsapp.setError(getString(R.string.errorwhatsapp));
                } else if (whatsapp.getText().toString().charAt(0) != '+') {
                    whatsapp.setError(getString(R.string.errorwhatsappplus));
                } else if (id.getText().toString().isEmpty()) {
                    id.setError(getString(R.string.erroruid));
                } else if (id.getText().toString().length() < 10) {
                    id.setError(getString(R.string.erroruidlength));
                } else
                    showDialogC();
                    saveDataOnFirebase();
            }
        });

    }
    ProgressDialog progressdialog;
    private void showDialogC() {
        progressdialog = new ProgressDialog(this);
        progressdialog.setMessage("Please Wait....");
        progressdialog.show();
    }


    private void saveDataOnFirebase() {
        uploadImageToFirebase();
    }
    private void uploadImageToFirebase() {

        StorageReference storageRef = storage.getReference();

// Create a reference to "mountains.jpg"
        StorageReference mountainsRef = storageRef.child(id.getText().toString()+".jpg");

// Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageRef.child("images/"+id.getText().toString()+"/"+id.getText().toString()+".jpg");

// While the file names are the same, the references point to different files
        mountainsRef.getName().equals(mountainImagesRef.getName());    // true
        mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false
        imagePerson.setDrawingCacheEnabled(true);
        imagePerson.buildDrawingCache();
        Bitmap bitmap = imagePerson.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
//                        String uri1 = uri.getPath();
                        uploadData(uri.toString());
                    }
                });
//                Log.e("rrr",downloadUrl);
            }
        });
    }
    private void tt(int tt) {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("tt", tt);
        editor.apply();
    }
    private void uploadData(String uri2) {
        AUser aUser = new AUser(username.getText().toString(), whatsapp.getText().toString(), id.getText().toString());
        aUser.setImageProfile(uri2);
        aUser.setCoins(""+0);
        aUser.setClk(""+0);
        db.collection("users")
                .document(id.getText().toString())
                .set(aUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        savePubgUid(aUser.getId());
                        Intent intent = new Intent(LoginActivity.this, GameActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("urlImage",uri2);
                        startActivity(intent);
                        finish();
                        tt(0);
                        progressdialog.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                        Toast.makeText(LoginActivity.this, getString(R.string.errorconnection), Toast.LENGTH_LONG).show();
                        progressdialog.dismiss();
                    }
                });

    }

    private void savePubgUid(String pubgUid) {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("pubgUid", pubgUid);
        editor.apply();
    }


    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                Bitmap newBitmapImage = Bitmap.createScaledBitmap(selectedImage,selectedImage.getWidth()/2,selectedImage.getHeight()/2,true);
                imagePerson.setImageBitmap(newBitmapImage);
                addPerson.setVisibility(View.GONE);
                editImage.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(LoginActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
}