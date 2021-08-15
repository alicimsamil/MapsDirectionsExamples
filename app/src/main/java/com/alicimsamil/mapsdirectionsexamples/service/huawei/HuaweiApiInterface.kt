package com.alicimsamil.mapsdirectionsexamples.service.huawei

import com.alicimsamil.mapsdirectionsexamples.model.huawei.DirectionResponse
import com.alicimsamil.mapsdirectionsexamples.model.huawei.DirectionsRequest
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HuaweiApiInterface {


    @POST("routeService/driving")
    fun getDirections(@Body directionRequest: DirectionsRequest,
                      @Query("key") apiKey: String) : Single<DirectionResponse>

}