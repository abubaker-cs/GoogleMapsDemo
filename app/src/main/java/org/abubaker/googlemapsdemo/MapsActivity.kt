package org.abubaker.googlemapsdemo

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.*
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import org.abubaker.googlemapsdemo.adapters.CustomInfoAdapter
import org.abubaker.googlemapsdemo.databinding.ActivityMapsBinding
import org.abubaker.googlemapsdemo.misc.CameraAndViewport
import org.abubaker.googlemapsdemo.misc.Overlays
import org.abubaker.googlemapsdemo.misc.Shapes
import org.abubaker.googlemapsdemo.misc.TypeAndStyle

class MapsActivity :
    AppCompatActivity(),
    OnMapReadyCallback,
    // OnMarkerClickListener,
    OnMarkerDragListener,
    OnPolylineClickListener,
    OnCircleClickListener {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding


    /**
     * References to our external classes in the @misc package
     */
    private val typeAndStyle by lazy { TypeAndStyle() }
    private val cameraAndViewport by lazy { CameraAndViewport() }
    private val shapes by lazy { Shapes() }
    private val overlays by lazy { Overlays() }

    val REQUEST_CODE = 101

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

        // Add Marker
        val losAngelesMarker = map.addMarker(
            MarkerOptions()
                // Position:
                .position(shapes.losAngeles)

                // Custom Title:
                .title("Marker in Sydney")

                // InfoWindow: Description
                .snippet("This is the snippet")

                // Enable Dragging
                // .draggable(true)

                // Custom Icon - Official Colors
                // .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))

                // Custom Icon - Custom HUE Color - https://www.w3schools.com/colors/colors_hsl.asp
                // .icon(BitmapDescriptorFactory.defaultMarker(134f))

                /**
                 * We cannot use Vector file directly, otherwise the app will crash. Instead we need
                 * to convert the vector to PNG file and then use it.
                 *
                 * 1. fromResource()
                 * 2. fromBitmap()
                 * 3. fromAsset()
                 * 4. fromPath()
                 * 5. fromFile()
                 */

                // .icon(fromVectorToBitmap(R.drawable.ic_android, Color.parseColor("#00D176")))
                // .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_android))

                // Transparency
                .alpha(0.85f)

                // Flat icons will:
                // 1. Retain their size when they are zoomed in or out
                // 2. Always face the camera (fixed direction)
                // .flat(true)

                // Rotation of the icon
                .rotation(90f)


        )

//        val losAngelesMarker2 = map.addMarker(
//            MarkerOptions()
//                // Position:
//                .position(losAngeles2)
//
//                // Custom Title:
//                .title("Marker in LosAngeles")
//
//                // InfoWindow: Description
//                .snippet("This is the snippet")
//
//                // Enable Dragging
//                .draggable(true)
//
//                // Z-Index
//                .zIndex(1f)
//        )

        // Important to initialize
        losAngelesMarker!!.tag = "Restaurant 1"
        // losAngelesMarker2!!.tag = "Restaurant 2"


        // Move the camera to the location
        // map.moveCamera(CameraUpdateFactory.newLatLng(targetLocation))

        // Move the camera to the location with ZOOM Level
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(shapes.losAngeles, 10f))
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
            isMyLocationButtonEnabled = true

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

        // Ground Overlay
        // val groundOverlay = overlays.addGroundOverlayWithTag(map)

        // lifecycleScope.launch {

        // 4 Seconds
        // delay(4000L)

        // Remove Ground Overlay from the map
        // groundOverlay?.remove()

        // Transparency
        // groundOverlay?.transparency = 0.5f

        // Change the image (must be bitmap, not a vector)
        // groundOverlay?.setImage(BitmapDescriptorFactory.fromResource(R.drawable.ic_android))

        // Log.d("MapsActivity", "Ground Overlay Tag: ${groundOverlay?.tag}")

        // }

        // TODO
        // Rectangular Area (Polygon Shape)
        // shapes.addPolygon(map)


        // Circular Area (Circle Shape)
        // shapes.addCircle(map)

        // Coroutine
        // lifecycleScope.launch { }


        // 1.1 Click Listener - Polyline
        // map.setOnPolylineClickListener(this)

        // 1.2 Click Listener - Circle
        // map.setOnCircleClickListener(this)

        // Constraints: Min/Max Zoom Levels
        // map.setMinZoomPreference(15f)
        // map.setMaxZoomPreference(17f)

        // TODO: Enable so the setOnMarkerClickListener() and onMarkerClick() can work
        // map.setOnMarkerClickListener(this)

        // Important! - Dragging
        map.setOnMarkerDragListener(this)

        // Custom InfoWindow
        map.setInfoWindowAdapter(CustomInfoAdapter(this))

        // Coroutine
        // lifecycleScope.launch {
        /**
         * ZoomIn/Out() - 1 Level
         * ZoomTo() - Specific Level
         * ZoomBy() - It will zoom by certain amount of zoom level
         */

        // It will wait for 5 seconds
        // delay(5000L)

        // losAngelesMarker!!.remove()

        // Then zoom by 3 levels
        // map.moveCamera(CameraUpdateFactory.zoomBy(3f))

        /**
         * 1. newLatLng
         * 2. newLatLngZoom
         * 3. newCameraPosition
         * 4. scrollBy
         */

        // moveCamera: 100f to the right side
        // map.moveCamera(CameraUpdateFactory.scrollBy(-200f, 100f))

        // Boundaries
        // map.moveCamera(CameraUpdateFactory.newLatLngZoom(cameraAndViewport.melbourneBounds.center, 10f))

        // 1. Set the Boundary range
        // map.moveCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.melbourneBounds, 100))

        // 2. Restrict user from moving the camera OUTSIDE of our boundaries
        // map.setLatLngBoundsForCameraTarget(cameraAndViewport.melbourneBounds)

        /**
         * Animate the camera to the specified location.
         * 1. animateCamera()
         * 2. stopAnimation()
         */

