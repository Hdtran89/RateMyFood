package com.example.ratemyfood.data.rest

import android.util.Log
import com.example.ratemyfood.dao.BusinessDao
import com.example.ratemyfood.dao.ReviewDao
import com.example.ratemyfood.data.model.Business
import com.example.ratemyfood.data.model.BusinessCard
import com.example.ratemyfood.data.model.Businesses
import com.example.ratemyfood.data.model.Review
import com.example.ratemyfood.util.Utils
import io.reactivex.Observable
import javax.inject.Inject

class YelpRepository @Inject constructor(
    val yelpService: YelpService,
    val businessDao: BusinessDao,
    val reviewDao: ReviewDao,
    val utils: Utils
) {
    fun getBusinessCard(key: String, limit : Int, offset: Int, search:String) : Observable<List<BusinessCard>>{
        val hasConnection = utils.isConnectedToInternet()
        var observableFromApi: Observable<List<BusinessCard>>? = null
        if (hasConnection){
            observableFromApi = getBusinessCardApi(key, search)
        }
        val observableFromDb = getBussinessCardLocal(limit, offset)

        return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
        else observableFromDb
    }

    fun getBusiness(key : String, search: String) : Observable<Businesses>{
        val location = "San Jose, California"
        return yelpService.getBusinesses(key, search, location).doOnNext {
            Log.e("API DB *** ", it.businesses.size.toString())
            it.businesses.forEach {
                business ->
                businessDao.insertBusiness(business)
            }
        }
    }

    fun getBusinessCardApi(key: String, search: String): Observable<List<BusinessCard>>{
        return getBusiness(key, search).flatMap {
            val list = ArrayList<BusinessCard>()
            it.businesses.forEach {
                list.add(BusinessCard(it, emptyList()))
            }
            Observable.just(list)
        }
    }

    fun getBussinessCardLocal(limit: Int, offset: Int): Observable<List<BusinessCard>>{
        return businessDao.getBusiness(limit, offset).toObservable().flatMap {
            Log.e("REPOSITORY DB *** ", it.size.toString())
            val list = arrayListOf<BusinessCard>()
            it.forEach {
                list.add(BusinessCard(it, emptyList()))
            }
            Observable.just(list)
        }
    }
}