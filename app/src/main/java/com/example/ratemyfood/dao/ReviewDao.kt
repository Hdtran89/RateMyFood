package com.example.ratemyfood.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.ratemyfood.data.model.Review
import io.reactivex.Single

@Dao
interface ReviewDao {
    @Query("SELECT * FROM review WHERE business_id=:business_id ORDER BY id limit:limit offset :offset")
    fun getReview(limit:Int, offset:Int, business_id: String): Single<List<Review>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReview(review: Review)
}