package com.ratanapps.notesapp.ui.notes.features.create.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNotesTopBar(onBackClick: ()->Unit) {
    TopAppBar(
        title = {Text("Notes App")},
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.secondary),
        navigationIcon = {
            IconButton(
                onClick = {
                    onBackClick.invoke()
                }
            ) {
                Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Menu")
            }
        }
    )
}