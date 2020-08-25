package com.owl.travels.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.owl.travels.R;
import com.owl.travels.adapters.IncidentsAdapter;
import com.owl.travels.models.GetTrafficIncidents;

import java.util.Arrays;

public class TrafficIncidentFragment extends Fragment implements OnMapReadyCallback {
    private static GetTrafficIncidents incidents = new GetTrafficIncidents();
    private static final String url = "http://datamall2.mytransport.sg/ltaodataservice/TrafficIncidents";
    private static RecyclerView recyclerView;
    private static GoogleMap googleMap;
    private static Marker marker;
    private static Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_traffic_incidents, container, false);
        setHasOptionsMenu(true);
        getActivity().setTitle("Traffic Incidents");
        context = getContext();
        recyclerView = view.findViewById(R.id.incidents_rv);
        generateRecyclerView();
        /*title = view.findViewById(R.id.incident_title);
        description = view.findViewById(R.id.incident_description);
        title.setText("No traffic incidents as of now.");
        description.setText("Drive safely!");*/
        /*MapView mapView = view.findViewById(R.id.incident_maps);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }*/
        return view;
    }
    public static void generateRecyclerView(){
        incidents.getService();
        IncidentsAdapter arrivalsAdapter = new IncidentsAdapter((incidents.getArr()).toArray(new String[incidents.getArr().size()]));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(arrivalsAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    //    setMap();
    }
    /*public static void setMap(){
        incidents.getService();
        if((incidents.getMessage() == "")) {
            title.setText("Vehicle Breakdown");
            description.setText("(29/3)18:22 Vehicle breakdown on ECP \n(towards Changi Airport) after Still Rd Sth Exit. Avoid lane 3.");
            incidents.setLat("1.30398068448214");
            incidents.setLong("103.919182834377");
            LatLng latLng = new LatLng(Double.parseDouble(incidents.getLat()), Double.parseDouble(incidents.getLong()));
            marker = googleMap.addMarker(new MarkerOptions()
                    .position(latLng).title("Site of incident").icon(BitmapDescriptorFactory.defaultMarker()));
            marker.setTag(latLng);
        }
    }*/

    private void fetchIncidents(){
        Log.d("ZZZ","incidfethc");
        Runnable runnable = () ->{

            try{

                incidents.getService();

                //if(!(incidents.getMessage() == "")) {
                    //title.setText("Vehicle Breakdown");
                    //description.setText("(29/3)18:22 Vehicle breakdown on ECP \n(towards Changi Airport) after Still Rd Sth Exit. Avoid lane 3.");
                    //incidents.setLat("1.30398068448214");
                    //incidents.setLong("103.919182834377");
                /*LatLng latLng = new LatLng(Double.parseDouble(incidents.getLat()),Double.parseDouble(incidents.getLong()));
                    marker = googleMap.addMarker(new MarkerOptions()
                        .position(latLng).icon(BitmapDescriptorFactory.defaultMarker()));
                marker.setTag(latLng);*/
                //}


            } catch (Exception e){
                e.printStackTrace();
                Log.d("ZZZ","died");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        /*ExecutorService es = Executors.newCachedThreadPool();
        es.execute(runnable);
        es.shutdown();*/
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            default:
       //         setMap();
                fetchIncidents();
                Snackbar.make(getView(),"Refreshed", BaseTransientBottomBar.LENGTH_SHORT).show();
                return true;
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        TrafficIncidentFragment.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraPosition cameraPosition = CameraPosition.builder().target(new LatLng(1.357437, 103.819313)).zoom(11).bearing(0).tilt(0).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_traffic_incidents, menu);
    }
}
