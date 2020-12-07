package com.example.semester_project

import android.content.Intent
import android.os.Bundle

class Location {
    var address: String
    val images = ArrayList<String>() // TODO
    var description: String
    var name: String

    constructor(i: Intent) {
        address = i.getStringExtra(LOCATION_ADDRESS)!!
        val tmpImages = i.getStringArrayListExtra(IMAGES)
        if (tmpImages != null) {
            for (image in tmpImages) {
                images.add(image)
            }
        }
        description = i.getStringExtra(DESCRIPTION)!!
        name = i.getStringExtra(NAME)!!
    }

    constructor(lName:String, address: String, des: String, photos: ArrayList<String> = ArrayList()) {
        this.address = address
        for (photo in photos) {
            images.add(photo)
        }
        description = des
        name = lName
    }

    override fun toString(): String {
        var result = address + ITEM_SEP + NAME + ITEM_SEP + images.size
        for (image in images) {
            result += ITEM_SEP + image
        }
        result += ITEM_SEP + description
        return result
    }

    fun packageIntent(intent: Intent) {
        intent.putExtra(NAME, name)
        intent.putExtra(LOCATION_ADDRESS, address)
        intent.putExtra(IMAGES, images)
        intent.putExtra(DESCRIPTION, description)
    }


    fun packageBundle(): Bundle{
        val bundle = Bundle()
        bundle.putString(LOCATION_ADDRESS, address)
        bundle.putString(NAME, name)
        bundle.putString(DESCRIPTION, description)
        bundle.putStringArrayList(IMAGES, images)
        return bundle
    }

    companion object {
        private val ITEM_SEP = System.getProperty("line.separator")
        private const val LOCATION_ADDRESS = "LOCATION_ADDRESS"
        private const val IMAGES = "IMAGES"
        private const val DESCRIPTION = "DESCRIPTION"
        private const val NAME = "NAME"

    }

}
