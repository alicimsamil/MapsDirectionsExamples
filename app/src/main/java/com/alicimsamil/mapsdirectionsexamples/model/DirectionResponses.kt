package com.alicimsamil.mapsdirectionsexamples.model

data class DirectionResponses(
    val geocoded_waypoints: List<GeocodedWaypoint>,
    val routes: List<Route>,
    val status: String
)