package com.example.meubusao.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meubusao.R

class BusAdapter(private val busList: List<com.example.meubusao.Linha>) : RecyclerView.Adapter<BusAdapter.BusViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bus, parent, false)
        return BusViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusViewHolder, position: Int) {
        val bus = busList[position]
        holder.busNameTextView.text = bus.nome
        holder.busIdTextView.text = bus.id
    }

    override fun getItemCount(): Int = busList.size

    class BusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val busNameTextView: TextView = itemView.findViewById(R.id.bus_name)
        val busIdTextView: TextView = itemView.findViewById(R.id.bus_id)
    }
}
