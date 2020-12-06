package com.example.semester_project

import android.content.Intent
import android.os.Bundle

class Location {
    var locationAddress: String
    var images = ArrayList<String>()
    var description: String
    var name: String

    constructor(i: Intent) {
        locationAddress = i.getStringExtra(LOCATION_ADDRESS)!!
        var tmpimages = i.getStringArrayListExtra(IMAGES)
        if (tmpimages != null) {
            for (image in tmpimages) {
                images.add(image)
            }
        }
        description = i.getStringExtra(DESCRIPTION)!!
        name = i.getStringExtra(NAME)!!
    }

    constructor(lName:String, address: String, photos: ArrayList<String>, des: String) {
        locationAddress = address
        for (photo in photos) {
            images.add(photo)
        }
        description = des
        name = lName
    }

    override fun toString(): String {
        var result = locationAddress + ITEM_SEP + images.size
        for (image in images) {
            result += ITEM_SEP + image
        }
        result += ITEM_SEP + description
        return result
    }

    fun packageIntent(intent: Intent) {
        intent.putExtra(NAME, name)
        intent.putExtra(LOCATION_ADDRESS, locationAddress)
        intent.putExtra(IMAGES, images)
        intent.putExtra(DESCRIPTION, description)
    }


    fun packageBundle(): Bundle{
        val bundle = Bundle()
        bundle.putString(LOCATION_ADDRESS, locationAddress)
        bundle.putString(NAME, name)
        bundle.putString(DESCRIPTION, description)
        bundle.putStringArrayList(IMAGES, images)
        return bundle
    }

    companion object {
        val ITEM_SEP = System.getProperty("line.separator")
        val LOCATION_ADDRESS = "LOCATION_ADDRESS"
        val IMAGES = "IMAGES"
        val DESCRIPTION = "DESCRIPTION"
        val NAME = "NAME"

    }

}
