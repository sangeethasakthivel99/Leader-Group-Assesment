package com.sangeetha.leadertask.di

import android.content.Context
import androidx.room.Room
import com.sangeetha.leadertask.data.local.LeaderGroupDao
import com.sangeetha.leadertask.data.local.LeaderGroupDatabase
import com.sangeetha.leadertask.data.remote.ApiService
import com.sangeetha.leadertask.repository.ContactsRepository
import com.sangeetha.leadertask.util.Constants.BASE_URL
import com.sangeetha.leadertask.util.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LeaderGroupModule {

    @Provides
    @Singleton
    fun provideBASEURL() = BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideLeaderGroupDatabase(@ApplicationContext context: Context): LeaderGroupDatabase =
        Room.databaseBuilder(
            context,
            LeaderGroupDatabase::class.java,
            "leader_group_database"
        ).build()

    @Provides
    @Singleton
    fun provideLeaderGroupDao(leaderGroupDatabase: LeaderGroupDatabase): LeaderGroupDao {
        return leaderGroupDatabase.leaderGroupDao()
    }

    @Provides
    @Singleton
    fun provideContactRepository(apiService: ApiService, leaderGroupDao: LeaderGroupDao): ContactsRepository {
        return ContactsRepository(apiService, leaderGroupDao)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelper(context)
    }
}
