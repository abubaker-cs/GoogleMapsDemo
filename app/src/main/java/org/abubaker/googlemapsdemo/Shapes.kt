package org.abubaker.googlemapsdemo

import android.graphics.Color
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
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

    val alaska = LatLng(61.2181, -149.9003)
    val yellowKnife = LatLng(62.4540, -114.3718)
    val calgary = LatLng(51.0486, -114.0708)
    val ocean = LatLng(51.58618506561299, -149.42485551972004)

    val p1 = LatLng(60.314200754442055, -146.02772676579826)
    val p2 = LatLng(60.96368008562178, -127.73316425180109)
    val p3 = LatLng(55.21794322638971, -131.73218757645867)
    val p4 = LatLng(52.63127495109249, -138.8952732548979)

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

    fun addPolygon(map: GoogleMap) {

        // We are calling Map's addPolygon() object
        val polygon = map.addPolygon(

            // Options
            PolygonOptions().apply {

                add(alaska, yellowKnife, calgary, ocean)

                // Solid Colors
                // fillColor(Color.RED)

                // Transparent Colors | XML
                // fillColor(Color.argb(15, 255, 0, 0))
                fillColor(R.color.black)

                // Solid Stroke
                // strokeColor(Color.BLUE)

                // Transparent Stroke | XML
                fillColor(R.color.black)

                // Hole inside the main polygon
                addHole(listOf(p1, p2, p3, p4))

                // !important = only use it if you want to add a clickListener and there is another
                // polygon on the map above/below this one
                zIndex(1f)

            }

        )

    }

}
