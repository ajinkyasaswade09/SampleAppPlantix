package com.example.plantixcodechallenge.di

import dagger.Module
import dagger.Provides
import javax.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

const val IO_DISPATCHER = "io-dispatcher"
const val MAIN_DISPATCHER = "main-dispatcher"

@Module
class DispatchersModule {

    @Provides
    @Named(IO_DISPATCHER)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Named(MAIN_DISPATCHER)
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
