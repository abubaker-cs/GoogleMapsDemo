package org.abubaker.googlemapsdemo.misc

class Markers {


    /**
     * Convert Vector to Bitmap
     */
//    private fun fromVectorToBitmap(id: Int, color: Int): BitmapDescriptor {
//
//        // Convert Vector to Bitmap
//        val vectorDrawable: Drawable? = ResourcesCompat.getDrawable(resources, id, null)
//
//        if (vectorDrawable == null) {
//
//            // Log the Error
//            Log.d("MapsActivity", "Resource not found")
//
//            // Return the default marker, because the resource is not found
//            return BitmapDescriptorFactory.defaultMarker()
//
//        }
//
//        // Set the size of the drawable
//        val bitmap = Bitmap.createBitmap(
//            vectorDrawable.intrinsicWidth,
//            vectorDrawable.intrinsicHeight,
//            Bitmap.Config.ARGB_8888
//        )
//
//        // Create canvas
//        val canvas = Canvas(bitmap)
//
//        // Setting the bounds of the canvas
//        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
//
//        // Set the color
//        DrawableCompat.setTint(vectorDrawable, color)
//
//        // Draw the vector drawable on the canvas
//        vectorDrawable.draw(canvas)
//
//        // Return the converted bitmap
//        return BitmapDescriptorFactory.fromBitmap(bitmap)
//
//    }

}
