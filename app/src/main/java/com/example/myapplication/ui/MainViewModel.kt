package com.example.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.service.database.RoomDataSource
import com.example.myapplication.service.model.Team
import com.example.myapplication.service.model.Teams
import com.example.myapplication.service.repository.MainRepository
import com.example.myapplication.service.repository.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val mainRepository: MainRepository, private val room: RoomDataSource) :
    ViewModel() {

    val teamsResult = MutableLiveData<ResultTeams>()

    fun getTeams() {
        viewModelScope.launch {
            val teams = withContext(Dispatchers.IO) {
                mainRepository.fetchTeams()
            }

            when (teams) {
                is Result.Success -> {
                    teamsResult.value =
                        ResultTeams(teams.data.teams)
                    room.saveTeams(teams.data.teams)
                }
                is Result.Error -> teamsResult.value =
                    ResultTeams(room.getTeams())
            }
        }
    }
}

data class ResultTeams(val teams: List<Team>? = null)