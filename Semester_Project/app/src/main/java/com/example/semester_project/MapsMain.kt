package com.example.semester_project

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class MapsMain: AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.mapslayout)
    }

    override fun onMapReady(p0: GoogleMap?) {
        TODO("Not yet implemented")
    }
}