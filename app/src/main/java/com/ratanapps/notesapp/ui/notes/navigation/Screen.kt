package com.ratanapps.notesapp.ui.notes.navigation

/**
 * Sealed class representing the different screens/destinations in the application.
 *
 * Each object defines a unique route string for navigation.
 *
 * @property route The string route used by the NavHost.
 */
sealed class Screen(val route: String) {
    /** Home screen displaying the list of notes. */
    object Home : Screen("notes_list")
    
    /** Screen for creating or editing a specific note. */
    object NotesDetail : Screen("notes_detail")

    /**
     * Helper function to append arguments to a route.
     *
     * @param noteId The ID of the note to be passed as an argument.
     * @return The formatted route string with the argument.
     */
    fun withArgs(noteId: Int) = "$route?noteId=$noteId"
}