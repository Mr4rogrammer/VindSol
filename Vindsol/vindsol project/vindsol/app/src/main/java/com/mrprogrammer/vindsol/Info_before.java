package com.mrprogrammer.vindsol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Info_before extends AppCompatActivity {

    Double result, result1, result4, result5, result6;
    private String type;
    private String typeinfo;
    Button nextpage;

    TextView title_model;

    RadioGroup radioGroup;


    TextView r1, r3, r4, r5, r6;



    LinearLayout model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_before);

        model=findViewById(R.id.mainlayout);
        model.setVisibility(View.GONE);

        radioGroup = findViewById(R.id.radiogroup1);




        ImageView back = findViewById(R.id.back);
        nextpage = findViewById(R.id.detailed1);


        r1 = findViewById(R.id.result1);

        r3 = findViewById(R.id.result3);
        r4 = findViewById(R.id.result4);
        r5 = findViewById(R.id.result5);
        r6 = findViewById(R.id.result6);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        try {
            Intent i = getIntent();
            result = i.getDoubleExtra("result3", 0);
            result4 = i.getDoubleExtra("result4", 0);

            result1 = i.getDoubleExtra("result1", 0);
            result5 = i.getDoubleExtra("result5", 0);
            result6 = i.getDoubleExtra("result6", 0);
        } catch (Exception e) {
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        r1.setText(String.format("%.2f", result1) + " Kw");
        r3.setText(String.format("%.2f", result) + " Kw");
        r4.setText(String.format("%.2f", result4) + " Kw");
        r5.setText(String.format("%.2f", result5) + " Kw");
        r6.setText(String.format("%.2f", result6) + " m3/hr");


        type = "";
        typeinfo = "";

        if ( result <= 16) {
            type = "VSP-030SP";
            typeinfo = "VSP030SP";
        } else if (result >16 && result <= 24) {
            type = "VSP-050SP";
            typeinfo = "VSP050SP";
        } else if (result >24  && result <= 30) {
            type = "VSP-065SP";
            typeinfo = "VSP065SP";
        } else if (result > 30 && result <= 38) {
            type = "VSP-075SP";
            typeinfo = "VSP075SP";
        } else if (result > 30&& result <= 50) {
            type = "VSP-100SP";
            typeinfo = "VSP100SP";
        } else if (result > 50 && result <= 63) {
            type = "VSP-120SP";
            typeinfo = "VSP120SP";
        } else if (result >63 && result <= 79) {
            type = "VSP-150SP";
            typeinfo = "VSP150SP";
        } else if (result > 79 && result <= 100) {
            type = "VSP-200SP";
            typeinfo = "VSP200SP";
        } else if (result >100 && result <= 125) {
            type = "VSP-250SP";
            typeinfo = "VSP250SP";
        } else if (result >125 && result <= 153) {
            type = "VSP-300SP";
            typeinfo = "VSP300SP";
        } else if (result > 153 && result <= 226) {
            type = "VSP-400SP";
            typeinfo = "VSP400SP";
        } else if (result > 226 && result <= 241) {
            type = "VSP-500SP";
            typeinfo = "VSP500SP";
        } else if (result > 241&& result <= 254) {
            type = "VSP-600SP";
            typeinfo = "VSP600SP";
        } else {

            LinearLayout linearLayout=findViewById(R.id.errorlay);
            linearLayout.setVisibility(View.VISIBLE);

            Button btn=findViewById(R.id.backgooo);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            model.setVisibility(View.GONE);
            nextpage.setVisibility(View.GONE);
        }


        RadioButton f, s, t;




        f = radioGroup.findViewById(R.id.single);
        s = radioGroup.findViewById(R.id.second);
        t = radioGroup.findViewById(R.id.third);

        if (type == "VSP-200SP") {
            model.setVisibility(View.VISIBLE);

            f.setText("1 Nos of VSP-200SP");
            s.setText("2 Nos of VSP-120SP");
            t.setText("4 Nos of VSP-050SP");

            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    type = "VSP-200SP";
                    typeinfo = "VSP200SP";

                }
            });

            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();


                    if (pt == "2 Nos of VSP-120SP") {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("4 Nos of VSP-050SP")) {
                        type = "VSP-050SP";
                        typeinfo = "VSP050SP";
                    }
                }
            });

            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();


                    if (pt == "2 Nos of VSP-120SP") {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("4 Nos of VSP-050SP")) {
                        type = "VSP-050SP";
                        typeinfo = "VSP050SP";
                    }
                }
            });
        } else if (type == "VSP-250SP") {

            model.setVisibility(View.VISIBLE);
            f.setText("1 Nos of VSP-250SP");
            s.setText("2 Nos of VSP-120SP");
            t.setText("4 Nos of VSP-065SP");


            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    type = "VSP-250SP";
                    typeinfo = "VSP250SP";
                }
            });

            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();
                    if (pt.equals("2 Nos of VSP-120SP")) {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("4 Nos of VSP-065SP")) {
                        type = "VSP-065SP";
                        typeinfo = "VSP065SP";
                    }
                }
            });

            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();
                    if (pt.equals("2 Nos of VSP-120SP")) {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("4 Nos of VSP-065SP")) {
                        type = "VSP-065SP";
                        typeinfo = "VSP065SP";
                    }
                }
            });
        } else if (type == "VSP-300SP") {
            model.setVisibility(View.VISIBLE);


            RadioButton fo = radioGroup.findViewById(R.id.fourth);
            RadioButton fi = radioGroup.findViewById(R.id.fiveth);
            RadioButton si = radioGroup.findViewById(R.id.sixth);



            fo.setVisibility(View.VISIBLE);
            fi.setVisibility(View.VISIBLE);
            si.setVisibility(View.VISIBLE);


            f.setText("1 Nos of VSP-300SP");
            s.setText("2 Nos of VSP-150SP");
            t.setText("3 Nos of VSP-120SP");
            fo.setText("4 Nos of VSP-075SP");
            fi.setText("5 Nos of VSP-075SP");
            si.setText("6 Nos of VSP-065SP");
            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    type = "VSP-300SP";
                    typeinfo = "VSP300SP";
                }
            });
            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();

                    if (pt.equals("2 Nos of VSP-150SP")) {
                        type = "VSP-150SP";
                        typeinfo = "VSP150SP";
                    } else if (pt.equals("3 Nos of VSP-120SP")) {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("4 Nos of VSP-075SP")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    } else if (pt.equals("5 Nos of VSP-075SP")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    } else if (pt.equals("6 Nos of VSP-065SP")) {
                        type = "VSP-065SP";
                        typeinfo = "VSP065SP";
                    }
                }
            });
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();

                    if (pt.equals("2 Nos of VSP-150SP")) {
                        type = "VSP-150SP";
                        typeinfo = "VSP150SP";
                    } else if (pt.equals("3 Nos of VSP-120SP")) {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("4 Nos of VSP-075SP")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    } else if (pt.equals("5 Nos of VSP-075SP")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    } else if (pt.equals("6 Nos of VSP-065SP")) {
                        type = "VSP-065SP";
                        typeinfo = "VSP065SP";
                    }
                }
            });
            fo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();

                    if (pt.equals("2 Nos of VSP-150SP")) {
                        type = "VSP-150SP";
                        typeinfo = "VSP150SP";
                    } else if (pt.equals("3 Nos of VSP-120SP")) {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("4 Nos of VSP-075SP")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    } else if (pt.equals("5 Nos of VSP-075SP")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    } else if (pt.equals("6 Nos of VSP-065SP")) {
                        type = "VSP-065SP";
                        typeinfo = "VSP065SP";
                    }
                }
            });

            fi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();

                    if (pt.equals("2 Nos of VSP-150SP")) {
                        type = "VSP-150SP";
                        typeinfo = "VSP150SP";
                    } else if (pt.equals("3 Nos of VSP-120SP")) {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("4 Nos of VSP-075SP")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    } else if (pt.equals("5 Nos of VSP-075SP")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    } else if (pt.equals("6 Nos of VSP-065SP")) {
                        type = "VSP-065SP";
                        typeinfo = "VSP065SP";
                    }
                }
            });
            si.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();

                    if (pt.equals("2 Nos of VSP-150SP")) {
                        type = "VSP-150SP";
                        typeinfo = "VSP150SP";
                    } else if (pt.equals("3 Nos of VSP-120SP")) {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("4 Nos of VSP-075SP")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    } else if (pt.equals("5 Nos of VSP-075SP")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    } else if (pt.equals("6 Nos of VSP-065SP")) {
                        type = "VSP-065SP";
                        typeinfo = "VSP065SP";
                    }
                }
            });


        } else if (type == "VSP-400SP") {
            model.setVisibility(View.VISIBLE);
            RadioButton fo = radioGroup.findViewById(R.id.fourth);
            RadioButton fi = radioGroup.findViewById(R.id.fiveth);





            fo.setVisibility(View.VISIBLE);
            fi.setVisibility(View.VISIBLE);




            f.setText("1 Nos of VSP-400sp");
            s.setText("3 Nos of VSP-150sp");
            t.setText("4 Nos of VSP-120sp");
            fo.setText("5 Nos of VSP-100sp");
            fi.setText("6 Nos of VSP-075sp");


            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    type = "VSP-400SP";
                    typeinfo = "VSP400SP";
                }
            });
            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();

                    if (pt.equals("3 Nos of VSP-150sp")) {
                        type = "VSP-150SP";
                        typeinfo = "VSP150SP";
                    } else if (pt.equals("4 Nos of VSP-120sp")) {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("5 Nos of VSP-100sp")) {
                        type = "VSP-100SP";
                        typeinfo = "VSP100SP";
                    } else if (pt.equals("6 Nos of VSP-075sp")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    }
                }
            });
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();

                    if (pt.equals("3 Nos of VSP-150sp")) {
                        type = "VSP-150SP";
                        typeinfo = "VSP150SP";
                    } else if (pt.equals("4 Nos of VSP-120sp")) {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("5 Nos of VSP-100sp")) {
                        type = "VSP-100SP";
                        typeinfo = "VSP100SP";
                    } else if (pt.equals("6 Nos of VSP-075sp")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    }
                }
            });
            fo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();

                    if (pt.equals("3 Nos of VSP-150sp")) {
                        type = "VSP-150SP";
                        typeinfo = "VSP150SP";
                    } else if (pt.equals("4 Nos of VSP-120sp")) {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("5 Nos of VSP-100sp")) {
                        type = "VSP-100SP";
                        typeinfo = "VSP100SP";
                    } else if (pt.equals("6 Nos of VSP-075sp")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    }
                }
            });
            fi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();

                    if (pt.equals("3 Nos of VSP-150sp")) {
                        type = "VSP-150SP";
                        typeinfo = "VSP150SP";
                    } else if (pt.equals("4 Nos of VSP-120sp")) {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("5 Nos of VSP-100sp")) {
                        type = "VSP-100SP";
                        typeinfo = "VSP100SP";
                    } else if (pt.equals("6 Nos of VSP-075sp")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    }
                }
            });


        } else if (type == "VSP-500SP") {


            RadioButton fo = radioGroup.findViewById(R.id.fourth);



            fo.setVisibility(View.VISIBLE);





            model.setVisibility(View.VISIBLE);

            f.setText("1 Nos of VSP-500SP");
            s.setText("4 Nos of VSP-120SP");
            t.setText("5 Nos of VSP-100Sp");
            fo.setText("6 Nos of VSP-075Sp");


            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    type = "VSP-500SP";
                    typeinfo = "VSP500SP";
                }
            });
            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();

                    if (pt.equals("4 Nos of VSP-120SP")) {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("5 Nos of VSP-100Sp")) {
                        type = "VSP-100SP";
                        typeinfo = "VSP100SP";
                    }else if (pt.equals("6 Nos of VSP-075Sp")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    }
                }
            });
            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();

                    if (pt.equals("4 Nos of VSP-120SP")) {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("5 Nos of VSP-100Sp")) {
                        type = "VSP-100SP";
                        typeinfo = "VSP100SP";
                    }else if (pt.equals("6 Nos of VSP-075Sp")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    }
                }
            });

            fo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();

                    if (pt.equals("4 Nos of VSP-120SP")) {
                        type = "VSP-120SP";
                        typeinfo = "VSP120SP";
                    } else if (pt.equals("5 Nos of VSP-100Sp")) {
                        type = "VSP-100SP";
                        typeinfo = "VSP100SP";
                    }else if (pt.equals("6 Nos of VSP-075Sp")) {
                        type = "VSP-075SP";
                        typeinfo = "VSP075SP";
                    }
                }
            });


        } else if (type == "VSP-600SP") {

            model.setVisibility(View.VISIBLE);
            f.setText("1 Nos of VSP-600SP");
            s.setText("5 Nos of VSP-100SP");
            t.setText("6 Nos of VSP-100SP");


            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    type = "VSP-600SP";
                    typeinfo = "VSP600SP";
                }
            });
            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();

                    if (pt.equals("5 Nos of VSP-100SP")) {
                        type = "VSP-100SP";
                        typeinfo = "VSP100SP";
                    } else if (pt.equals("6 Nos of VSP-100SP")) {
                        type = "VSP-100SP";
                        typeinfo = "VSP100SP";
                    }
                }
            });

            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int radioid = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(radioid);
                    String pt = radioButton.getText().toString();

                    if (pt.equals("5 Nos of VSP-100SP")) {
                        type = "VSP-100SP";
                        typeinfo = "VSP100SP";
                    } else if (pt.equals("6 Nos of VSP-100SP")) {
                        type = "VSP-100SP";
                        typeinfo = "VSP100SP";
                    }
                }
            });


        }


        title_model = findViewById(R.id.title_model);
        title_model.setText(typeinfo);

        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Info_before.this, Info_after.class);
                i.putExtra("types", type);
                i.putExtra("typeinfo", typeinfo);
                startActivity(i);

            }
        });


    }


}