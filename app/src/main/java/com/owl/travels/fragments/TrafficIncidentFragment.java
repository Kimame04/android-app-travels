package com.owl.travels.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.owl.travels.R;
import com.owl.travels.models.GetTrafficIncidents;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrafficIncidentFragment extends Fragment {
    private GetTrafficIncidents incidents = new GetTrafficIncidents();
    private static final String url = "http://datamall2.mytransport.sg/ltaodataservice/TrafficIncidents";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_train, container, false);
        setHasOptionsMenu(true);
        getActivity().setTitle("Traffic Incidents");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fetchIncidents();
    }

    private void fetchIncidents(){
        Runnable runnable = () ->{
            try{
                incidents.getService();
                System.out.println(incidents.getMessage());
            } catch (Exception e){
                e.printStackTrace();
            }
        };
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(runnable);
        es.shutdown();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            default:
                fetchIncidents();
                Snackbar.make(getView(),"Refreshed", BaseTransientBottomBar.LENGTH_SHORT).show();
                return true;
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_traffic_incidents, menu);
    }
}
