package com.example.yanotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = ArrayList<String>()
        list.add("title 1")
        list.add("title 1")
        list.add("title 1")
        list.add("title 1")
        list.add("title 1")
        list.add("title 1")
        list.add("title 1")
        list.add("title 1")
        list.add("title 1")
        list.add("title 1")

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter=rvAdapter(this,list)
    }
}