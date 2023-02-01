package com.example.yanotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rvlayout.view.*

class rvAdapter(private val context: Context, private val tlist:ArrayList<String>):RecyclerView.Adapter<rvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.rvlayout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = tlist[position]
    }

    override fun getItemCount(): Int {
        return tlist.size
    }

    class ViewHolder(itemview:View):RecyclerView.ViewHolder(itemview) {
        val title: TextView = itemview.title
    }
}