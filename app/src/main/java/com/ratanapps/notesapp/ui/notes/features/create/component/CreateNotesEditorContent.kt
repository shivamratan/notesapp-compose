package com.ratanapps.notesapp.ui.notes.features.create.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateNotesEditorContent(
    titleText: String,
    onTitleChange: (String) -> Unit,
    notesBodyText: String,
    onBodyChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = titleText,
            onValueChange = onTitleChange,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, 5.dp),
            leadingIcon = {
                Icon(Icons.Default.Title, contentDescription = "Notes Title")
            },
            singleLine = true,
            placeholder = { Text("Note Title") },
            shape = RoundedCornerShape(12.dp)
        )


        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = notesBodyText,
            onValueChange = onBodyChange,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, 5.dp),
            minLines = 10,
            maxLines = 15,
            placeholder = { Text("Enter your note here...") },
            shape = RoundedCornerShape(12.dp)
        )

    }
}