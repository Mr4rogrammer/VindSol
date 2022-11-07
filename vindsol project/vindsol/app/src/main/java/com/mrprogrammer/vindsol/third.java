package com.mrprogrammer.vindsol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mrprogrammer.vindsol.Model.PBY;

public class third extends AppCompatActivity {


    TextInputEditText length, width, depth, itemp, ftemp, humidity;
    double result1;
    double radio_value = 0.5;
    double result2;
    double c47;
    double result3;
    double result5;
    double result6;
    int lper = 0;
    Button check;

    RadioGroup radioGroup, radioGroup1, pooltype;
    ProgressBar progressBar;
    private FirebaseDatabase rootnode;
    private DatabaseReference reference;
    private FirebaseAuth auth;
    float total_volume;

    float len = 0;
    float wid = 0;
    float dep = 00;
    float it = 0;
    float ft = 0;
    float iht = 0;
    float humidi = 0;
    String UM = "";
    int PT = 0;

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


            UM = newema;
            //  Toast.makeText(this, String.valueOf(user.getPhoneNumber()), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        auth = FirebaseAuth.getInstance();

        ImageView back = findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        try {
            length = findViewById(R.id.length);
            width = findViewById(R.id.width);
            depth = findViewById(R.id.depth);
            itemp = findViewById(R.id.itemp);
            ftemp = findViewById(R.id.ftemp);


            humidity = findViewById(R.id.humidity);

            radioGroup = findViewById(R.id.radiogroup);
            radioGroup1 = findViewById(R.id.radiogroup1);

            pooltype = findViewById(R.id.pool);


            progressBar = findViewById(R.id.pro);
            check = findViewById(R.id.check);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        length.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                try {
                    if (Double.valueOf(length.getText().toString()) > 50) {
                        length.setError("Length should be below 50");

                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        width.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                try {
                    if (Double.valueOf(width.getText().toString()) > 25) {
                      width.setError("Length should be below 25");

                  }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        depth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                try {
                    if (Double.valueOf(depth.getText().toString()) > 3) {
                       depth.setError("depth should be below 3");

                   }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ftemp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                try {
                    if (Double.valueOf(ftemp.getText().toString()) > 35) {
                        ftemp.setError("Temperature should be below 35");

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


                if (length.getText().toString().isEmpty()) {
                    length.setError("Please enter the value");
                } else if (width.getText().toString().isEmpty()) {
                    width.setError("Please enter the value");
                } else if (depth.getText().toString().isEmpty()) {
                    depth.setError("Please enter the value");
                } else if (itemp.getText().toString().isEmpty()) {
                    itemp.setError("Please enter the value");
                } else if (ftemp.getText().toString().isEmpty()) {
                    ftemp.setError("Please enter the value");
                } else if (humidity.getText().toString().isEmpty()) {
                    humidity.setError("Please enter the value");
                } else if (Double.valueOf(length.getText().toString()) > 50) {
                    length.setError("Length should be below 50");

                } else if (Double.valueOf(width.getText().toString()) > 25) {
                    width.setError("Length should be below 25");

                } else if (Double.valueOf(depth.getText().toString()) > 3) {
                    depth.setError("depth should be below 3");

                } else if (Double.valueOf(ftemp.getText().toString()) > 35) {
                    ftemp.setError("Length should be below 35");

                } else {
                    progressBar.setVisibility(View.VISIBLE);

                    try {
                        len = Float.parseFloat(length.getText().toString());
                        wid = Float.parseFloat(width.getText().toString());
                        dep = Float.parseFloat(depth.getText().toString());
                        it = Float.parseFloat(itemp.getText().toString());
                        ft = Float.parseFloat(ftemp.getText().toString());
                        // iht = Float.parseFloat(itime.getText().toString());

                        humidi = Float.parseFloat(humidity.getText().toString());

                    } catch (NumberFormatException e) {
                        Toast.makeText(third.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    int radioid = radioGroup.getCheckedRadioButtonId();
                    int radioid1 = radioGroup1.getCheckedRadioButtonId();
                    int pools = pooltype.getCheckedRadioButtonId();

                    RadioButton radioButton = findViewById(radioid);
                    RadioButton radioButton1 = findViewById(radioid1);
                    RadioButton pool_type = findViewById(pools);

                    String pt = pool_type.getText().toString();

                    // Toast.makeText(third.this, pt, Toast.LENGTH_SHORT).show();
                    if (pt.equals("Kids")) {
                        PT = 2;
                    } else if (pt.equals("Regular")) {
                        PT = 4;
                    } else if (pt.equals("Competition or Training")) {
                        PT = 6;
                    }

                    // Toast.makeText(third.this, String.valueOf(PT), Toast.LENGTH_SHORT).show();

                    String vals = radioButton1.getText().toString();

                    String IO = radioButton.getText().toString();

                    if (vals.equals("24")) {
                        iht = 24;
                    } else if (vals.equals("36")) {
                        iht = 36;
                    } else if (vals.equals("48")) {
                        iht = 48;
                    }
                    //  Toast.makeText(third.this, String.valueOf(iht), Toast.LENGTH_SHORT).show();


                    if (IO.equals("Outdoor")) {
                        radio_value = 2;
                        lper = 3;
                    } else {
                        radio_value = 0.5;
                        lper = 5;
                    }
                    total_volume = len * wid * dep;
                    float surface_area = wid * len;

                    result1 = (float) (4186.8 * (ft - it) * total_volume / iht / 3600);

                    String val = String.valueOf((int) Math.ceil(ft));

                    //  Toast.makeText(third.this, String.valueOf(result1), Toast.LENGTH_SHORT).show();
                    try {
                        rootnode = FirebaseDatabase.getInstance();

                        reference = rootnode.getReference("Data1").child(val).child("data");
                    } catch (Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(third.this, "Error", Toast.LENGTH_SHORT).show();
                    }


                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            try {
                                PBY pby = snapshot.getValue(PBY.class);
                                //   Toast.makeText(third.this, String.valueOf(pby.getY()), Toast.LENGTH_SHORT).show();

                                //p
                                float c30 = (float) (pby.getPb() * (humidi / 100));
                                //Toast.makeText(third.this, String.valueOf(surface_area), Toast.LENGTH_SHORT).show();

                                //y
                                result2 = 4.1868 * pby.getY() * (0.0174 * radio_value + 0.0229) * (pby.getPb() - c30) * surface_area * (0.9934640523);

                                //  Toast.makeText(third.this, String.valueOf(pby.getPb()), Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            try {
                                double qc = 0.2 * result2;
                                double qx = result2;
                                double q = qx + qc;
                                double c45 = q / 4.1868;
                                c47 = (c45 * 1.163) / 1000;


                                // Toast.makeText(third.this, String.valueOf(c47), Toast.LENGTH_SHORT).show();

                                result3 = c47 + result1;

                                //    Toast.makeText(third.this, String.valueOf(qx), Toast.LENGTH_SHORT).show();


                                Double TSPW = (double) ft;
                                Double SWT = (double) it;
                                Double HT = 12.0;


                                double qb = total_volume * lper * 10;


                                result5 = (4.1868 * qb * (TSPW - SWT)) / HT;
                                //    Toast.makeText(third.this, String.valueOf(lper), Toast.LENGTH_SHORT).show();
                                result5 = result5 / 4.1868;
                                result5 = result5 * 0.00116222222;

                                // Toast.makeText(third.this, String.valueOf(result5), Toast.LENGTH_SHORT).show();
                                //   Toast.makeText(third.this, String.valueOf(result5), Toast.LENGTH_SHORT).show();


                                result6 = (total_volume * 1.1) / PT;

                                //   Toast.makeText(third.this, String.valueOf(result6), Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }



                            Intent i = new Intent(third.this, Info_before.class);
                            i.putExtra("result3", result3);
                            i.putExtra("result2", result2);
                            i.putExtra("result1", result1);
                            i.putExtra("result4", c47);
                            i.putExtra("result5", result5);
                            i.putExtra("result6", result6);


                            progressBar.setVisibility(View.GONE);
                            startActivity(i);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }
        });


    }
}