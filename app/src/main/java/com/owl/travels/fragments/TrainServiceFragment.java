package com.owl.travels.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.owl.travels.R;
import com.owl.travels.models.GetTrainService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrainServiceFragment extends Fragment {
   private GetTrainService trainService = new GetTrainService();
    private TextView title;
    private TextView message;
    private TextView shuttle;
    private TextView publicBus;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_train_service_alerts, container, false);
        setHasOptionsMenu(true);
        getActivity().setTitle("Train Info");
        fetchServiceAlerts();
        title = view.findViewById(R.id.disruption_title);
        message = view.findViewById(R.id.message);
        shuttle = view.findViewById(R.id.shuttle_bus);
        publicBus = view.findViewById(R.id.public_bus);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!trainService.getDisruption())
            title.setText("No disruption. Have a nice day!");
        else{
            title.setText("Disruption in the " + trainService.getLines() + ".\nDirection: " + trainService.getDirection());
            message.setText(trainService.getMessage());
            shuttle.setText("There are free shuttles available in\n"+trainService.getShuttleBus()+"\nGoing in "+trainService.getShuttleDirn()+
                    " direction.");
            publicBus.setText("There are free bus services available in\n"+trainService.getPublicBus());
        }

    }

    private void fetchServiceAlerts(){
        Runnable runnable = () ->{
            try{
                trainService.getService();
                System.out.println(trainService.getMessage());
            } catch (Exception e){
                e.printStackTrace();
            }
        };
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(runnable);
        es.shutdown();
    }
}
