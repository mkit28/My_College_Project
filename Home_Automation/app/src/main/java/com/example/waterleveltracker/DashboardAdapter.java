package com.example.waterleveltracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DashboardAdapter  extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder> {

    private Context mContext;
    private List<ModelsClass> list;

    public DashboardAdapter(Context mContext, List<ModelsClass> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.all_items_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.txtView.setText(list.get(position).getName());

        Picasso.get()
                .load(list.get(position).getImageUrl())
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtView;

        public MyViewHolder(View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.imageView);
            txtView = itemView.findViewById(R.id.textView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (getAdapterPosition()){
                        case 0:
                            openWaterLevelActivity();
                            break;
                        case 1:
                            motorStateActivity();
                            break;
                        case 2:
                            roomLightsActivity();
                            break;
                        case 3:
                            fanOnAndOff();
                            break;
                    }

                }
            });
        }
    }

    private void roomLightsActivity() {

        Intent intent = new Intent(mContext, RoomLightsActivity.class);
        mContext.startActivity(intent);
    }

    private void fanOnAndOff() {

        Intent intent = new Intent(mContext, FanActivity.class);
        mContext.startActivity(intent);

    }

    private void openWaterLevelActivity() {

        Intent intent = new Intent(mContext, WaterLevelActivity.class);
        mContext.startActivity(intent);

    }

    private void motorStateActivity(){
        Intent intent = new Intent(mContext,MotorStateActivity.class);
        mContext.startActivity(intent);
    }
}
