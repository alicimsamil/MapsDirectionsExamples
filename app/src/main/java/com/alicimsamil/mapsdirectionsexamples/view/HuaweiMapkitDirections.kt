package com.alicimsamil.mapsdirectionsexamples.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alicimsamil.mapsdirectionsexamples.R
import com.alicimsamil.mapsdirectionsexamples.model.huawei.DirectionResponse
import com.alicimsamil.mapsdirectionsexamples.model.huawei.DirectionsRequest
import com.alicimsamil.mapsdirectionsexamples.model.huawei.LatLngData
import com.alicimsamil.mapsdirectionsexamples.model.huawei.Path
import com.alicimsamil.mapsdirectionsexamples.service.huawei.HuaweiApiRetrofit
import com.alicimsamil.mapsdirectionsexamples.viewmodel.HuaweiViewModel
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.OnMapReadyCallback
import com.huawei.hms.maps.model.LatLng
import com.huawei.hms.maps.model.MarkerOptions
import com.huawei.hms.maps.model.PolylineOptions


class HuaweiMapkitDirections : AppCompatActivity(),OnMapReadyCallback {

    private lateinit var hMap:HuaweiMap
    private lateinit var mMapView:MapView
    private var temp=0
    private lateinit var origin:LatLngData
    private lateinit var destination:LatLngData
    private val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    private lateinit var directionPoints:DirectionsRequest
    private lateinit var viewModel: HuaweiViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huawei_mapkit_directions)
        viewModel=ViewModelProvider(this).get(HuaweiViewModel::class.java)
        mMapView=findViewById(R.id.huaweiMapView)
        mMapView.onCreate(savedInstanceState?.getBundle(MAPVIEW_BUNDLE_KEY))
        mMapView.getMapAsync(this@HuaweiMapkitDirections)

    }

    override fun onMapReady(p0: HuaweiMap?) {
        hMap=p0!!
        hMap.setOnMapLongClickListener {
            onLongTapListener(it)
        }


    }


    private fun addPolyLines(directionResponse: DirectionResponse){
        val path=directionResponse.routes.get(0).paths.get(0)
        val options = PolylineOptions()
        options.add(LatLng(path.startLocation.lat, path.startLocation.lng))
        path.steps.forEach {
            it.polyline.forEach {
                options.add(LatLng(it.lat, it.lng))
            }
        }
        options.add(LatLng(path.endLocation.lat, path.endLocation.lng))
        options.color(Color.BLUE)
        options.width(10f)
        hMap.addPolyline(options)

    }







    fun onLongTapListener(latLan: LatLng){
        if(temp==0){
            hMap.addMarker(MarkerOptions().position(latLan))
            origin=LatLngData(latLan.latitude,latLan.longitude)
        }
        else if (temp==1){
            hMap.addMarker(MarkerOptions().position(latLan))
            destination=LatLngData(latLan.latitude,latLan.longitude)
            directionPoints= DirectionsRequest(origin,destination)
            viewModel.getPoint(this@HuaweiMapkitDirections,directionPoints,"Your API Key")
            viewModel.points.observe(this, Observer {
                addPolyLines(it)
            })


        }
        temp=temp+1


    }











    override fun onStart() {
        super.onStart()
        mMapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView.onStop()
    }
    override fun onDestroy() {
        super.onDestroy()
        mMapView.onDestroy()
    }

    override fun onPause() {
        mMapView.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }



}


