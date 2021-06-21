package com.example.waterleveltracker;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MotorStateActivity extends AppCompatActivity {

    private Button onButton;
    private Button offButton;
    private TextView textView;
    String str = "";
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_state);


        databaseReference = FirebaseDatabase.getInstance().getReference("motorStatus");
        textView = findViewById(R.id.tv_motorStatus);
        onButton = findViewById(R.id.status_on);
        offButton = findViewById(R.id.status_off);



        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.setValue("ON");
                str = "ON";
                textView.setText("Motor Status: ON");
                onButton.setBackgroundResource(R.drawable.selected_on_btn_bg);
                offButton.setBackgroundResource(R.drawable.not_selected_btn_bg);
            }
        });

        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.setValue("OFF");
                str = "OFF";
                textView.setText("Motor Status: OFF");
                offButton.setBackgroundResource(R.drawable.selected_off_btn_bg);
                onButton.setBackgroundResource(R.drawable.not_selected_btn_bg);
            }
        });
    }
}