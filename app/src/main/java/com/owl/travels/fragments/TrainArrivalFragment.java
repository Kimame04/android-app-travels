package com.owl.travels.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.owl.travels.R;
import com.owl.travels.adapters.ArrivalsAdapter;
import com.owl.travels.models.GetArrivalTimes;

import java.util.Arrays;

public class TrainArrivalFragment extends Fragment {
    private static Context context;
    private static String[] pos = {};
    public static String[] list = {};
    private static String[] temp = {};
    private Spinner stationSpinner;
    private GetArrivalTimes times = new GetArrivalTimes();
    private static RecyclerView recyclerView;
    private Spinner.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
            switch(position){
                case 0: list = getContext().getResources().getStringArray(R.array.nsl_stations);
                    //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
                    //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //stationSpinner.setAdapter(spinnerArrayAdapter);
                    break;
                case 1: list = getContext().getResources().getStringArray(R.array.ccl_stations);
                   // spinnerArrayAdapter = new ArrayAdapter<String>(
                     //       this, android.R.layout.simple_spinner_item, list);
                    //stationSpinner.setAdapter(spinnerArrayAdapter)
                    ;break;
                case 2: list = getContext().getResources().getStringArray(R.array.changi_stations);
                //    spinnerArrayAdapter = new ArrayAdapter<String>(
                  //          this, android.R.layout.simple_spinner_item, list);
                   // stationSpinner.setAdapter(spinnerArrayAdapter)
                    ;break;
                case 3: list = getContext().getResources().getStringArray(R.array.ewl_stations);
                    //spinnerArrayAdapter = new ArrayAdapter<String>(
                      //      this, android.R.layout.simple_spinner_item, list);
                   // stationSpinner.setAdapter(spinnerArrayAdapter)
                    ;break;
                case 4: list = getContext().getResources().getStringArray(R.array.tel_stations);
                    //spinnerArrayAdapter = new ArrayAdapter<String>(
                      //      this, android.R.layout.simple_spinner_item, list);
                  //  stationSpinner.setAdapter(spinnerArrayAdapter);
                    break;
            }
            temp = new String[list.length];
            for (int i=0;i<list.length;i++){
                temp[i] = list[i].substring(4);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, Arrays.asList(temp));
            stationSpinner.setAdapter(adapter);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };
    private Spinner.OnItemSelectedListener selectStationListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            pos = new String[]{list[i]};
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_train_arrivals, container, false);
        setHasOptionsMenu(true);
        getActivity().setTitle("Train Info");
        context = getContext();
        Spinner spinner = view.findViewById(R.id.arrivals_spinner);
        spinner.setOnItemSelectedListener(onItemSelectedListener);
        stationSpinner = view.findViewById(R.id.arrivals_station_spinner);
        stationSpinner.setOnItemSelectedListener(selectStationListener);
        recyclerView = view.findViewById(R.id.arrivals_rv);
        generateRecyclerView();
        //fetchArrivals();
        return view;
    }
    public static void generateRecyclerView(){
        ArrivalsAdapter arrivalsAdapter = new ArrivalsAdapter(pos);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(arrivalsAdapter);
    }

    /*private void fetchArrivals(){
        Runnable runnable = () ->{
            try{
                for (String i: list){
                    times.refreshFormBody(i.substring(0,3));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        };
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(runnable);
        es.shutdown();
    }*/
}
