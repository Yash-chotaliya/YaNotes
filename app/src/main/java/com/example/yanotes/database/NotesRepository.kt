package com.example.yanotes.database

import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NotesDao) {

    val getAll : LiveData<List<Notes>> = notesDao.getAll()
    suspend fun insert(notes: Notes){
        notesDao.insert(notes)
    }

    suspend fun delete(x:Int){
        notesDao.delete(x)
    }
}