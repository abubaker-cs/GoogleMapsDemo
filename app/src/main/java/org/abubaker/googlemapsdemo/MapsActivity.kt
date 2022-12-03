package org.abubaker.googlemapsdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.abubaker.googlemapsdemo.databinding.ActivityMapsBinding

class MapsActivity :
    AppCompatActivity(),
    OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        //
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        // Assigning the map
        map = googleMap

        // Add a marker at the custom location and move the camera
        val targetLocation = LatLng(-34.0, 151.0)

        // Add Marker
        map.addMarker(
            MarkerOptions()
                // Position:
                .position(targetLocation)

                // Custom Title:
                .title("Marker in Sydney")
        )

        // Move the camera to the location
        // map.moveCamera(CameraUpdateFactory.newLatLng(targetLocation))

        // Move the camera to the location with ZOOM Level
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(targetLocation, 15f))

        map.uiSettings.apply {
            // Enables: Zoom +/- buttons on the map
            isZoomControlsEnabled = true

            // Static Image View
            // ==============================================
            // 1. Fixed Position: Disables camera movement
            // 2. Disabled Zoom functionality
            // isScrollGesturesEnabled = false
            // isZoomGesturesEnabled = false

            // My Location
            // * Requires enabling my-location layer
            // ==============================================
            // isMyLocationButtonEnabled = true

            // onPress Toolbar
            // ==============================================
            // 1. Go to Google Maps
            // 2. Find Direction
            isMapToolbarEnabled = true

            // on Rotation
            isCompassEnabled = true

        }

        // Padding: L T R B
        map.setPadding(0, 0, 0, 0)

    }
}
