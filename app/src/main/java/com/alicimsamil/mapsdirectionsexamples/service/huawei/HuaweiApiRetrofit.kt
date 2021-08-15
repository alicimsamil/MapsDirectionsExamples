package com.alicimsamil.mapsdirectionsexamples.service.huawei

import android.content.Context
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HuaweiApiRetrofit {

    fun apiServices(context: Context): HuaweiApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mapapi.cloud.huawei.com/mapApi/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create<HuaweiApiInterface>(HuaweiApiInterface::class.java)
    }



}