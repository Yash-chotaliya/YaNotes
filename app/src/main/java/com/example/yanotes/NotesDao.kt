package com.example.yanotes

import androidx.room.*

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertt(notes: Notes)

    @Delete
    suspend fun delete(notes: Notes)

    @Query(" SELECT * FROM Notes_Table")
    fun getAll():List<Notes>
}