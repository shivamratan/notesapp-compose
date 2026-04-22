package com.ratanapps.notesapp.ui.notes.features.create

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ratanapps.notesapp.data.local.util.DatabaseResponse
import com.ratanapps.notesapp.ui.notes.features.create.component.LoadingOverlay
import com.ratanapps.notesapp.ui.notes.features.create.component.CreateNotesEditorContent
import com.ratanapps.notesapp.ui.notes.features.create.component.CreateNotesFab
import com.ratanapps.notesapp.ui.notes.features.create.component.CreateNotesTopBar
import com.ratanapps.notesapp.ui.notes.features.create.viewmodel.CreateNotesUiState
import com.ratanapps.notesapp.ui.notes.features.create.viewmodel.CreateNotesViewModel
import com.ratanapps.notesapp.utils.NotesUtil


/**
 * Screen for creating and editing notes.
 *
 * This composable manages the UI for inputting a note's title and content,
 * handles database operations (save, update, fetch) via the [CreateNotesViewModel],
 * and provides navigation and loading feedback.
 *
 * @param navController Controller for navigating back to the previous screen.
 * @param createNotesViewModel ViewModel containing state and logic for this screen.
 * @param noteId The ID of the note to edit, or null/ -1 if creating a new note.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNotesScreen(navController: NavController, createNotesViewModel: CreateNotesViewModel, noteId: Int?) {

    val context = LocalContext.current
    val saveState by createNotesViewModel.saveNoteState.collectAsState()
    val getNoteState by createNotesViewModel.getNoteState.collectAsState()
    val createNotesUiState by createNotesViewModel.createNotesUiState.collectAsState()

    BackHandler(true) {
        navController.popBackStack()
    }

    LaunchedEffect(saveState) {
        when (saveState) {
            is DatabaseResponse.Success -> {
                NotesUtil.showToast(context, "Notes saved successfully")
                navController.popBackStack()
            }
            is DatabaseResponse.Error -> {
                NotesUtil.showToast(context, (saveState as DatabaseResponse.Error).message)
            }
            else -> Unit
        }
    }

    LaunchedEffect(Unit) {
        if (noteId != null && noteId != -1) {
            createNotesViewModel.getNoteById(noteId)
        }
    }

    LaunchedEffect(getNoteState) {
        when (val response = getNoteState) {
            is DatabaseResponse.Success -> {
                response.data?.let {
                    createNotesViewModel.onTitleChange(it.notesTitle)
                    createNotesViewModel.onBodyChange(it.notesContent)
                }
            }
            is DatabaseResponse.Error -> {
                NotesUtil.showToast(context, response.message)
            }
            else -> Unit
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CreateNotesTopBar {
                navController.popBackStack()
            }
        },
        floatingActionButton = {
            CreateNotesFab(onClick = {
                if (createNotesUiState.title.isEmpty() || createNotesUiState.body.isEmpty()) {
                    NotesUtil.showToast(context, "Please enter title and body")
                } else {
                    createNotesViewModel.onSaveFabClicked(noteId)
                }
            })
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
            CreateNotesEditorContent(
                titleText = createNotesUiState.title,
                onTitleChange = { createNotesViewModel.onTitleChange(it) },
                notesBodyText = createNotesUiState.body,
                onBodyChange = { createNotesViewModel.onBodyChange(it) }
            )

            if (saveState is DatabaseResponse.Loading || getNoteState is DatabaseResponse.Loading) {
                LoadingOverlay()
            }
        }
    }
}
