package com.example.myapplication.service.repository

import com.example.myapplication.service.model.Events
import com.example.myapplication.service.model.Teams
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SportsApi {

    @GET("lookup_all_teams.php")
    suspend fun getTeams(@Query("id") id: String): Response<Teams>

    @GET("https://www.thesportsdb.com/api/v1/json/1/eventsnext.php")
    suspend fun getNextEvents(@Query("id") id: String):Response<Events>
}