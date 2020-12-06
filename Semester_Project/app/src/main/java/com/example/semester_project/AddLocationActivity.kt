package com.example.semester_project

import android.app.*
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.*
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class AddLocationActivity: Activity() {

    private lateinit var mAddImage1: Button
    private lateinit var mImage1: TextView
    private val images = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todo)

        val cancelButton = findViewById<View>(R.id.cancelButton) as Button
        cancelButton.setOnClickListener {
            Log.i(TAG, "Entered cancelButton.OnClickListener.onClick()")

            val tmpIntent = Intent()
            setResult(RESULT_CANCELED, tmpIntent)
            finish()
        }

        mAddImage1 = findViewById(R.id.addImage1)
        mAddImage1.setOnClickListener {
            val tmpIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(tmpIntent, IMAGE_REQUEST)
        }


        val resetButton = findViewById<View>(R.id.resetButton) as Button
        resetButton.setOnClickListener { Log.i(TAG, "Entered resetButton.OnClickListener.onClick()")
            mTitleText!!.text.clear()
            mDefaultStatusButton!!.isChecked = true
            mDefaultPriorityButton!!.isChecked = true

        }


        val submitButton = findViewById<View>(R.id.submitButton) as Button
        submitButton.setOnClickListener {
            Log.i(TAG, "Entered submitButton.OnClickListener.onClick()")

            val title = mTitleText!!.text.toString()
            val date = dateView!!.text.toString() + " " + timeView!!.text.toString()
            val tmpIntent = Intent()
            tmpIntent.putExtra(NUMBER, intent.getIntExtra(NUMBER, -1))
            ToDoItem.packageIntent(tmpIntent, title, priority, status, date)
            setResult(RESULT_OK, tmpIntent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        Log.i(TAG, "Entered onActivityResult()")

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK) {
            val selectedImage = data?.let { it.data }
            if (selectedImage != null) {
                try {
                    images.add(selectedImage.path!!)
                    mImage1.text = selectedImage.lastPathSegment
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

    }


    companion object {

        private val LOCATION_ADDRESS = "LOCATION_ADDRESS"
        private val IMAGES = "IMAGES"
        private val DESCRIPTION = "DESCRIPTION"
        private val NUMBER = "NUMBER"
        private val IMAGE_REQUEST = 0
        private val TAG = "Semester-Project"

        // IDs for menu items
        private val MENU_DELETE = Menu.FIRST
        private val MENU_DUMP = Menu.FIRST + 1
    }
}
