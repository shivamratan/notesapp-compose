package com.ratanapps.notesapp.ui.notes.viewmodel

import androidx.lifecycle.ViewModel
import com.ratanapps.notesapp.data.local.dao.NotesDao
import com.ratanapps.notesapp.data.repo.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(notesDao: NotesDao, notesRepository: NotesRepository): ViewModel() {



}