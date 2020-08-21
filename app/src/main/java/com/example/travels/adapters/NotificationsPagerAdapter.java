package com.example.travels.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.travels.fragments.ServiceAlertFragment;
import com.example.travels.fragments.TrainArrivalFragment;

public class NotificationsPagerAdapter extends FragmentStateAdapter {
    public NotificationsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TrainArrivalFragment();
            default:
                return new ServiceAlertFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
