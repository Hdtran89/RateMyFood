package com.example.ratemyfood.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "business")
data class Business(
    @Json(name = "id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : String,
    @Json(name = "image_url")
    @ColumnInfo(name = "image_url")
    val image_url: String) : Serializable