package com.ratanapps.notesapp.ui.notes.features.home.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ratanapps.notesapp.data.local.entity.NotesEntity
import com.ratanapps.notesapp.data.local.util.DatabaseResponse
import com.ratanapps.notesapp.data.repo.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the Home screen (Notes List).
 *
 * Manages the state for the list of notes, loading status for the splash screen,
 * and global app settings like the dark theme toggle.
 *
 * @property notesRepository The repository used for database operations.
 */
@HiltViewModel
class MainViewModel @Inject constructor(val notesRepository: NotesRepository): ViewModel() {

    private val _saveState =
        MutableStateFlow<DatabaseResponse<List<NotesEntity>>>(DatabaseResponse.Idle)

    val getAllNoteState: StateFlow<DatabaseResponse<List<NotesEntity>>> = _saveState

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    var isDark = mutableStateOf(false)


    init {
        viewModelScope.launch {
            delay(1500)
            _isLoading.value = false
        }
    }

    fun getAllNotesFromDb()  {
        viewModelScope.launch {
            _saveState.value = DatabaseResponse.Loading
            try {
                 notesRepository.getAllNotes().collect { notesList ->
                     _saveState.value = DatabaseResponse.Success(notesList)
                 }

            } catch (exception: Exception) {
                _saveState.value = DatabaseResponse.Error(exception.message ?: "Something went wrong")
            }
        }
    }

    fun deleteNoteFromDb(id: Int) {
        viewModelScope.launch {
            _saveState.value = DatabaseResponse.Loading
            try {
                notesRepository.deleteNotedById(id)
                //_saveState.value = DatabaseResponse.Success(Unit)
            } catch (exception: Exception) {
                _saveState.value = DatabaseResponse.Error(exception.message ?: "Something went wrong")
            }
        }
    }

}