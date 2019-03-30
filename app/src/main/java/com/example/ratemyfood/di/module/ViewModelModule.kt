package com.example.ratemyfood.di.module

import dagger.Module
import javax.inject.Singleton
import android.arch.lifecycle.ViewModelProvider
import com.example.ratemyfood.util.ViewModelFactory
import dagger.Binds

@Singleton
@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}