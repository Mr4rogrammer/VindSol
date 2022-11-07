package com.mrprogrammer.vindsol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class third1 extends AppCompatActivity {

    MaterialCardView sphp;
    MaterialCardView Com;
    MaterialCardView high;
    MaterialCardView domastic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third1);

        ImageView back = findViewById(R.id.back);
        sphp = findViewById(R.id.sphp);
        Com = findViewById(R.id.com);
        high = findViewById(R.id.high);
        domastic=findViewById(R.id.dom);

        ImageView imageView = findViewById(R.id.main1);
        TextView title = findViewById(R.id.main2);
        try {
            Intent i = getIntent();
            int val = Integer.valueOf(i.getStringExtra("key"));

            if (val == 1) {

                imageView.setImageResource(R.drawable.a5);
                title.setText("Air Source Heat Pump");


            } else if (val == 2) {
                high.setVisibility(View.GONE);
                imageView.setImageResource(R.drawable.a7);
                title.setText("Thermodynamics Solar Heat Pump");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }





        domastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(third1.this, Doma.class);
                startActivity(i);
            }
        });



        high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(third1.this, HighTemp.class);
                startActivity(i);
            }
        });


        Com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(third1.this, MainActivity2.class);
                startActivity(i);
            }
        });


        sphp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(third1.this, third.class);
                startActivity(i);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}