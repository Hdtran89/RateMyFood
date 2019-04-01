package com.example.ratemyfood.data.rest

import android.util.Log
import com.example.ratemyfood.dao.BusinessDao
import com.example.ratemyfood.dao.ReviewDao
import com.example.ratemyfood.data.model.*
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
            it.businesses.forEach {
                business ->
                businessDao.insertBusiness(business)
            }
        }
    }

    fun getReviews(key: String, businessId: String) : Observable<Reviews>{
        return yelpService.getReviews(businessId, key).doOnNext {
            reviewDao.insertReview(it.reviews[0])
            reviewDao.insertReview(it.reviews[1])
            val list = ArrayList<Review>()
            list.add(it.reviews[0])
            list.add(it.reviews[1])
        }
    }

    fun getBusinessCardApi(key: String, search: String) : Observable<List<BusinessCard>> {
       return getBusiness(key, search)
           .flatMap { Observable.fromIterable(it.businesses) }
           .flatMap {
               business ->
               val list = arrayListOf<BusinessCard>()
               getReviews(key, business.id)
                   .flatMap { Observable.fromIterable(it.reviews) }
                   .flatMap {
                       review ->
                       val listReview = arrayListOf<Review>()
                       listReview.add(review)
                       list.add(BusinessCard(business, listReview))
                       Log.e("Test API", list.size.toString())
                       Observable.just(list)
                   }
           }
    }

    fun getBussinessCardLocal(limit: Int, offset: Int): Observable<List<BusinessCard>>{
        return businessDao.getBusiness(limit, offset)
            .toObservable()
            .flatMap { Observable.fromIterable(it) }
            .flatMap {
                business ->
                val list = arrayListOf<BusinessCard>()
                reviewDao.getReview()
                    .toObservable()
                    .flatMap { Observable.fromIterable(it) }
                    .flatMap {
                        review ->
                        val listReview = arrayListOf<Review>()
                        listReview.add(review)
                        list.add(BusinessCard(business, listReview))
                        Log.e("Test LOCAL", list.size.toString())
                        Observable.just(list)
                    }
            }

    }
}