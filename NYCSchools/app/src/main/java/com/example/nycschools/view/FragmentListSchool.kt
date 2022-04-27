package com.example.nycschools.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nycschools.databinding.ListSchoolFragmentLayoutBinding
import com.example.nycschools.model.Repository
import com.example.nycschools.model.RepositoryImpl
import com.example.nycschools.viewModel.NYCViewModel

class FragmentListSchool : Fragment() {

    private lateinit var binding : ListSchoolFragmentLayoutBinding
    private val viewModel : NYCViewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return NYCViewModel(respository) as T
            }

        })[NYCViewModel::class.java]
    }

    private val respository : Repository by lazy {
        RepositoryImpl()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = ListSchoolFragmentLayoutBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }




}