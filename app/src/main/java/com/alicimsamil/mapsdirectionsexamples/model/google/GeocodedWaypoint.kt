package com.alicimsamil.mapsdirectionsexamples.model.google

data class GeocodedWaypoint(
    val geocoder_status: String,
    val place_id: String,
    val types: List<String>
)