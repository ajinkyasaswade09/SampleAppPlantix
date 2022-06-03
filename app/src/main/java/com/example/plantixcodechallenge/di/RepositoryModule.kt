package com.example.plantixcodechallenge.di

import com.example.plantixcodechallenge.Logger
import com.example.plantixcodechallenge.data.local.LocalDataSource
import com.example.plantixcodechallenge.data.remote.RemoteDataSource
import com.example.plantixcodechallenge.data.repository.NameRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
object RepositoryModule {

    @Provides
    fun provideNameRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        dispatcher: CoroutineDispatcher,
        logger: Logger
    ): NameRepository = NameRepository(localDataSource, remoteDataSource, dispatcher, logger)
}