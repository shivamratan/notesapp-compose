package com.ratanapps.notesapp.ui.notes.navigation

sealed class Screen(val route: String) {
    object Home : Screen("notes_list")
    object NotesDetail : Screen("notes_detail")

    fun withArgs(noteId: Int) = "$route?noteId=$noteId"
}