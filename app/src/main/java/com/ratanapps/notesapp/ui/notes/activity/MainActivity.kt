package com.ratanapps.notesapp.ui.notes.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.AutoFixNormal
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ratanapps.notesapp.ui.notes.navigation.AppNavHost
import com.ratanapps.notesapp.ui.notes.navigation.Screen
import com.ratanapps.notesapp.ui.notes.viewmodel.MainViewModel
import com.ratanapps.notesapp.ui.notes.viewmodel.NotesDetailViewModel
import com.ratanapps.notesapp.ui.theme.NotesAppTheme
import com.ratanapps.notesapp.utils.NotesUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.sin

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                AppNavHost()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNotesDashboard(navController: NavController, mainViewModel: MainViewModel) {
    val context = LocalContext.current

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.fillMaxWidth(0.7f)) {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                Spacer(modifier = Modifier.height(5.dp))

                NavigationDrawerItem(
                    label = { Text("Compose Note") },
                    selected = false,
                    onClick = {
                        NotesUtil.showToast(context, "Compose Note Item Clicked")
                    }
                )

                Spacer(modifier = Modifier.height(5.dp))
                NavigationDrawerItem(
                    label = { Text("Note List") },
                    selected = false,
                    onClick = {
                        NotesUtil.showToast(context, "Note List Clicked")
                    }
                )

                Spacer(modifier = Modifier.height(5.dp))
                NavigationDrawerItem(
                    label = { Text("About") },
                    selected = false,
                    onClick = {
                        NotesUtil.showToast(context, "About Clicked")
                    }
                )
            }
        },
        content = {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = {Text("Notes App")},
                        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.secondary),
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed)
                                                open()
                                            else
                                                close()
                                        }
                                    }
                                }
                            ) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = {
                            NotesUtil.showToast(context, "Floating Action Button Clicked")
                        }
                    ) {
                        Icon(Icons.Filled.Add, "Floating action button.")
                    }
                }
            ) { innerPadding ->
                HomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesDetailScreen(navController: NavController, notesDetailViewModel: NotesDetailViewModel, noteId: Int?) {

    val context = LocalContext.current
    BackHandler(true) {
        navController.popBackStack()
    }

    var titleText by remember { mutableStateOf("") }
    var notesBodyText by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {Text("Notes App")},
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.secondary),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Menu")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    //NotesUtil.showToast(context, "Floating Action Button Clicked")
                    if (titleText.isNotEmpty() && notesBodyText.isNotEmpty()) {

                    } else {
                        NotesUtil.showToast(context, "Please enter title and notes")
                    }
                }
            ) {
                Icon(Icons.Filled.Save, "Save")
            }
        }
    ) { innerPadding ->

        Box(modifier = Modifier.fillMaxSize().padding(innerPadding), contentAlignment = Alignment.Center) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(5.dp))

                OutlinedTextField(
                    value = titleText,
                    onValueChange = {
                        titleText = it
                    },
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
                    onValueChange = {
                        notesBodyText = it
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, 5.dp),
                    minLines = 10,
                    maxLines = 15,
                    placeholder = { Text("Enter your note here...") },
                    shape = RoundedCornerShape(12.dp)
                )

            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier, navController: NavController) {

    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Home Screen",
            modifier = modifier
        )

        Button(onClick = {
            navController.navigate(Screen.NotesDetail.withArgs(5))
            NotesUtil.showToast(context, "Navigating to Notes Detail");
        }) {
            Text(text = "Navigate to Notes Detail")
        }
    }
}





@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Button(onClick = {  }) {
            Text(text = "Navigate to Notes Detail")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesAppTheme {
        Greeting("Android")
    }
}