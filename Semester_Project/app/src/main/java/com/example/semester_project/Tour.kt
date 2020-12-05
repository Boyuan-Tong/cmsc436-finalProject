package com.example.semester_project

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tour(val img: Int, val name:String, val description:String) : Parcelable