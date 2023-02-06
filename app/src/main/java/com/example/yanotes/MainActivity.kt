package com.example.yanotes

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialogue.*

class MainActivity : AppCompatActivity(), IrvAdapter {

    private lateinit var viewmodel : NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.layoutManager = LinearLayoutManager(this)
        viewmodel = ViewModelProvider(this).get(NotesViewModel::class.java)
        viewmodel.getall.observe(this, Observer { list->
            rv.adapter = rvAdapter(this,list.reversed(),this)

        })

        addnote.setOnClickListener {
            val intent = Intent(this,AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("InflateParams")
    override fun onItemClickeddelete(x: Int) {
        val builder = Dialog(this)
        builder.setContentView(layoutInflater.inflate(R.layout.dialogue,null))
        builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        builder.show()

        builder.no.setOnClickListener {
            builder.dismiss()
        }
        builder.yes.setOnClickListener {
            viewmodel.delete(x)
            builder.dismiss()
        }
    }

    override fun onlayoutclicked(notes: Notes) {
        val intent = Intent(this,shownotes::class.java)
        intent.putExtra("title",notes.title)
        intent.putExtra("time",notes.time)
        intent.putExtra("text",notes.text)
        startActivity(intent)
    }
}