package com.ratanapps.notesapp.ui.notes.features.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ratanapps.notesapp.data.local.entity.NotesEntity
import com.ratanapps.notesapp.data.local.util.DatabaseResponse
import com.ratanapps.notesapp.data.repo.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class NotesDetailViewModel  @Inject constructor(val notesRepository: NotesRepository): ViewModel() {

    private val _saveState = MutableStateFlow<DatabaseResponse<Unit>>(DatabaseResponse.Idle)
    val saveNoteState: StateFlow<DatabaseResponse<Unit>> = _saveState;


    private val _getNoteState =
        MutableStateFlow<DatabaseResponse<NotesEntity?>>(DatabaseResponse.Idle)
    val getNoteState: StateFlow<DatabaseResponse<NotesEntity?>> = _getNoteState;


    fun saveNotesToDb(title: String, message: String)  {
        viewModelScope.launch {
            _saveState.value = DatabaseResponse.Loading
            try {
                notesRepository.addNotesToDb(title = title, content = message)
                _saveState.value = DatabaseResponse.Success(Unit)
            } catch (exception: Exception) {
                _saveState.value = DatabaseResponse.Error(exception.message ?: "Something went wrong")
            }
        }
    }

    fun modifyNotesToDb(noteId: Int, title: String, message: String)  {
        viewModelScope.launch {
            _saveState.value = DatabaseResponse.Loading
            try {
                notesRepository.modifyNotesToDb(noteId = noteId, title = title, content = message)
                _saveState.value = DatabaseResponse.Success(Unit)
            } catch (exception: Exception) {
                _saveState.value = DatabaseResponse.Error(exception.message ?: "Something went wrong")
            }
        }
    }

    fun getNoteById(noteId: Int) {
        viewModelScope.launch {
            _getNoteState.value = DatabaseResponse.Loading
            try {
                 notesRepository.getNoteById(noteId).collect { notesEntity ->
                     _getNoteState.value = DatabaseResponse.Success(notesEntity)
                 }
            } catch (exception: Exception) {
                _getNoteState.value = DatabaseResponse.Error(exception.message ?: "Something went wrong")
            }
        }
    }

}