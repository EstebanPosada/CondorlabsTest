package com.example.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.service.database.LocalDataSource
import com.example.myapplication.service.model.Event
import com.example.myapplication.service.model.Events
import com.example.myapplication.service.model.Team
import com.example.myapplication.service.repository.MainRepository
import com.example.myapplication.service.repository.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val mainRepository: MainRepository, private val room: LocalDataSource) :
    ViewModel() {

    val teamsResult = MutableLiveData<ResultTeams>()
    val teamData = MutableLiveData<DataTeam>()

    fun getTeams(id: String) {
        viewModelScope.launch {
            val teams = withContext(Dispatchers.IO) {
                mainRepository.fetchTeams(id)
            }
            when (teams) {
                is Result.Success -> {
                    teamsResult.value =
                        ResultTeams(teams.data.teams)
                    room.saveTeams(teams.data.teams)
                }
                is Result.Error -> teamsResult.value =
                    ResultTeams(room.getTeams())
                is Result.NetworkError -> teamsResult.value =
                    ResultTeams(room.getTeams())
                is Result.GenericError -> teamsResult.value =
                    ResultTeams(room.getTeams())
            }
        }
    }

    fun getTeamFromId(id: String) {
        viewModelScope.launch {
            val events = withContext(Dispatchers.IO) { mainRepository.fetchEvents(id) }
            val data = room.findById(id)

            when (events) {
                is Result.Success -> teamData.value = DataTeam(data, events.data.events)
                is Result.Error -> teamData.value = DataTeam(data)
                is Result.NetworkError -> teamData.value = DataTeam(data)
                is Result.GenericError -> teamData.value = DataTeam(data)
            }
        }
    }
}

data class ResultTeams(val teams: List<Team>? = null)
data class DataTeam(val team: Team, val events: List<Event>? = null)