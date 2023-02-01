package com.example.yanotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application):AndroidViewModel(application) {

    val getall : LiveData<List<Notes>>
    private val repository : NotesRepository

    init {
        val notesDao = NoteDatabase.getInstance(application).getNoteDao()
        repository = NotesRepository(notesDao)
        getall = repository.getall
    }

    fun insert(notes:Notes){
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(notes)
        }
    }
}