package com.owl.travels.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.owl.travels.fragments.TrainArrivalFragment;
import com.owl.travels.fragments.TrainServiceFragment;

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
                return new TrainServiceFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
