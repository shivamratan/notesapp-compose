package com.ratanapps.notesapp.ui.notes.features.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ratanapps.notesapp.ui.notes.navigation.Screen

/**
 * A dropdown menu for individual note items.
 * Provides options to open, modify, or delete a specific note.
 *
 * @param onOpenItemClicked Callback for the "Open" action.
 * @param onModifyItemClicked Callback for the "Modify" action.
 * @param onDeleteItemClicked Callback for the "Delete" action.
 */
@Composable
fun NotesItemDropdownMenu(onOpenItemClicked: ()->Unit, onModifyItemClicked: ()->Unit, onDeleteItemClicked: ()->Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(
            modifier = Modifier,
            onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Open") },
                onClick = {
                    expanded = !expanded
                    onOpenItemClicked.invoke()
                }
            )
            DropdownMenuItem(
                text = { Text("Modify") },
                onClick = {
                    expanded = !expanded
                    onModifyItemClicked.invoke()
                }
            )
            DropdownMenuItem(
                text = { Text("Delete") },
                onClick = {
                    expanded = !expanded
                    onDeleteItemClicked.invoke()
                }
            )
        }
    }
}