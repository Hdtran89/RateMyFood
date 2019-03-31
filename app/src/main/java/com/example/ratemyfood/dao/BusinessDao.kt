package com.example.ratemyfood.dao

import android.arch.persistence.room.*
import com.example.ratemyfood.data.model.Business
import io.reactivex.Single

@Dao
interface BusinessDao {
    @Query("SELECT * FROM business ORDER BY id limit:limit offset :offset")
    fun getBusiness(limit:Int, offset:Int): Single<List<Business>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBusiness(business:Business)
}