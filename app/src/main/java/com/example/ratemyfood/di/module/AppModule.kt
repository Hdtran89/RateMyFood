package com.example.ratemyfood.di.module

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import com.example.ratemyfood.dao.BusinessDao
import com.example.ratemyfood.dao.Database
import com.example.ratemyfood.dao.ReviewDao
import com.example.ratemyfood.util.Constants
import com.example.ratemyfood.util.Utils
import com.example.ratemyfood.util.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app:Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideBusinessCardDatabase(app: Application): Database = Room.databaseBuilder(app,
        Database::class.java, Constants.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideBusinessDao(
        database: Database): BusinessDao = database.businessDao()

    @Provides
    @Singleton
    fun provideReviewDao(
        database: Database): ReviewDao = database.reviewDao()

    @Provides
    @Singleton
    fun provideViewModelFactory(
        factory: ViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)
}