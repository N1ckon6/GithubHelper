package com.example.githubhelper.domain.repository

import com.example.githubhelper.common.IoDispatcher
import com.example.githubhelper.data.remote.db.DatabaseDao
import com.example.githubhelper.data.remote.GithubHelperApi
import com.example.githubhelper.data.repository.GithubHelperRepository
import com.example.githubhelper.domain.mappers.toRepositoryDO
import com.example.githubhelper.domain.mappers.toUser
import com.example.githubhelper.domain.mappers.toUserDO
import com.example.githubhelper.domain.mappers.toUserRepository
import com.example.githubhelper.domain.model.User
import com.example.githubhelper.domain.model.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubHelperRepositoryImpl @Inject constructor(
        private val api: GithubHelperApi,
        private val database: DatabaseDao,
        @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GithubHelperRepository {

    override suspend fun getUsers(): List<User> =
        withContext(ioDispatcher) {
            api.getUsers().map { it.toUser() }
        }

    override suspend fun getUserRepositoriesByLogin(login: String): List<UserRepository> =
        withContext(ioDispatcher) {
            api.getUserRepositories(login).map { it.toUserRepository() }
        }

    override suspend fun saveUsersToDb(users: List<User>) {
        withContext(ioDispatcher) {
            database.insertUsers(users.map { it.toUserDO() })
        }
    }

    override suspend fun saveReposToDb(repos: List<UserRepository>, ownerId: Int?) {
        withContext(ioDispatcher) {
            database.insertUsersRepos(repos.map { it.toRepositoryDO() })
        }
    }

    override suspend fun getUsersFromDb(): List<User> = database.getUsers().map { it.toUser() }

    override suspend fun getReposFromDb(ownerId: Int?): List<UserRepository> = database.getUsersRepos(ownerId).map { it.toUserRepository() }


}