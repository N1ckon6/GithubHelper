package com.example.githubhelper.domain.db_models

import androidx.room.Entity
import com.example.githubhelper.common.Constants


@Entity(primaryKeys = ["ownerId", "id"], tableName = Constants.REPOSITORY_TABLE_NAME)
data class RepositoryDO(
    val ownerId: Int = 0,
    val id: Int = 0,
    val name: String = "",
    val updated_at: String = "",
    val stargazers_count: Int = 0,
    val language: String = "",
    val url: String = ""
)