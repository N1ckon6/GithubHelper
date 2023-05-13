package com.example.githubhelper.domain.mappers

import com.example.githubhelper.data.remote.dto.UserDto
import com.example.githubhelper.data.remote.dto.UserRepositoryDto
import com.example.githubhelper.domain.db_models.RepositoryDO
import com.example.githubhelper.domain.db_models.UserDO
import com.example.githubhelper.domain.model.User
import com.example.githubhelper.domain.model.UserRepository

fun User.toUserDO() = UserDO(avatar_url = this.avatar_url, login = this.login, id = this.id ?: 0)

fun UserDO.toUser() = User(avatar_url = this.avatar_url, login = this.login, id = this.id)

fun UserDto.toUser(): User {
    return User(
        avatar_url = avatar_url,
        login = login,
        id = id
    )
}

fun UserRepository.toRepositoryDO(parentId: Int): RepositoryDO {
    return RepositoryDO(
        ownerId = parentId,
        name = name,
        updated_at = updated_at,
        stargazers_count = stargazers_count,
        language = language,
        url = url,
        id = id ?: 0
    )
}

fun RepositoryDO.toUserRepository(): UserRepository {
    return UserRepository(
        name = name,
        updated_at = updated_at,
        stargazers_count = stargazers_count,
        language = language,
        url = url,
        id = id
    )
}

fun UserRepositoryDto.toUserRepository(): UserRepository {
    return UserRepository(
        name = name,
        updated_at = updated_at,
        stargazers_count = stargazers_count,
        language = language,
        url = html_url,
        id = id ?: 0
    )
}