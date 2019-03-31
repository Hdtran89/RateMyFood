package com.example.ratemyfood.data.rest


import com.example.ratemyfood.data.model.Businesses
import com.example.ratemyfood.data.model.Reviews

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface YelpService {

    @GET("businesses/search")
    fun getBusinesses(@Header ("Authorization") auth: String,
                      @Query("term") term: String,
                      @Query("location") location: String): Observable<Businesses>

    @GET("businesses/{id}/reviews")
    fun getReviews(@Path("id") id: String,
                   @Header ("Authorization") auth: String): Observable<Reviews>

}