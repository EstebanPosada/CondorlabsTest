package com.example.myapplication.service.repository

import com.example.myapplication.service.model.Teams

class MainRepositoryImpl(private val api: SportsApi) : MainRepository {

    override suspend fun fetchTeams(): Result<Teams> =
        safeApiCall { doFetchTeams() }

    private suspend fun doFetchTeams(): Result<Teams> {
        val response = api.getTeams()
        return if (response.isSuccessful)
            Result.Success(response.body()!!)
        else Result.GenericError(response.code(), response.message())
    }

}