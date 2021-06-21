package com.example.waterleveltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FanActivity extends AppCompatActivity {

    private Button btnFirstOn, btnFirstOff, btnSecondOn, btnSecondOff;
    private DatabaseReference databaseReference;
    String str="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan);
        btnFirstOn = findViewById(R.id.fanFirstOn);
        btnFirstOff = findViewById(R.id.fanFirstOFF);
        btnSecondOn = findViewById(R.id.fanSecondOn);
        btnSecondOff = findViewById(R.id.fanSecondOFF);

        databaseReference = FirebaseDatabase.getInstance().getReference("Fan");



        btnFirstOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.child("FirstFloorFan").setValue("ON");
                btnFirstOn.setBackgroundResource(R.drawable.selected_on_btn_bg);
                btnFirstOff.setBackgroundResource(R.drawable.not_selected_btn_bg);
            }
        });

        btnFirstOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.child("FirstFloorFan").setValue("OFF");
                btnFirstOff.setBackgroundResource(R.drawable.selected_off_btn_bg);
                btnFirstOn.setBackgroundResource(R.drawable.not_selected_btn_bg);
            }
        });

        btnSecondOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.child("SecondFloorFan").setValue("ON");
                
                btnSecondOn.setBackgroundResource(R.drawable.selected_on_btn_bg);
                btnSecondOff.setBackgroundResource(R.drawable.not_selected_btn_bg);
            }
        });

        btnSecondOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.child("SecondFloorFan").setValue("OFF");
                btnSecondOff.setBackgroundResource(R.drawable.selected_off_btn_bg);
                btnSecondOn.setBackgroundResource(R.drawable.not_selected_btn_bg);

            }
        });

    }


}