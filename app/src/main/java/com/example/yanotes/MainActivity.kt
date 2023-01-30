package com.example.yanotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = ArrayList<Type>()
        list.add(Type("title 1","10.00"))
        list.add(Type("title 2","11.00"))
        list.add(Type("title 3","1.00"))
        list.add(Type("title 4","09.00"))
        list.add(Type("title 5","01.00"))
        list.add(Type("title 6","22.00"))
        list.add(Type("title 7","18.00"))
        list.add(Type("title 8","7.00"))
        list.add(Type("title 9","9.00"))
        list.add(Type("title 10","9.00"))


        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter=rvAdapter(this,list)
    }
}