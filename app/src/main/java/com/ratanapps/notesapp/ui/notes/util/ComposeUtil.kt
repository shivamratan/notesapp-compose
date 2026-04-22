package com.ratanapps.notesapp.ui.notes.util

import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

/**
 * Utility object for Jetpack Compose related helper functions.
 *
 * Provides specialized logging methods that execute within [SideEffect] to ensure
 * logs are only printed during successful recompositions.
 */
object ComposeUtil {


    /**
     * Prints a debug level log message.
     *
     * @param tag The identifier for the log source.
     * @param message The text to be logged.
     */
    @Composable
    fun showDebugLogs(tag: String, message: String) {
        SideEffect {
            Log.d(tag, message)
        }
    }

    /**
     * Prints an error level log message.
     *
     * @param tag The identifier for the log source.
     * @param message The text to be logged.
     */
    @Composable
    fun showErrorLogs(tag: String, message: String) {
        SideEffect {
            Log.e(tag, message)
        }
    }

}