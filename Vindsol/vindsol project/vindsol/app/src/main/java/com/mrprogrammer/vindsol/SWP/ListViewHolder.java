package com.mrprogrammer.vindsol.SWP;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrprogrammer.vindsol.R;


public class ListViewHolder extends RecyclerView.ViewHolder {
    private int i = 1;

    View view;


    public ListViewHolder(@NonNull View itemView) {
        super(itemView);


        view = itemView;


    }

    public void setdetails(Context context, String name, String value) {
        TextView Name, Value;

        Name=view.findViewById(R.id.name);
        Value=view.findViewById(R.id.value);



        Name.setText(name);
        Value.setText(value);

    }


}
