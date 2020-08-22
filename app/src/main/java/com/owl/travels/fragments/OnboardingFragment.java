package com.owl.travels.fragments;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.leanback.app.OnboardingSupportFragment;
import androidx.preference.PreferenceManager;

import com.owl.travels.MainActivity;
import com.owl.travels.R;

public class OnboardingFragment extends OnboardingSupportFragment {

    private static final int numPages = 5;


    @Override
    protected int getPageCount() {
        return numPages;
    }

    @Override
    protected CharSequence getPageTitle(int pageIndex) {
        switch (pageIndex) {
            case 0:
                return "Welcome to Travels!";
            case 1:
                return "Train Arrivals";
            case 2:
                return "Bus Timings";
            case 3:
                return "Traffic Incidents";
            default:
                return "Enjoy Travels!";
        }
    }

    @Override
    protected CharSequence getPageDescription(int pageIndex) {
        switch (pageIndex) {
            case 0:
                return "Your companion for getting around Singapore's sophisticated transport system!";
            case 1:
                return "Finally, you know in advance when your next train is! We also give you disruption notices!";
            case 2:
                return "You don't need to actually be at the bus stop to know when your next ride is arriving!";
            case 3:
                return "So that you can plan the fastest drive.";
            default:
                return "To send feedback, shake your phone!";
        }
    }

    @Nullable
    @Override
    protected View onCreateBackgroundView(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    @Nullable
    @Override
    protected View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
        setArrowColor(Color.RED);
        setArrowBackgroundColor(Color.BLACK);
        setStartButtonText("Get started!");
        setDotBackgroundColor(Color.GRAY);
        ImageView content = new ImageView(getContext());
        content.setImageResource(R.mipmap.ic_launcher);
        content.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        content.setPadding(0, 32, 0, 32);
        return content;
    }

    @Override
    protected void onPageChanged(int newPage, int previousPage) {
        super.onPageChanged(newPage, previousPage);
    }

    @Nullable
    @Override
    protected View onCreateForegroundView(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    @Nullable
    @Override
    protected Animator onCreateLogoAnimation() {
        return AnimatorInflater.loadAnimator(getContext(),
                R.animator.lb_onboarding_logo_enter);
    }

    @Override
    protected void onFinishFragment() {
        super.onFinishFragment();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
            pref.edit().putBoolean("onboarding_complete", true).apply();
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }

}
