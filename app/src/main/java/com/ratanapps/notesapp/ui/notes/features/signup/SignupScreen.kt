package com.ratanapps.notesapp.ui.notes.features.signup

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ratanapps.notesapp.R

private val TitleColor = Color(0xFF3A86B7)

private val TextSecondaryColor = Color(0xFF6B7280)

@Composable
fun SignupScreen(modifier: Modifier = Modifier,
                 onLoginClicked: () -> Unit = {},
                 onGoogleSignupClicked: ()-> Unit = {}
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Box(       modifier = modifier
            .size(56.dp)
            .clip(CircleShape)
            .border(1.dp, Color.LightGray.copy(alpha = 0.5f), CircleShape)
            .clickable { onGoogleSignupClicked() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_google),
                contentDescription = "Google",
                modifier = Modifier.size(32.dp),
                tint = Color.Unspecified
            )
        }

        Spacer(modifier = modifier.fillMaxWidth().height(10.dp))

        val annotatedText = buildAnnotatedString {
            append(stringResource(R.string.already_have_account))
            withStyle(SpanStyle(color = TitleColor, fontWeight = FontWeight.Bold)) {
                append(stringResource(R.string.sign_in))
            }
        }

        Text(
            text = annotatedText,
            modifier = Modifier.padding(bottom = 32.dp).clickable { onLoginClicked() },
            fontSize = 14.sp,
            color = TextSecondaryColor
        )

    }
}

@Preview
@Composable
fun SignupScreenPreview() {
    SignupScreen()
}