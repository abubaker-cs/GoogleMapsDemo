package org.abubaker.googlemapsdemo

import android.graphics.Color
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.delay

class Shapes {

    // Add a marker at the custom location and move the camera
    val losAngeles = LatLng(34.04692127928215, -118.24748421830992)
    val losAngeles2 = LatLng(34.125037184477044, -118.38286807008632)
    val newYork = LatLng(40.7128, -74.0060)
    val spain = LatLng(39.556230288288084, -3.310744388397795)
    val london = LatLng(51.507621260687635, -0.13113122832629123)
    val sydney = LatLng(-33.8674869, 151.2069902)
    val panama = LatLng(8.9936, -79.5197)

    suspend fun addPolyline(map: GoogleMap) {

        // We are calling Map's addPolyline() object
        val polyline = map.addPolyline(

            // Options
            PolylineOptions().apply {

                // Specifies whether this polyline is clickable. The default setting is false
                clickable(true)

                // Adds vertices to the end of the polyline being built.
                add(losAngeles, newYork, london, spain)

                // Sets the width of the polyline in screen pixels. The default is 10.
                width(5f)

                // Sets the color of the polyline as a 32-bit ARGB color. The default color is black ( 0xff000000)
                color(Color.BLUE)

                // Important: It will force the lines to follow earth's curvature
                // ==============================================================
                // Specifies whether to draw each segment of this polyline as a geodesic.
                // The default setting is false
                geodesic(true)

            }

        )

        // 5 Seconds delay
        delay(5000)

        //
        val newList = listOf(
            panama, losAngeles, spain
        )

        polyline.points = newList

    }

}
