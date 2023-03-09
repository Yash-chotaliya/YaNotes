package com.example.yanotes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yanotes.databinding.RvlayoutBinding

class rvAdapter(private val context: Context, private var tlist:List<Notes>, private val listener:IrvAdapter):RecyclerView.Adapter<rvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewHolder(RvlayoutBinding.inflate(LayoutInflater.from(context),parent,false))
        binding.delete.setOnClickListener {
            listener.onItemClickeddelete(tlist[binding.adapterPosition].id)
        }
        binding.rl.setOnClickListener {
            listener.onlayoutclicked(tlist[binding.adapterPosition])
        }
        return binding
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = tlist[position].title
        holder.time.text = tlist[position].time
    }

    override fun getItemCount(): Int {
        return tlist.size
    }

    class ViewHolder(itembinding: RvlayoutBinding):RecyclerView.ViewHolder(itembinding.root) {
        val title: TextView = itembinding.title
        val time:TextView = itembinding.time
        val delete:ImageView = itembinding.delete
        val rl : RelativeLayout= itembinding.rl
    }
}

interface IrvAdapter{
    fun onItemClickeddelete(x:Int)
    fun onlayoutclicked(notes:Notes)
}
