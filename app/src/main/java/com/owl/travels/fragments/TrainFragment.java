package com.owl.travels.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.owl.travels.R;
import com.owl.travels.adapters.NotificationsPagerAdapter;

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
            case R.id.menu_train_filter:
                generateAlertDialog(getContext(),getLayoutInflater());
                return true;
            case R.id.menu_train_refresh:
                Snackbar.make(getView(),"Refreshed",Snackbar.LENGTH_SHORT).show();
                return true;
            default:
                return true;
        }
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_train, menu);
    }
    private void generateAlertDialog(Context context, LayoutInflater inflater){
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_change_filter, null);
        RadioGroup rg = view.findViewById(R.id.filter_rg);
        Button cancel = view.findViewById(R.id.filter_cancel);
        cancel.setOnClickListener(view1 -> {
            dialog.dismiss();
        });
        Button apply = view.findViewById(R.id.filter_apply);
        apply.setOnClickListener(view2 ->{
            //TODO - Make the sorting algorithm
            dialog.dismiss();
            Snackbar.make(getView(),"Changes applied",Snackbar.LENGTH_SHORT).show();
        });
        switch(rg.getCheckedRadioButtonId()){
            case R.id.filter_1: //A-Z
                break;
            case R.id.filter_2: //Z-A
                break;
        }
        dialog.setView(view);
        dialog.show();
    }

}
