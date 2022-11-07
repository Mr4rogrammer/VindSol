package com.mrprogrammer.vindsol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mrprogrammer.vindsol.SWP.Data;
import com.mrprogrammer.vindsol.SWP.ListViewHolder;

import org.jetbrains.annotations.NotNull;

public class HighTemp1 extends AppCompatActivity {

    double result;
    Button nextpage;
    ImageView back;
    String model;

    FirebaseRecyclerAdapter<Data, ListViewHolder> firebaseRecyclerAdapter;
    private FirebaseDatabase rootnode;
    private DatabaseReference reference;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    TextView title_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_temp1);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        try {
            Intent i = getIntent();
            result = Double.parseDouble(i.getStringExtra("result"));

//            Toast.makeText(this, String.valueOf(result), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        TextView resultss = findViewById(R.id.result);
        resultss.setText(String.valueOf(result)+" Kw");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HighTemp1.this);
        progressBar = findViewById(R.id.pro);
        recyclerView = findViewById(R.id.reee);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        //  Toast.makeText(this, String.valueOf(result), Toast.LENGTH_SHORT).show();
        if (result <= 13) {
            model = "VCHT-015BC";

        } else if (result > 13 && result <= 18) {
            model = "VCHT-020BC";

        } else if (result > 18 && result <= 26) {
            model = "VCHT-026BC";

        } else if (result > 26 && result <= 40) {
            model = "VCHT-040BC";

        } else if (result > 40 && result <= 52) {
            model = "VCHT-055BC";

        } else if (result > 52 && result <= 64) {
            model = "VCHT-065BC";

        } else if (result > 64 && result <= 78) {
            model = "VCHT-080BC";

        } else if (result > 78 && result <= 102) {
            model = "VCHT-100BC";

        } else if (result > 102 && result <= 130) {
            model = "VCHT-130BC";

        }
        else if(result>130)
        {
            CardView tt,detailed;

            tt=findViewById(R.id.tt);
            detailed=findViewById(R.id.detailed);

            tt.setVisibility(View.GONE);
            detailed.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);

            LinearLayout  linearLayout=findViewById(R.id.errorlay);
            linearLayout.setVisibility(View.VISIBLE);
            Button back=findViewById(R.id.backgooo);
            progressBar.setVisibility(View.GONE);

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

        }





        title_model = findViewById(R.id.title_model);
        title_model.setText(model);
        try {
            rootnode = FirebaseDatabase.getInstance();
            reference = rootnode.getReference("high").child("Model").child(model).child("value");
        } catch (Exception e) {

        }
        kk();


        nextpage = findViewById(R.id.detailedbtn);
        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HighTemp1.this, Info.class);
                i.putExtra("types", model+"pdf");
                i.putExtra("database", "highpdf");
                startActivity(i);

            }
        });

    }

    public void kk() {


        try {


            FirebaseRecyclerOptions<Data> options = new FirebaseRecyclerOptions.Builder<Data>()
                    .setQuery(reference, Data.class).build();

            firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Data, ListViewHolder>(options) {


                @NonNull
                @NotNull
                @Override
                public ListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

                    View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.swplayout), parent, false);

                    return new ListViewHolder(view);

                }

                @Override
                protected void onBindViewHolder(@NonNull @NotNull ListViewHolder holder, int position, @NonNull @NotNull Data model) {


                    progressBar.setVisibility(View.GONE);
                    holder.setdetails(HighTemp1.this, model.getName(), model.getValue());


                }

            };


            firebaseRecyclerAdapter.startListening();

            recyclerView.setAdapter(firebaseRecyclerAdapter);

        } catch (Exception e) {
         //   Toast.makeText(HighTemp1.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}