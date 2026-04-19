package com.ratanapps.notesapp.data.local.util


sealed class DatabaseResponse<out T> {
    data class Success<out T>(val data: T) : DatabaseResponse<T>()
    data class Error(val message: String) : DatabaseResponse<Nothing>()
    object Loading : DatabaseResponse<Nothing>()
    object Idle : DatabaseResponse<Nothing>()
}