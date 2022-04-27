package com.example.nycschools.model

import com.example.nycschools.model.remote.SchoolListResponse
import com.example.nycschools.model.remote.SchoolSatResponse
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun useCaseSchoolList(): Flow<UIState>
    fun useCaseSchoolSatByDBN(dbn: String) : Flow<UIState>
}