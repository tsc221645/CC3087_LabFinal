package com.uvg.ana.labfinal.utils


import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private const val DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss"

    /**
     * Gets the current date and time as a formatted string.
     * @param format The format of the date string. Defaults to "yyyy-MM-dd HH:mm:ss".
     * @return The formatted date string.
     */
    fun getCurrentDateTime(format: String = DEFAULT_FORMAT): String {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(Date())
    }

    /**
     * Formats a given Date object into a string.
     * @param date The Date object to format.
     * @param format The format of the date string. Defaults to "yyyy-MM-dd HH:mm:ss".
     * @return The formatted date string.
     */
    fun formatDateTime(date: Date, format: String = DEFAULT_FORMAT): String {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(date)
    }

    /**
     * Parses a string into a Date object.
     * @param dateString The date string to parse.
     * @param format The format of the date string. Defaults to "yyyy-MM-dd HH:mm:ss".
     * @return The Date object, or null if parsing fails.
     */
    fun parseDateTime(dateString: String, format: String = DEFAULT_FORMAT): Date? {
        return try {
            val dateFormat = SimpleDateFormat(format, Locale.getDefault())
            dateFormat.parse(dateString)
        } catch (e: Exception) {
            null
        }
    }
}
