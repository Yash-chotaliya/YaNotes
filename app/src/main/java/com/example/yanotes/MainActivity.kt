package com.example.yanotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

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

    override fun onItemClicked(x: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("are you sure ?")
        builder.setIcon(R.drawable.ic_baseline_delete_24)
        builder.setPositiveButton("Yes"){dialogInterface, which ->
            viewmodel.delete(x)
        }
        builder.setNegativeButton("No"){dialogInterface , which ->
        }
        builder.create().show()
    }
}