//            map.animateCamera(
//                CameraUpdateFactory.newCameraPosition(
//                    cameraAndViewport.losAngeles
//                ),
//                2000,
//                object : GoogleMap.CancelableCallback {
//
//                    override fun onCancel() {
//                        Toast.makeText(this@MapsActivity, "Animation Cancelled", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//
//                    override fun onFinish() {
//                        Toast.makeText(this@MapsActivity, "Animation Finished", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//
//                }
//            )

        // map.animateCamera(CameraUpdateFactory.newLatLngZoom(losAngeles, 15f), 2000, null)
        // map.animateCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.melbourneBounds, 100), 2000, null)

        // }

        // Coroutine
        // lifecycleScope.launch {
        // shapes.addPolyline(map)
        // }

//        onMapClicked()
//        onMapLongClicked()

        checkLocationPermission()

    }

    // override fun onMarkerClick(marker: Marker): Boolean {

    // map.animateCamera(CameraUpdateFactory.zoomTo(17f), 2000, null)

    // This is the workaround, to show the InfoWindow while returning true
    // marker.showInfoWindow()

    // Hide the info window
    // marker.hideInfoWindow()

    // return true = it will disable the default behavior of showing the info window
    // return false = it will show the default info window
    // return false


//        if (marker != null) {
//            Log.d("Marker", marker.tag as String)
//        } else {
//            Log.d("Marker", "Marker is null")
//        }

    // }

    /**
     * Methods for allowing the user to Drag the Marker on map
     */

    // Start
    override fun onMarkerDragStart(marker: Marker) {
        Log.d("Drag", "onMarkerDragStart")
    }

    // Drag: When the user starts dragging the marker
    override fun onMarkerDrag(marker: Marker) {
        Log.d("Drag", "onMarkerDrag")
    }

    // End
    override fun onMarkerDragEnd(marker: Marker) {
        Log.d("Drag", "onMarkerDragEnd")
    }

    /**
     *
     */
//    private fun onMapClicked() {
//        map.setOnMapClickListener {
//            Toast.makeText(this, "Single Click", Toast.LENGTH_SHORT).show()
//        }
//    }

    /**
     *
     */
//    private fun onMapLongClicked() {
//        map.setOnMapLongClickListener {
//            // Toast.makeText(this, "Long Clicked at: ${it.longitude}, ${it.latitude}", Toast.LENGTH_SHORT).show()
//
//            // Add Marker
//            map.addMarker(
//                MarkerOptions()
//
//                    // Position:
//                    .position(it)
//
//                    // Custom Title:
//                    .title("New Marker")
//            )
//
//        }
//
//    }

    // 1.1 Click Listener - Polyline
    override fun onPolylineClick(p0: Polyline) {
        // Toast.makeText(this, "Polyline Clicked", Toast.LENGTH_SHORT).show()
    }

    // 1.2 Click Listener - Circle
    override fun onCircleClick(p0: Circle) {
        // Toast.makeText(this, "Circle Clicked", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("MissingPermission")
    private fun checkLocationPermission() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            // This will display the MY LOCATION BUTTON on the map
            map.isMyLocationEnabled = true

            Toast.makeText(this, "Already enabled", Toast.LENGTH_SHORT).show()

        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_CODE
        )
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != REQUEST_CODE) {
            return
        }

        when {

            // If the permission was granted by the user
            grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED -> {

                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()

                // This will redirect the user to his current location
                map.isMyLocationEnabled = true

            }

            else -> {
                Toast.makeText(this, "We need your permission", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
