package com.example.myapplication.service.repository

import com.example.myapplication.service.model.Event
import com.example.myapplication.service.model.Events
import com.example.myapplication.service.model.Teams

class MainRepositoryImpl(private val api: SportsApi) : MainRepository {

    override suspend fun fetchTeams(id: String): Result<Teams> =
        safeApiCall { doFetchTeams(id) }

    private suspend fun doFetchTeams(id: String): Result<Teams> {
        val response = api.getTeams(id)
        return if (response.isSuccessful)
            Result.Success(response.body()!!)
        else Result.GenericError(response.code(), response.message())
    }

    override suspend fun fetchEvents(id: String): Result<Events> = safeApiCall { doFetchEvents(id) }

    private suspend fun doFetchEvents(id: String): Result<Events> {
        val response = api.getNextEvents(id)
        return if (response.isSuccessful)
            Result.Success(response.body()?: Events())
        else Result.GenericError(response.code(), response.message())
    }
}