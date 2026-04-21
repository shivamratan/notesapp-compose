package com.ratanapps.notesapp.ui.notes.features.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ratanapps.notesapp.ui.notes.features.home.component.NotesListFab
import com.ratanapps.notesapp.ui.notes.features.home.component.NotesListNavDrawerSheet
import com.ratanapps.notesapp.ui.notes.features.home.component.NotesListScreen
import com.ratanapps.notesapp.ui.notes.features.home.component.NotesListTopAppBar
import com.ratanapps.notesapp.ui.notes.features.home.viewmodel.MainViewModel
import com.ratanapps.notesapp.ui.notes.navigation.Screen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListRoute(navController: NavController, mainViewModel: MainViewModel) {
    val context = LocalContext.current

    val isDark by mainViewModel.isDark
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NotesListNavDrawerSheet(
                onComposeNoteClicked = {
                    scope.launch { drawerState.close() }
                    navController.navigate(Screen.NotesDetail.withArgs(-1))
                },
                onNoteListClicked = {
                    scope.launch { drawerState.close() }
                },
                onAboutClicked = {
                    scope.launch { drawerState.close() }
                }
            )
        },
        content = {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    NotesListTopAppBar(
                        onHamburgerClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed)
                                        open()
                                    else
                                        close()
                                }
                            }
                        },
                        onThemeCheckChange = {
                            mainViewModel.isDark.value = it
                        },
                        isDark
                    )
                },
                floatingActionButton = {
                    NotesListFab {
                        navController.navigate(Screen.NotesDetail.withArgs(-1))
                    }
                }
            ) { innerPadding ->
                NotesListScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    mainViewModel
                )
            }
        }
    )
}