package com.example.githubhelper.domain.mappers

import com.example.githubhelper.data.remote.dto.UserDto
import com.example.githubhelper.data.remote.dto.UserRepositoryDto
import com.example.githubhelper.domain.db_models.RepositoryDO
import com.example.githubhelper.domain.db_models.UserDO
import com.example.githubhelper.domain.model.User
import com.example.githubhelper.domain.model.UserRepository

fun User.toUserDO() = UserDO(avatar_url = this.avatar_url, login = this.login)

fun UserDO.toUser() = User(avatar_url = this.avatar_url, login = this.login, ownerId = this.id)

fun UserDto.toUser() : User {
    return User(
        avatar_url = avatar_url ?: "",
        login = login ?: "",
        ownerId = id ?: 0
    )
}

fun UserRepository.toRepositoryDO() : RepositoryDO {
    return RepositoryDO(
        ownerId = id ?: 0,
        name = name ?: "",
        updated_at = updated_at ?: "",
        stargazers_count = stargazers_count ?: 0,
        language = language ?: "",
        url = url
    )
}

fun RepositoryDO.toUserRepository() : UserRepository {
    return UserRepository(
        name = name ?: "",
        updated_at = updated_at ?: "",
        stargazers_count = stargazers_count ?: 0,
        language = language ?: "",
        url = url,
        id = id ?: 0
    )
}

fun UserRepositoryDto.toUserRepository() : UserRepository {
    return UserRepository(
        name = name ?: "",
        updated_at = updated_at ?: "",
        stargazers_count = stargazers_count ?: 0,
        language = language ?: "",
        url = html_url ?: "",
        id = id ?: 0
    )
}