package com.example.semester_project

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(val context: Context, val isLogin: Boolean) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    private val mTours = ArrayList<Tour>()
    private val tourId = ArrayList<String>()

    fun add(tour: Tour, id: String) {
        mTours.add(tour)
        tourId.add(id)
        notifyDataSetChanged()
    }

    fun clear() {
        mTours.clear()
        tourId.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgView = itemView.findViewById<ImageView>(R.id.tour_image)
        val titleView = itemView.findViewById<TextView>(R.id.tour_title)
        val descriptionView = itemView.findViewById<TextView>(R.id.tour_description)
        val cardView = itemView.findViewById<CardView>(R.id.card_view)
        val authorView = itemView.findViewById<TextView>(R.id.tour_author)
        val checkBox = itemView.findViewById<CheckBox>(R.id.favoriteCheckBox)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.tour_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mTours.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        // TODO: Delete with layout OR change
//        //Use the first Image from frist location as Title Image
//        val location = mTours[position].locations
//        val images = location[0].getStringArrayList(IMAGES)
//        holder.imgView.setImageBitmap(BitmapFactory.decodeFile(images?.get(0)))
//        //

        holder.titleView.text = mTours[position].name
        holder.descriptionView.text = mTours[position].description
        holder.authorView.text = mTours[position].author

        holder.cardView.setOnClickListener {
            val tmpIntent = Intent(context, TourDetailActivity::class.java)
            mTours[position].packageIntent(tmpIntent)
            tmpIntent.putExtra(TOUR_ID, tourId[position])
            context.startActivity(tmpIntent)
        }

        holder.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(!isLogin){
                Toast.makeText(context,"Please login first!", Toast.LENGTH_LONG)
                val tmpIntent = Intent(context, LoginActivity::class.java)
                context.startActivity(tmpIntent)
            }else{
                if(isChecked){
                    FavoriteList.remove(mTours[position].name)
                }else{
                    FavoriteList.add(mTours[position].name, mTours[position])
                }
            }
        }

    }

    companion object {
        private const val IMAGES = "IMAGES"
        private const val TOUR_ID = "TOUR_ID"
    }
}