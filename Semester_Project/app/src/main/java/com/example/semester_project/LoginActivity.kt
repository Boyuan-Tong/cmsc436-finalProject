package com.example.semester_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class LoginActivity: Activity() {
    lateinit var toolBar:Toolbar
    lateinit var loginBut:Button
    lateinit var registerBut:Button
    lateinit var clearBut:Button
    lateinit var userNameView: EditText
    lateinit var passwordView: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addauthor)

        // ToolBar and Menu
        toolBar = findViewById(R.id.toolbar) as Toolbar
        toolBar.setTitle(R.string.app_name)
        toolBar.setSubtitle("User")
        toolBar.inflateMenu(R.menu.switch_activities)
        toolBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.map -> {
                    val intent = Intent(this, MapActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.discovery -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }

        loginBut = findViewById(R.id.loginBtn)
        clearBut = findViewById(R.id.clearBtn)
        registerBut = findViewById(R.id.registerBtn)
        userNameView = findViewById(R.id.userName)
        passwordView = findViewById(R.id.password)

        loginBut.setOnClickListener{v ->
            val password = passwordView.text as String?
            val userName = userNameView.text as String?
            val valid = true

            //TODO: Database
            // Check username and password

            if(!valid){
                Toast.makeText(this, "Invalid username or password!", Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this, UserPageActivity::class.java)

                //TODO: (Database) put necessary data into intent with putExtra()

                startActivity(intent)
            }
        }

        clearBut.setOnClickListener{v ->
            userNameView.setText("")
            passwordView.setText("")
        }

        registerBut.setOnClickListener{v ->
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }
}