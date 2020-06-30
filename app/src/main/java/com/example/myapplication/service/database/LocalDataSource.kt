package com.example.myapplication.service.database

import com.example.myapplication.service.model.Team

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveTeams(teams: List<Team>)
    suspend fun getTeams(): List<Team>
    suspend fun findById(id: Int): Team
    suspend fun update(team: Team)
}