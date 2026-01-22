package org.example.pantrywisecmp.product.presentation.helper

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.daysUntil
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlin.time.Instant

object DateTimeHelper {

    @OptIn(FormatStringsInDatetimeFormats::class)
    private val dayMonthYearFormatter =
        LocalDateTime.Format {
            byUnicodePattern("dd/MM/yyyy")
        }

    fun formatTimestampToDayMonthYear(
        timestampMillis: Long,
        timeZone: TimeZone = TimeZone.currentSystemDefault()
    ): String {
        val instant = Instant.fromEpochMilliseconds(timestampMillis)
        val dateTime = instant.toLocalDateTime(timeZone)
        return dayMonthYearFormatter.format(dateTime)
    }

    fun daysBetweenTimestampAndNow(timestampMillis: Long): Int {
        val timeZone = TimeZone.currentSystemDefault()

        val targetInstant = Instant.fromEpochMilliseconds(timestampMillis)
        val targetDate: LocalDate = targetInstant.toLocalDateTime(timeZone).date

        val today: LocalDate = kotlin.time.Clock.System.now()
            .toLocalDateTime(timeZone)
            .date

        return today.daysUntil(targetDate)
    }
}
