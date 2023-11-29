package com.comp304_group4_lab5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val EXTRA_LANDMARK_TYPE = "com.comp304_group4_lab5.LANDMARK_TYPE"
const val EXTRA_LANDMARK = "com.comp304_group4_lab5.LANDMARK"

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView;
    lateinit var landmark_types: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        landmark_types = resources.getStringArray(R.array.types)

        recyclerView.adapter = LandTypesAdapter(this,landmark_types)
    }

    private fun onItemClick(item: String) {
        val intent = Intent(this, landmarks::class.java)
        intent.putExtra(EXTRA_LANDMARK_TYPE, item)
        startActivity(intent)
    }
}