package com.alicimsamil.mapsdirectionsexamples.model.huawei

data class Path(
    val distance: Double,
    val distanceText: String,
    val duration: Double,
    val durationInTraffic: Double,
    val durationInTrafficText: String,
    val durationText: String,
    val endAddress: String,
    val endLocation: EndLocation,
    val startAddress: String,
    val startLocation: StartLocation,
    val steps: List<Step>
)