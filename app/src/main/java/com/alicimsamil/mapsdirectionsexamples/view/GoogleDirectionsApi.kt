package com.alicimsamil.mapsdirectionsexamples.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alicimsamil.mapsdirectionsexamples.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.alicimsamil.mapsdirectionsexamples.model.GoogleDirectionsApi.ApiRetrofit
import com.alicimsamil.mapsdirectionsexamples.model.GoogleDirectionsApi.DirectionResponses
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GoogleDirectionsApi : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var origin: String
    private lateinit var destination: String
    private var temp=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_directions_api)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener(this)




    }

    private fun drawPolyline(response: Response<DirectionResponses>) {
        val shape = response.body()?.routes?.get(0)?.overviewPolyline?.points
        val polyline = PolylineOptions()
            .addAll(PolyUtil.decode(shape))
            .width(8f)
            .color(Color.RED)
        mMap.addPolyline(polyline)
    }





    override fun onMapLongClick(p0: LatLng?) {
        if(temp==0){
            mMap.addMarker(MarkerOptions().position(p0!!))
            origin=p0!!.latitude.toString() + "," + p0!!.longitude.toString()


        }
        else if (temp==1){
            mMap.addMarker(MarkerOptions().position(p0!!))
            destination=p0!!.latitude.toString() + "," + p0!!.longitude.toString()

            val apiServices = ApiRetrofit().apiServices(this)
            apiServices.getDirection(origin, destination, "Your Api Key")
                .enqueue(object : Callback<DirectionResponses> {
                    override fun onResponse(call: Call<DirectionResponses>, response: Response<DirectionResponses>) {
                        drawPolyline(response)
                    }

                    override fun onFailure(call: Call<DirectionResponses>, t: Throwable) {
                        Log.e("Fail: ",t.localizedMessage)
                    }
                })


        }
        temp=temp+1
    }
}
