package com.alicimsamil.mapsdirectionsexamples.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alicimsamil.mapsdirectionsexamples.R
import com.alicimsamil.mapsdirectionsexamples.model.google.DirectionResponses
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.alicimsamil.mapsdirectionsexamples.viewmodel.GoogleViewModel
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher


class GoogleDirectionsApi : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var origin: String
    private lateinit var destination: String
    private var temp=0
    private lateinit var viewModel:GoogleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_directions_api)
        viewModel=ViewModelProvider(this).get(GoogleViewModel::class.java)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener(this)

    }




    private fun drawPolyline(response: DirectionResponses) {
        val shape = response.routes.get(0).overview_polyline.points
        val polyline = PolylineOptions()
            .addAll(PolyUtil.decode(shape))
            .width(8f)
            .color(Color.RED)
        mMap.addPolyline(polyline)
    }





    override fun onMapLongClick(p0: LatLng?) {
        if(temp==0){
            mMap.addMarker(MarkerOptions().position(p0!!))
            origin=p0.latitude.toString() + "," + p0.longitude.toString()

        }
        else if (temp==1){
            mMap.addMarker(MarkerOptions().position(p0!!))
            destination=p0.latitude.toString() + "," + p0.longitude.toString()

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.getData(this@GoogleDirectionsApi,origin,destination,getString(R.string.google_maps_key))
            }

            viewModel.points.observe(this, Observer {
                drawPolyline(it)
            })




        }
        temp=temp+1
    }

}


