package com.example.yanotes.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yanotes.*
import com.example.yanotes.database.Notes
import com.example.yanotes.database.NotesViewModel
import com.example.yanotes.databinding.ActivityMainBinding
import com.example.yanotes.databinding.DialogueBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), IrvAdapter {

    private lateinit var notesList : List<Notes>
    private lateinit var viewmodel : NotesViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notesList = arrayListOf()

        binding.noNotesText.visibility = View.GONE
        binding.rv.visibility = View.VISIBLE

        binding.rv.layoutManager = GridLayoutManager(this,2)
        viewmodel = ViewModelProvider(this)[NotesViewModel::class.java]
        viewmodel.getAll.observe(this) { list ->

            notesList = list.reversed()
            if(notesList.isEmpty()){
                binding.noNotesText.visibility = View.VISIBLE
                binding.rv.visibility = View.GONE
            }
            else{
                binding.noNotesText.visibility = View.GONE
                binding.rv.visibility = View.VISIBLE
                binding.rv.adapter = RVAdapter(this, notesList, this)
            }
        }

        binding.addnote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

        binding.searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if(notesList.isNotEmpty()){
                    binding.noNotesText.visibility = View.GONE
                    binding.rv.visibility = View.VISIBLE
                    filterList(newText)
                    return true
                }
                binding.noNotesText.visibility = View.VISIBLE
                binding.rv.visibility = View.GONE
                return false
            }
        })
    }

    private fun filterList(query:String?){
        if(query!=null){
            val filteredList:List<Notes>
            val list = ArrayList<Notes>()
            for(i in notesList){
                if(i.title.lowercase(Locale.ROOT).contains(query)){
                    list.add(i)
                }
            }
            filteredList = list
            if(filteredList.isEmpty()){
                binding.noNotesText.visibility = View.VISIBLE
                binding.rv.visibility = View.GONE
            }
            else{
                binding.noNotesText.visibility = View.GONE
                binding.rv.visibility = View.VISIBLE
                binding.rv.adapter = RVAdapter(this,filteredList,this)
            }
        }
    }
    @SuppressLint("InflateParams")
    override fun onItemClickedDelete(x: Int) {
        val builder = Dialog(this)
        val dialogueBinding = DialogueBinding.inflate(layoutInflater)
        builder.setContentView(dialogueBinding.root)
        builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        builder.show()

        dialogueBinding.no.setOnClickListener {
            builder.dismiss()
        }
        dialogueBinding.yes.setOnClickListener {
            viewmodel.delete(x)
            builder.dismiss()
        }
    }

    override fun onLayoutClicked(notes: Notes) {
        val intent = Intent(this, ShowNotes::class.java)
        intent.putExtra("id",notes.id)
        intent.putExtra("title",notes.title)
        intent.putExtra("time",notes.time)
        intent.putExtra("description",notes.description)
        startActivity(intent)
    }

}