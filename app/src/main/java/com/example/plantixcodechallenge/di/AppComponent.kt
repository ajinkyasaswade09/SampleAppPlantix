package com.example.plantixcodechallenge.di

import android.content.Context
import com.example.plantixcodechallenge.PlantixApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        ViewModelModule::class,
        DispatchersModule::class,
        DataModule::class]
)
interface AppComponent : AndroidInjector<PlantixApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
