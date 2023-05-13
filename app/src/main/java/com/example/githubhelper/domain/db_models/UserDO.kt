package com.example.githubhelper.domain.db_models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.githubhelper.common.Constants

@Entity(tableName = Constants.USERS_TABLE_NAME)
data class UserDO(
    val avatar_url: String? = null,
    val login: String? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)