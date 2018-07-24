package hu.aut.android.mapdemo

import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.util.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var myMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    fun mapTest(view: View) {
        myMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
    }

    override fun onMapReady(googleMap: GoogleMap) {
        myMap = googleMap

        // Add a marker in Sydney and move the camera
        val hungary = LatLng(47.0, 19.0)
        myMap.addMarker(MarkerOptions().position(hungary).title("Marker in Hungary"))
        myMap.moveCamera(CameraUpdateFactory.newLatLng(hungary))

        val markOpts = MarkerOptions()
                .position(LatLng(48.0, 19.0))
                .title("TITLE")
                .snippet("Részletes szöveg")
        val marker = myMap.addMarker(markOpts)
        marker.isDraggable = true


        myMap.isTrafficEnabled = true


        myMap.uiSettings.isRotateGesturesEnabled = true
        myMap.uiSettings.isCompassEnabled = true
        myMap.uiSettings.isZoomControlsEnabled = true


        val polyRect: PolygonOptions = PolygonOptions().add(
                LatLng(44.0, 19.0),
                LatLng(44.0, 26.0),
                LatLng(48.0, 26.0),
                LatLng(46.0, 28.0),
                LatLng(48.0, 19.0))
        val polygon: Polygon = myMap.addPolygon(polyRect)
        polygon.fillColor = Color.argb(50, 0, 255, 0)


        myMap.setOnMapClickListener { latLng ->
            val markerOptions = MarkerOptions().position(latLng).title("Marker").snippet("Long text of the marker")

            val marker = myMap.addMarker(markerOptions)
            marker.isDraggable = true

            animateCamera(latLng)
        }

        myMap.setOnMarkerClickListener { marker ->
            val gc = Geocoder(this@MainActivity,
                    Locale.getDefault())

            var addressList: List<Address>? = null
            try {
                addressList = gc.getFromLocation(marker.position.latitude,
                        marker.position.longitude, 5)

                val sb = StringBuilder()
                sb.append(addressList!![0].getAddressLine(0))


                Toast.makeText(this@MainActivity, sb.toString(), Toast.LENGTH_SHORT).show()
            } catch (e: Throwable) {
                e.printStackTrace()
            }

            //Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
            true
        }


    }

    val rand: Random = Random(System.currentTimeMillis())

    private fun animateCamera(latLng: LatLng) {
        val cameraPosition = CameraPosition.Builder()
                .target(latLng)
                .zoom(rand.nextInt(12).toFloat())
                .bearing(rand.nextInt(90).toFloat())
                .tilt(rand.nextInt(90).toFloat())
                .build()
        myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}