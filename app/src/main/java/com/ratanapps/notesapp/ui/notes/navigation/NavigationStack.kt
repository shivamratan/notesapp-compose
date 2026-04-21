package com.ratanapps.notesapp.ui.notes.navigation

import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ratanapps.notesapp.ui.notes.activity.MyNotesDashboard
import com.ratanapps.notesapp.ui.notes.activity.NotesDetailScreen
import com.ratanapps.notesapp.ui.notes.viewmodel.MainViewModel
import com.ratanapps.notesapp.ui.notes.viewmodel.NotesDetailViewModel

@Composable
fun AppNavHost() {
    val navController = rememberNavController();
    val context = LocalContext.current

    NavHost(navController = navController,
        startDestination = Screen.Home.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(500)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        }
    ) {
        composable(route = Screen.Home.route) {
            val mainViewModel: MainViewModel = hiltViewModel(context as ComponentActivity)
            MyNotesDashboard(navController, mainViewModel)
        }
        composable(route = "${Screen.NotesDetail.route}?noteId={noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.IntType
                    defaultValue = -1
                })
        ) {
            val notesDetailViewModel: NotesDetailViewModel = hiltViewModel()
            val noteId = it.arguments?.getInt("noteId")
            NotesDetailScreen(navController, notesDetailViewModel, noteId)
        }
    }

}