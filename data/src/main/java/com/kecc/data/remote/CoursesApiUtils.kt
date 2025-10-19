package com.kecc.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoursesApiUtils {
    const val BASE_URL = "https://drive.usercontent.google.com"

    fun getInstance(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}