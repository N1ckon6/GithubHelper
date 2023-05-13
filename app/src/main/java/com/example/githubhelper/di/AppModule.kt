package com.example.githubhelper.di

import android.content.Context
import androidx.room.Room
import com.example.githubhelper.common.Constants
import com.example.githubhelper.common.DefaultDispatcher
import com.example.githubhelper.common.IoDispatcher
import com.example.githubhelper.common.MainDispatcher
import com.example.githubhelper.data.db.AppDatabase
import com.example.githubhelper.data.db.DatabaseDao
import com.example.githubhelper.data.remote.GithubHelperApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGithubHelperApi() : GithubHelperApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubHelperApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Constants.DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(database: AppDatabase): DatabaseDao {
        return database.dao()
    }

    @Provides
    @DefaultDispatcher
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @IoDispatcher
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

}