package com.example.githubhelper.domain.use_case.interfaces

import com.example.githubhelper.domain.model.UserRepository

interface ReposUseCase {
    suspend fun getReposFromServer(login:String, ownerId: Int): List<UserRepository>

    suspend fun getReposFromDb(ownerId: Int): List<UserRepository>

    suspend fun saveReposToDb(repos:List<UserRepository>, ownerId: Int)
}