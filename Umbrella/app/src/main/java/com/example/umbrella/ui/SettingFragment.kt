package com.example.umbrella.ui

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceFragmentCompat
import com.example.umbrella.R
import com.example.umbrella.viewmodel.WeatherViewModel


class SettingFragment : PreferenceFragmentCompat() {

    private val shareViewModel : WeatherViewModel by activityViewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        val context = preferenceManager.context


        addPreferencesFromResource(R.xml.preferences)
    }

}