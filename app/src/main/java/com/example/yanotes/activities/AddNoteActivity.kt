package com.example.yanotes.activities

import com.example.yanotes.database.NotesViewModel

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.yanotes.database.Notes
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

        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        val sdf = SimpleDateFormat("MMM dd, hh:mm a")
        val currentDate = sdf.format(Date())

        binding.time.text = currentDate

        binding.back.setOnClickListener {
            onBackPressed()
        }

        binding.copy.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData: ClipData = ClipData.newPlainText("data",binding.description.text.toString())
            clipboard.setPrimaryClip(clipData)
        }

        binding.share.setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_TEXT, binding.description.text)
            startActivity(Intent.createChooser(share, "Share Via"))
        }

        binding.save.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote(){
        val title = binding.title.text.toString()
        val time = binding.time.text.toString()
        val description = binding.description.text.toString()

        if(title.isEmpty()){
            Toast.makeText(this,"Title is mandatory",Toast.LENGTH_SHORT).show()
        }
        else {
            val notes = Notes(0,title, time, description)
            viewModel.insert(notes)
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}