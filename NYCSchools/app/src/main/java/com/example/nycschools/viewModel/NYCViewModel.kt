package com.example.nycschools.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nycschools.model.Repository
import com.example.nycschools.model.UIState
import com.example.nycschools.model.remote.SchoolListResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NYCViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _schoolList = MutableLiveData<UIState>()
    val schoolList: LiveData<UIState> get() = _schoolList

    init {
        getSchoolList()
    }

    /**
     * Coroutine scope define a "container" of Coroutines
     * Launch - Create and forget.
     * Async - Create and "await" for a value
     *
     * Dispatchers.IO - Network calls OR database transaction OR java.io.file
     * Dispatchers.Main - Main thread reference
     * Dispatchers.Default - Default Thread pool
     * Dispatchers.Undefined - Don't USE
     */
    private fun getSchoolList() {

        CoroutineScope(Dispatchers.IO).launch {
            repository.useCaseSchoolList().collect {
                _schoolList.value = it
            }
        }

    }
}