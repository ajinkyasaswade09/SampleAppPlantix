package com.example.plantixcodechallenge.data.repository

import com.example.plantixcodechallenge.Logger
import com.example.plantixcodechallenge.data.local.LocalDataSource
import com.example.plantixcodechallenge.data.model.Name
import com.example.plantixcodechallenge.data.remote.RemoteDataSource
import com.example.plantixcodechallenge.di.IO_DISPATCHER
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class NameRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    @Named(IO_DISPATCHER)
    private val dispatcher: CoroutineDispatcher,
    private val logger: Logger
) {

    /**
     * Function checks the data in the local data source if it exists it returns local data
     * if not then it makes call to remote data and fetch the data
     */
    suspend fun getData(): List<Name> {
        return withContext(dispatcher) {
            val localData = localDataSource.getNameList()
            if (localData.isEmpty()) {
                logger.log("fetching data from remote")
                //delay to simulate remote api call
                delay(2000L)
                val list = remoteDataSource.getNameList()
                localDataSource.saveCacheDataToPreferenceStore(list)
                list
            } else {
                logger.log("fetching data from local")
                localData
            }
        }
    }
}
