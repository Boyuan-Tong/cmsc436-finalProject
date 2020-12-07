package com.example.semester_project

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class LocationImageAdapter(val context: Context) : RecyclerView.Adapter<LocationImageAdapter.ViewHolder>() {

    private val myImages = ArrayList<Uri>()

    fun add(uri: Uri) {
        myImages.add(uri)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgView = itemView.findViewById<ImageView>(R.id.locationImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.image_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myImages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imagePath = myImages[position]
        holder.imgView.setImageURI(imagePath)
    }
}