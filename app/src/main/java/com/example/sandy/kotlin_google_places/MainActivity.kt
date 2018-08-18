package com.example.sandy.kotlin_google_places

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.location.places.ui.PlacePicker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lati: Double? = null
    var longi: Double? = null

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lManager = getSystemService(Context.LOCATION_SERVICE)
                as LocationManager
        lManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1000.toLong(), 1.toFloat(), object : LocationListener {
            override fun onLocationChanged(location: Location?) {

                lati = location!!.latitude
                longi = location!!.longitude
                tv_lati.text = lati.toString()
                tv_longi.text = longi.toString()
                lManager.removeUpdates(this) //for stop updating location


            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onProviderEnabled(provider: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onProviderDisabled(provider: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

        //location picker (manually)
        loc_pin.setOnClickListener {

            val builder = PlacePicker.IntentBuilder()
            startActivityForResult(builder.build(this@MainActivity),
                    1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val place = PlacePicker.getPlace(data!!, this)
        lati = place.latLng.latitude
        longi = place.latLng.longitude
        tv_lati.text = lati.toString()
        tv_longi.text = longi.toString()

    }
}
