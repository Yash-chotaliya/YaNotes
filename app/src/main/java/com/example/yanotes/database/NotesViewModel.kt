package com.example.yanotes.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotesViewModel(application: Application):AndroidViewModel(application) {

    val getAll : LiveData<List<Notes>>
    private val repository : NotesRepository

    init {
        val notesDao = NoteDatabase.getInstance(application).getNoteDao()
        repository = NotesRepository(notesDao)
        getAll = repository.getAll
    }

    fun insert(notes: Notes){
        viewModelScope.launch {
            repository.insert(notes)
        }

    }
    fun delete(x:Int){
        viewModelScope.launch {
            repository.delete(x)
        }
    }
}