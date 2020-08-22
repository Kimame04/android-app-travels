package com.owl.travels;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.owl.travels.fragments.BusArrivalFragment;
import com.owl.travels.fragments.SettingsFragment;
import com.owl.travels.fragments.TrafficIncidentFragment;
import com.owl.travels.fragments.TrainFragment;
import com.squareup.seismic.ShakeDetector;

public class MainActivity extends AppCompatActivity {
    private Context context;
    public static FrameLayout frameLayout;
    private SensorManager sensorManager;
    private ShakeDetector shakeDetector;
    private RelativeLayout relativeLayout;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            item -> {
                if (bottomNavigationView.getSelectedItemId() == item.getItemId())
                    return true;
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.buses:
                        selectedFragment = new BusArrivalFragment();
                        break;
                    case R.id.trains:
                        selectedFragment = new TrainFragment();
                        break;
                    case R.id.traffic:
                        selectedFragment = new TrafficIncidentFragment();
                        break;
                    case R.id.settings:
                        selectedFragment = new SettingsFragment();
                        break;
                }
                if (androidx.preference.PreferenceManager.getDefaultSharedPreferences(context).getBoolean("settings_animation", true))
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left).replace(R.id.fragment_container, selectedFragment).commitAllowingStateLoss();
                else
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commitAllowingStateLoss();
                return true;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        shakeDetector = new ShakeDetector(() -> new MaterialAlertDialogBuilder(this)
                .setTitle("Shake detected").setMessage("Send feedback via email?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    String[] TO = {"h1710095@nushigh.edu.sg"};
                    String[] CC = {};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_CC, CC);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Put a meaningful subject name here");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Insert your feedback here. Provide feedback constructively!");
                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send mail"));
                    } catch (android.content.ActivityNotFoundException e) {
                        Snackbar.make(relativeLayout, "You have no email client installed.", BaseTransientBottomBar.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("No", null).show());
        shakeDetector.start(sensorManager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        frameLayout = findViewById(R.id.fragment_container);
        relativeLayout = findViewById(R.id.main_rl);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        switch (sharedPreferences.getString("settings_startup_fragment", "")) {
            case "Bus Arrivals":
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BusArrivalFragment()).commitAllowingStateLoss();
                bottomNavigationView.setSelectedItemId(R.id.buses);
                break;
            case "Settings":
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commitAllowingStateLoss();
                bottomNavigationView.setSelectedItemId(R.id.settings);
                break;
            case "Traffic Incidents":
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TrafficIncidentFragment()).commitAllowingStateLoss();
                bottomNavigationView.setSelectedItemId(R.id.traffic);
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TrainFragment()).commitAllowingStateLoss();
                bottomNavigationView.setSelectedItemId(R.id.trains);
                break;
        }
        if (!sharedPreferences.getBoolean("onboarding_compelete",false)){
            startActivity(new Intent(this, OnboardingActivity.class));
            finish();
        }

    }
    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    public static void showNoConnectionSnackBar() {
        final Snackbar snackbar = Snackbar.make(frameLayout, "No Internet Connection", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }
}