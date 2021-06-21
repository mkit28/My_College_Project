package com.example.waterleveltracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RoomLightsActivity extends AppCompatActivity {

    private Button btnFirstOn, btnFirstOff, btnSecondOn, btnSecondOff;

   // private String fanStr="";
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_lights);

        btnFirstOn = findViewById(R.id.lightFirstOn);
        btnFirstOff = findViewById(R.id.lightFirstOFF);
        btnSecondOn = findViewById(R.id.lightSecondOn);
        btnSecondOff = findViewById(R.id.lightSecondOFF);
        databaseReference = FirebaseDatabase.getInstance().getReference("Led");

        btnFirstOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("FirstFloorLed").setValue("ON");

                btnFirstOn.setBackgroundResource(R.drawable.selected_on_btn_bg);
                btnFirstOff.setBackgroundResource(R.drawable.not_selected_btn_bg);
            }
        });

        btnFirstOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("FirstFloorLed").setValue("OFF");

                btnFirstOff.setBackgroundResource(R.drawable.selected_off_btn_bg);
                btnFirstOn.setBackgroundResource(R.drawable.not_selected_btn_bg);
            }
        });

        btnSecondOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("SecondFloorLed").setValue("ON");

                btnSecondOn.setBackgroundResource(R.drawable.selected_on_btn_bg);
                btnSecondOff.setBackgroundResource(R.drawable.not_selected_btn_bg);
            }
        });

        btnSecondOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("SecondFloorLed").setValue("OFF");

                btnSecondOff.setBackgroundResource(R.drawable.selected_off_btn_bg);
                btnSecondOn.setBackgroundResource(R.drawable.not_selected_btn_bg);

            }
        });


    }
}