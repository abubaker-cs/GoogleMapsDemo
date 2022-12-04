package org.abubaker.googlemapsdemo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.abubaker.googlemapsdemo.databinding.ActivityMapsBinding
import org.abubaker.googlemapsdemo.misc.CameraAndViewport
import org.abubaker.googlemapsdemo.misc.TypeAndStyle

class MapsActivity :
    AppCompatActivity(),
    OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    /**
     * References to our external classes in the @misc package
     */
    private val typeAndStyle by lazy { TypeAndStyle() }
    private val cameraAndViewport by lazy { CameraAndViewport() }

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
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     *
     *
     * This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * [.onPrepareOptionsMenu].
     *
     *
     * The default implementation populates the menu with standard system
     * menu items.  These are placed in the [Menu.CATEGORY_SYSTEM] group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     *
     *
     * You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     *
     *
     * When you add items to the menu, you can implement the Activity's
     * [.onOptionsItemSelected] method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     *
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     *
     * @see .onPrepareOptionsMenu
     *
     * @see .onOptionsItemSelected
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // return super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.map_types, menu)
        return true

    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     *
     *
     * Derived classes should call through to the base class for it to
     * perform the default menu handling.
     *
     * @param item The menu item that was selected.
     *
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     *
     * @see .onCreateOptionsMenu
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        typeAndStyle.setMapType(item, map)

        return true

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
        val losAngeles = LatLng(-34.0, 151.0)
        val newYork = LatLng(40.7128, -74.0060)

        // Add Marker
        map.addMarker(
            MarkerOptions()
                // Position:
                .position(losAngeles)

                // Custom Title:
                .title("Marker in Sydney")
        )

        // Move the camera to the location
        // map.moveCamera(CameraUpdateFactory.newLatLng(targetLocation))

        // Move the camera to the location with ZOOM Level
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(losAngeles, 10f))
        // map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.losAngeles))

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


        // Custom Map Style
        typeAndStyle.setMapStyle(map, this)

        // Constraints: Min/Max Zoom Levels
        // map.setMinZoomPreference(15f)
        // map.setMaxZoomPreference(17f)

        // Coroutine
        lifecycleScope.launch {
            /**
             * ZoomIn/Out() - 1 Level
             * ZoomTo() - Specific Level
             * ZoomBy() - It will zoom by certain amount of zoom level
             */

            // It will wait for 5 seconds
            delay(5000L)

            // Then zoom by 3 levels
            // map.moveCamera(CameraUpdateFactory.zoomBy(3f))

            /**
             * 1. newLatLng
             * 2. newLatLngZoom
             * 3. newCameraPosition
             */
            map.moveCamera(CameraUpdateFactory.newLatLng(newYork))

        }
    }
}


