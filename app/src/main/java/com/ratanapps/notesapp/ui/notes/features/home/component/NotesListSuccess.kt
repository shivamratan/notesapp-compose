package com.ratanapps.notesapp.ui.notes.features.home.component

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ratanapps.notesapp.data.local.entity.NotesEntity
import com.ratanapps.notesapp.ui.notes.features.common.AlphabetAvatar
import com.ratanapps.notesapp.ui.notes.features.common.NotesAlertDialog
import com.ratanapps.notesapp.ui.notes.features.home.viewmodel.MainViewModel
import com.ratanapps.notesapp.ui.notes.navigation.Screen
import kotlin.text.get

@Composable
fun NotesListSuccess(modifier: Modifier, notesEntityList: List<NotesEntity>, navController: NavController, mainViewModel: MainViewModel) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val clickedEntity = remember { mutableStateOf<NotesEntity?>(null) }

    Box(modifier = modifier.fillMaxSize()) {

        LazyColumn(modifier = Modifier.fillMaxSize().padding(5.dp)) {
            items(notesEntityList.size) { index ->
                NotesListItem(notesEntity = notesEntityList[index],
                    navController = navController,
                    onItemClicked = {
                        navController.navigate(Screen.NotesDetail.withArgs(notesEntityList[index].id))
                    },
                    onDeleteMenuItemClicked = {
                        openAlertDialog.value = true
                        clickedEntity.value = notesEntityList[index]
                    }
                )
            }
        }

        if (openAlertDialog.value) {
            NotesAlertDialog(
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