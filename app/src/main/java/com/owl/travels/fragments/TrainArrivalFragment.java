package com.owl.travels.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TrainArrivalFragment extends Fragment {
    private static Context context;
    private static int pos;
    private static String[] list = {};
    private GetArrivalTimes times = new GetArrivalTimes();
    private static RecyclerView recyclerView;
    private Spinner.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
            switch(position){
                case 0: list = getContext().getResources().getStringArray(R.array.nsl_stations);
                break;
                case 1: list = getContext().getResources().getStringArray(R.array.ccl_stations);break;
                case 2: list = getContext().getResources().getStringArray(R.array.changi_stations);break;
                case 3: list = getContext().getResources().getStringArray(R.array.ewl_stations);break;
                case 4: list = getContext().getResources().getStringArray(R.array.tel_stations);break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
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
        recyclerView = view.findViewById(R.id.arrivals_rv);
        generateRecyclerView();
        //fetchArrivals();
        return view;
    }
    public static void generateRecyclerView(){
        ArrivalsAdapter arrivalsAdapter = new ArrivalsAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(arrivalsAdapter);
    }


    /*private void fetchArrivals(){
=======
    private void fetchArrivals(){

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

}
