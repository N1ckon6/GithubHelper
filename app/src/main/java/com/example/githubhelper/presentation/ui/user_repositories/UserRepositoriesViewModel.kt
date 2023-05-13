package com.example.githubhelper.presentation.ui.user_repositories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubhelper.domain.use_case.implementations.ReposUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRepositoriesViewModel @Inject constructor(
    private val reposUseCase: ReposUseCaseImpl
) : ViewModel() {

    private val _state = mutableStateOf(UserRepositoriesState())
    val state: State<UserRepositoriesState> = _state

    fun getReposFromServer(login: String, ownerId: Int) {
        viewModelScope.launch {
            runCatching {
                _state.value = _state.value.copy(isLoading = true)
                reposUseCase.getReposFromServer(login, ownerId)
            }.onSuccess {
                if (it.isNotEmpty())
                    _state.value =
                        UserRepositoriesState(userRepositories = it.sortedBy { repo -> repo.name })
            }.onFailure {
                _state.value = _state.value.copy(error = it, isLoading = false)
            }
        }
    }

    fun getReposFromDb(ownerId: Int) {
        viewModelScope.launch {
            runCatching {
                _state.value = _state.value.copy(isLoading = true)
                reposUseCase.getReposFromDb(ownerId)
            }.onSuccess {
                _state.value =
                    UserRepositoriesState(userRepositories = it.sortedBy { repo -> repo.name })
            }.onFailure {
                _state.value = _state.value.copy(error = it, isLoading = false)
            }
        }
    }
}