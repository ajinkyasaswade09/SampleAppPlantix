package com.example.plantixcodechallenge.data

import com.example.plantixcodechallenge.data.model.Name

interface DataSource {
    suspend fun getNameList(): List<Name>
}
