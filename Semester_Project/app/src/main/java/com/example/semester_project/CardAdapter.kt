package com.example.semester_project

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class CardAdapter(tours: List<Tour>, val context: Context) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    private val myTours = tours

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgView = itemView.findViewById<ImageView>(R.id.tour_image)
        val titleView = itemView.findViewById<TextView>(R.id.tour_title)
        val descriptionView = itemView.findViewById<TextView>(R.id.tour_description)
        val cardView = itemView.findViewById<CardView>(R.id.card_view)

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
        holder.imgView.setImageResource(myTours[position].img)
        holder.titleView.text = myTours[position].name
        holder.descriptionView.text = myTours[position].description

        holder.cardView.setOnClickListener { v ->
            val intent = Intent(context, TourDetailActivity::class.java)

            intent.putExtra("tour", myTours[position] as Parcelable)
            context.startActivity(intent)
        }

    }
}