package com.ratanapps.notesapp.ui.notes.features.home.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

/**
 * The top app bar for the home screen.
 * Contains a navigation menu icon (hamburger), app title, and a theme toggle switch.
 *
 * @param onHamburgerClick Callback triggered when the menu icon is clicked.
 * @param onThemeCheckChange Callback triggered when the theme switch is toggled.
 * @param isDark The current theme state (true for dark mode).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListTopAppBar(onHamburgerClick: ()->Unit, onThemeCheckChange: (Boolean)->Unit, isDark: Boolean) {
    TopAppBar(
        title = {Text("Notes App")},
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.secondary),
        navigationIcon = {
            IconButton(
                onClick = onHamburgerClick
            ) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            Switch(
                checked = isDark,
                onCheckedChange = onThemeCheckChange
            )
        }
    )
}