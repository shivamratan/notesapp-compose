package com.ratanapps.notesapp.ui.notes.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import com.ratanapps.notesapp.data.local.entity.NotesEntity
import com.ratanapps.notesapp.data.local.util.DatabaseResponse
import com.ratanapps.notesapp.ui.notes.navigation.AppNavHost
import com.ratanapps.notesapp.ui.notes.navigation.Screen
import com.ratanapps.notesapp.ui.notes.util.ComposeUtil
import com.ratanapps.notesapp.ui.notes.util.ComposeUtil.AlertDialogExample
import com.ratanapps.notesapp.ui.notes.viewmodel.MainViewModel
import com.ratanapps.notesapp.ui.notes.viewmodel.NotesDetailViewModel
import com.ratanapps.notesapp.ui.theme.NotesAppTheme
import com.ratanapps.notesapp.utils.NotesConstant
import com.ratanapps.notesapp.utils.NotesUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { viewModel.isLoading.value }

        enableEdgeToEdge()
        setContent {

            val isDark by viewModel.isDark

            NotesAppTheme(darkTheme = isDark) {
               // Log.e(NotesConstant.MAINACTIVITY_TAG, "Theme is ${ if (isDark) "Dark" else "Light" }")
                ComposeUtil.showErrorLogs(NotesConstant.MAINACTIVITY_TAG, "Theme is ${if (isDark) "isDark" else "isLight"}")
                AppNavHost()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNotesDashboard(navController: NavController, mainViewModel: MainViewModel) {
    val context = LocalContext.current

    val isDark by mainViewModel.isDark
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
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
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(Screen.NotesDetail.withArgs(-1))
                    }
                )

                Spacer(modifier = Modifier.height(5.dp))
                NavigationDrawerItem(
                    label = { Text("Note List") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                    }
                )

                Spacer(modifier = Modifier.height(5.dp))
                NavigationDrawerItem(
                    label = { Text("About") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        NotesUtil.showToast(context, "About Section")
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
                        },
                        actions = {
                            Switch(
                                checked = isDark,
                                onCheckedChange = { mainViewModel.isDark.value = it }
                            )
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = {
                            navController.navigate(Screen.NotesDetail.withArgs(-1))
                        }
                    ) {
                        Icon(Icons.Filled.Add, "Floating action button.")
                    }
                }
            ) { innerPadding ->
                HomeScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    mainViewModel
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

    val saveState by notesDetailViewModel.saveNoteState.collectAsState()

    LaunchedEffect(saveState) {
        when (saveState) {
            is DatabaseResponse.Success -> {
                NotesUtil.showToast(context, "Notes saved successfully")
                navController.popBackStack()
            }

            is DatabaseResponse.Error -> {
                NotesUtil.showToast(context, (saveState as DatabaseResponse.Error).message)
            }

            else -> Unit
        }
    }

    val getNoteState by notesDetailViewModel.getNoteState.collectAsState()


    var titleText by remember { mutableStateOf("") }
    var notesBodyText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        if (noteId != null && noteId != -1) {
            notesDetailViewModel.getNoteById(noteId)
        }
    }

    LaunchedEffect(getNoteState) {
        when (val response = getNoteState) {
            is DatabaseResponse.Success -> {
                response.data?.let {
                    titleText = it.notesTitle
                    notesBodyText = it.notesContent
                }

            }

            is DatabaseResponse.Error -> {
                NotesUtil.showToast(context, response.message)
            }

            else -> {

            }
        }
    }

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
                        if (noteId != null && noteId != -1) {
                            notesDetailViewModel.modifyNotesToDb(noteId = noteId, title = titleText, message = notesBodyText)
                        } else {
                            notesDetailViewModel.saveNotesToDb(title = titleText, message = notesBodyText)
                        }
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

            // 🔥 LOADER OVERLAY
            if (saveState is DatabaseResponse.Loading || getNoteState is DatabaseResponse.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier, navController: NavController, mainViewModel: MainViewModel) {

    val allNotesState by mainViewModel.getAllNoteState.collectAsState()

    LaunchedEffect(Unit) {
        mainViewModel.getAllNotesFromDb()
    }

    when(val response = allNotesState) {
        is DatabaseResponse.Loading -> {
            NotesListLoading(modifier)
        }

        is DatabaseResponse.Success -> {
           NotesListSuccess(modifier,response.data, navController, mainViewModel)
        }

        is DatabaseResponse.Error -> {
            NoteListError(modifier)
        }

        else -> {}
    }
}

