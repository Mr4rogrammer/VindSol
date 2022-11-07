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

public class Doma_tank extends AppCompatActivity {
    FirebaseRecyclerAdapter<Data, ListViewHolder> firebaseRecyclerAdapter;
    int result;
    String type, typeinfo;
    Button nextpage;
    RecyclerView recyclerView;
    TextView title_model;
    private FirebaseDatabase rootnode;
    ProgressBar progressBar;
    private DatabaseReference reference;
    int type_val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doma_tank);

        ImageView back = findViewById(R.id.back);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Doma_tank.this);


        progressBar = findViewById(R.id.pro);

        recyclerView = findViewById(R.id.recyleview);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        try {
            Intent i = getIntent();
            result = i.getIntExtra("result", 0);
            type_val = i.getIntExtra("type", 0);
        } catch (Exception e) {
            //Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

      if(type_val==2)
      {
          if (result >= 1 && result <= 5) {
              typeinfo = "VSGLT200";
              type = "200pdf";

          } else if (result >= 6 && result <= 7) {
              typeinfo = "VSGLT300";
              type = "300pdf";
          } else if (result >= 8 && result <= 9) {
              typeinfo = "VSGLT500";
              type = "500pdf";
          } else if (result >= 10 && result <= 13) {
              typeinfo = "VSGLT600";
              type = "600pdf";
          } else if (result >= 13 && result <= 18) {
              typeinfo = "VSGLT700";
              type = "700pdf";
          }
      }
      else if(type_val ==1)
      {
          if (result >= 1 && result <= 5) {
              typeinfo = "VSGLT200";
              type = "200pdf";

          } else if (result >= 6 && result <= 9) {
              typeinfo = "VSGLT300";
              type = "300pdf";
          } else if (result >= 10 && result <= 13) {
              typeinfo = "VSGLT500";
              type = "500pdf";
          }
      }

        title_model = findViewById(R.id.title_model);
        title_model.setText(typeinfo);


        try {
            rootnode = FirebaseDatabase.getInstance();
            reference = rootnode.getReference("DomaTank").child("Model").child(typeinfo).child("value");
        } catch (Exception e) {
            Toast.makeText(this, "No data found !", Toast.LENGTH_SHORT).show();
            finish();
        }

        kk();
        nextpage = findViewById(R.id.detailed1);
        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Doma_tank.this, Info.class);
                if (type == "200pdf") {
                    i.putExtra("types", "Tank200ltspdf");
                } else if (type == "300pdf") {
                    i.putExtra("types", "Tank300ltspdf");
                } else if (type == "500pdf") {
                    i.putExtra("types", "Tank500ltspdf");
                } else if (type == "600pdf") {
                    i.putExtra("types", "Tank600ltspdf");
                } else if (type == "700pdf") {
                    i.putExtra("types", "Tank700ltspdf");
                }
                i.putExtra("database", "StorageTank");
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
                    holder.setdetails(Doma_tank.this, model.getName(), model.getValue());


                }

            };


            firebaseRecyclerAdapter.startListening();

            recyclerView.setAdapter(firebaseRecyclerAdapter);

        } catch (Exception e) {
            Toast.makeText(Doma_tank.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}