package com.example.ratemyfood.data.model

data class BusinessCard(
    val business: Business,
    val reviewList: List<Review> ? = null)