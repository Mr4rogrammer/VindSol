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

public class Solar extends AppCompatActivity {

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
        setContentView(R.layout.activity_solar);

        {
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


                    TextView model, Material, dim, test;

                    model = findViewById(R.id.model);
                    Material = findViewById(R.id.Material);
                    dim = findViewById(R.id.dim);
                    test = findViewById(R.id.test);

                    error.setVisibility(View.GONE);
                    if (noperson.getText().toString().isEmpty()) {
                        noperson.setError("Input Required");
                    } else if (Integer.parseInt(noperson.getText().toString()) > 13) {

                        error.setVisibility(View.VISIBLE);
                        error.setText("* No Model Found !");
                    } else {


                        String val = noperson.getText().toString();
                        progressBar.setVisibility(View.VISIBLE);

                        int result = Integer.parseInt(val);
                        String typeinfo = null;
                        if (result >= 1 && result <= 5) {
                            typeinfo = "MiNi1-200";


                        } else if (result >= 6 && result <= 7) {
                            typeinfo = "MiNi1-300";

                        } else if (result >= 8 && result <= 9) {
                      //      Toast.makeText(Solar.this, String.valueOf(result), Toast.LENGTH_SHORT).show();
                            typeinfo = "MiNi2-300";

                        } else if (result >= 10 && result <= 11) {
                            typeinfo = "MiNi2-500";

                        }
                        else if (result >= 12 && result <= 13) {
                            typeinfo = "MiNi4-500";

                        }


                        try {

                            reference = rootnode.getReference("Thermodynamics").child("Model").child(typeinfo).child("value");

                              } catch (Exception e) {
                            Toast.makeText(Solar.this, "No data found !", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        reference.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {


                               //  Toast.makeText(Solar.this, String.valueOf(snapshot.getChildrenCount()), Toast.LENGTH_SHORT).show();
                                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                                    Data data = snapshot1.getValue(Data.class);


                                    if (i == 0) {
                                        model.setText(data.getValue());

                                    }
                                    if (i == 9) {
                                        Material.setText(data.getValue());

                                    }
                                    if (i == 10) {
                                        dim.setText(data.getValue());

                                    }
                                    if (i == 11) {
                                        test.setText(data.getValue());

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


                        } else if (result >= 6 && result <= 9) {
                            typeinfo1 = "VSGLT300";

                        } else if (result >= 10 && result <= 13) {
                            typeinfo1 = "VSGLT500";

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
                    } else if (Integer.parseInt(noperson.getText().toString()) > 13) {

                        error.setVisibility(View.VISIBLE);
                        error.setText("* No model found !");
                    } else {


                        String val = noperson.getText().toString();

                        progressBar.setVisibility(View.VISIBLE);

                        startActivity(new Intent(Solar.this, Doma_tank.class).putExtra("result", Integer.parseInt(val)).putExtra("type", 1));
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
                    } else if (Integer.parseInt(noperson.getText().toString()) > 13) {

                        error.setVisibility(View.VISIBLE);
                        error.setText("* No Model Found !");
                    } else {


                        String val = noperson.getText().toString();

                        progressBar.setVisibility(View.VISIBLE);

                        startActivity(new Intent(Solar.this, Solor_info.class).putExtra("result", Integer.parseInt(val)));
                        progressBar.setVisibility(View.GONE);


                    }
                }
            });

        }
    }
}