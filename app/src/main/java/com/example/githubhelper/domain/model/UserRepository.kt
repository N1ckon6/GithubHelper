package com.example.githubhelper.domain.model

data class UserRepository(
    val name: String,
    val updated_at: String,
    val stargazers_count: Int,
    val language: String,
    val url: String,
    val id: Int
)
