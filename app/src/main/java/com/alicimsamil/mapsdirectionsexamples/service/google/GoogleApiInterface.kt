package com.alicimsamil.mapsdirectionsexamples.service.google

import com.alicimsamil.mapsdirectionsexamples.model.google.DirectionResponses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApiInterface {
    @GET("maps/api/directions/json")
    fun getDirection(@Query("origin") origin: String,
                     @Query("destination") destination: String,
                     @Query("key") apiKey: String): Call<DirectionResponses>
}