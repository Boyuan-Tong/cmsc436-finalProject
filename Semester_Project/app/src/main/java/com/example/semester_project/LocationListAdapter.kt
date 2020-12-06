package com.example.semester_project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList

class LocationListAdapter(private val mContext: Context) : BaseAdapter() {

    private val mTour = ArrayList<Location>()

    fun add(location: Location) {

        mTour.add(location)
        notifyDataSetChanged()

    }

    fun clear() {

        mTour.clear()
        notifyDataSetChanged()

    }

    override fun getCount(): Int {

        return mTour.size

    }

    override fun getItem(pos: Int): Location {

        return mTour[pos]

    }

    override fun getItemId(pos: Int): Long {

        return pos.toLong()

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val tourId = getItem(position)

        val viewHolder: ViewHolder

        if (null == convertView) {

            viewHolder = ViewHolder()

            // TODO: layout and area
            val newView = LayoutInflater.from(mContext).inflate(R.layout.location_card, parent, false)
            viewHolder.mItemLayout = newView.findViewById(R.id.RelativeLayout1)
            viewHolder.mTitleView = newView.findViewById(R.id.titleView)
            viewHolder.mPriorityView = newView.findViewById(R.id.priorityView)
            viewHolder.mStatusView = newView.findViewById(R.id.statusCheckBox)
            viewHolder.mDateView = newView.findViewById(R.id.dateView)
            newView.setTag(viewHolder)

        } else {

            viewHolder = convertView.tag as ViewHolder
            viewHolder.mStatusView!!.setOnCheckedChangeListener(null)

        }



        viewHolder.position = position
        var tour = FirebaseDatabase.getInstance().getReference("tours")
            .child(tourId)
        // TODO - Fill the areas
        viewHolder.mTitleView!!.text = toDoItem.title

        // TODO
        viewHolder.mStatusView!!.isChecked = toDoItem.status == Status.DONE

        // TODO
        viewHolder.mPriorityView!!.text = toDoItem.priority.toString()

        // TODO
        viewHolder.mDateView!!.text =  ToDoItem.FORMAT.format(toDoItem.date)

        return viewHolder.mItemLayout

    }

    fun adjust(location: Location, pos: Int) {
        if (pos == -1)
            return
        mTour.removeAt(pos)
        mTour.add(pos, location)
        notifyDataSetChanged()
    }

    internal class ViewHolder {
        var position: Int = 0
        // TODO - NEED CHANGE
        var mItemLayout: RelativeLayout? = null
        var mTitleView: TextView? = null
        var mStatusView: CheckBox? = null
        var mPriorityView: TextView? = null
        var mDateView: TextView? = null
    }

    companion object {

        private val TAG = "Lab-UserInterface"
    }
}