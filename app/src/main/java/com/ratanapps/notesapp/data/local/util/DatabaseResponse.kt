package com.ratanapps.notesapp.data.local.util


/**
 * A sealed class representing the state of a database operation.
 * 
 * Used to communicate loading progress, success data, or error messages 
 * from the data layer to the UI.
 *
 * @param T The type of data returned on success.
 */
sealed class DatabaseResponse<out T> {
    data class Success<out T>(val data: T) : DatabaseResponse<T>()
    data class Error(val message: String) : DatabaseResponse<Nothing>()
    object Loading : DatabaseResponse<Nothing>()
    object Idle : DatabaseResponse<Nothing>()
}