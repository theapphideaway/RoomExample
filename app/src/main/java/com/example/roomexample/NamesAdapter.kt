package com.example.roomexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NamesAdapter(private val names: List<Name>): RecyclerView.Adapter<NamesAdapter.NamesViewHolder>() {
    inner class NamesViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        fun setData(name: String){
            val nameTV = view.findViewById<TextView>(R.id.name_tv)
            nameTV.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.names_item, parent, false)
        return NamesViewHolder(inflater)
    }

    override fun getItemCount(): Int = names.count()

    override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
        holder.setData(names[position].name)
    }
}