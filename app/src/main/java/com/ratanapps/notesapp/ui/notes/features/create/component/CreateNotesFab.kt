package com.ratanapps.notesapp.ui.notes.features.create.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


/**
 * A Floating Action Button (FAB) component for the Create/Edit Notes screen.
 * Displays a save icon and triggers the provided [onClick] action.
 *
 * @param onClick Callback to be executed when the FAB is clicked.
 */
@Composable
fun CreateNotesFab(onClick: () -> Unit) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = {
            onClick.invoke()
        }
    ) {
        Icon(Icons.Filled.Save, "Save")
    }
}

