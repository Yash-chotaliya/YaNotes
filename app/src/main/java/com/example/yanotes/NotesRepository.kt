package com.example.yanotes

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NotesDao) {

    val getall : LiveData<List<Notes>> = notesDao.getAll()
    suspend fun insert(notes:Notes){
        notesDao.insertt(notes)
    }

    suspend fun delete(notes: Notes){
        notesDao.delete(notes)
    }
}