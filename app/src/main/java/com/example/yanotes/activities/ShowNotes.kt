package com.example.yanotes.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.yanotes.database.Notes
import com.example.yanotes.database.NotesViewModel
import com.example.yanotes.databinding.ActivityShowNotesBinding
import com.example.yanotes.databinding.DialogueBinding
import java.text.SimpleDateFormat
import java.util.Date

class ShowNotes : AppCompatActivity() {

    private lateinit var viewModel: NotesViewModel
    private lateinit var binding: ActivityShowNotesBinding
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id",0)
        binding.title.setText(intent.getStringExtra("title"))
        binding.time.text = intent.getStringExtra("time")
        binding.description.setText(intent.getStringExtra("description"))

        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        binding.copy.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData:ClipData = ClipData.newPlainText("data",binding.description.text.toString())
            clipboard.setPrimaryClip(clipData)
        }

        binding.delete.setOnClickListener {
            val builder = Dialog(this)
            val dialogueBinding = DialogueBinding.inflate(layoutInflater)
            builder.setContentView(dialogueBinding.root)
            builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            builder.show()

            dialogueBinding.no.setOnClickListener {
                builder.dismiss()
            }
            dialogueBinding.yes.setOnClickListener {
                viewModel.delete(id)
                builder.dismiss()
                startActivity(Intent(this,MainActivity::class.java))
            }
        }

        binding.share.setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_TEXT, binding.description.text)
            startActivity(Intent.createChooser(share, "Share Via"))
        }

        binding.save.setOnClickListener {
            updateDta(id)
        }
    }
    @SuppressLint("SimpleDateFormat")
    private fun updateDta(id:Int){
        val title = binding.title.text.toString()
        val sdf = SimpleDateFormat("MMM dd, hh:mm a")
        val time = sdf.format(Date())
        val description = binding.description.text.toString()

        val notes = Notes(0, title, time, description)
        viewModel.insert(notes)

        viewModel.delete(id)

        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}