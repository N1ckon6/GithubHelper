package com.example.githubhelper.domain.use_case.interfaces

import com.example.githubhelper.domain.model.User

interface UsersUseCase {
    suspend fun getUsersFromServ(): List<User>

    suspend fun getUsersFromDb(): List<User>

    suspend fun saveUsersToDb(users: List<User>)
}