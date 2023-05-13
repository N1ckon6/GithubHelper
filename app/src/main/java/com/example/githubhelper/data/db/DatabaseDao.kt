package com.example.githubhelper.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubhelper.common.Constants
import com.example.githubhelper.domain.db_models.RepositoryDO
import com.example.githubhelper.domain.db_models.UserDO

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserDO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsersRepos(repos: List<RepositoryDO>)

    @Query("SELECT * FROM ${Constants.USERS_TABLE_NAME}")
    suspend fun getUsers(): List<UserDO>

    @Query(value = "SELECT * FROM ${Constants.REPOSITORY_TABLE_NAME} WHERE ownerId == :ownerId")
    suspend fun getUsersRepos(ownerId: Int?): List<RepositoryDO>

}