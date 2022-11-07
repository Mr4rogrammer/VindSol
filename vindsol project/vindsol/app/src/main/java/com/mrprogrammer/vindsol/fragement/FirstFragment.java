package com.mrprogrammer.vindsol.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.mrprogrammer.vindsol.R;
import com.mrprogrammer.vindsol.Theromo_menu;
import com.mrprogrammer.vindsol.third1;


public class FirstFragment extends Fragment {

    MaterialCardView materialCardView;
    MaterialCardView materialCardView2;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_first, container, false);


        materialCardView = root.findViewById(R.id.select1);
        materialCardView2 = root.findViewById(R.id.a2);


        materialCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Theromo_menu.class);

                startActivity(i);
            }
        });

        materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getContext(), third1.class);
                i.putExtra("key", "1");
                startActivity(i);

            }
        });

        return root;

    }


}