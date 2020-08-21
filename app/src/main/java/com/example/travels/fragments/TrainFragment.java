package com.example.travels.fragments;

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
import androidx.viewpager2.widget.ViewPager2;

import com.example.travels.R;
import com.example.travels.adapters.NotificationsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TrainFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_train, container, false);
        setHasOptionsMenu(true);
        tabLayout = view.findViewById(R.id.notifications_tab_layout);
        viewPager2 = view.findViewById(R.id.notifications_pager2);
        viewPager2.setAdapter(new NotificationsPagerAdapter(getActivity()));
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Train Arrivals");
                            break;
                        case 1:
                            tab.setText("Service Alerts");
                            break;
                    }
                }).attach();
        return view;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            default:
                return true;
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_train, menu);
    }
}
