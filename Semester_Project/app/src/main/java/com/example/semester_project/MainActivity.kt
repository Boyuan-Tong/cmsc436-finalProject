package com.example.semester_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener as OnItemTouchListener


class MainActivity : Activity() {
    lateinit var toolBar: Toolbar
    lateinit var recycleView: RecyclerView
    lateinit var myTours: List<Tour>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ToolBar and Menu
        toolBar = findViewById(R.id.toolbar) as Toolbar
        toolBar.setTitle(R.string.app_name)
        toolBar.setSubtitle("Discovery")
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
        recycleView = findViewById(R.id.recyclerView) as RecyclerView
        recycleView.layoutManager = LinearLayoutManager(this)
        myTours = initToursList()
        recycleView.adapter = CardAdapter(myTours, this)
    }


    // TODO: Database part
    //  Put tour information (img, name, description) into Tour object. Then add Tour objects to an Arraylist and return
    fun initToursList():List<Tour> {
        var t = ArrayList<Tour>()
        val imagePath = R.drawable.default_tour_image
        val imageList = ArrayList<String>()
        for(i in 1..3){
          //  imageList.add(imagePath)
        }
        val locationList = ArrayList<Bundle>()
        for(i in 1..5){
            val location = Location("Cool Place","Some where on the moon",imageList, "Cool place where you can find 1 ton of gold")
            locationList.add(location.packageBundle())
        }
        return t
    }






}


