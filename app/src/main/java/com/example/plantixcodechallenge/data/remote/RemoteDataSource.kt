package com.example.plantixcodechallenge.data.remote

import com.example.plantixcodechallenge.data.DataSource
import com.example.plantixcodechallenge.data.model.Name
import javax.inject.Inject

class RemoteDataSource @Inject constructor() : DataSource {
    override suspend fun getNameList(): List<Name> = arrayListOf(
        Name("plantix"),
        Name("android"),
        Name("assignment"),
        Name("check"),
        Name("first"),
        Name("character"),
        Name("capital")
    )
}
