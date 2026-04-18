package com.ratanapps.notesapp.data.local.util


sealed class DatabaseResponse<out T> {
    object Success : DatabaseResponse<Nothing>()
    data class Error(val message: String) : DatabaseResponse<Nothing>()
    object Loading : DatabaseResponse<Nothing>()
    object Idle : DatabaseResponse<Nothing>()
}