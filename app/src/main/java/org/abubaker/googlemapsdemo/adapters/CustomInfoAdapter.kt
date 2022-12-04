package org.abubaker.googlemapsdemo.adapters

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.model.Marker
import org.abubaker.googlemapsdemo.R

class CustomInfoAdapter(context: Context) : InfoWindowAdapter {

    private val contentView =
        (context as Activity).layoutInflater.inflate(R.layout.custom_info_window, null)

    override fun getInfoWindow(marker: Marker): View? {
        renderViews(marker, contentView)
        return contentView
    }

    override fun getInfoContents(marker: Marker): View? {
        renderViews(marker, contentView)
        return contentView
    }

    private fun renderViews(marker: Marker?, contentView: View) {

        val titleTextView = contentView.findViewById<TextView>(R.id.tv_title)
        titleTextView.text = marker?.title

        val descriptionTextView = contentView.findViewById<TextView>(R.id.tv_description)
        descriptionTextView.text = marker?.snippet

    }

}
