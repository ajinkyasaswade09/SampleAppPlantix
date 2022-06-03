package com.example.plantixcodechallenge.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plantixcodechallenge.di.scope.ViewModelKey
import com.example.plantixcodechallenge.viewmodel.NameListViewModel
import com.example.plantixcodechallenge.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NameListViewModel::class)
    abstract fun bindNameListViewModel(nameListViewModel: NameListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
