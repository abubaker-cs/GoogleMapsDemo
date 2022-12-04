package org.abubaker.googlemapsdemo.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

class CameraAndViewport {

    val losAngeles: CameraPosition = CameraPosition.Builder()

        // Actual Target
        .target(LatLng(-34.0, 151.0))

        // Zoom Level
        .zoom(17f)

        // Bearing
        .bearing(0f)

        // Camera Rotation
        .tilt(45f)

        // Build
        .build()

}
