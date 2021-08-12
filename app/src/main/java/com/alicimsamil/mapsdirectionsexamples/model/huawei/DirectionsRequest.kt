package com.alicimsamil.mapsdirectionsexamples.model.huawei

import com.google.gson.annotations.SerializedName

data class DirectionsRequest(@SerializedName("origin") val origin: LatLngData,
                             @SerializedName("destination") val destination: LatLngData)
