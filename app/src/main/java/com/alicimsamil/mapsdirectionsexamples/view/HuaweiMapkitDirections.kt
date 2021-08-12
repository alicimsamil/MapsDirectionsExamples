package com.alicimsamil.mapsdirectionsexamples.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alicimsamil.mapsdirectionsexamples.R
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.OnMapReadyCallback
import com.huawei.hms.maps.model.MarkerOptions

class HuaweiMapkitDirections : AppCompatActivity(),OnMapReadyCallback {

    private lateinit var hMap:HuaweiMap
    private lateinit var mMapView:MapView
    private var temp=0

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

        if(temp==0){
            hMap.setOnMapLongClickListener {
                hMap.addMarker(MarkerOptions().position(it))
            }
            temp=temp+1
        }
        else if (temp==1){
            hMap.setOnMapLongClickListener {
                hMap.addMarker(MarkerOptions().position(it))
            }
            temp=temp+1
        }




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


