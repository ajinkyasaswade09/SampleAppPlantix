package com.example.plantixcodechallenge.di

import android.content.Context
import com.example.plantixcodechallenge.Logger
import com.example.plantixcodechallenge.SystemLogger
import com.example.plantixcodechallenge.data.DataSource
import com.example.plantixcodechallenge.data.local.LocalDataSource
import com.example.plantixcodechallenge.data.remote.RemoteDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
object DataModule {

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideLocalDataSource(gson: Gson, context: Context): DataSource =
        LocalDataSource(gson, context)

    @Provides
    fun provideRemoteDataSource(): DataSource = RemoteDataSource()

    @Provides
    fun provideLogger(): Logger = SystemLogger()
}
