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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.yanotes.Notes
import com.example.yanotes.NotesViewModel
import com.example.yanotes.databinding.ActivityShownotesBinding
import com.example.yanotes.databinding.DialogueBinding

class ShowNotes : AppCompatActivity() {

    private lateinit var viewModel:NotesViewModel
    private lateinit var binding: ActivityShownotesBinding
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShownotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val id = intent.getIntExtra("id",0)
        binding.tTitle.text = intent.getStringExtra("title")
        binding.time.text = intent.getStringExtra("time")
        binding.text.setText(intent.getStringExtra("text"))

        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        binding.copynote.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData:ClipData = ClipData.newPlainText("data",binding.text.text.toString())
            clipboard.setPrimaryClip(clipData)
            Toast.makeText(this,"copied",Toast.LENGTH_SHORT).show()
        }

        binding.deletenote.setOnClickListener {
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

        binding.sharenote.setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.putExtra(Intent.EXTRA_TEXT, binding.text.text)
            startActivity(Intent.createChooser(share, "Share Via"))
        }

        binding.updatenote.setOnClickListener {
            updateDta(id)
        }
    }
    private fun updateDta(id:Int){
        val x = binding.tTitle.text.toString()
        val y = binding.time.text.toString()
        val z = binding.text.text.toString()

        val notes = Notes(0,x,y,z)
        viewModel.insert(notes)

        viewModel.delete(id)

        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}

