package br.com.lucascordeiro.nexly.shared.ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatVolume(volume: Double): String {
    val (format, value) = when {
        volume >= 1_000_000_000_000 -> Pair("US$ %.1f tri", volume / 1_000_000_000_000)
        volume >= 1_000_000_000 -> Pair("US$ %.1f bi", volume / 1_000_000_000)
        volume >= 1_000_000 -> Pair("US$ %.1f mi", volume / 1_000_000)
        volume >= 1_000 -> Pair("US$ %.0f mil", volume / 1_000)
        else -> Pair("US$ %.0f", volume)
    }

    return String.format(Locale.getDefault(), format, value)
}

fun Date.toFormattedString(
    format: String = "dd/MM/yyyy",
    locale: Locale = Locale.getDefault()
): String {
    val dateFormat = SimpleDateFormat(format, locale)
    return dateFormat.format(this)
}