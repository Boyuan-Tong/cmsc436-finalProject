package com.example.semester_project

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.ArrayList

class LocationListAdapter(private val mContext: Context, tourId: String) : BaseAdapter() {

    private val mTour = ArrayList<Bundle>()
    private val tourId = tourId
    private var locationIds = ArrayList<String>()

    fun addBundle(bundle:Bundle){
        mTour.add(bundle)
        notifyDataSetChanged()
    }

    fun addLocation(location: Location, id: String) {
        mTour.add(location.packageBundle())
        locationIds.add(id)
        notifyDataSetChanged()
    }

    fun clear() {
        mTour.clear()
        notifyDataSetChanged()
    }

    fun getTourId(): String {
        return tourId
    }

    fun getLocationId(pos: Int): String {
        return locationIds[pos]
    }

    override fun getCount(): Int {
        return mTour.size
    }

    override fun getItem(pos: Int): Location {
        val bundle = mTour[pos]
        return Location(bundle.getString(NAME)!!, bundle.getString(LOCATION_ADDRESS)!!,
            bundle.getString(DESCRIPTION)!!, bundle.getStringArrayList(IMAGES)!!)
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val location = getItem(position)
        val viewHolder: ViewHolder

        if (null == convertView) {
            viewHolder = ViewHolder()

            val newView = LayoutInflater.from(mContext).inflate(R.layout.location_card, parent, false)
            viewHolder.name = newView.findViewById(R.id.locationName)
            viewHolder.address = newView.findViewById(R.id.locationAddress)
            viewHolder.description = newView.findViewById(R.id.locationDesc)
            viewHolder.mItemLayout = newView.findViewById(R.id.locationRelativeLayout)
            newView.setTag(viewHolder)

        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        viewHolder.position = position
        viewHolder.name!!.text = location.name
        viewHolder.address!!.text = location.address
        viewHolder.description!!.text = location.description

        return viewHolder.mItemLayout
    }

    fun adjust(location: Location, pos: Int) {
        if (pos == -1)
            return
        mTour.removeAt(pos)
        mTour.add(pos, location.packageBundle())
        notifyDataSetChanged()
    }

    internal class ViewHolder {
        var position: Int = 0
        var name: TextView? = null
        var address: TextView? = null
        var description: TextView? = null
        var mItemLayout: RelativeLayout? = null
    }

    companion object {
        private const val LOCATION_ADDRESS = "LOCATION_ADDRESS"
        private const val IMAGES = "IMAGES"
        private const val DESCRIPTION = "DESCRIPTION"
        private const val NAME = "NAME"

    }

}