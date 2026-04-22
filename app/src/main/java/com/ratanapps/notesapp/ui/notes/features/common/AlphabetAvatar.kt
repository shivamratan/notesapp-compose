package com.ratanapps.notesapp.ui.notes.features.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A circular avatar component that displays the first letter of a title.
 * The background color is randomly selected from a predefined list of Material colors.
 *
 * @param title The text from which the first letter will be extracted.
 * @param modifier Modifier for the avatar container.
 * @param size The diameter of the circular avatar. Defaults to 48.dp.
 */
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