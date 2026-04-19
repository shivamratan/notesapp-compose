package com.ratanapps.notesapp.data.repo

import com.ratanapps.notesapp.data.local.dao.NotesDao
import com.ratanapps.notesapp.data.local.entity.NotesEntity
import com.ratanapps.notesapp.utils.NotesUtil
import jakarta.inject.Inject

class NotesRepository @Inject constructor(private val notesDao: NotesDao) {

    suspend fun getAllNotes() = notesDao.getAllNotes()

    suspend fun addNotesToDb(title: String, content: String) {
        notesDao.apply {
            insertNote(
                NotesEntity(
                    notesTitle = title,
                    notesContent =  content,
                    timeStamp = NotesUtil.getCurrentTimeStamp()
                )
            )
        }
    }

    suspend fun getNoteById(id: Int) = notesDao.getNoteById(id)

    suspend fun modifyNotesToDb(noteId: Int, title: String, content: String) {
        notesDao.apply {
            updateNote(
                NotesEntity(
                    id = noteId,
                    notesTitle = title,
                    notesContent =  content,
                    timeStamp = NotesUtil.getCurrentTimeStamp()
                )
            )
        }
    }

    suspend fun deleteNotedById(id: Int) {
        notesDao.deleteNoteById(id)
    }
}