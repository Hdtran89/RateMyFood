package com.example.ratemyfood.di.module

import com.example.ratemyfood.ui.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract fun bindSearchActivity(): SearchActivity
}