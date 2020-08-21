package com.example.travels;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    /*private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            item -> {
                if (bottomNavigationView.getSelectedItemId() == item.getItemId())
                    return true;
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.profile:
                        selectedFragment = new ProfileFragment();
                        break;
                    case R.id.explore:
                        selectedFragment = new ExploreFragment();
                        break;
                    case R.id.calendar:
                        selectedFragment = new EventsFragment();
                        break;
                    case R.id.settings:
                        selectedFragment = new SettingsFragment();
                        break;
                    case R.id.notifications:
                        selectedFragment = new NotificationsFragment();
                        break;
                }
                if (androidx.preference.PreferenceManager.getDefaultSharedPreferences(context).getBoolean("settings_animation", true))
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left).replace(R.id.fragment_container, selectedFragment).commitAllowingStateLoss();
                else
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commitAllowingStateLoss();
                return true;
            };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}