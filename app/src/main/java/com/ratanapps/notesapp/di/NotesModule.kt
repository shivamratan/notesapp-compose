package com.ratanapps.notesapp.di

import android.content.Context
import androidx.room.Room
import com.ratanapps.notesapp.data.local.dao.NotesDao
import com.ratanapps.notesapp.data.local.db.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NotesModule {

    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): NotesDatabase {
        return Room.databaseBuilder(context, NotesDatabase::class.java, "notes_database").build();
    }

    @Provides
    fun provideNotesDao(notesDatabase: NotesDatabase): NotesDao {
        return notesDatabase.getNotesDao()
    }

}