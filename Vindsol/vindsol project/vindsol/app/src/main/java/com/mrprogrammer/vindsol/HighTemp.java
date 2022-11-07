package com.mrprogrammer.vindsol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputEditText;

public class HighTemp extends AppCompatActivity {
    TextInputEditText  temp, hotwater, heat;
    ImageView back;
    Button check;
    ProgressBar progressBar;

    double  temps, heats,water;
    double result1;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_temp);


        back = findViewById(R.id.back);

        temp = findViewById(R.id.temp);

        heat = findViewById(R.id.heat);

        hotwater=findViewById(R.id.hotwater);


        check = findViewById(R.id.check);
        progressBar = findViewById(R.id.pro);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        heat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                try {
                    if((Integer.valueOf(heat.getText().toString()) <4) || (Integer.valueOf(heat.getText().toString()) >8))
                    {
                        heat.setError("Enter in correct interval");
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hotwater.getText().toString().isEmpty()) {
                    hotwater.setError("Input Required !");
                } else if (temp.getText().toString().isEmpty()) {
                    temp.setError("Input Required !");
                } else if (heat.getText().toString().isEmpty()) {
                    heat.setError("Input Required !");
                }
                else if((Integer.valueOf(heat.getText().toString()) <4) || (Integer.valueOf(heat.getText().toString()) >8))
                {
                    heat.setError("Enter in correct interval");
                }

                else {
                    progressBar.setVisibility(View.VISIBLE);
                    water = Double.parseDouble(hotwater.getText().toString());  //C7
                    temps = Double.parseDouble(temp.getText().toString());//c3

                    heats = Double.parseDouble(heat.getText().toString());//c14

                    result1 = water;
                    result1 = result1 * (75 - temps);
                    result1 = result1 / 860 / heats;


                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(HighTemp.this,HighTemp1.class).putExtra("result",String.valueOf(Math.round(result1))));

                    //   Toast.makeText(MainActivity2.this, String.valueOf((int) result1), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}