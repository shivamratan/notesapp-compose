package com.ratanapps.notesapp.ui.notes.util

import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

object ComposeUtil {


    @Composable
    fun showDebugLogs(tag: String, message: String) {
        SideEffect {
            Log.d(tag, message)
        }
    }

    @Composable
    fun showErrorLogs(tag: String, message: String) {
        SideEffect {
            Log.e(tag, message)
        }
    }

}