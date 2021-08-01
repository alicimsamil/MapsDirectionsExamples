package com.alicimsamil.mapsdirectionsexamples.model.GoogleDirectionsApi

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRetrofit {



    fun apiServices(context: Context): ApiServices {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://maps.googleapis.com")
            .build()
        return retrofit.create<ApiServices>(ApiServices::class.java)
    }




}