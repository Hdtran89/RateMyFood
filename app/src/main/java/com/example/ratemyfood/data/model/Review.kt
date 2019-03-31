package com.example.ratemyfood.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "review")
data class Review(
    @Json(name = "id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : String,
    @Json(name = "text")
    @ColumnInfo(name = "text")
    val text : String,
    @Json(name = "business_id")
    @ColumnInfo(name = "business_id")
    val business_id: String) : Serializable