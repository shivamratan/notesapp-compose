package com.ratanapps.notesapp.ui.notes.features.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Error state component for the home screen.
 * Displays a generic error message when note retrieval fails.
 *
 * @param modifier Modifier for the container box.
 */
@Composable
fun NoteListError(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = "Something went wrong", modifier = Modifier.align(Alignment.Center))
    }
}