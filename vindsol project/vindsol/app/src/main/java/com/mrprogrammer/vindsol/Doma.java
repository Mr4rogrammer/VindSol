package com.mrprogrammer.vindsol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mrprogrammer.vindsol.SWP.Data;

public class Doma extends AppCompatActivity {
    ImageView Back;
    TextInputEditText noperson;

    TextView error;
    Button check, tank;
    ProgressBar progressBar;
    private FirebaseDatabase rootnode;
    int i = 0;
    int ii = 0;
    LinearLayout syblay;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doma);
        rootnode = FirebaseDatabase.getInstance();

        Back = findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        noperson = findViewById(R.id.noperson);

        check = findViewById(R.id.check);
        progressBar = findViewById(R.id.pro);
        error = findViewById(R.id.error);

        Button check1 = findViewById(R.id.check1);

        check1.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                syblay = findViewById(R.id.sublay);
                syblay.setVisibility(View.GONE);
                TextView heat, lhr, lhr1, power;

                heat = findViewById(R.id.heat);
                lhr = findViewById(R.id.lhr);
                lhr1 = findViewById(R.id.lhr1);
                power = findViewById(R.id.power);

                error.setVisibility(View.GONE);
                if (noperson.getText().toString().isEmpty()) {
                    noperson.setError("Input Required");
                } else if (Integer.parseInt(noperson.getText().toString()) > 18) {

                    error.setVisibility(View.VISIBLE);
                    error.setText("* Kindly select from commercial heat pump as the required Hot water capacity heat pumps will fall under under commercial range of heat pumps");
                } else {


                    String val = noperson.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);

                    int result = Integer.parseInt(val);
                    String typeinfo = null;
                    if (result >= 1 && result <= 5) {
                        typeinfo = "VDHP01TR";


                    } else if (result >= 6 && result <= 9) {
                        typeinfo = "VDHP15TR";

                    } else if (result >= 10 && result <= 13) {
                        typeinfo = "VDHP23TR";

                    } else if (result >= 14 && result <= 18) {
                        typeinfo = "VDHP3TR";

                    }


                    try {

                        reference = rootnode.getReference("Doma").child("Model").child(typeinfo).child("value");
                    } catch (Exception e) {
                        Toast.makeText(Doma.this, "No data found !", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    reference.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            // Toast.makeText(Doma.this, String.valueOf(snapshot.getChildrenCount()), Toast.LENGTH_SHORT).show();
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                                Data data = snapshot1.getValue(Data.class);


                                if (i == 0) {
                                    heat.setText(data.getValue());

                                }
                                if (i == 2) {
                                    lhr.setText(data.getValue());

                                }
                                if (i == 3) {
                                    lhr1.setText(data.getValue());

                                }
                                if (i == 6) {
                                    power.setText(data.getValue());

                                    break;
                                }

                                i = i + 1;
                            }

                            i=0;


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    String typeinfo1 = null;

                    if (result >= 1 && result <= 5) {
                        typeinfo1 = "VSGLT200";


                    } else if (result >= 6 && result <= 7) {
                        typeinfo1 = "VSGLT300";

                    } else if (result >= 8 && result <= 9) {
                        typeinfo1 = "VSGLT500";

                    } else if (result >= 10 && result <= 13) {
                        typeinfo1 = "VSGLT600";

                    } else if (result >= 13 && result <= 18) {
                        typeinfo1 = "VSGLT700";

                    }


                  //  Toast.makeText(Doma.this, typeinfo1, Toast.LENGTH_SHORT).show();

                    reference = rootnode.getReference("DomaTank").child("Model").child(typeinfo1).child("value");

                    TextView vos = findViewById(R.id.vo);
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                     //       Toast.makeText(Doma.this, String.valueOf(snapshot.getChildrenCount()), Toast.LENGTH_SHORT).show();
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                                Data data = snapshot1.getValue(Data.class);



                                if (ii == 1) {
                                    vos.setText(data.getValue());

                                    syblay.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.GONE);
                              //      Toast.makeText(Doma.this, data.getValue(), Toast.LENGTH_SHORT).show();
                                }
                                ii = ii + 1;


                            }
                            ii=0;
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }


            }
        });


        tank = findViewById(R.id.watertank);

        tank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error.setVisibility(View.GONE);
                if (noperson.getText().toString().isEmpty()) {
                    noperson.setError("Input Required");
                } else if (Integer.parseInt(noperson.getText().toString()) > 18) {

                    error.setVisibility(View.VISIBLE);
                    error.setText("* Kindly select from commercial heat pump as the required Hot water capacity heat pumps will fall under under commercial range of heat pumps");
                } else {


                    String val = noperson.getText().toString();

                    progressBar.setVisibility(View.VISIBLE);

                    startActivity(new Intent(Doma.this, Doma_tank.class).putExtra("result", Integer.parseInt(val)).putExtra("type", 2));
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error.setVisibility(View.GONE);
                if (noperson.getText().toString().isEmpty()) {
                    noperson.setError("Input Required");
                } else if (Integer.parseInt(noperson.getText().toString()) > 18) {

                    error.setVisibility(View.VISIBLE);
                    error.setText("* Kindly select from commercial heat pump as the required Hot water capacity heat pumps will fall under under commercial range of heat pumps");
                } else {


                    String val = noperson.getText().toString();

                    progressBar.setVisibility(View.VISIBLE);

                    startActivity(new Intent(Doma.this, Dom_Info.class).putExtra("result", Integer.parseInt(val)));
                    progressBar.setVisibility(View.GONE);


                }
            }
        });
    }
}