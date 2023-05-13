package com.example.githubhelper.data.repository

import com.example.githubhelper.domain.model.User
import com.example.githubhelper.domain.model.UserRepository

interface GithubHelperRepository {

    suspend fun getUsers(): List<User>

    suspend fun getUserRepositoriesByLogin(login: String): List<UserRepository>

    suspend fun saveUsersToDb(users: List<User>)

    suspend fun saveReposToDb(repos: List<UserRepository>, ownerId: Int)

    suspend fun getUsersFromDb(): List<User>

    suspend fun getReposFromDb(ownerId: Int?): List<UserRepository>
}