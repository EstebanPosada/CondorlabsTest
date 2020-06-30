package com.example.myapplication.service.model

data class League(
    val name: String,
    val id: String
){
    override fun toString(): String {
        return name
    }
}