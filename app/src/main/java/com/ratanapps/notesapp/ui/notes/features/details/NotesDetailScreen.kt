package com.ratanapps.notesapp.ui.notes.features.details

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
import com.ratanapps.notesapp.ui.notes.features.details.component.LoadingOverlay
import com.ratanapps.notesapp.ui.notes.features.details.component.NotesDetailEditorContent
import com.ratanapps.notesapp.ui.notes.features.details.component.NotesDetailFab
import com.ratanapps.notesapp.ui.notes.features.details.component.NotesDetailTopBar
import com.ratanapps.notesapp.ui.notes.features.details.viewmodel.NotesDetailViewModel
import com.ratanapps.notesapp.utils.NotesUtil


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesDetailScreen(navController: NavController, notesDetailViewModel: NotesDetailViewModel, noteId: Int?) {

    val context = LocalContext.current
    val saveState by notesDetailViewModel.saveNoteState.collectAsState()
    val getNoteState by notesDetailViewModel.getNoteState.collectAsState()

    var titleText by remember { mutableStateOf("") }
    var notesBodyText by remember { mutableStateOf("") }

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
            notesDetailViewModel.getNoteById(noteId)
        }
    }

    LaunchedEffect(getNoteState) {
        when (val response = getNoteState) {
            is DatabaseResponse.Success -> {
                response.data?.let {
                    titleText = it.notesTitle
                    notesBodyText = it.notesContent
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
            NotesDetailTopBar {
                navController.popBackStack()
            }
        },
        floatingActionButton = {
            NotesDetailFab(onClick = {
                //NotesUtil.showToast(context, "Floating Action Button Clicked")
                onFabClick(titleText, notesBodyText, noteId, context, notesDetailViewModel)
            })
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
            NotesDetailEditorContent(
                titleText = titleText,
                onTitleChange = { titleText = it },
                notesBodyText = notesBodyText,
                onBodyChange = { notesBodyText = it }
            )

            if (saveState is DatabaseResponse.Loading || getNoteState is DatabaseResponse.Loading) {
                LoadingOverlay()
            }
        }
    }
}

private fun onFabClick(titleText: String, notesBodyText: String, noteId: Int?, context: Context, notesDetailViewModel: NotesDetailViewModel) {
    if (titleText.isNotEmpty() && notesBodyText.isNotEmpty()) {
        if (noteId != null && noteId != -1) {
            notesDetailViewModel.modifyNotesToDb(noteId = noteId, title = titleText, message = notesBodyText)
        } else {
            notesDetailViewModel.saveNotesToDb(title = titleText, message = notesBodyText)
        }
    } else {
        NotesUtil.showToast(context, "Please enter title and notes")
    }
}