package com.example.ratemyfood.di.module

import android.app.Application
import com.example.ratemyfood.data.rest.YelpService
import dagger.Module
import retrofit2.Retrofit
import dagger.Provides
import javax.inject.Singleton
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Singleton
@Module(includes = [ViewModelModule::class])
class AppModule(val app:Application) {
    private val BASE_URL = "https://api.yelp.com/v3/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): YelpService {
        return retrofit.create<YelpService>(YelpService::class.java)
    }

    @Singleton
    @Provides
    fun provideApplication(): Application = app
}