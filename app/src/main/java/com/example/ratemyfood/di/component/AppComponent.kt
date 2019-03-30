package com.example.ratemyfood.di.component

import android.app.Application
import com.example.ratemyfood.di.module.ActivityBuilder
import com.example.ratemyfood.di.module.AppModule
import com.example.ratemyfood.di.module.ContextModule
import dagger.Component
import javax.inject.Singleton
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = [ContextModule::class, AppModule::class, AndroidSupportInjectionModule::class, ActivityBuilder::class])
interface AppComponent {
    fun inject(app: Application)
}