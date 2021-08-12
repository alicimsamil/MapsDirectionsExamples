package com.alicimsamil.mapsdirectionsexamples.model.google

data class DirectionResponses(
    val geocoded_waypoints: List<GeocodedWaypoint>,
    val routes: List<Route>,
    val status: String
)