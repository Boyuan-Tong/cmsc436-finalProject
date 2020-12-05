package com.example.semester_project

import android.app.Activity

import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import com.example.semester_project.Tour

class TourDetailActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tour_detail)
        val intent = getIntent()
        val tour: Tour = intent.getParcelableExtra<Parcelable>("tour") as Tour
        val imageView = findViewById<ImageView>(R.id.detail_image)
        val titleView = findViewById<TextView>(R.id.detail_title)
        val descView = findViewById<TextView>(R.id.detail_description)
        imageView.setImageResource(tour.img)
        titleView.text = tour.name
        descView.text = tour.description
    }
}