package com.example.semester_project

import android.app.Activity
import android.content.Intent

import android.os.Bundle
import android.os.Parcelable
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.semester_project.Tour

class TourDetailActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tour_detail)
        val intent = getIntent()
        val tour: Tour = intent.getParcelableExtra<Parcelable>("tour") as Tour
        val titleView = findViewById<TextView>(R.id.detail_title)
        val descView = findViewById<TextView>(R.id.detail_description)
        val authorView = findViewById<TextView>(R.id.detail_author)
        val locations = tour.locations
        titleView.text = tour.name
        descView.text = tour.description
        authorView.text = tour.author


        //ListView display list of locations
        val listView = findViewById<ListView>(R.id.locationListView)
        val adapter = LocationListAdapter(applicationContext)

        //add location bundle to adapter
        for(i in 0 until locations.size){
            adapter.addBundle(locations[i])
        }
        listView.adapter = adapter

        //Show Tour in Map Button
        val showTourBut = findViewById<Button>(R.id.showTourBut)
        showTourBut.setOnClickListener{v ->
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("tour", tour)
            startActivity(intent)
        }
    }
}