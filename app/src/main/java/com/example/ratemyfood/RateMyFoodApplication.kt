package com.example.ratemyfood

import android.app.Activity
import android.app.Application
import com.example.ratemyfood.di.component.DaggerAppComponent
import com.example.ratemyfood.di.module.AppModule
import com.example.ratemyfood.di.module.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class RateMyFoodApplication : Application(), HasActivityInjector{
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        val BASE_URL = "https://api.yelp.com/v3/"
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(BASE_URL))
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}
