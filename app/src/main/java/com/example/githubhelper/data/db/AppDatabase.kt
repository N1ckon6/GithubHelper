package com.example.githubhelper.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubhelper.domain.db_models.RepositoryDO
import com.example.githubhelper.domain.db_models.UserDO

@Database(
    entities = [UserDO::class, RepositoryDO::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): DatabaseDao
}