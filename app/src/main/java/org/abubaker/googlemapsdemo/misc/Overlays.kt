package org.abubaker.googlemapsdemo.misc

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import org.abubaker.googlemapsdemo.R

class Overlays {

    private var losAngeles = LatLng(34.04692127928215, -118.24748421830992)

    private var losAngelesBounds = LatLngBounds(

        // Southwest
        LatLng(34.04192127928215, -118.25248421830992),

        // Northeast
        LatLng(34.05192127928215, -118.24248421830993)

    )

    fun addGroundOverlay(map: GoogleMap): GroundOverlay? {

        return map.addGroundOverlay(

            GroundOverlayOptions().apply {

                // Actual Image
                image(BitmapDescriptorFactory.fromResource(R.drawable.custom_marker))

                // Position: Center in the middle of the SouthWest and NorthEast points
                positionFromBounds(losAngelesBounds)

                // Fixed Position
                // .position(

                // Geolocation: LosAngeles
                // losAngeles,

                // Width | 1000 meters
                // 1000f

                // Height | 1000 meters
                // 10000f

                // )

            }


        )

    }

}
