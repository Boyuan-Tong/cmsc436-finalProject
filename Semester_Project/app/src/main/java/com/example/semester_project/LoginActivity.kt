package com.example.semester_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.footer_view.view.*

class LoginActivity: Activity() {
    lateinit var toolBar:Toolbar
    lateinit var loginBut:Button
    lateinit var registerBut:Button
    lateinit var clearBut:Button
    lateinit var userNameView: EditText
    lateinit var passwordView: EditText

    private lateinit var mAuth: FirebaseAuth

    private lateinit var mAdapter: FavorateListAdapter
    private lateinit var mName: TextView
    private lateinit var mFavorateList: ListView
    private lateinit var user: DatabaseReference
    private lateinit var addTour: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        if (mAuth.currentUser == null) {
            setContentView(R.layout.login_page)

            setToolbar()

            loginBut = findViewById(R.id.loginBtn)
            clearBut = findViewById(R.id.clearBtn)
            registerBut = findViewById(R.id.registerBtn)
            userNameView = findViewById(R.id.userName)
            passwordView = findViewById(R.id.password)

            loginBut.setOnClickListener{ loginUserAccount() }

            clearBut.setOnClickListener{v ->
                userNameView.setText("")
                passwordView.setText("")
            }

            registerBut.setOnClickListener{
                val intent = Intent(this, RegisterActivity::class.java)
                startActivityForResult(intent, REGISTER_REQUEST)
            }
        } else {
            setContentView(R.layout.user_page)
            addTour = findViewById(R.id.editBut)
            addTour.setOnClickListener {
                val tmpIntent = Intent(this, EditActivity::class.java)
                startActivity(tmpIntent)
            }

            mAdapter = FavorateListAdapter(applicationContext)
            mName = findViewById(R.id.userName)
            mFavorateList = findViewById(R.id.favorateListView)
            mFavorateList.adapter = mAdapter


            mFavorateList.onItemClickListener = AdapterView.OnItemClickListener{ adapterView, view, i, l ->
                val tour = mAdapter.getItem(i)
                val tmpIntent = Intent(this, TourDetailActivity::class.java)
                tmpIntent.putExtra(TOUR_ID, tour)
                startActivity(tmpIntent)
            }
        }


    }

    private fun setToolbar() {
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REGISTER_REQUEST && resultCode == RESULT_OK) {
            recreate()
        }
    }

    private fun loginUserAccount() {

        val email: String = userNameView.text.toString()
        val password: String = passwordView.text.toString()
        var validator = Validators()

        if (!validator.validEmail(email)) {
            Toast.makeText(applicationContext, "Please enter a valid email...",
                Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Login successful!", Toast.LENGTH_LONG)
                        .show()
                    recreate()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Login failed! Please try again later",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    companion object {

        private val REGISTER_REQUEST = 0
        private val TOUR_ID = "TOUR_ID"

    }
}