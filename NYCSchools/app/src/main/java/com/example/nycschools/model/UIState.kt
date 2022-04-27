package com.example.nycschools.model

import com.example.nycschools.model.remote.SchoolListResponse
import com.example.nycschools.model.remote.SchoolSatResponse

sealed class UIState {

    data class ResponseListSchool(val data: List<SchoolListResponse>) : UIState()
    data class ResponseSchoolSat(val data: SchoolSatResponse) : UIState()
    data class Error(val errorMessage: String) : UIState()
    data class Loading(val isLoading: Boolean = false) : UIState()

}
