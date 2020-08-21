package com.example.travels.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travels.R;
import com.example.travels.models.TrainInfo;

import java.util.List;

public class ArrivalsAdapter extends RecyclerView.Adapter<ArrivalsAdapter.ViewHolder>{
    private List<TrainInfo> trainInfoList;
    private Context context;
    public ArrivalsAdapter(List<TrainInfo>list){this.trainInfoList = list;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cards_train_arrival, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArrivalsAdapter.ViewHolder holder, int position) {
        TrainInfo info = trainInfoList.get(position);
        holder.stationName.setText("hardcoded");
    }

    @Override
    public int getItemCount() {
        return trainInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView stationName;
        private TextView platform1;
        private TextView platform2;
        private TextView platform3;
        private TextView platform4;
        private TextView platform5;
        private TextView platform6;

        ViewHolder(View view){
            super(view);
            stationName = view.findViewById(R.id.station_title);
            platform1 = view.findViewById(R.id.platform_1);
            platform2 = view.findViewById(R.id.platform_2);
            platform3 = view.findViewById(R.id.platform_3);
            platform4 = view.findViewById(R.id.platform_4);
            platform5 = view.findViewById(R.id.platform_5);
            platform6 = view.findViewById(R.id.platform_6);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
