package com.example.plantixcodechallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantixcodechallenge.data.model.Name
import com.example.plantixcodechallenge.data.repository.NameRepository
import javax.inject.Inject
import kotlinx.coroutines.launch

class NameListViewModel @Inject constructor(private val repository: NameRepository) : ViewModel() {

    private val _nameList = MutableLiveData<List<Name>>()
    val nameList: LiveData<List<Name>> = _nameList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getNameList() {
        viewModelScope.launch {
            _loading.value = true
            val list = repository.getData()
            list.map {
                it.nameValue = it.nameValue.replaceFirstChar { firstChar ->
                    firstChar.uppercase()
                }
            }
            _nameList.value = list
            _loading.value = false
        }
    }
}
