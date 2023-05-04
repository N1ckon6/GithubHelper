package com.example.githubhelper.data.remote

import com.example.githubhelper.data.remote.dto.UserDto
import com.example.githubhelper.data.remote.dto.UserRepositoryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubHelperApi {

    @GET("/users")
    suspend fun getUsers() : List<UserDto>

    @GET("/users/{login}/repos")
    suspend fun getUserRepositories(@Path("login") login: String) : List<UserRepositoryDto>
}