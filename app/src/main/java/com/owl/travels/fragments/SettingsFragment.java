package com.owl.travels.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.owl.travels.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    private Context context;
    private static Preference.OnPreferenceChangeListener onPreferenceChangeListener =
            (preference, newValue) -> {
                String value = newValue.toString();
                if (preference instanceof ListPreference) {
                    ListPreference listPreference = (ListPreference) preference;
                    int index = listPreference.findIndexOfValue(value);
                    if (listPreference.getValue() == null)
                        listPreference.setValueIndex(1);
                    preference.setSummary(index >= 0 ? listPreference.getEntries()[index] : null);
                }
                return true;
            };
    private static void bindSummaryValue(Preference preference){
        preference.setOnPreferenceChangeListener(onPreferenceChangeListener);
        onPreferenceChangeListener.onPreferenceChange(preference, PreferenceManager.getDefaultSharedPreferences(preference.getContext()).getString(preference.getKey(),""));
    }
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings,rootKey);
        getActivity().setTitle("Settings");
        context = getContext();
        bindSummaryValue(findPreference("settings_startup_fragment"));
        Preference notificationsPref = findPreference("settings_notifications");
/*        notificationsPref.setOnPreferenceClickListener(preference -> {
            Intent toSettings = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            toSettings.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
            startActivity(toSettings);
            return true;
        });*/
    }
}
