package com.example.myapplication.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Team(
    @PrimaryKey
    val idTeam: String,
    val idAPIfootball: String,
    val strStadium: String,
    val idLeague: String,
    val strStadiumThumb: String,
    val strTeam: String,
    val strAlternate: String,
    val strTeamBadge: String,
    val strTeamJersey: String,
    val strDescriptionEN: String,
    val strDescriptionES: String,
    val intFormedYear: String,
    val strWebsite: String,
    val strFacebook: String,
    val strTwitter: String,
    val strInstagram: String
)

data class Teams(
    val teams: List<Team>
)