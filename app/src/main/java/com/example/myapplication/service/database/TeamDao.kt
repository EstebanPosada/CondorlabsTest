package com.example.myapplication.service.database

import androidx.room.*
import com.example.myapplication.service.model.Team

@Dao
interface TeamDao {

    @Query("SELECT * FROM Team")
    fun getAll(): List<Team>

    @Query("SELECT * FROM Team WHERE id = :id")
    fun findById(id: Int): Team

    @Query("SELECT COUNT(id) FROM Team")
    fun teamCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTeams(teams: List<Team>)

    @Update
    fun updateTeam(team: Team)
}