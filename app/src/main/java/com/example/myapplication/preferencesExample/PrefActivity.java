package com.example.myapplication.preferencesExample;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;

import com.example.myapplication.R;

public class PrefActivity extends PreferenceActivity {

    CheckBoxPreference chb;
    PreferenceCategory categ2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //прописать это активити в манифесте
        addPreferencesFromResource(R.xml.pref);

        chb = (CheckBoxPreference) findPreference("chb");
        categ2 = (PreferenceCategory) findPreference("categ2");
        categ2.setEnabled(chb.isChecked());

        chb.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                categ2.setEnabled(chb.isChecked());
                return false;
            }
        });
    }
}
