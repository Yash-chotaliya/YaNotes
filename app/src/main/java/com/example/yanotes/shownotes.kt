package com.example.yanotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_shownotes.*

class shownotes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shownotes)
        setSupportActionBar(toolbar)

        tTitle.text = intent.getStringExtra("title")
        time.text = intent.getStringExtra("time")
        text.text = intent.getStringExtra("text")
    }
}