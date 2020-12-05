package com.example.semester_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class UserPageActivity: Activity() {
    lateinit var editBut: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_page)
        editBut.findViewById<Button>(R.id.editBut)
        editBut.setOnClickListener{v ->
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
    }
}