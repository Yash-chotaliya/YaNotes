package com.example.yanotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yanotes.database.Notes
import com.example.yanotes.databinding.RvLayBinding

class RVAdapter(private val context: Context, private var list:List<Notes>, private val listener:IrvAdapter):RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewHolder(RvLayBinding.inflate(LayoutInflater.from(context),parent,false))
        binding.delete.setOnClickListener {
            listener.onItemClickedDelete(list[binding.adapterPosition].id)
        }
        binding.rl.setOnClickListener {
            listener.onLayoutClicked(list[binding.adapterPosition])
        }
        return binding
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.time.text = list[position].time
        if(list[position].description.isNotEmpty()){
            holder.description.visibility = View.VISIBLE
            holder.description.text = list[position].description
        }
        else{
            holder.description.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(rvLayBinding: RvLayBinding):RecyclerView.ViewHolder(rvLayBinding.root) {
        val title: TextView = rvLayBinding.title
        val time:TextView = rvLayBinding.time
        val description = rvLayBinding.description
        val delete:ImageView = rvLayBinding.delete
        val rl : RelativeLayout= rvLayBinding.rl
    }
}

interface IrvAdapter{
    fun onItemClickedDelete(x:Int)
    fun onLayoutClicked(notes: Notes)
}
