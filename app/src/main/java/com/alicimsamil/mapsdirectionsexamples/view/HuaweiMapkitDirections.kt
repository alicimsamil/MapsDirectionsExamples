package com.alicimsamil.mapsdirectionsexamples.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alicimsamil.mapsdirectionsexamples.R
import com.alicimsamil.mapsdirectionsexamples.model.huawei.DirectionResponse
import com.alicimsamil.mapsdirectionsexamples.model.huawei.DirectionsRequest
import com.alicimsamil.mapsdirectionsexamples.model.huawei.LatLngData
import com.alicimsamil.mapsdirectionsexamples.model.huawei.Path
import com.alicimsamil.mapsdirectionsexamples.service.huawei.HuaweiApiRetrofit
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.OnMapReadyCallback
import com.huawei.hms.maps.model.LatLng
import com.huawei.hms.maps.model.MarkerOptions
import com.huawei.hms.maps.model.PolylineOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HuaweiMapkitDirections : AppCompatActivity(),OnMapReadyCallback {

    private lateinit var hMap:HuaweiMap
    private lateinit var mMapView:MapView
    private var temp=0
    private lateinit var directionPoints:DirectionsRequest
    private lateinit var origin:LatLngData
    private lateinit var destination:LatLngData
    companion object{
        private const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huawei_mapkit_directions)
        mMapView=findViewById(R.id.huaweiMapView)
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle =
                savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        mMapView?.apply {
            onCreate(mapViewBundle)
            getMapAsync(this@HuaweiMapkitDirections)
        }

    }

    override fun onMapReady(p0: HuaweiMap?) {
        hMap=p0!!

        hMap.setOnMapLongClickListener {
            onLongTapListener(it)
        }


    }






    private fun addPolyLines(directionResponse: Response<DirectionResponse>){
        val path=directionResponse.body()?.routes?.get(0)?.paths?.get(0)
        val options = PolylineOptions()
        path?.let {
            options.add(LatLng(it.startLocation.lat, it.startLocation.lng))
            it.steps.forEach {
                it.polyline.forEach {
                    options.add(LatLng(it.lat, it.lng))
                }
            }
            options.add(LatLng(it.endLocation.lat, it.endLocation.lng))
        }
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
            val apiService=HuaweiApiRetrofit().apiServices(this)
            apiService.getDirections(directionPoints,"CgF6e3x9a3QW18Y7YOK0WtOwwf2vZwQqQ16cAOMnuhwSw+saTE3Pj3kVafsTloGNjSb1a5SrmhmkSqAWGpyzGJ2x").enqueue(object : Callback<DirectionResponse>{
                override fun onResponse(call: Call<DirectionResponse>, response: Response<DirectionResponse>) {
                    println(response.body())
                    addPolyLines(response)

                }

                override fun onFailure(call: Call<DirectionResponse>, t: Throwable) {
                    println(t.localizedMessage)
                }


            })


        }
        temp=temp+1


    }











    override fun onStart() {
        super.onStart()
        mMapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView?.onStop()
    }
    override fun onDestroy() {
        super.onDestroy()
        mMapView?.onDestroy()
    }

    override fun onPause() {
        mMapView?.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        mMapView?.onResume()
    }



}


