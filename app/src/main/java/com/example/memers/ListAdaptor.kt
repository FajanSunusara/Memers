package com.example.memers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListAdaptor(val item:ArrayList<String>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_views,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.itemView.context).load(item).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return item.size
    }

}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleview:TextView = itemView.findViewById(R.id.textView)
    val imageView = itemView.findViewById<ImageView>(R.id.imageView1)
}