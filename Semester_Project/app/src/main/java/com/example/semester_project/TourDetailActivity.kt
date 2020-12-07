package com.example.semester_project

import android.app.Activity
import android.content.Intent

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.semester_project.Tour
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.Exception

class TourDetailActivity : Activity() {
    private lateinit var titleView: TextView
    private lateinit var descView: TextView
    private lateinit var authorView: TextView
    private lateinit var listView: ListView
    private lateinit var adapter: LocationListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tour_detail)
        titleView = findViewById(R.id.detail_title)
        descView = findViewById(R.id.detail_description)
        authorView = findViewById(R.id.detail_author)
        titleView.text = intent.getStringExtra(NAME)
        descView.text = intent.getStringExtra(DESCRIPTION)
        authorView.text = intent.getStringExtra(AUTHOR)


        //ListView display list of locations
        listView = findViewById(R.id.locationListView)
        adapter = LocationListAdapter(applicationContext, intent.getStringExtra(TOUR_ID)!!)
        listView.adapter = adapter

        //Show Tour in Map Button
        val showTourBut = findViewById<Button>(R.id.showTourBut)
        showTourBut.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            // TODO
            intent.putExtra("tour", tour)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val tour = FirebaseDatabase.getInstance().getReference("locations")

        tour.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                adapter.clear()

                var tour: Tour? = null
                for (postSnapshot in dataSnapshot.child(adapter.getTourId())
                    .children) {
                    try {
                        tours.add(postSnapshot.key!!)
                        tour = postSnapshot.getValue(Tour::class.java)
                    } catch (e: Exception) {
                        Log.e(TAG, e.toString())
                    } finally {
                        adapter.addLocation(tour!!, postSnapshot.key!!)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }


    companion object {

        private const val TAG = "Semester-Project"
        private const val TOUR_ID = "TOUR_ID"
        private const val AUTHOR = "AUTHOR"
        private const val DESCRIPTION = "DESCRIPTION"
        private const val NAME = "NAME"
    }

}