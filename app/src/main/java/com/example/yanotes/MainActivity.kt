package com.example.yanotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewmodel : NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.layoutManager = LinearLayoutManager(this)
        viewmodel = ViewModelProvider(this).get(NotesViewModel::class.java)
        viewmodel.getall.observe(this, Observer { list->
            rv.adapter = rvAdapter(this,list.reversed())

        })

        addnote.setOnClickListener {
            val intent = Intent(this,AddNoteActivity::class.java)
            startActivity(intent)
        }
    }
}