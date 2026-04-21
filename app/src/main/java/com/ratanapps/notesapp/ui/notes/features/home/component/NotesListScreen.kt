package com.ratanapps.notesapp.ui.notes.features.home.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ratanapps.notesapp.data.local.util.DatabaseResponse
import com.ratanapps.notesapp.ui.notes.features.home.viewmodel.MainViewModel

@Composable
fun NotesListScreen(modifier: Modifier, navController: NavController, mainViewModel: MainViewModel) {

    val allNotesState by mainViewModel.getAllNoteState.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.getAllNotesFromDb()
    }

    when(val response = allNotesState) {
        is DatabaseResponse.Loading -> {
            NotesListLoading(modifier)
        }

        is DatabaseResponse.Success -> {
            NotesListSuccess(modifier,response.data, navController, mainViewModel)
        }

        is DatabaseResponse.Error -> {
            NoteListError(modifier)
        }

        else -> {}
    }
}