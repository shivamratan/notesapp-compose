package com.ratanapps.notesapp.ui.notes.features.create.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ratanapps.notesapp.data.local.entity.NotesEntity
import com.ratanapps.notesapp.data.local.util.DatabaseResponse
import com.ratanapps.notesapp.data.repo.NotesRepository
import com.ratanapps.notesapp.utils.NotesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the Create/Edit Notes screen.
 *
 * Manages the UI state, text inputs, and database operations for creating,
 * modifying, and fetching individual notes.
 *
 * @property notesRepository The repository for data access.
 */
@HiltViewModel
class CreateNotesViewModel  @Inject constructor(val notesRepository: NotesRepository): ViewModel() {

    private val _saveState = MutableStateFlow<DatabaseResponse<Unit>>(DatabaseResponse.Idle)
    val saveNoteState: StateFlow<DatabaseResponse<Unit>> = _saveState;


    private val _getNoteState =
        MutableStateFlow<DatabaseResponse<NotesEntity?>>(DatabaseResponse.Idle)
    val getNoteState: StateFlow<DatabaseResponse<NotesEntity?>> = _getNoteState;


    private val _createNotesUiState = MutableStateFlow(CreateNotesUiState())
    val createNotesUiState: StateFlow<CreateNotesUiState> = _createNotesUiState.asStateFlow()


    fun onTitleChange(newTitle: String) {
        _createNotesUiState.update { it.copy(title = newTitle) }
    }

    fun onBodyChange(newContent: String) {
        _createNotesUiState.update { it.copy(body = newContent) }
    }

    fun onSaveFabClicked(notesId: Int?) {
        val currentCreateNotesUiState = createNotesUiState.value;
        if (currentCreateNotesUiState.title.isNotEmpty() && currentCreateNotesUiState.body.isNotEmpty()) {
            if (notesId != null && notesId != -1) {
                modifyNotesToDb(noteId = notesId, title = currentCreateNotesUiState.title, message = currentCreateNotesUiState.body)
            } else {
                saveNotesToDb(title = currentCreateNotesUiState.title, message = currentCreateNotesUiState.body)
            }
        }
    }


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

/**
 * Data class representing the UI state for text inputs in the Create Notes screen.
 *
 * @property title The current text in the title field.
 * @property body The current text in the body/content field.
 */
data class CreateNotesUiState(
    val title: String = "",
    val body: String = ""
)