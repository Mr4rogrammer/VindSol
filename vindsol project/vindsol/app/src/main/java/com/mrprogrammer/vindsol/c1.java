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

public class c1 extends AppCompatActivity {


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
        setContentView(R.layout.activity_c1);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        try {
            Intent i = getIntent();
            model = i.getStringExtra("model");


            //  Toast.makeText(this, String.valueOf(result), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }




        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(c1.this);

        recyclerView = findViewById(R.id.reee);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        progressBar = findViewById(R.id.pro);

        title_model = findViewById(R.id.title_model);
        title_model.setText(model);
        try {
            rootnode = FirebaseDatabase.getInstance();
            reference = rootnode.getReference("commercial").child("Model").child(model).child("value");
        } catch (Exception ignored) {

        }
        kk();


        nextpage = findViewById(R.id.detailedbtn);
        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(c1.this, Info.class);
                i.putExtra("types", model+"pdf");
                i.putExtra("database", "CommercialPdf");
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
                    holder.setdetails(c1.this, model.getName(), model.getValue());


                }

            };


            firebaseRecyclerAdapter.startListening();

            recyclerView.setAdapter(firebaseRecyclerAdapter);

        } catch (Exception e) {
           // Toast.makeText(c1.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}