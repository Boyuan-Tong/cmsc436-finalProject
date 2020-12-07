package com.example.semester_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.Exception
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener as OnItemTouchListener


class MainActivity : Activity() {
    lateinit var toolBar: Toolbar
    lateinit var recycleView: RecyclerView
    lateinit var myTours: List<Tour>
    lateinit var mAdapter: CardAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ToolBar and Menu
        toolBar = findViewById(R.id.toolbar)
        toolBar.setTitle(R.string.app_name)
        toolBar.subtitle = "Discovery"
        toolBar.inflateMenu(R.menu.switch_activities)
        toolBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.map -> {
                    val intent = Intent(this, MapActivity::class.java)
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

        // CardView & RecycleView
        recycleView = findViewById(R.id.recyclerView)
        recycleView.layoutManager = LinearLayoutManager(this)
        mAdapter = CardAdapter(this)
        recycleView.adapter = mAdapter
    }

    override fun onStart() {
        super.onStart()

        if (FirebaseDatabase.getInstance().getReference("tours") == null)
            return

        FirebaseDatabase.getInstance().getReference("tours")
            .addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mAdapter.clear()

                var tour: Tour? = null
                for (postSnapshot in dataSnapshot.children) {
                    try {
                        tour = postSnapshot.getValue(Tour::class.java)
                    } catch (e: Exception) {
                        Log.e(TAG, e.toString())
                    } finally {
                        mAdapter.add(tour!!, postSnapshot.key!!)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }

    companion object {
        private const val TAG = "Semester-Project"

    }

}


