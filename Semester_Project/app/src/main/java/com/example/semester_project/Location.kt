package com.example.semester_project

import android.content.Intent

class Location {
    private var locationAddress: String
    private var images = ArrayList<String>()
    private var description: String

    constructor(i: Intent) {
        locationAddress = i.getStringExtra(LOCATION_ADDRESS)!!
        var tmpimages = i.getStringArrayListExtra(IMAGES)
        if (tmpimages != null) {
            for (image in tmpimages) {
                images.add(image)
            }
        }
        description = i.getStringExtra(DESCRIPTION)!!
    }

    constructor(address: String, photos: ArrayList<String>, des: String) {
        locationAddress = address
        for (photo in photos) {
            images.add(photo)
        }
        description = des
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
        intent.putExtra(LOCATION_ADDRESS, locationAddress)
        intent.putExtra(IMAGES, images)
        intent.putExtra(DESCRIPTION, description)
    }

    companion object {
        private val ITEM_SEP = System.getProperty("line.separator")
        private val LOCATION_ADDRESS = "LOCATION_ADDRESS"
        private val IMAGES = "IMAGES"
        private val DESCRIPTION = "DESCRIPTION"

    }

}
