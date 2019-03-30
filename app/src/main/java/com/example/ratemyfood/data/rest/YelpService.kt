package com.example.ratemyfood.data.rest

import com.example.ratemyfood.data.model.Business
import com.example.ratemyfood.data.model.Review
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface YelpService {

    @GET("businesses/search")
    fun getRepositories(@Header ("Authorization") auth: String): Single<List<Business>>

    @GET("businesses/{id}/reviews")
    fun getReviews(@Path("id") id: String,
                   @Header ("Authorization") auth: String): Single<List<Review>>
}