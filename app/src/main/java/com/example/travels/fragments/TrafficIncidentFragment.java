package com.example.travels.fragments;

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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.travels.MainActivity;
import com.example.travels.R;

import org.json.JSONObject;

public class TrafficIncidentFragment extends Fragment {
    private Context context;
    private static final String url = "http://datamall2.mytransport.sg/ltaodataservice/TrafficIncidents";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_train, container, false);
        setHasOptionsMenu(true);
        getActivity().setTitle("Traffic Incidents");
        context = getContext();
        generateLocalInfo(url);
        return view;
    }
    private void generateLocalInfo(String url){
        if(!MainActivity.isConnectedToInternet(getContext()))
            MainActivity.showNoConnectionSnackBar();
        else{
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    //try {
                        Log.d("test", String.valueOf(response));
                        /*String dorscon = response.getString("dorscon");
                        String hospitalised = response.getJSONObject("caseData").getString("Hospitalised");
                        String stable = response.getJSONObject("caseData").getString("Hospitalised (Stable)");
                        String critical = response.getJSONObject("caseData").getString("Hospitalised (Critical)");
                        String death = response.getJSONObject("caseData").getString("Death");
                        String discharged = response.getJSONObject("caseData").getString("Discharged");
                        String total_cases = response.getJSONObject("caseData").getString("Total Confirmed Cases");
                        String last_updated = response.getString("lastUpdated");
                        info.setText("Dorscon level: " + dorscon + "\n" + "Total cases: " + total_cases + "\n"
                                + "Stable: " + stable + "\n" + "Critical: " + critical + "\n" + "Discharged: " + discharged + "\n"
                                + "Dead: " + death + "\n" + "Last updated as of:\n" + last_updated + "\n___\n");*/
                    //} catch (JSONException e) { e.printStackTrace(); }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) { error.printStackTrace(); }
            });
            Volley.newRequestQueue(context).add(jsonObjectRequest);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            default:
                generateLocalInfo(url);
                return true;
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_traffic_incidents, menu);
    }
}
