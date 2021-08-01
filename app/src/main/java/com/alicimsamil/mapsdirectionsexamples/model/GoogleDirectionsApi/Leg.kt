package com.alicimsamil.mapsdirectionsexamples.model.GoogleDirectionsApi

import com.google.gson.annotations.SerializedName

data class Leg(
    @SerializedName("distance")
    var distance: Distance?,
    @SerializedName("duration")
    var duration: Duration?,
    @SerializedName("end_address")
    var endAddress: String?,
    @SerializedName("end_location")
    var endLocation: EndLocation?,
    @SerializedName("start_address")
    var startAddress: String?,
    @SerializedName("start_location")
    var startLocation: StartLocation?,
    @SerializedName("steps")
    var steps: List<Step?>?,
    @SerializedName("traffic_speed_entry")
    var trafficSpeedEntry: List<Any?>?,
    @SerializedName("via_waypoint")
    var viaWaypoint: List<Any?>?
)