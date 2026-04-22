package com.ratanapps.notesapp.ui.notes.features.home.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

/**
 * Floating Action Button (FAB) for the home screen to trigger note creation.
 *
 * @param onFabClick Callback triggered when the FAB is clicked.
 */
@Composable
fun NotesListFab(onFabClick: ()->Unit) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = onFabClick
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}