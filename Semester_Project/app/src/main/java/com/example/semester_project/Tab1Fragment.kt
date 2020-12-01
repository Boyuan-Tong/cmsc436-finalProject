package com.example.semester_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
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
        val mapFrag = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFrag.onResume()
        mapFrag.getMapAsync(this)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {


        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(Tab1ViewModel::class.java)
        activity.let {
            viewModel = ViewModelProviders.of(it!!).get(Tab1ViewModel::class.java)
        }




        // TODO: Use the ViewModel
    }

    override fun onMapReady(p0: GoogleMap?) {



        if (p0 != null) {
            mMap = p0
        }

        // Add a marker in Sydney and move the camera

        // Add a marker in Sydney and move the camera
        val sydney = LatLng((-34).toDouble(), 151.0)


    }

}