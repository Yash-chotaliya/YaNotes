package com.example.yanotes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes_Table")
data class Notes(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "time") val time: String,
    @ColumnInfo(name = "description") val description: String
)
