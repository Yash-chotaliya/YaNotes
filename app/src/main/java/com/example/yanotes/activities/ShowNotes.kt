package com.example.yanotes.activities

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.yanotes.NotesViewModel
import com.example.yanotes.R
import kotlinx.android.synthetic.main.activity_shownotes.*
import kotlinx.android.synthetic.main.dialogue.*

class ShowNotes : AppCompatActivity() {

    private lateinit var viewModel:NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shownotes)
        setSupportActionBar(toolbar)

        val id = intent.getIntExtra("id",0)
        tTitle.text = intent.getStringExtra("title")
        time.text = intent.getStringExtra("time")
        text.text = intent.getStringExtra("text")

        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]

        copy.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData:ClipData = ClipData.newPlainText("data",text.text.toString())
            clipboard.setPrimaryClip(clipData)
            Toast.makeText(this,"copied",Toast.LENGTH_SHORT).show()
        }

        delete.setOnClickListener {
            val builder = Dialog(this)
            builder.setContentView(layoutInflater.inflate(R.layout.dialogue,null))
            builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            builder.show()

            builder.no.setOnClickListener {
                builder.dismiss()
            }
            builder.yes.setOnClickListener {
                viewModel.delete(id)
                builder.dismiss()
                startActivity(Intent(this,MainActivity::class.java))
            }
        }
    }
}