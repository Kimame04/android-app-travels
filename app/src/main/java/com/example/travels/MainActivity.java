package com.example.travels;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.travels.fragments.BusArrivalFragment;
import com.example.travels.fragments.SettingsFragment;
import com.example.travels.fragments.TrainArrivalFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.seismic.ShakeDetector;

public class MainActivity extends AppCompatActivity {
    private Context context;
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
                        selectedFragment = new TrainArrivalFragment();
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
    }
}