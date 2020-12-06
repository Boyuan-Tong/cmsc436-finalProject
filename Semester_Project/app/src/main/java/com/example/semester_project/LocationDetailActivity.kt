package com.example.semester_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LocationDetailActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_detail)
        val intent = intent
        val locationBundle = intent.getBundleExtra("locationBundle")
        val nameView = findViewById<TextView>(R.id.locationName)
        val addressView = findViewById<TextView>(R.id.locationAddress)
        val descView = findViewById<TextView>(R.id.locationDesc)
        nameView.text = locationBundle!!.getString(Location.NAME)
        addressView.text = locationBundle!!.getString(Location.LOCATION_ADDRESS)
        descView.text = locationBundle!!.getString(Location.DESCRIPTION)
        val images = locationBundle.getStringArrayList(Location.IMAGES)

        //Set RecycleView for images
        val imageRecycleView = findViewById<RecyclerView>(R.id.locationImageRecycleView)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        imageRecycleView.layoutManager = layoutManager
        imageRecycleView.adapter = LocationImageAdapter(images!!,this)

        //Set show location on map button
        val showBut = findViewById<Button>(R.id.showLocationBut)
        showBut.setOnClickListener{v ->
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("LocationBundle", locationBundle)
            startActivity(intent)
        }
    }
}