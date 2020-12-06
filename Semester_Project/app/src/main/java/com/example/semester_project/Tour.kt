package com.example.semester_project

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.example.semester_project.Location

@Parcelize
data class Tour(val locations: ArrayList<Bundle>, val name:String, val description:String, val author:String) : Parcelable