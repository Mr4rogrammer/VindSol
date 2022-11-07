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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mrprogrammer.vindsol.SWP.Data;
import com.mrprogrammer.vindsol.SWP.ListViewHolder;

import org.jetbrains.annotations.NotNull;

public class Info_after extends AppCompatActivity {

    FirebaseRecyclerAdapter<Data, ListViewHolder> firebaseRecyclerAdapter;
    Button infomation;
    RecyclerView recyclerView;
    String type;
    String typeinfo;
    ProgressBar progressBar;
    TextView title_model;

    FirebaseAuth auth;
    private FirebaseDatabase rootnode;

    private DatabaseReference reference, reference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_after);

        infomation=findViewById(R.id.detailed1);


        try {
            Intent i =getIntent();

            typeinfo= i.getStringExtra("typeinfo");
            type= i.getStringExtra("types");
        } catch (Exception e) {
            e.printStackTrace();
        }


        ImageView back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Info_after.this);
        progressBar = findViewById(R.id.pro);

        recyclerView = findViewById(R.id.reee);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(linearLayoutManager);


        title_model = findViewById(R.id.title_model);


        title_model.setText(typeinfo);

        try {
            rootnode = FirebaseDatabase.getInstance();
            reference = rootnode.getReference("SWPDB").child("Model").child(typeinfo).child("value");
        } catch (Exception e) {
            Toast.makeText(this, "No data found !", Toast.LENGTH_SHORT).show();
            finish();
        }


        kk();


        infomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Info_after.this, Info.class);
                i.putExtra("types", type+"pdf");
                i.putExtra("database", "Swimmingpoolpdf");
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
                    holder.setdetails(Info_after.this, model.getName(), model.getValue());


                }

            };


            firebaseRecyclerAdapter.startListening();

            recyclerView.setAdapter(firebaseRecyclerAdapter);

        } catch (Exception e) {
            Toast.makeText(Info_after.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}