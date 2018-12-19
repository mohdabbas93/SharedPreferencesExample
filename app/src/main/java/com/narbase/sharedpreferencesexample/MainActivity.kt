package com.narbase.sharedpreferencesexample

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        
        setupButtons(sharedPreferences)
    }

    private fun setupButtons(sharedPreferences: SharedPreferences) {
        val sharedPreferencesEditor = sharedPreferences.edit()

        saveButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val fullName = fullNameEditText.text.toString()

            sharedPreferencesEditor.putString(USER_NAME, username)
            sharedPreferencesEditor.putString(FULL_NAME, fullName)

            sharedPreferencesEditor.apply()

            Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show()
        }

        retrieveButton.setOnClickListener {
            if(sharedPreferences.contains(USER_NAME)) {
                usernameEditText.setText(sharedPreferences.getString(USER_NAME, ""))
            }
            if(sharedPreferences.contains(FULL_NAME)) {
                fullNameEditText.setText(sharedPreferences.getString(FULL_NAME, ""))
            }
        }
    }

    companion object {
        private const val PREFS_NAME = "PREFS_NAME"
        private const val USER_NAME = "USER_NAME"
        private const val FULL_NAME = "FULL_NAME"
    }
}
