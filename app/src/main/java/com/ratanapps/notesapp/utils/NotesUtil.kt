package com.ratanapps.notesapp.utils

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object NotesUtil {
    fun getCurrentTimeStamp(): String {
        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat(
            "dd MMMM yyyy, HH:mm z",
            Locale.ENGLISH
        )

        formatter.timeZone = TimeZone.getTimeZone("Asia/Kolkata")

        val formattedDate = formatter.format(calendar.time)
        return formattedDate;
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}