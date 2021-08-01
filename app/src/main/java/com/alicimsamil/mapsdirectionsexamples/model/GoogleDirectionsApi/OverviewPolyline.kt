package com.alicimsamil.mapsdirectionsexamples.model.GoogleDirectionsApi

import com.google.gson.annotations.SerializedName

data class OverviewPolyline(
    @SerializedName("points")
    var points: String?
)