@Composable
fun NotesListLoading(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.3f)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun NotesListSuccess(modifier: Modifier, notesEntityList: List<NotesEntity>, navController: NavController, mainViewModel: MainViewModel) {
    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize()) {
        val openAlertDialog = remember { mutableStateOf(false) }
        val clickedEntity = remember { mutableStateOf<NotesEntity?>(null) }

        LazyColumn(modifier = Modifier.fillMaxSize().padding(5.dp)) {
            items(notesEntityList.size) { index ->
                var expanded by remember { mutableStateOf(false) }

                Card(modifier = Modifier.fillMaxWidth().padding(5.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        navController.navigate(Screen.NotesDetail.withArgs(notesEntityList[index].id))
                    }
                ) {
                    Row(modifier = Modifier.fillMaxWidth().padding(all = 12.dp), verticalAlignment = Alignment.CenterVertically) {
                        AlphabetAvatar(title = notesEntityList[index].notesTitle, modifier = Modifier.size(48.dp))

                        Spacer(modifier = Modifier.width(5.dp))

                        Column(modifier = Modifier.padding(5.dp).weight(1f)
                        ) {
                            Text(text = notesEntityList[index].notesTitle, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                            Text(text = notesEntityList[index].notesContent, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = notesEntityList[index].timeStamp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.align(Alignment.Start),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))
                        Box {
                            IconButton(
                                modifier = Modifier,
                                onClick = { expanded = !expanded }) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "More options"
                                )
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Open") },
                                    onClick = {
                                        expanded = !expanded
                                        navController.navigate(Screen.NotesDetail.withArgs(notesEntityList[index].id)) }
                                )
                                DropdownMenuItem(
                                    text = { Text("Modify") },
                                    onClick = {
                                        expanded = !expanded
                                        navController.navigate(Screen.NotesDetail.withArgs(notesEntityList[index].id)) }
                                )
                                DropdownMenuItem(
                                    text = { Text("Delete") },
                                    onClick = {
                                        expanded = !expanded
                                        openAlertDialog.value = true
                                        clickedEntity.value = notesEntityList[index]
                                    }
                                )
                            }
                        }

                    }
                }
            }
        }

        if (openAlertDialog.value) {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    openAlertDialog.value = false
                    clickedEntity.value?.id?.let {
                        mainViewModel.deleteNoteFromDb(it)
                    }
                },
                dialogTitle = "Delete Note",
                dialogText = "Are you sure want to delete the note ?"
            )
        }
    }
}


@Composable
fun AlphabetAvatar(title: String, modifier: Modifier, size: Dp =48.dp) {
    val letter = title.trim().firstOrNull()?.uppercase() ?: "?"

    // Material color list
    val colorList = listOf(
        Color(0xFFE57373), // Red
        Color(0xFF64B5F6), // Blue
        Color(0xFF81C784), // Green
        Color(0xFFFFB74D), // Orange
        Color(0xFFBA68C8), // Purple
        Color(0xFF4DB6AC), // Teal
        Color(0xFFA1887F), // Brown
        Color(0xFF90A4AE)  // Grey
    )

    val backgroundColor = remember { colorList.random() }

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(text = letter,
            color = Color.White,
            fontSize = size.value.sp / 2,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun NoteListError(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(text = "Something went wrong", modifier = Modifier.align(Alignment.Center))
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