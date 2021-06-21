package com.example.waterleveltracker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class DashbordActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private DashboardAdapter adapter;
    private ArrayList<ModelsClass> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        recyclerView = findViewById(R.id.recyclerView);

        list.add(new ModelsClass("Water Level","https://www.transparentpng.com/thumb/water/I5SUyL-water-photo.png"));
        list.add(new ModelsClass("Motor Status","https://5.imimg.com/data5/OQ/PE/MY-10900297/monosub-submersible-pump-500x500.png"));
        list.add(new ModelsClass("Room Light's","https://i0.wp.com/ecolightindia.com/wp-content/uploads/2018/09/modern-led-wall-lights-for-interiors.png?resize=449%2C449"));

        list.add(new ModelsClass("Control Fan's","https://toppng.com/uploads/preview/electrical-ceiling-fan-115269071978ppzf9n41c.png"));


        adapter = new DashboardAdapter(this,list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        recyclerView.setHasFixedSize(true);



    }
}