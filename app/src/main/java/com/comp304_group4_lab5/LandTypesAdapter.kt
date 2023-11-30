package com.comp304_group4_lab5

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LandTypesAdapter(private val context: Context,private val landmarkTypes: Array<String>): RecyclerView.Adapter<LandTypesAdapter.LandTypesViewHolder>() {

    class LandTypesViewHolder(itemVew : View) : RecyclerView.ViewHolder(itemVew){
        var type: TextView = itemVew.findViewById(R.id.type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandTypesAdapter.LandTypesViewHolder {
        val itemVew = LayoutInflater.from(parent.context).inflate(R.layout.row_item,parent,false)
        return  LandTypesViewHolder(itemVew)
    }

    override fun onBindViewHolder(holder: LandTypesAdapter.LandTypesViewHolder, position: Int) {
        val currentItem = landmarkTypes[position]
        holder.type.text = currentItem

        holder.type.setOnClickListener{
            val intent = Intent(context,landmarks::class.java)
            intent.putExtra("type",currentItem)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return landmarkTypes.size
    }
}