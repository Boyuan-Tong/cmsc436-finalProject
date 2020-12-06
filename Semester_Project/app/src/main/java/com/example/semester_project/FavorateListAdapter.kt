package com.example.semester_project

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class FavorateListAdapter(private val mContext: Context) : BaseAdapter() {

    private val mTour = ArrayList<Tour>()

    fun add(tour: Tour) {
        mTour.add(tour)
        notifyDataSetChanged()
    }

    fun clear() {
        mTour.clear()
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return mTour.size
    }

    override fun getItem(pos: Int): Tour {
        val tour = mTour[pos]
        return tour
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val tour = getItem(position)
        val viewHolder: ViewHolder

        if (null == convertView) {
            viewHolder = ViewHolder()

            // TODO: layout and area
            val newView = LayoutInflater.from(mContext).inflate(R.layout.favorite_tour, parent, false)
            viewHolder.name = newView.findViewById(R.id.tourName)
            viewHolder.author = newView.findViewById(R.id.tourAuthor)
            viewHolder.description = newView.findViewById(R.id.tourDesc)
            viewHolder.mItemLayout = newView.findViewById(R.id.tourRelativeLayout)
            newView.setTag(viewHolder)

        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        viewHolder.position = position
        //var tour = FirebaseDatabase.getInstance().getReference("tours")
        //    .child(tourId)
        // TODO - Fill the areas
        viewHolder.name!!.text = tour.name
        viewHolder.author!!.text = tour.author
        viewHolder.description!!.text = tour.description

        return viewHolder.mItemLayout
    }

    fun adjust(tour: Tour, pos: Int) {
        if (pos == -1)
            return
        mTour.removeAt(pos)
        mTour.add(pos, tour)
        notifyDataSetChanged()
    }

    internal class ViewHolder {
        var position: Int = 0
        var name: TextView? = null
        var author: TextView? = null
        var description: TextView? = null
        var mItemLayout: RelativeLayout? = null
    }

}