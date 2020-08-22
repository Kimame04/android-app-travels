package com.owl.travels;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.owl.travels.fragments.OnboardingFragment;

public class OnboardingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.OnboardingTheme);
        setContentView(R.layout.activity_onboarding);
        getSupportFragmentManager().beginTransaction().replace(R.id.onboarding_frame_layout, new OnboardingFragment()).commitAllowingStateLoss();
    }
}
