package com.ratanapps.notesapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val notesTitle: String,
    val notesContent: String,
    val timeStamp: String
)
