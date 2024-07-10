package com.example.yanotes.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: Notes)

    @Query("delete from Notes_Table where id=:x")
    suspend fun delete(x:Int)

    @Query(" SELECT * FROM Notes_Table")
    fun getAll():LiveData<List<Notes>>
}