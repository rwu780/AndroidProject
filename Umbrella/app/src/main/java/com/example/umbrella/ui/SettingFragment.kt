package com.example.umbrella.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.umbrella.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private var binding : FragmentSettingBinding? = null
    private var configs = mapOf<String, String>("Zip" to "", "Units" to "Fahrenheit")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBuilding = FragmentSettingBinding.inflate(inflater, container, false)
        binding = fragmentBuilding
        return fragmentBuilding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}