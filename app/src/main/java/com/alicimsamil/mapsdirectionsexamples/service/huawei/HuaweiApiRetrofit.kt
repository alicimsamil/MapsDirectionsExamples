package com.alicimsamil.mapsdirectionsexamples.service.huawei

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HuaweiApiRetrofit {

    fun apiServices(context: Context): HuaweiApiInterface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://mapapi.cloud.huawei.com/mapApi/v1/")
            .build()
        return retrofit.create<HuaweiApiInterface>(HuaweiApiInterface::class.java)
    }



}