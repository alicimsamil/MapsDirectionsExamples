package com.alicimsamil.mapsdirectionsexamples.service.google

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GoogleApiRetrofit {



    fun apiServices(context: Context): GoogleApiInterface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://maps.googleapis.com")
            .build()
        return retrofit.create<GoogleApiInterface>(GoogleApiInterface::class.java)
    }




}