package com.example.githubhelper.presentation.ui.user_list

import com.example.githubhelper.domain.model.User

data class UserListState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: Throwable? = null
)