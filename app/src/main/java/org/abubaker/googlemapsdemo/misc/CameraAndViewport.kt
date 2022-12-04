package org.abubaker.googlemapsdemo.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class CameraAndViewport {

    val losAngeles: CameraPosition = CameraPosition.Builder()

        // Actual Target
        .target(LatLng(34.04692127928215, -118.24748421830992))

        // Zoom Level
        .zoom(17f)

        // Bearing
        .bearing(0f)

        // Camera Rotation
        .tilt(45f)

        // Build
        .build()


    // Boundaries
    val melbourneBounds = LatLngBounds(
        LatLng(-37.814107, 144.963280),  // SW bounds
        LatLng(-37.812395, 144.971459)   // NE bounds

    )

}
