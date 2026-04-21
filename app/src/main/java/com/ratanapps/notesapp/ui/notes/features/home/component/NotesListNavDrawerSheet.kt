package com.ratanapps.notesapp.ui.notes.features.home.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ratanapps.notesapp.ui.notes.navigation.Screen
import com.ratanapps.notesapp.utils.NotesUtil
import kotlinx.coroutines.launch

@Composable
fun NotesListNavDrawerSheet(onComposeNoteClicked: ()->Unit, onNoteListClicked: ()->Unit, onAboutClicked: ()->Unit) {
    ModalDrawerSheet(modifier = Modifier.fillMaxWidth(0.7f)) {
        Text(text = "Notes App",
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        NavigationDrawerItem(
            label = { Text("Compose Note") },
            selected = false,
            onClick = onComposeNoteClicked
        )

        Spacer(modifier = Modifier.height(5.dp))
        NavigationDrawerItem(
            label = { Text("Note List") },
            selected = false,
            onClick = onNoteListClicked
        )

        Spacer(modifier = Modifier.height(5.dp))
        NavigationDrawerItem(
            label = { Text("About") },
            selected = false,
            onClick = onAboutClicked
        )
    }
}