package com.gitrend.ui.repos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gitrend.domain.GithubRepository
import com.gitrend.domain.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        loadRepos()
    }

    fun loadRepos() {
        uiState = UiState.Loading
        viewModelScope.launch {
            runCatching {
                error("dfg")
                uiState = UiState.Data(repos = repository.getRepos())
            }.onFailure {
                uiState = UiState.Error
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        object Error : UiState()
        data class Data(val repos: List<Repo> = emptyList()) : UiState()
    }
}