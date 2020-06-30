package com.example.myapplication.service.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.service.model.Team

@Database(entities = [Team::class], version = 1)
abstract class TeamDatabase : RoomDatabase(){

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            TeamDatabase::class.java,
            "team-db"
        ).build()
    }

    abstract fun teamDao(): TeamDao
}