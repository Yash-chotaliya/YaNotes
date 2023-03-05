package com.example.yanotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rvlayout.view.*

class rvAdapter(private val context: Context, private var tlist:List<Notes>, private val listener:IrvAdapter):RecyclerView.Adapter<rvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewholder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.rvlayout,parent,false))
        viewholder.delete.setOnClickListener {
            listener.onItemClickeddelete(tlist[viewholder.adapterPosition].id)
        }
        viewholder.rl.setOnClickListener {
            listener.onlayoutclicked(tlist[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = tlist[position].title
        holder.time.text = tlist[position].time
    }

    override fun getItemCount(): Int {
        return tlist.size
    }

    class ViewHolder(itemview:View):RecyclerView.ViewHolder(itemview) {
        val title: TextView = itemview.title
        val time:TextView = itemview.time
        val delete:ImageView=itemview.delete
        val rl : RelativeLayout= itemview.rl
    }
}

interface IrvAdapter{
    fun onItemClickeddelete(x:Int)
    fun onlayoutclicked(notes:Notes)
}
