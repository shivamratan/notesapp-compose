package com.ratanapps.notesapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ratanapps.notesapp.data.local.dao.NotesDao
import com.ratanapps.notesapp.data.local.entity.NotesEntity

/**
 * The Room database for the application.
 *
 * Defines the database configuration and serves as the main access point
 * for the persisted data.
 */
@Database(entities = [NotesEntity::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    /**
     * Provides access to the Notes Data Access Object (DAO).
     *
     * @return The [NotesDao] instance.
     */
    abstract fun getNotesDao(): NotesDao

}