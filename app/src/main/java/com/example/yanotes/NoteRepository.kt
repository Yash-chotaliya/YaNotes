package com.example.yanotes

import androidx.lifecycle.LiveData

class NoteRepository(private val notedao:NotesDao) {
    val allNotes : LiveData<List<Notes>> = notedao.getAll()

    suspend fun insert(notes: Notes){
        notedao.insert(notes)
    }

    suspend fun delete(notes: Notes){
        notedao.delete(notes)
    }
}