package com.alicimsamil.mapsdirectionsexamples.model.GoogleDirectionsApi

import com.google.gson.annotations.SerializedName

data class Southwest(
    @SerializedName("lat")
    var lat: Double?,
    @SerializedName("lng")
    var lng: Double?
)