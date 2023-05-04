package com.example.githubhelper.presentation.ui.user_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubhelper.domain.use_case.implementations.UsersUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val usersUseCaseImpl: UsersUseCaseImpl
) : ViewModel() {

    private val _state = mutableStateOf(UserListState())
    val state: State<UserListState> = _state

    fun getUsersFromServ() {
        viewModelScope.launch {
            runCatching {
                _state.value = UserListState(isLoading = true)
                usersUseCaseImpl.getUsersFromServ()
            }.onSuccess {
                _state.value = UserListState(users = it)
                usersUseCaseImpl.saveUsersToDb(it)
            }.onFailure {
                _state.value = UserListState(error = it)
            }
        }
    }

    fun getUsersFromDb() {
        viewModelScope.launch {
            runCatching {
                _state.value = _state.value.copy(isLoading = true)
                usersUseCaseImpl.getUsersFromDb()
            }.onSuccess {
                _state.value = UserListState(users = it)
            }.onFailure {
                _state.value = UserListState(error = it)
            }
        }
    }
}