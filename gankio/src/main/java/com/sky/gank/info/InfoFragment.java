package com.sky.gank.info;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.sky.gank.R;

public class InfoFragment extends PreferenceFragmentCompat {

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_info);
    }

}
