package com.example.ratemyfood.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.ratemyfood.data.model.Business
import com.example.ratemyfood.data.model.Review

@Database(entities = arrayOf(Business::class, Review::class), version = 2)
abstract class Database: RoomDatabase(){
    abstract fun businessDao(): BusinessDao
    abstract fun reviewDao(): ReviewDao
}
