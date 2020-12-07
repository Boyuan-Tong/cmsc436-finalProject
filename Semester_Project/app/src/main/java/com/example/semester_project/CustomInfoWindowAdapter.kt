package com.example.semester_project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.semester_project.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker


class CustomInfoWindowAdapter(): GoogleMap.InfoWindowAdapter {

    private lateinit var mWindow: View
    private lateinit var mContext: Context


   constructor(context: Context?) : this() {
       if (context != null) {
           mContext = context
       }

       mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null)

   }


    private fun rendowWindowText(
        marker: Marker,
        view: View?
    ) {
        val title = marker.title
        val tvTitle = view!!.findViewById<View>(R.id.title) as TextView
        if (title != "") {
            tvTitle.text = title
        }
        val snippet = marker.snippet
        val tvSnippet = view.findViewById<View>(R.id.snippet) as TextView
        if (snippet != "") {
            tvSnippet.text = snippet
        }
    }

    override fun getInfoWindow(marker: Marker): View? {
        rendowWindowText(marker, mWindow)
        return mWindow
    }

    override fun getInfoContents(marker: Marker): View? {
        rendowWindowText(marker, mWindow)
        return mWindow
    }



}