package com.example.ratemyfood.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.ratemyfood.data.model.Business
import com.example.ratemyfood.data.model.BusinessCard
import com.example.ratemyfood.data.model.Review
import com.example.ratemyfood.data.rest.YelpRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BusinessCardViewModel @Inject constructor(
    private val yelpRepository: YelpRepository
) : ViewModel() {
    private var businessCardResult: MutableLiveData<List<BusinessCard>> = MutableLiveData()
    private var businessCardError: MutableLiveData<String> = MutableLiveData()
    private var businessCardLoader: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var disposableObserverBusinessCard: DisposableObserver<List<BusinessCard>>
    fun businessCardResult(): LiveData<List<BusinessCard>> {
        return businessCardResult
    }

    fun businessCardError(): LiveData<String> {
        return businessCardError
    }
    fun businessCardLoader(): LiveData<Boolean> {
        return businessCardLoader
    }

    fun fetchBusinessCards(limit : Int, offset: Int, search: String) {
        disposableObserverBusinessCard = object : DisposableObserver<List<BusinessCard>>() {
            override fun onComplete() {

            }

            override fun onNext(businessCardList: List<BusinessCard>) {
                businessCardResult.postValue(businessCardList)
                businessCardLoader.postValue(false)
            }

            override fun onError(e: Throwable) {
                businessCardError.postValue(e.message)
                businessCardLoader.postValue(false)
            }
        }
        //TODO:: Grab Key
        val key = "Bearer fb0JWmA1Z8-W_SLIhbFoLyGdf7PR-n7tNc7zYsj-jiWX-8Oj3oiM5JWcNzdehXBKtXYpay7NCEunqZo1IeR8rMYPjjdYtA35drcawFT4pbuI365gmVy5RKWqgaieXHYx"
        yelpRepository.getBusinessCard(key, limit, offset, search)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .delay(500, TimeUnit.MILLISECONDS)
            .subscribe(disposableObserverBusinessCard)
    }

    fun disposeElements(){
        if(null != disposableObserverBusinessCard && !disposableObserverBusinessCard.isDisposed) disposableObserverBusinessCard.dispose()
    }

}