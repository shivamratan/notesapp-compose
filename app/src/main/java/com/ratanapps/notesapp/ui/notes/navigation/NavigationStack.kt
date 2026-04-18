package com.ratanapps.notesapp.ui.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ratanapps.notesapp.ui.notes.activity.MyNotesDashboard
import com.ratanapps.notesapp.ui.notes.activity.NotesDetailScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController();

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            MyNotesDashboard(navController)
        }
        composable(route = "${Screen.NotesDetail.route}?noteId={noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.IntType
                    defaultValue = -1
                })
        ) {
            NotesDetailScreen(navController)
        }
    }

}