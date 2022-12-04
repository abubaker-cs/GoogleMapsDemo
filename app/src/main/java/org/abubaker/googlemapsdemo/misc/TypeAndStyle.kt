package org.abubaker.googlemapsdemo.misc

import android.content.Context
import android.util.Log
import android.view.MenuItem
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MapStyleOptions
import org.abubaker.googlemapsdemo.R

class TypeAndStyle {

    /**
     * Load style.json file from assets folder @res/raw/style.json
     */
    fun setMapStyle(map: GoogleMap, context: Context) {

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            // Success
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    context, R.raw.style
                )
            )

            // Failed to load map style
            if (!success) {
                Log.e("Maps", "Failed to load Style.")
            }

        } catch (e: Exception) {
            Log.e("Maps", e.toString())
        }
    }

    /**
     * Change the map type based on the user's selection.
     */
    fun setMapType(item: MenuItem, map: GoogleMap) {
        when (item.itemId) {

            R.id.normal_map -> {
                map.mapType = GoogleMap.MAP_TYPE_NORMAL
            }

            R.id.hybrid_map -> {
                map.mapType = GoogleMap.MAP_TYPE_HYBRID
            }

            R.id.satellite_map -> {
                map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            }

            R.id.terrain_map -> {
                map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            }

            R.id.none_map -> {
                map.mapType = GoogleMap.MAP_TYPE_NONE
            }
        }
    }

}
