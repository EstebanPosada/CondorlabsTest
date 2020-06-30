package com.example.myapplication.service.database

import androidx.room.*
import com.example.myapplication.service.model.Team

@Dao
interface TeamDao {

    @Query("SELECT * FROM Team")
    fun getAll(): List<Team>

    @Query("SELECT * FROM Team WHERE idTeam = :id")
    fun findById(id: String): Team

    @Query("SELECT COUNT(idTeam) FROM Team")
    fun teamCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTeams(teams: List<Team>)

    @Update
    fun updateTeam(team: Team)
}