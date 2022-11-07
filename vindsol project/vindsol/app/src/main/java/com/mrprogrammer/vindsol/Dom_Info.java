package com.mrprogrammer.vindsol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mrprogrammer.vindsol.SWP.Data;
import com.mrprogrammer.vindsol.SWP.ListViewHolder;

import org.jetbrains.annotations.NotNull;

public class Dom_Info extends AppCompatActivity {
    FirebaseRecyclerAdapter<Data, ListViewHolder> firebaseRecyclerAdapter;
    int result;
    private String type;
    private String typeinfo;
    Button nextpage;
    RecyclerView recyclerView;
    TextView title_model;

    private FirebaseDatabase rootnode;
    ProgressBar progressBar;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dom_info);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Dom_Info.this);


        progressBar = findViewById(R.id.pro);

        recyclerView = findViewById(R.id.recyleview);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);


        ImageView back = findViewById(R.id.back);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        try {
            Intent i = getIntent();
            result = i.getIntExtra("result", 0);
        } catch (Exception e) {
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        if (result >= 1 && result <= 5) {
            typeinfo = "VDHP 01TR";
            type = "DomesticHP1pdf";

        } else if (result >= 6 && result <= 9) {
            typeinfo = "VDHP 1.5TR";
            type = "DomesticHP15pdf";
        } else if (result >= 10 && result <= 13) {
            typeinfo = "VDHP 2.3TR";
            type = "DomesticHP2pdf";
        } else if (result >= 14 && result <= 18) {
            typeinfo = "VDHP 3TR";
            type = "DomesticHP3pdf";
        }


        title_model = findViewById(R.id.title_model);
        title_model.setText(typeinfo);


        typeinfo=typeinfo.replace(".","");
        typeinfo=typeinfo.replace(" ","");

        try {
            rootnode = FirebaseDatabase.getInstance();
            reference = rootnode.getReference("Doma").child("Model").child(typeinfo).child("value");
        } catch (Exception e) {
            Toast.makeText(this, "No data found !", Toast.LENGTH_SHORT).show();
            finish();
        }


        kk();
        nextpage = findViewById(R.id.detailed1);
        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dom_Info.this, Info.class);
                i.putExtra("types", type);
                i.putExtra("database", "DomesticPdf");
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
                    holder.setdetails(Dom_Info.this, model.getName(), model.getValue());


                }

            };


            firebaseRecyclerAdapter.startListening();

            recyclerView.setAdapter(firebaseRecyclerAdapter);

        } catch (Exception e) {
            Toast.makeText(Dom_Info.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}