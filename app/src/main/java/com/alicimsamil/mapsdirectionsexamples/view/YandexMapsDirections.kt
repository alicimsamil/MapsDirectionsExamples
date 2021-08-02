package com.alicimsamil.mapsdirectionsexamples.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alicimsamil.mapsdirectionsexamples.R
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.Error
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.directions.driving.*


class YandexMapsDirections : AppCompatActivity(),InputListener,DrivingSession.DrivingRouteListener {

    private val API_KEY="Your Yandex Mapkit Api Key"
    private lateinit var mapView: MapView
    private lateinit var mapObjects:MapObjectCollection
    private lateinit var drivingRouter: DrivingRouter
    private lateinit var drivingSession: DrivingSession
    private lateinit var fpoint: Point
    private lateinit var spoint: Point
    private var temp=0






    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.setApiKey(API_KEY)
        MapKitFactory.initialize(this)
        DirectionsFactory.initialize(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yandex_maps_directions)
        mapView=findViewById(R.id.mapview)
        mapView.map.addInputListener(this)
        mapObjects=mapView.map.mapObjects.addCollection()
        drivingRouter = DirectionsFactory.getInstance().createDrivingRouter()

    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }




    override fun onDrivingRoutes(p0: MutableList<DrivingRoute>) {
            mapObjects.addPolyline(p0[0].geometry)
    }

    override fun onDrivingRoutesError(p0: Error) {
        Toast.makeText(this, p0.toString(), Toast.LENGTH_LONG).show()
    }


    override fun onMapTap(p0: Map, p1: Point) {

    }

    override fun onMapLongTap(p0: Map, p1: Point) {

        if(temp==0){
            mapObjects.addPlacemark(p1)
            fpoint=p1

        }
        else if(temp==1){
            mapObjects.addPlacemark(p1)
            spoint=p1
            val drivingOptions = DrivingOptions()
            val vehicleOptions = VehicleOptions()
            val requestPoints= arrayListOf<RequestPoint>()
            requestPoints.add(RequestPoint(fpoint,RequestPointType.WAYPOINT,null))
            requestPoints.add(RequestPoint(spoint,RequestPointType.WAYPOINT,null))
            drivingSession=drivingRouter.requestRoutes(requestPoints,drivingOptions,vehicleOptions,this)
        }
        temp=temp+1
    }


}