package com.comp304_group4_lab5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class landmarks : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landmarks)

        val type = intent.getStringExtra("type")

        val landmarkID = when (type) {
            "Old Buildings" -> R.array.old_build
            "Museums" -> R.array.museums
            "Stadiums" -> R.array.stadiums
            "Attractions" -> R.array.attractions
            // Add more cases as needed for other types
            else -> R.array.old_build
        }

        val landmarkAddressID = when (type) {
            "Old Buildings" -> R.array.old_buildAddress
            "Museums" -> R.array.museumsAddress
            "Stadiums" -> R.array.stadiumsAddress
            "Attractions" -> R.array.attractionsAddress
            // Add more cases as needed for other types
            else -> R.array.old_buildAddress
        }

        val landmark =resources.getStringArray(landmarkID)
        val address = resources.getStringArray(landmarkAddressID)

        recyclerView = findViewById(R.id.LandmarkrecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = landmarkAdapter(this,landmark,address)

    }
    private fun onItemClick(item: String) {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }
}