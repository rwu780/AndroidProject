package com.example.musicapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.musicapp.databinding.FragmentClassicBinding

class ClassicFragment: Fragment() {

    private lateinit var binding: FragmentClassicBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClassicBinding.inflate(inflater, container, false)
        return binding.root

//        return super.onCreateView(inflater, container, savedInstanceState)
    }
}