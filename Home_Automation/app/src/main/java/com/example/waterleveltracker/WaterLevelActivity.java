package com.example.waterleveltracker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.john.waveview.WaveView;



public class WaterLevelActivity extends AppCompatActivity {

    private TextView txtProgress;
    WaveView waveView;
//    TextToSpeech textToSpeech;
   public DatabaseReference mRef;

    private static final String TAG = "WaterLevelActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_level);

        waveView = findViewById(R.id.waveView);
        txtProgress = findViewById(R.id.txtProgress);

        mRef = FirebaseDatabase.getInstance().getReference("data");
        getSensorValue(mRef);


    }



    private void getSensorValue(DatabaseReference mRef) {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int ulrtaValue = snapshot.child("ultraSonicValue").getValue(Integer.class);

                if(ulrtaValue > 100){
                    ulrtaValue = 100;

                    txtProgress.setText("Water Level Full");

                }

                String value = String.valueOf(ulrtaValue);
                txtProgress.setText(value+"%");


                waveView.setProgress(ulrtaValue);

                if(ulrtaValue < 15){
                    txtProgress.setText("Emergency Water Use");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}