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
import com.example.yanotes.databinding.ActivityAddNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var viewModel: NotesViewModel
    private lateinit var binding: ActivityAddNoteBinding

    @SuppressLint("SimpleDateFormat", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        val sdf = SimpleDateFormat("MMM dd , hh:mm a EEE")
        val currentDate = sdf.format(Date())

        binding.time.text = currentDate

        if(Intent.ACTION_SEND==intent.action){
            val data = intent.getStringExtra(Intent.EXTRA_TEXT)
            Toast.makeText(this,data,Toast.LENGTH_SHORT).show()
            //text.text = data as Editable
        }
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
        val x = binding.tTitle.text.toString()
        val y = binding.time.text.toString()
        val z = binding.text.text.toString()

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