package com.example.githubhelper.domain.use_case.implementations

import com.example.githubhelper.domain.model.User
import com.example.githubhelper.domain.repository.GithubHelperRepositoryImpl
import com.example.githubhelper.domain.use_case.interfaces.UsersUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersUseCaseImpl @Inject constructor(
    private val repository: GithubHelperRepositoryImpl
) : UsersUseCase {
    override suspend fun getUsersFromServ(): List<User> {
        val users = repository.getUsers()
        if(users.isNotEmpty())
            repository.saveUsersToDb(users)
        return users
    }

    override suspend fun getUsersFromDb(): List<User> = repository.getUsersFromDb()

    override suspend fun saveUsersToDb(users: List<User>) = repository.saveUsersToDb(users)
}