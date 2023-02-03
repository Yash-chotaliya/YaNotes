package com.example.yanotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rvlayout.view.*

class rvAdapter(private val context: Context, private val tlist:List<Notes>,val listener:IrvAdapter):RecyclerView.Adapter<rvAdapter.ViewHolder>() {

    private lateinit var viewModel: NotesViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewholder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.rvlayout,parent,false))
        viewholder.more.setOnClickListener {
            listener.onItemClicked(tlist[viewholder.adapterPosition].id)
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = tlist[position].title
        holder.time.text = tlist[position].time
        holder.text.text = tlist[position].text
    }

    override fun getItemCount(): Int {
        return tlist.size
    }

    class ViewHolder(itemview:View):RecyclerView.ViewHolder(itemview) {
        val title: TextView = itemview.title
        val time:TextView = itemview.time
        val text:TextView = itemview.text
        val more:ImageView=itemview.more
    }
}

interface IrvAdapter{
    fun onItemClicked(x:Int)
}