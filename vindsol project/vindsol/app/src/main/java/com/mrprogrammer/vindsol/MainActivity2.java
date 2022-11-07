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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
    TextInputEditText nos, temp, ipd, heat;
    ImageView back;
    Button check;
    ProgressBar progressBar;

    double noss, temps, ipds, heats, noos;
    double result1;


    String UM;
    private FirebaseDatabase rootnode;
    private DatabaseReference reference, reference1;
    private FirebaseAuth auth;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {

            String newema = user.getEmail().replace("@", "");
            newema = newema.replace(".", "");
            newema = newema.replace("#", "");
            newema = newema.replace("$", "");
            newema = newema.replace("[", "");
            newema = newema.replace("]", "");


            UM=newema;

        } else {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        auth=FirebaseAuth.getInstance();
        back = findViewById(R.id.back);
        nos = findViewById(R.id.nos);
        temp = findViewById(R.id.temp);
        ipd = findViewById(R.id.ipd);
        heat = findViewById(R.id.heat);


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
                if (nos.getText().toString().isEmpty()) {
                    nos.setError("Input Required !");
                } else if (temp.getText().toString().isEmpty()) {
                    temp.setError("Input Required !");
                } else if (ipd.getText().toString().isEmpty()) {
                    ipd.setError("Input Required !");
                } else if (heat.getText().toString().isEmpty()) {
                    heat.setError("Input Required !");
                }
                else if((Integer.valueOf(heat.getText().toString()) <4) || (Integer.valueOf(heat.getText().toString()) >8))
                {
                    heat.setError("Enter in correct interval");
                }

                else {
                    progressBar.setVisibility(View.VISIBLE);
                    noss = Double.parseDouble(nos.getText().toString());  //C2
                    temps = Double.parseDouble(temp.getText().toString());//c14
                    ipds = Double.parseDouble(ipd.getText().toString());//c3
                    heats = Double.parseDouble(heat.getText().toString());//c6
                    noos = 1;
                    result1 = (ipds * noss);
                    result1 = result1 * 0.8;
                    result1 = result1 / heats;
                    result1 = result1 / noos;
                    result1 = result1 * (55 - temps);
                    result1 = result1 / 860 / heats;


                    progressBar.setVisibility(View.GONE);


                    if(result1 <=13)
                    {
                        String model = "VCHP-1013H";
                        startActivity(new Intent(MainActivity2.this,c1.class).putExtra("model",model));

                        return;
                    }

                    if(result1 >13  && result1 <=17)
                    {
                        String model = "VCHP-1416H";
                        startActivity(new Intent(MainActivity2.this,c1.class).putExtra("model",model));

                        return;
                    }

                    if(result1 >17  && result1 <=23)
                    {
                        String model = "VCHP-1720H";
                        startActivity(new Intent(MainActivity2.this,c1.class).putExtra("model",model));

                        return;
                    }

                    if(result1 >23  && result1 <=32)
                    {
                        String model = "VCHP-2528H";
                        startActivity(new Intent(MainActivity2.this,c1.class).putExtra("model",model));

                        return;
                    }


                    startActivity(new Intent(MainActivity2.this,before_c1.class).putExtra("result",String.valueOf(Math.round(result1))));


                }
            }
        });
    }
}