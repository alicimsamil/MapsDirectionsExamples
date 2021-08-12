package com.alicimsamil.mapsdirectionsexamples.model.huawei

data class Step(
    val action: String,
    val distance: Double,
    val distanceText: String,
    val duration: Double,
    val durationText: String,
    val endLocation: EndLocationX,
    val instruction: String,
    val orientation: Int,
    val polyline: List<Polyline>,
    val roadName: String,
    val startLocation: StartLocationX
)