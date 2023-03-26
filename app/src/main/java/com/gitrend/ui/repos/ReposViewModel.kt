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

    var uiState: UiState by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            uiState = UiState(repos = repository.getRepos())
        }
    }

    data class UiState(val repos: List<Repo> = emptyList())
}