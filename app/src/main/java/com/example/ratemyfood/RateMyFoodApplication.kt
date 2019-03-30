package com.example.ratemyfood

import android.app.Activity
import android.app.Application
import com.example.ratemyfood.di.component.DaggerAppComponent
import com.example.ratemyfood.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class RateMyFoodApplication : Application(), HasActivityInjector{
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}
