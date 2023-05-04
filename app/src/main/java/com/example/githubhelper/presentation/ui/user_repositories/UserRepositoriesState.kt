package com.example.githubhelper.presentation.ui.user_repositories

import com.example.githubhelper.domain.model.UserRepository

data class UserRepositoriesState(
    val isLoading: Boolean = false,
    val userRepositories: List<UserRepository> = emptyList(),
    val error: Throwable? = null
)