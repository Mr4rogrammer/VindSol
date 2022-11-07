package com.mrprogrammer.vindsol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class before_c1 extends AppCompatActivity {

    double result;
    String model, pdf;

    String type="";

    RadioGroup radioGroup;

    Button nextpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_c1);

        {
            ImageView back = findViewById(R.id.back);

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

            try {
                Intent i = getIntent();
                result = Double.parseDouble(i.getStringExtra("result"));


                //  Toast.makeText(this, String.valueOf(result), Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            TextView resultss = findViewById(R.id.heat);
            resultss.setText(String.valueOf(result) + " Kw");
        }

        radioGroup = findViewById(R.id.radiogroup1);

        nextpage = findViewById(R.id.detailedbtn);




        if (result <= 42) {
            model = "VCHP-3640H";

        } else if (result > 42 && result <= 50) {
            model = "VCHP-4248H";

        } else if (result > 50 && result <= 65) {
            model = "VCHP-5258H";

        } else if (result > 65 && result <= 80) {
            model = "VCHP-7076H";

        } else if (result > 80 && result <= 100) {
            model = "VCHP-8996H";

        } else if (result > 100 && result <= 120) {
            model = "VCHP-1050116H";

        } else if (result > 120 && result <= 130) {
            model = "130";

        } else if (result > 130 && result <= 160) {
            model = "160";

        } else if (result > 160 && result <= 200) {
            model = "200";

        } else if (result > 200 && result <= 225) {
            model = "225";

        } else if (result > 225) {

            ScrollView scrollView=findViewById(R.id.scrollable);
            scrollView.setVisibility(View.GONE);

            LinearLayout linearLayout=findViewById(R.id.errorlay);

            linearLayout.setVisibility(View.VISIBLE);

            Button btn=findViewById(R.id.backgooo);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }




        RadioButton f, s, t, fo, fi;
        f = radioGroup.findViewById(R.id.single);
        s = radioGroup.findViewById(R.id.second);
        t = radioGroup.findViewById(R.id.third);
        fo = radioGroup.findViewById(R.id.fourth);
        fi = radioGroup.findViewById(R.id.fiveth);

        if (model == "VCHP-3640H") {
            f.setText("1 Nos of VCHP 3640H");
            s.setText("2 Nos of VCHP 1720H");

            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-3640H";
                }
            });

            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-1720H";
                }
            });


        } else if (model == "VCHP-4248H") {
            f.setText("1 Nos of VCHP 4248H");
            s.setText("2 Nos of VCHP 2528H");

            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-4248H";
                }
            });

            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-2528H";
                }
            });


        } else if (model == "VCHP-5258H") {
            f.setText("1 Nos of VCHP 5258H");
            s.setText("2 Nos of VCHP 3640H");
            t.setText("3 Nos of VCHP 1720H");
            t.setVisibility(View.VISIBLE);

            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-5258H";
                }
            });

            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-3640H";
                }
            });

            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-1720H";
                }
            });


        } else if (model == "VCHP-7076H") {
            f.setText("1 Nos of VCHP 7076H");
            s.setText("2 Nos of VCHP 3640H");
            t.setText("3 Nos of VCHP 2528H");
            t.setVisibility(View.VISIBLE);

            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-7076H";
                }
            });

            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-3640H";
                }
            });

            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-2528H";
                }
            });


        } else if (model == "VCHP-8996H") {
            f.setText("1 Nos of VCHP 8996H");
            s.setText("2 Nos of VCHP 4248H");
            t.setText("3 Nos of VCHP 2528H");
            fo.setText("4 Nos of VCHP 2528H");
            fi.setText("5 Nos of VCHP 1720H");

            t.setVisibility(View.VISIBLE);
            fo.setVisibility(View.VISIBLE);
            fi.setVisibility(View.VISIBLE);

            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-8996H";
                }
            });

            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-4248H";
                }
            });

            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-2528H";
                }
            });

            fo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-2528H";
                }
            });

            fi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-1720H";
                }
            });


        }
//
        else if (model == "VCHP-1050116H") {
            f.setText("1 Nos of VCHP 105116H");
            s.setText("2 Nos of VCHP 5258H");
            t.setText("3 Nos of VCHP 3640H");
            fo.setText("4 Nos of VCHP 2528H");
            fi.setText("5 Nos of VCHP 1720H");

            t.setVisibility(View.VISIBLE);
            fo.setVisibility(View.VISIBLE);
            fi.setVisibility(View.VISIBLE);

            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-105116H";
                }
            });

            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-5258H";
                }
            });

            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-3640H";
                }
            });

            fo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-2528H";
                }
            });

            fi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-1720H";
                }
            });


        } else if (model == "130") {
            f.setText("2 Nos of VCHP 5258H");
            s.setText("3 Nos of VCHP 4248H");
            t.setText("4 Nos of VCHP 2528H");
            fo.setText("5 Nos of VCHP 2528H");
            fi.setText("6 Nos of VCHP 1720H");

            t.setVisibility(View.VISIBLE);
            fo.setVisibility(View.VISIBLE);
            fi.setVisibility(View.VISIBLE);

            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-5258H";
                }
            });

            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-4248H";
                }
            });

            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-2528H";
                }
            });

            fo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-2528H";
                }
            });

            fi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-1720H";
                }
            });


        } else if (model == "160") {
            f.setText("2 Nos of VCHP 7076H");
            s.setText("3 Nos of VCHP 5258H");
            t.setText("4 Nos of VCHP 3640H");
            fo.setText("5 Nos of VCHP 2528H");
            fi.setText("6 Nos of VCHP 2528H");

            t.setVisibility(View.VISIBLE);
            fo.setVisibility(View.VISIBLE);
            fi.setVisibility(View.VISIBLE);

            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-7076H";
                }
            });

            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-5258H";
                }
            });

            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-3640H";
                }
            });

            fo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-2528H";
                }
            });

            fi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-2528H";
                }
            });


        } else if (model == "200") {
            f.setText("2 Nos of VCHP 8996H");
            s.setText("3 Nos of VCHP 7076H");
            t.setText("4 Nos of VCHP 2528H");
            fo.setText("5 Nos of VCHP 3640H");
            fi.setText("6 Nos of VCHP 3640H");

            t.setVisibility(View.VISIBLE);
            fo.setVisibility(View.VISIBLE);
            fi.setVisibility(View.VISIBLE);

            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-8996H";
                }
            });

            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-7076H";
                }
            });

            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-2528H";
                }
            });

            fo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-3640H";
                }
            });

            fi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-3640H";
                }
            });


        } else if (model == "225") {
            f.setText("2 Nos of VCHP 105116H");
            s.setText("3 Nos of VCHP 7076H");
            t.setText("4 Nos of VCHP 5258H");
            fo.setText("5 Nos of VCHP 4248H");
            fi.setText("6 Nos of VCHP 3640H");

            t.setVisibility(View.VISIBLE);
            fo.setVisibility(View.VISIBLE);
            fi.setVisibility(View.VISIBLE);

            f.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-105116H";
                }
            });

            s.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-7076H";
                }
            });

            t.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-5258H";
                }
            });

            fo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-4248H";
                }
            });

            fi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "VCHP-3640H";
                }
            });


        }



        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    Toast.makeText(before_c1.this, "kk", Toast.LENGTH_SHORT).show();
               if(type.isEmpty() )
               {
                   Toast.makeText(before_c1.this, "Please Select a model", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   startActivity(new Intent(before_c1.this, c1.class).putExtra("model", type));
               }
            }
        });

    }
}