package com.alicimsamil.mapsdirectionsexamples.model.GoogleDirectionsApi

import com.google.gson.annotations.SerializedName

data class Step(
    @SerializedName("distance")
    var distance: Distance?,
    @SerializedName("duration")
    var duration: Duration?,
    @SerializedName("end_location")
    var endLocation: EndLocation?,
    @SerializedName("html_instructions")
    var htmlInstructions: String?,
    @SerializedName("maneuver")
    var maneuver: String?,
    @SerializedName("polyline")
    var polyline: Polyline?,
    @SerializedName("start_location")
    var startLocation: StartLocation?,
    @SerializedName("travel_mode")
    var travelMode: String?
)