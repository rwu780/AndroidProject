package com.example.nycschools.model

import com.example.nycschools.model.remote.NycApi
import com.example.nycschools.model.remote.SchoolListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

class RepositoryImpl : Repository {

    private val service = NycApi.initRetrofit()

    override fun useCaseSchoolList(): Flow<UIState> {
        return flow {
            emit(UIState.Loading(true))
            kotlinx.coroutines.delay(500)

            val response = service.getSchoolList()
            emit(UIState.Loading())

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.ResponseListSchool( it ))
                } ?: emit( UIState.Error(response.message()) )
            } else {
                emit( UIState.Error(response.message()) )
            }

        }
    }

    override fun useCaseSchoolSatByDBN(dbn: String): Flow<UIState> {
        TODO("Not yet implemented")
    }
}