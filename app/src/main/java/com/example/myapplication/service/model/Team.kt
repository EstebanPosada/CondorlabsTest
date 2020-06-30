package com.example.myapplication.service.model

data class Team(
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
    val intFormedYear: String
)

data class Teams(
    val teams: List<Team>
)