package com.example.ktor.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor.common.Resource
import com.example.ktor.domain.use_case.GetGithubInfoUseCase
import com.example.ktor.domain.use_case.GithubInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel

class GithubViewModel @Inject constructor(
    private val getGithubInfoUseCase: GetGithubInfoUseCase
) : ViewModel() {
    private val _state = mutableStateOf(GithubInfoState())
    val state: State<GithubInfoState> = _state

    init {
        getInfo()
    }

    private fun getInfo() {
        getGithubInfoUseCase().onEach { res ->
            when (res) {
                is Resource.Success -> {
                    _state.value = GithubInfoState(info = res.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = GithubInfoState(error = res.message ?: "Error occurred")
                }

                is Resource.Loading -> {
                    _state.value = GithubInfoState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}