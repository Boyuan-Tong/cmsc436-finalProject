package com.example.semester_project

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class RegisterActivity:Activity(){
    lateinit var registerBut: Button
    lateinit var clearBut: Button
    lateinit var userNameView: EditText
    lateinit var passwordView: EditText
    lateinit var emailView:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)
        clearBut = findViewById(R.id.clearBtn)
        registerBut = findViewById(R.id.registerBtn)
        userNameView = findViewById(R.id.userName)
        passwordView = findViewById(R.id.password)
        emailView = findViewById(R.id.email)

        registerBut.setOnClickListener{v ->
            val success = false
            //TODO: (Database) Implement registration logic

            if(!success){
                Toast.makeText(this, "Register fail, an error occurs!", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Register successfully!", Toast.LENGTH_LONG).show()
            }
        }

        clearBut.setOnClickListener{v ->
            userNameView.setText("")
            passwordView.setText("")
        }
    }
}