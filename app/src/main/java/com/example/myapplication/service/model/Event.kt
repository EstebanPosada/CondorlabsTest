package com.example.myapplication.service.model

data class Event (
    val idEvent: String,
    val strEvent: String,
    val dateEvent: String
)

data class Events(
    val events: List<Event> = emptyList()
)