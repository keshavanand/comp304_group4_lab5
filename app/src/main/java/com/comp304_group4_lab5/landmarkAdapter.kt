package com.comp304_group4_lab5

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.recyclerview.widget.RecyclerView

class landmarkAdapter(private val context: Context,private val landmarks: Array<String>,private val address: Array<String>)
    : RecyclerView.Adapter<landmarkAdapter.LandmarkViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class LandmarkViewHolder(itemVew : View) : RecyclerView.ViewHolder(itemVew){
        var landmark: TextView = itemVew.findViewById(R.id.landmarkName)
        var address: TextView = itemVew.findViewById(R.id.landmarkAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): landmarkAdapter.LandmarkViewHolder {
        val itemVew = LayoutInflater.from(parent.context).inflate(R.layout.landmark_item,parent,false)
        return  LandmarkViewHolder(itemVew)
    }

    override fun onBindViewHolder(holder: landmarkAdapter.LandmarkViewHolder, position: Int) {
        val currentItem = landmarks[position]
        val currentIemAddress = address[position]
        holder.landmark.text = currentItem
        holder.address.text = currentIemAddress

        holder.landmark.setOnClickListener{
            val intent = Intent(context, MapActivity::class.java)
            intent.putExtra("EXTRA_LANDMARK",currentItem)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return landmarks.size
    }


}