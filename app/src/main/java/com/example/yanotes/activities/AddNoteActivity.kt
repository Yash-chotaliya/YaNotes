package com.example.yanotes.activities

import com.example.yanotes.NotesViewModel

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.yanotes.Notes
import com.example.yanotes.R
import kotlinx.android.synthetic.main.activity_add_note.*
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var viewModel: NotesViewModel

    @SuppressLint("SimpleDateFormat", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        val sdf = SimpleDateFormat("MMM dd , hh:mm a EEE")
        val currentDate = sdf.format(Date())

        time.text = currentDate

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.addbuttonmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save ->{
                savedta()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun savedta(){
        val x = tTitle.text.toString()
        val y = time.text.toString()
        val z = text.text.toString()

        if(x.isEmpty()){
            Toast.makeText(this,"Title is mandatory",Toast.LENGTH_SHORT).show()
        }
        else {
            val notes = Notes(0,x,y,z)
            viewModel.insert(notes)
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}