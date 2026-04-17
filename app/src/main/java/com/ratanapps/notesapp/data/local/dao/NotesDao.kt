package com.ratanapps.notesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ratanapps.notesapp.data.local.entity.NotesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): Flow<List<NotesEntity>>

    @Query("SELECT * FROM notes_table WHERE id = :id")
    fun getNoteById(id: Int): Flow<NotesEntity?>

    @Query("DELETE FROM notes_table WHERE id = :id")
    suspend fun deleteNoteById(id: Int)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()

   /* @Query("UPDATE notes_table SET notesTitle= :notesTitle, notesContent = :notesContent, timeStamp = :timeStamp WHERE id = :id")
    suspend fun updateNote(id: Int, notesTitle: String, notesContent: String, timeStamp: String)*/

   /* @Query("INSERT INTO notes_table (notesTitle, notesContent, timeStamp) VALUES (:notesTitle, :notesContent, :timeStamp)")
    suspend fun insertNote(notesTitle: String, notesContent: String, timeStamp: String)*/

    @Update
    suspend fun updateNote(notesEntity: NotesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notesEntity: NotesEntity)

}