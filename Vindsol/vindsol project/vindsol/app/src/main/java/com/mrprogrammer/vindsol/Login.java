package com.mrprogrammer.vindsol;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Login extends AppCompatActivity {


    CardView cardView;
    ImageView google;

    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignClient;
    private final static int RC_SIGN_IN = 123;
    private FirebaseDatabase rootnode;
    private DatabaseReference reference;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {

            Intent i = new Intent(Login.this, first.class);
            i.putExtra("photourl", user.getPhotoUrl().toString());
            i.putExtra("email", user.getEmail());
            i.putExtra("username", user.getDisplayName());
            startActivity(i);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        createrequest();

        cardView = findViewById(R.id.login);
        google=findViewById(R.id.google);

        try {
            Glide.with(Login.this).load("https://cdn-icons-png.flaticon.com/512/281/281764.png").into(google);
        } catch (Exception e) {
            e.printStackTrace();
        }

        progressBar = findViewById(R.id.pro);
        cardView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                signin();
            }
        });

    }


    private void createrequest() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignClient = GoogleSignIn.getClient(this, gso);

    }

    private void signin() {


        Intent sign = mGoogleSignClient.getSignInIntent();
        startActivityForResult(sign, RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);

            } catch (ApiException e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String username = user.getDisplayName();
                    progressBar.setVisibility(View.INVISIBLE);


                    String newema = user.getEmail().replace("@", "");
                    newema = newema.replace(".", "");
                    newema = newema.replace("#", "");
                    newema = newema.replace("$", "");
                    newema = newema.replace("[", "");
                    newema = newema.replace("]", "");

                    rootnode = FirebaseDatabase.getInstance();
                    reference = rootnode.getReference("UserLogindata").push();
                    try {
                        reference.child("Username").setValue(user.getDisplayName());
                        reference.child("Email").setValue(user.getEmail());
                        reference.child("Mobile").setValue(user.getPhoneNumber());
                        reference.child("Imageurl").setValue(user.getPhotoUrl().toString());
                        reference.child("Providerid").setValue(user.getProviderId().toString());
                        reference.child("Uid").setValue(user.getUid().toString());

                        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                        String currentTime = new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date());

                        reference.child("date").setValue(currentDate);
                        reference.child("time").setValue(currentTime);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(Login.this, "Welcome " + username, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, first.class);


                    i.putExtra("photourl", user.getPhotoUrl().toString());
                    i.putExtra("email", user.getEmail());
                    i.putExtra("username", user.getDisplayName());
                    startActivity(i);
                    finish();


                } else {
                    progressBar.setVisibility(View.INVISIBLE);


                }

            }
        });
    }

}