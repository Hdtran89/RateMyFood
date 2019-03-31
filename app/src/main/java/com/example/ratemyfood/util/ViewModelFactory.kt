package com.example.ratemyfood.util

import android.arch.lifecycle.ViewModel
import android.support.annotation.NonNull
import javax.inject.Inject
import android.arch.lifecycle.ViewModelProvider
import com.example.ratemyfood.viewmodel.BusinessCardViewModel
import javax.inject.Provider
import javax.inject.Singleton


@Singleton
class ViewModelFactory @Inject constructor(
    private val businessCardViewModel: BusinessCardViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusinessCardViewModel::class.java!!)) {
            return businessCardViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}