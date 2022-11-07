package com.mrprogrammer.vindsol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import org.jetbrains.annotations.NotNull;

public class first extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    RelativeLayout relativeLayout;
    LinearLayout website, call, location, share;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    static final float END_SCALE = 0.7f;
    String photo;
    ImageView dp;

    String username, email;

    Animation roate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        roate = AnimationUtils.loadAnimation(this, R.anim.fade_in);


        TextView textView = findViewById(R.id.version);
        try {
            textView.setText("Version : " + BuildConfig.VERSION_NAME);
        } catch (Exception e) {
            textView.setText("Version :  2.1.0");
            e.printStackTrace();
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(first.this, "Developed by : Mr.Programmer \nContact : +91 7548882167", Toast.LENGTH_LONG).show();
            }
        });


        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try {
                    String url = "tel:7548882167";
                    Intent ii = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(ii);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });


        try {
            FirebaseMessaging.getInstance().subscribeToTopic("vindsol")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {


                            }

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Intent i = getIntent();

            photo = i.getStringExtra("photourl");
            username = i.getStringExtra("username");
            email = i.getStringExtra("email");
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        try {
            dp = findViewById(R.id.dps);
            Glide.with(this).load(photo).into(dp);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        pager2 = findViewById(R.id.view_pager2);


        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   profile_dialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        pager2.setCurrentItem(0);

        drawerLayout = findViewById(R.id.drawlayout);
        relativeLayout = findViewById(R.id.moves);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        navigationView.setCheckedItem(R.id.nav_home);


        website = findViewById(R.id.website);
        call = findViewById(R.id.call);
        location = findViewById(R.id.location);

        share = findViewById(R.id.share);


        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sharemessage = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\nVindSol Heat Pump\n\nProviding Next-Gen Heat pump technology based advanced Eco-Friendly heating solutions";


                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Share");

                    intent.putExtra(Intent.EXTRA_TEXT, sharemessage);
                    startActivity(Intent.createChooser(intent, "Share by"));
                    Toast.makeText(first.this, "Thanks for Sharing !", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(first.this, "Error !", Toast.LENGTH_SHORT).show();
                }

            }
        });




        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String url = "https://maps.app.goo.gl/YiQEZUsAcebUSWPu8";
                    Intent ii = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(ii);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String url = "tel:9353017075";
                    Intent ii = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(ii);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.close();
                }
                String url = "https://vindsol.in";
                Intent ii = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                ii.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ii.setPackage("com.android.chrome");
                try {
                    startActivity(ii);
                } catch (ActivityNotFoundException ex) {
                    ii.setPackage(null);
                    startActivity(ii);

                }
            }
        });


        ImageView left = findViewById(R.id.leftdd);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });

        drawermovesani();
        View headview = navigationView.getHeaderView(0);
    }

    @Override
    public void onBackPressed() {

        try {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.close();
            } else {
                super.onBackPressed();
                finish();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:


                pager2.setCurrentItem(0);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.close();
                }


                break;

            case R.id.Disclaimer:
                startActivity(new Intent(first.this, disclaimer.class));
                break;
            case R.id.about:
                startActivity(new Intent(first.this, about.class));
                break;
            case R.id.warranty:


                Intent i = new Intent(first.this, Warranty.class);
                i.putExtra("email", email);
                i.putExtra("username", username);
                startActivity(i);


                break;

            case R.id.support:


                Intent ii = new Intent(first.this, Support.class);
                ii.putExtra("email", email);
                ii.putExtra("username", username);
                startActivity(ii);


                break;


        }
        return true;
    }

    public void drawermovesani() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.green));
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull @NotNull View drawerView, float slideOffset) {
                final float diffscaleoffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffscaleoffset;

                relativeLayout.setScaleX(offsetScale);
                relativeLayout.setScaleY(offsetScale);

                final float xoffset = drawerView.getWidth() * slideOffset;
                final float xoffsetdiff = relativeLayout.getWidth() * diffscaleoffset;
                final float xtanslation = xoffset - xoffsetdiff;
                relativeLayout.setTranslationX(xtanslation);
            }

            @Override
            public void onDrawerOpened(@NonNull @NotNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull @NotNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }


        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_home);

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.close();
        }
    }


    public void  profile_dialog()
    {
        Dialog dialog = new Dialog(first.this);


        Window window = dialog.getWindow();
        window.getAttributes().windowAnimations = R.style.Dialogani;
        window.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        dialog.setContentView(R.layout.profiledialog);

        TextView username1,email1;
        ImageView profile;
        Button logout;

        username1=dialog.findViewById(R.id.username);
        email1=dialog.findViewById(R.id.email);
        profile=dialog.findViewById(R.id.ddp);
        logout=dialog.findViewById(R.id.logout);

        Glide.with(this).load(photo).into(profile);
        username1.setText(username);
        email1.setText(email);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                GoogleSignIn.getClient(getApplicationContext(), GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).signOut();

                startActivity(new Intent(first.this, Login.class));
                dialog.dismiss();
                finish();

            }
        });


        dialog.show();
    }
}