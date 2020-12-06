package com.example.semester_project

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.*
import java.text.ParseException

class EditActivity: Activity() {

    private lateinit var mAdapter: LocationListAdapter
    private lateinit var mLocationList: ListView
    private lateinit var mTourName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_tour)

        mAdapter = LocationListAdapter(applicationContext)
        mTourName = findViewById(R.id.editName)

        mLocationList = findViewById(R.id.listView)

        mLocationList.setFooterDividersEnabled(true)

        val footerView = LayoutInflater.from(this).inflate(R.layout.footer_view, null, false)

        mLocationList.addFooterView(footerView)

        footerView.setOnClickListener {
            val tmpIntent = Intent(this, AddLocationActivity::class.java)
            startActivityForResult(tmpIntent, ADD_LOCATION_REQUEST)
        }

        mLocationList.adapter = mAdapter

        mLocationList.setOnItemClickListener { parent, view, position, id ->
            val tmpIntent = Intent(this, AddLocationActivity::class.java)
            mAdapter.getItem(position).packageIntent(tmpIntent)
            tmpIntent.putExtra(NUMBER, position)
            startActivityForResult(tmpIntent, ADJUST_LOCATION_REQUEST)
        }

        val submitButton = findViewById<View>(R.id.submitBut) as Button
        submitButton.setOnClickListener { submitNewTour() }

    }

    private fun submitNewTour() {
        if (mTourName.text.isNullOrEmpty()) {
            Toast.makeText(this, "Please Enter the Tour Name", Toast.LENGTH_SHORT).show()
            return
        }

        if (mAdapter.count > 0) {
            val author = FirebaseAuth.getInstance().currentUser!!.email
            val tourName = mTourName.text.toString()
            val tourReference = FirebaseDatabase.getInstance().getReference("Tours")
            val id = tourReference.push().key
            tourReference.child(id!!).child("author").setValue(author)
            tourReference.child(id).child("tourName").setValue(tourName)
            val imageReference = FirebaseStorage.getInstance().reference

            for (i in 0 until mAdapter.count) {
                //TODO
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        Log.i(TAG, "Entered onActivityResult()")

        if (requestCode == ADD_LOCATION_REQUEST && resultCode == RESULT_OK) {
            var location = data?.let { Location(it) }
            if (location != null) {
                mAdapter.addLocation(location)
            }
        }

        if (requestCode == ADJUST_LOCATION_REQUEST && resultCode == RESULT_OK) {
            var location = data?.let { Location(it) }
            if (location != null) {
                mAdapter.adjust(location, data!!.getIntExtra(NUMBER, -1))
            }
        }

    }


    public override fun onResume() {
        super.onResume()

        if (mAdapter.count == 0)
            loadItems()
    }

    override fun onPause() {
        super.onPause()

        saveItems()

    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        super.onCreateOptionsMenu(menu)
//
//        menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, "Delete all")
//        menu.add(Menu.NONE, MENU_DUMP, Menu.NONE, "Dump to log")
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            MENU_DELETE -> {
//                mAdapter.clear()
//                return true
//            }
//            MENU_DUMP -> {
//                dump()
//                return true
//            }
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }
//
//    fun dump() {
//        for (i in 0 until mAdapter.count) {
//            val data = (mAdapter.getItem(i) as ToDoItem).toLog()
//            Log.i(TAG,
//                "Item " + i + ": " + data.replace(ToDoItem.ITEM_SEP, ","))
//        }
//    }

    private fun loadItems() {
        var reader: BufferedReader? = null
        try {
            val fis = openFileInput(FILE_NAME)
            reader = BufferedReader(InputStreamReader(fis))

            var address: String?
            var images = ArrayList<String>()
            var description: String?
            var num: Int

            do {
                address = reader.readLine()
                if (address == null)
                    break
                num = Integer.parseInt(reader.readLine())
                for (i in 0 until num) {
                    images.add(reader.readLine())
                }
                description = reader.readLine()
                mAdapter.add(Location(address, images, description))

            }
            while (true)

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ParseException) {
            e.printStackTrace()
        } finally {
            if (null != reader) {
                try {
                    reader.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
    }

    private fun saveItems() {
        var writer: PrintWriter? = null
        try {
            val fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
            writer = PrintWriter(BufferedWriter(OutputStreamWriter(fos)))
            for (idx in 0 until mAdapter.count) {
                writer.println(mAdapter.getItem(idx))
            }

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            writer?.close()
        }
    }


    companion object {

        private val ADD_LOCATION_REQUEST = 0
        private val ADJUST_LOCATION_REQUEST = 1
        private val NUMBER = "NUMBER"
        private val FILE_NAME = "AddTourActivityData.txt"
        private val TAG = "Semester-Project"

    }

}