package com.alicimsamil.mapsdirectionsexamples

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.alicimsamil.mapsdirectionsexamples.databinding.ActivityGoogleDirectionsApiBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val googleButton= findViewById <Button> (R.id.googleBtn)
        googleButton.setOnClickListener {
            val intent=Intent(this,GoogleDirectionsApi::class.java)
            startActivity(intent)
            finish()

        }



    }




}