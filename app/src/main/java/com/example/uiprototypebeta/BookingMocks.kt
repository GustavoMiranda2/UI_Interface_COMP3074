package com.example.uiprototypebeta

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Centralizes mock booking data so both selection screens share the same state.
 */
object BookingMocks {
    // --- MOCK BOOKING DATA START ---
    private val mutableBusy = mutableMapOf(
        todayKey() to mutableListOf("12:00 PM", "4:00 PM"),
        plusDaysKey(1) to mutableListOf("11:15 AM", "3:30 PM"),
        plusDaysKey(2) to mutableListOf("10:00 AM", "1:45 PM", "6:30 PM")
    )
    // --- MOCK BOOKING DATA END ---

    val busySlots: Map<String, List<String>> get() = mutableBusy

    fun appendBusy(dateKey: String, slots: List<String>) {
        val list = mutableBusy.getOrPut(dateKey) { mutableListOf() }
        list.addAll(slots)
    }

    fun generateSlots(): List<String> {
        val slots = mutableListOf<String>()
        val startMinutes = 10 * 60
        val endMinutes = 19 * 60
        var current = startMinutes
        while (current <= endMinutes) {
            slots.add(formatMinutes(current))
            current += 15
        }
        return slots
    }

    fun blocksToHold(serviceDuration: Int, addOnDuration: Int): Int {
        val totalMinutes = serviceDuration + addOnDuration
        val slots = (totalMinutes + 14) / 15
        return (slots - 1).coerceAtLeast(0)
    }

    private fun formatMinutes(totalMinutes: Int): String {
        val cal = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, totalMinutes / 60)
            set(Calendar.MINUTE, totalMinutes % 60)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        return SimpleDateFormat("h:mm a", Locale.getDefault()).format(cal.time)
    }

    private fun todayKey(): String {
        val cal = Calendar.getInstance()
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cal.time)
    }

    private fun plusDaysKey(days: Int): String {
        val cal = Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, days) }
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cal.time)
    }
}
