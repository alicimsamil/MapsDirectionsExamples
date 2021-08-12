package com.alicimsamil.mapsdirectionsexamples.model.huawei

import com.google.gson.annotations.SerializedName

data class LatLngData(@SerializedName("lat") val lat: Double,
                      @SerializedName("lng") val lng: Double)
