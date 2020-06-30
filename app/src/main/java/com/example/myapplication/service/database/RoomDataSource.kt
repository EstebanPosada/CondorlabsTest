package com.example.myapplication.service.database

import com.example.myapplication.service.model.Team
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: TeamDatabase) : LocalDataSource {

    private val teamDao = db.teamDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { teamDao.teamCount() <= 0 }

    override suspend fun saveTeams(teams: List<Team>) {
        withContext(Dispatchers.IO) { teamDao.insertTeams(teams) }
    }

    override suspend fun getTeams(): List<Team> = withContext(Dispatchers.IO) { teamDao.getAll() }

    override suspend fun findById(id: String): Team =
        withContext(Dispatchers.IO) { teamDao.findById(id) }

    override suspend fun update(team: Team) {
        withContext(Dispatchers.IO) { teamDao.updateTeam(team) }
    }
}