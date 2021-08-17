package com.alicimsamil.mapsdirectionsexamples.service.google

import android.content.Context
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GoogleApiRetrofit {

    fun apiServices(context: Context): GoogleApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create<GoogleApiInterface>(GoogleApiInterface::class.java)
    }

}