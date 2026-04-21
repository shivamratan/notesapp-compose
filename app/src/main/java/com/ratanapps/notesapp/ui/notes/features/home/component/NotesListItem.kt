package com.ratanapps.notesapp.ui.notes.features.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ratanapps.notesapp.data.local.entity.NotesEntity
import com.ratanapps.notesapp.ui.notes.features.common.AlphabetAvatar
import com.ratanapps.notesapp.ui.notes.navigation.Screen

@Composable
fun NotesListItem(notesEntity: NotesEntity, navController: NavController, onItemClicked: ()->Unit, onDeleteMenuItemClicked: ()->Unit) {

    Card(modifier = Modifier.fillMaxWidth().padding(5.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = onItemClicked
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(all = 12.dp), verticalAlignment = Alignment.CenterVertically) {
            AlphabetAvatar(title = notesEntity.notesTitle, modifier = Modifier.size(48.dp))

            Spacer(modifier = Modifier.width(5.dp))

            Column(modifier = Modifier.padding(5.dp).weight(1f)
            ) {
                Text(text = notesEntity.notesTitle, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                Text(text = notesEntity.notesContent, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = notesEntity.timeStamp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.align(Alignment.Start),
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(modifier = Modifier.width(10.dp))
            NotesItemDropdownMenu(
                onOpenItemClicked = {
                    navController.navigate(Screen.NotesDetail.withArgs(notesEntity.id))
                },
                onModifyItemClicked = {
                    navController.navigate(Screen.NotesDetail.withArgs(notesEntity.id))
                },
                onDeleteItemClicked = onDeleteMenuItemClicked
            )

        }
    }
}