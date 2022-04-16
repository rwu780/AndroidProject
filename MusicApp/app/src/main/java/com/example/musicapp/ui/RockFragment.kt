package com.example.musicapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.musicapp.databinding.FragmentPopBinding
import com.example.musicapp.databinding.FragmentRockBinding

class RockFragment : Fragment() {

    private lateinit var binding: FragmentRockBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRockBinding.inflate(inflater, container, false)
        return binding.root
    }

}