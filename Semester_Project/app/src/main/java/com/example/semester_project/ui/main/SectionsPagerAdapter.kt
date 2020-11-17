package com.example.semester_project.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.semester_project.R
import com.example.semester_project.Tab1Fragment
import com.example.semester_project.Tab2Fragment
import com.example.semester_project.welcomeTab

private val TAB_TITLES = arrayOf(
        R.string.welcome,
        R.string.tab_text_1,
        R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        var frag : Fragment?
        if(position == 0){
            frag = welcomeTab()
        }else if(position == 1){
            frag = Tab1Fragment()
        }else{
            frag = Tab2Fragment()
        }
       // return PlaceholderFragment.newInstance(position + 1)
        return frag
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 3
    }
}