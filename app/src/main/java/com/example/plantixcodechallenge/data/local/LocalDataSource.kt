package com.example.plantixcodechallenge.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.plantixcodechallenge.data.DataSource
import com.example.plantixcodechallenge.data.model.Name
import com.google.gson.Gson
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val NAME_DATA_PREFERENCES_NAME = "name_data"

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = NAME_DATA_PREFERENCES_NAME
)

class LocalDataSource @Inject constructor(
    private val gson: Gson,
    private val context: Context
) : DataSource {

    private val nameListPreferences = stringPreferencesKey("namesList")
    private var nameList: ArrayList<Name> = arrayListOf()

    suspend fun saveCacheDataToPreferenceStore(namesList: List<Name>) {
        val jsonText: String = gson.toJson(namesList)

        context.dataStore.edit { preferences ->
            preferences[nameListPreferences] = jsonText
        }
    }

    override suspend fun getNameList(): List<Name> {
        val preferenceFlow: Flow<List<Name>> = context.dataStore.data
            .catch {
                if (it is IOException) {
                    it.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw it
                }
            }
            .map { preferences ->
                val data = preferences[nameListPreferences]
                data?.let {
                    val jsonText: String = it
                    val list = gson.fromJson(
                        jsonText,
                        Array<Name>::class.java
                    )
                    list?.let {
                        nameList = list.toCollection(ArrayList())
                    }
                }
                nameList
            }
        return preferenceFlow.first()
    }
}
