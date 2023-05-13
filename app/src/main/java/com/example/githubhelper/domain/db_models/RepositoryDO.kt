package com.example.githubhelper.domain.db_models

import androidx.room.Entity
import com.example.githubhelper.common.Constants


@Entity(primaryKeys = ["ownerId", "id"], tableName = Constants.REPOSITORY_TABLE_NAME)
data class RepositoryDO(
    val ownerId: Int = 0,
    val id: Int = 0,
    val name: String? = null,
    val updated_at: String? = null,
    val stargazers_count: Int? = null,
    val language: String? = null,
    val url: String? = null
)