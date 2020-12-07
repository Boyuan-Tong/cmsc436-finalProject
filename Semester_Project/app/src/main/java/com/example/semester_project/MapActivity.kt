package com.example.semester_project


import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener


class MapActivity: AppCompatActivity(), OnMapReadyCallback, PlaceSelectionListener {

    private lateinit var mMap: GoogleMap
    private lateinit var mFused: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private lateinit var toolBar: Toolbar
    private lateinit var locationArray: ArrayList<String>
    private lateinit var namesArray: ArrayList<String>
    private lateinit var descriptionArray: ArrayList<String>


    //TODO: implement Map logic
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mapslayout)

        toolBar = findViewById(R.id.toolbar)
        toolBar.inflateMenu(R.menu.switch_activities)
        toolBar.setOnMenuItemClickListener { item ->

            when (item.itemId) {
                R.id.discovery -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.user -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        mFused = LocationServices.getFusedLocationProviderClient(this)

        // Get the string array list intents from Tour detail activity
        if (intent.getStringArrayListExtra(LOCATIONS) == null)
            return

        locationArray = intent.getStringArrayListExtra(LOCATIONS)!!
        namesArray = intent.getStringArrayListExtra(NAMES)!!
        descriptionArray = intent.getStringArrayListExtra(DESCRIPTIONS)!!


        var position = 0

        for (element in locationArray) {

            if(goToLocationFromAddress(element).longitude == 0.0 && goToLocationFromAddress(element).longitude == 0.0){

                Toast.makeText(this, "Not A Valid Address", Toast.LENGTH_SHORT).show()

            } else {

                addMarker(
                    mMap,
                    goToLocationFromAddress(element),
                    namesArray[position],
                    descriptionArray[position]
                )

            }

            position += 1

        }


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
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        val options = GoogleMapOptions()

        mMap.uiSettings.isZoomControlsEnabled = true
        if (ActivityCompat.checkSelfPermission(
                applicationContext!!,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.uiSettings.isMyLocationButtonEnabled = true
        }
        mMap.uiSettings.isMapToolbarEnabled = true
        mMap.uiSettings.isCompassEnabled = true


        setUpMap()
        var pos = LatLng(38.9881, -76.9426)
        addMarker(googleMap, pos, "Hornbake Plaza", "This is a library")


    }

    override fun onPlaceSelected(p0: com.google.android.libraries.places.api.model.Place) {
        Toast.makeText(applicationContext, "" + p0!!.name + p0!!.latLng, Toast.LENGTH_LONG).show();
    }

    override fun onError(p0: Status) {
        Toast.makeText(applicationContext, "", Toast.LENGTH_LONG).show();
    }

    private fun setUpMap() {

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        mMap.isMyLocationEnabled = true

        mFused.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                //placeMarkerOnMap(currentLatLng)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16f))
            }
        }
    }


    fun addMarker(googleMap: GoogleMap, position: LatLng, title: String, data: String) {

        mMap = googleMap


        val mark = mMap.addMarker(
            MarkerOptions().position(position).snippet(title).title(data).icon(
                bitmapDescriptorFromVector(
                    applicationContext,
                    R.drawable.ic_baseline_emoji_flags_24
                )
            )
        )
        mark.showInfoWindow()
    }


    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor {
        var vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.getIntrinsicWidth(),
            vectorDrawable.getIntrinsicHeight()
        );
        var bitmap = Bitmap.createBitmap(
            vectorDrawable.getIntrinsicWidth(),
            vectorDrawable.getIntrinsicHeight(),
            Bitmap.Config.ARGB_8888
        );
        var canvas = Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    private fun goToLocationFromAddress(strAddress: String?): LatLng {
        //Create coder with Activity context - this
        val coder = Geocoder(this)
        val address: List<Address>?
        val latLng: LatLng
        val emptyLatLng = LatLng(0.0, 0.0)


        //Get latLng from String
        address = coder.getFromLocationName(strAddress, 5)

        //check for null
        if (address != null) {

            //Lets take first possibility from the all possibilities.

            val location = address[0]
             latLng =
                LatLng(
                    location.latitude,
                    location.longitude
                )


            return latLng

        }

        return emptyLatLng

    }





    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val LOCATIONS = "LOCATIONS"
        private const val NAMES = "NAMES"
        private const val DESCRIPTIONS = "DESCRIPTIONSS"

    }


}