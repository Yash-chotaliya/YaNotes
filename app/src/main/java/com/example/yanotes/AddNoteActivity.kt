package com.example.yanotes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_add_note.*
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var viewModel: NotesViewModel

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        time.text = currentDate

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbarmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save ->{
                savedta()
            }
            R.id.undo->{
                Toast.makeText(this,"undo",Toast.LENGTH_SHORT).show()
            }
            R.id.redo->{
                Toast.makeText(this,"redo",Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun savedta(){
        val x = tTitle.text.toString()
        val y = time.text.toString()
        val z = text.text.toString()

        if(x.isNotEmpty() && y.isNotEmpty() && z.isNotEmpty()){
            val notes = Notes(0,x,y,z)
            viewModel.insert(notes)
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        else{
            Toast.makeText(this,"enter data",Toast.LENGTH_SHORT).show()
        }
    }
}