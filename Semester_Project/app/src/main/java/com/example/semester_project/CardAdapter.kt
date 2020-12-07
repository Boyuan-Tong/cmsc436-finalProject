package com.example.semester_project

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.semester_project.Location
import java.io.File

class CardAdapter(tours: List<Tour>, val context: Context) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    private val myTours = tours

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgView = itemView.findViewById<ImageView>(R.id.tour_image)
        val titleView = itemView.findViewById<TextView>(R.id.tour_title)
        val descriptionView = itemView.findViewById<TextView>(R.id.tour_description)
        val cardView = itemView.findViewById<CardView>(R.id.card_view)
        val authorView = itemView.findViewById<TextView>(R.id.tour_author)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.tour_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myTours.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = myTours[position].locations

        // TODO
        //Use the first Image from frist location as Title Image
        val images = location[0].getStringArrayList(IMAGES)
        holder.imgView.setImageBitmap(BitmapFactory.decodeFile(images?.get(0)))

        holder.titleView.text = myTours[position].name
        holder.descriptionView.text = myTours[position].description
        holder.authorView.text = myTours[position].author

        holder.cardView.setOnClickListener { v ->
            val intent = Intent(context, TourDetailActivity::class.java)
            intent.putExtra(TOUR_ID, myTours[position] as Parcelable)
            context.startActivity(intent)
        }

    }

    companion object {
        private const val IMAGES = "IMAGES"
        private const val TOUR_ID = "TOUR_ID"
    }
}