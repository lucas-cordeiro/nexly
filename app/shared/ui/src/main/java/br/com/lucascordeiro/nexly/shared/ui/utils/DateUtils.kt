package br.com.lucascordeiro.nexly.shared.ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(
    date: String?,
    format: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
    locale: Locale = Locale.getDefault()
): Date? {
    if (date.isNullOrEmpty()) {
        return null
    }

    return try {
        val dateFormat = SimpleDateFormat(format, locale)
        dateFormat.parse(date)
    } catch (e: Exception) {
        null
    }
}