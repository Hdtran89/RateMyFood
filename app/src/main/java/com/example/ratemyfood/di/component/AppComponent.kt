package com.example.ratemyfood.di.component

import com.example.ratemyfood.RateMyFoodApplication
import com.example.ratemyfood.di.module.ActivityBuilder
import com.example.ratemyfood.di.module.AppModule
import com.example.ratemyfood.di.module.ContextModule
import com.example.ratemyfood.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules =
    [   ContextModule::class,
        AppModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        NetworkModule::class
    ])
interface AppComponent {
    fun inject(app: RateMyFoodApplication)
}