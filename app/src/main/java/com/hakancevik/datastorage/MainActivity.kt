package com.hakancevik.datastorage

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hakancevik.datastorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sharedPreferencesStore: SharedPreferences
    private var getAgeFromSharedPreferences: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferencesStore =
            this.getSharedPreferences("com.hakancevik.datastorage", Context.MODE_PRIVATE)

        getAgeFromSharedPreferences = sharedPreferencesStore.getInt("age", -1)

        if (getAgeFromSharedPreferences == -1) {
            binding.ageTextView.text = "Your age: "
        } else {
            binding.ageTextView.text = "Your age: $getAgeFromSharedPreferences"
        }


    }


    fun save(view: View) {


        val age = binding.ageEditText.text.toString().toIntOrNull()
        if (age != null) {
            binding.ageTextView.text = "Your age: $age"
            sharedPreferencesStore.edit().putInt("age", age).apply()
        }
    }


    fun delete(view: View) {

        getAgeFromSharedPreferences = sharedPreferencesStore.getInt("age", -1)

        if (getAgeFromSharedPreferences != -1) {
            sharedPreferencesStore.edit().remove("age").apply()
            binding.ageTextView.text = "Your age: "
        }

    }

}