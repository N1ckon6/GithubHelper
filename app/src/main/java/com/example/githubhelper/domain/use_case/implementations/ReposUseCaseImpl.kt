package com.example.githubhelper.domain.use_case.implementations

import com.example.githubhelper.domain.model.UserRepository
import com.example.githubhelper.domain.repository.GithubHelperRepositoryImpl
import com.example.githubhelper.domain.use_case.interfaces.ReposUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReposUseCaseImpl @Inject constructor(
    private val repository: GithubHelperRepositoryImpl
) : ReposUseCase {
    override suspend fun getReposFromServer(login: String, ownerId: Int): List<UserRepository> {
        val repos = repository.getUserRepositoriesByLogin(login)
        if (repos.isNotEmpty())
            repository.saveReposToDb(repos, ownerId)
        return repos
    }

    override suspend fun getReposFromDb(ownerId: Int): List<UserRepository> =
        repository.getReposFromDb(ownerId)

}