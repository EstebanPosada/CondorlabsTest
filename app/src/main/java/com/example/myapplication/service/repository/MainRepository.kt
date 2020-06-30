package com.example.myapplication.service.repository

import com.example.myapplication.service.model.Teams

interface MainRepository {
    suspend fun fetchTeams(): Result<Teams>
}