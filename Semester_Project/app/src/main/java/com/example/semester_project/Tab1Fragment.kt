package com.example.semester_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import kotlinx.android.synthetic.main.mapslayout.*

class Tab1Fragment : Fragment(), OnMapReadyCallback {



    companion object {
        fun newInstance() = Tab1Fragment()
    }

    private lateinit var viewModel: Tab1ViewModel
    private lateinit var mMap: GoogleMap
    private var mapReady = false



    override fun onCreateView(



        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView =  inflater.inflate(R.layout.mapslayout, container, false)


        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {


        super.onActivityCreated(savedInstanceState)
        val mapFrag = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFrag.getMapAsync(this)



        // TODO: Use the ViewModel
    }

    override fun onMapReady(map: GoogleMap?) {


        if (map != null) {

            // Set the map position
            map.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        29.0,
                        -88.0
                    ), 3.0f
                )
            )

            // Add a marker on Washington, DC, USA
            map.addMarker(
                MarkerOptions().position(
                    LatLng(38.8895, -77.0352)
                ).title(
                    "Hi There"
                )
            )

            // Add a marker on Mexico City, Mexico
            map.addMarker(
                MarkerOptions().position(
                    LatLng(19.13, -99.4)
                ).title(
                    "In Mexico"
                )
            )
        }

    }



    }

