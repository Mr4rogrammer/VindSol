package com.mrprogrammer.vindsol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class disclaimer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);

        ImageView back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        String text ="The Vindsol heat pumps Smart Selection App remains the property of Mechzephyr Engineering Pvt Ltd. and is provided to our customers in assisting them to select the best suitable  heat pumps for them and for their project thereby making their work easy and quick. This software is recommended for technically qualified users for rough calculations and final verifications.\n" +
                "<br><br>This does not replace specialist knowledge or experience when calculating water heating capacity and selecting products.<br>" +
                "<br>This program is accurate and validated; however, we do not guarantee the result because of widely variable application, and data that can be misinterpreted between parties during initial project/product evaluation. We have used assumptions and approximations at required instances and make a user-friendly application. Therefore, the usage of the program is solely at user’s risk and Mechzephyr Engineering Pvt Ltd is not responsible for the same.\n" +
                "All data provided in this app is for specific products and applications and is subject to change without prior notice. It is strictly forbidden to use or share any data other than the downloadable files in this app with any third party, without any written permission from Mechzephyr Engineering Pvt Ltd.\n" +
                "<br><br>We will continuously update and improve this software from time to time.<br>" +
                "Our customer’s comments and their valuable feedback would assist us to enhance the use of this application and would be greatly appreciated.<br>" +
                "<br><br>For feedback, please contact us at ";
        TextView dis=findViewById(R.id.diss);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dis.setText(Html.fromHtml(text+"<font color='red'>sales@vindsol.in</font>.", Html.FROM_HTML_MODE_COMPACT));
        } else {
            dis.setText(Html.fromHtml(text+"<font color='red'>sales@vindsol.in</font>."));
        }
    }
}