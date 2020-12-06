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
    private lateinit var mAddImage2: Button
    private lateinit var mAddImage3: Button
    private lateinit var mImage1: ImageView
    private lateinit var mImage2: ImageView
    private lateinit var mImage3: ImageView
    lateinit var nameView: EditText
    lateinit var addressView: EditText
    lateinit var descView: EditText
    private val images = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_location)

        nameView = findViewById(R.id.editName)
        addressView = findViewById(R.id.editAddress)
        descView = findViewById(R.id.editDesc)

        val cancelButton = findViewById<View>(R.id.cancelLoactionBut) as Button
        cancelButton.setOnClickListener {
            Log.i(TAG, "Entered cancelButton.OnClickListener.onClick()")

            val tmpIntent = Intent()
            setResult(RESULT_CANCELED, tmpIntent)
            finish()
        }

        mAddImage1 = findViewById(R.id.addImageBut1)
        mAddImage2 = findViewById(R.id.addImageBut2)
        mAddImage3 = findViewById(R.id.addImageBut3)

        mAddImage1.setOnClickListener {
            val tmpIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(tmpIntent, IMAGE_REQUEST)
        }
        mAddImage2.setOnClickListener {
            val tmpIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(tmpIntent, IMAGE_REQUEST)
        }
        mAddImage3.setOnClickListener {
            val tmpIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(tmpIntent, IMAGE_REQUEST)
        }



        // TODO: (DATABASE) implement submitButton logic
        val submitButton = findViewById<View>(R.id.submitLoactionBut) as Button
        submitButton.setOnClickListener {
            val name = nameView.text as String
            val address = addressView.text as String
            val description = descView.text as String
            val tmpIntent = Intent()

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
                    //mImage1.text = selectedImage.lastPathSegment
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
