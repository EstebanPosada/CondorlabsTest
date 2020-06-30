package com.example.myapplication.service.repository

import com.example.myapplication.service.model.Teams
import retrofit2.Response
import retrofit2.http.GET

interface SportsApi {

    @GET("lookup_all_teams.php?id=4335")
    suspend fun getTeams(): Response<Teams>
}