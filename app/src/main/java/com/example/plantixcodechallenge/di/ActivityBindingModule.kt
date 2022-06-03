package com.example.plantixcodechallenge.di

import com.example.plantixcodechallenge.ui.NameListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun bindingNameListActivity(): NameListActivity
}
