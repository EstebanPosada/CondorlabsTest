package com.example.myapplication.service.repository

import com.example.myapplication.service.model.Events
import com.example.myapplication.service.model.Teams

interface MainRepository {
    suspend fun fetchTeams(id: String): Result<Teams>
    suspend fun fetchEvents(id: String): Result<Events>
}