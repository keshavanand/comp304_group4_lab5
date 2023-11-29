package com.comp304_group4_lab5

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.Locale

class MapActivity : AppCompatActivity() , OnMapReadyCallback{
    private lateinit var mMap: GoogleMap
    private lateinit var landmarkName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val landmark = intent.getStringExtra(EXTRA_LANDMARK)
        landmarkName = landmark.toString()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        zoomToLandmark()
    }
    private fun zoomToLandmark() {
        val coder = Geocoder(this, Locale.getDefault())

        try {
            // use geocoder to get the lat long by name
            val addresses = coder.getFromLocationName("Toronto $landmarkName", 5)

            if (!addresses.isNullOrEmpty()) {

                val bestMatch: Address = addresses[0]

                // create marker and zoom to that location
                val location = LatLng(bestMatch.latitude, bestMatch.longitude)
                mMap.addMarker(MarkerOptions().position(location).title(landmarkName))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))

            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            R.id.default_map -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                true
            }
            R.id.satellite_map -> {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
