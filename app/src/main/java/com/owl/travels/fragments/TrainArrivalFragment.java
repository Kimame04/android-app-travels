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
import com.owl.travels.models.TrainInfo;

import java.util.ArrayList;
import java.util.List;

public class TrainArrivalFragment extends Fragment {
    private Context context;
    private static int pos;
    private RecyclerView recyclerView;
    List<TrainInfo> testList = new ArrayList<>();
    private GetArrivalTimes times = new GetArrivalTimes();

    private Spinner.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
            pos = position;
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
        TrainInfo train1 = new TrainInfo(5,"Pasir Ris");
        testList.add(train1);
        ArrivalsAdapter arrivalsAdapter = new ArrivalsAdapter(testList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(arrivalsAdapter);
        return view;
    }
}
