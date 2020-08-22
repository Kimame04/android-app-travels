package com.owl.travels.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.owl.travels.R;
import com.owl.travels.models.GetTrafficIncidents;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TrafficIncidentFragment extends Fragment implements OnMapReadyCallback {
    private GetTrafficIncidents incidents = new GetTrafficIncidents();
    private static final String url = "http://datamall2.mytransport.sg/ltaodataservice/TrafficIncidents";
    private TextView title;
    private TextView description;
    private GoogleMap googleMap;
    private Marker marker;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_traffic_incidents, container, false);
        setHasOptionsMenu(true);
        getActivity().setTitle("Traffic Incidents");
        title = view.findViewById(R.id.incident_title);
        description = view.findViewById(R.id.incident_description);
        title.setText("No traffic incidents as of now.");
        description.setText("Drive safely!");
        MapView mapView = view.findViewById(R.id.incident_maps);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
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
                //if(!(incidents.getMessage() == "")) {
                    //title.setText("Vehicle Breakdown");
                    //description.setText("(29/3)18:22 Vehicle breakdown on ECP \n(towards Changi Airport) after Still Rd Sth Exit. Avoid lane 3.");
                    //incidents.setLat("1.30398068448214");
                    //incidents.setLong("103.919182834377");
                LatLng latLng = new LatLng(Double.parseDouble(incidents.getLat()),Double.parseDouble(incidents.getLong()));
                    marker = googleMap.addMarker(new MarkerOptions()
                        .position(latLng).icon(BitmapDescriptorFactory.defaultMarker()));
                marker.setTag(latLng);
                //}

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
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraPosition cameraPosition = CameraPosition.builder().target(new LatLng(1.357437, 103.819313)).zoom(11).bearing(0).tilt(0).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_traffic_incidents, menu);
    }
}
