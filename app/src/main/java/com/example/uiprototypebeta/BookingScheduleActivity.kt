package com.example.uiprototypebeta

import android.os.Bundle
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uiprototypebeta.databinding.ActivityBookingScheduleBinding
import com.google.android.material.chip.Chip
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BookingScheduleActivity : AppCompatActivity() {

    private lateinit var b: ActivityBookingScheduleBinding

    private var selectedDateKey: String? = null
    private var selectedSlot: String? = null
    private var availableSlots: List<String> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityBookingScheduleBinding.inflate(layoutInflater)
        setContentView(b.root)

        val serviceId = intent.getStringExtra("service_id") ?: return finish()
        val serviceTitle = intent.getStringExtra("service_title") ?: ""
        val servicePrice = intent.getIntExtra("service_price", 0)
        val serviceDuration = intent.getIntExtra("service_duration", 0)
        val addBrows = intent.getBooleanExtra("add_brows", false)
        val addBrowsPrice = intent.getIntExtra("add_brows_price", 0)
        val addBrowsDuration = intent.getIntExtra("add_brows_duration", 0)

        b.toolbar.setNavigationOnClickListener { finish() }
        b.tvServiceSummary.text = buildSummary(serviceTitle, servicePrice, addBrows, addBrowsPrice)

        val today = Calendar.getInstance()
        setSelectedDate(today)
        loadSlotsForDate(selectedDateKey!!)

        b.calendarBooking.setOnDateChangeListener { _: CalendarView, year: Int, month: Int, dayOfMonth: Int ->
            val cal = Calendar.getInstance().apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            setSelectedDate(cal)
            loadSlotsForDate(selectedDateKey!!)
        }

        b.btnConfirmSlot.setOnClickListener {
            val slot = selectedSlot
            val date = selectedDateKey
            if (slot != null && date != null) {
                val totalPrice = servicePrice + if (addBrows) addBrowsPrice else 0
                val summary = StringBuilder().apply {
                    append(serviceTitle)
                    if (addBrows) append(" + Brows")
                    append("\nDate: ${b.tvSelectedDate.text}")
                    append("\nTime: $slot")
                    append("\nPrice: $$totalPrice")
                }.toString()

                Toast.makeText(this, "Booked!", Toast.LENGTH_SHORT).show()
                // --- MOCK BOOKING DATA START ---
                holdSlot(date, slot, serviceDuration, if (addBrows) addBrowsDuration else 0)
                // --- MOCK BOOKING DATA END ---

                finish()
            } else {
                Toast.makeText(this, "Select a date and time", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setSelectedDate(cal: Calendar) {
        selectedDateKey = dateKey(cal)
        b.tvSelectedDate.text = SimpleDateFormat("EEE, MMM d", Locale.getDefault()).format(cal.time)
    }

    private fun loadSlotsForDate(dateKey: String) {
        val dailyBusy = BookingMocks.busySlots[dateKey].orEmpty()
        val slots = BookingMocks.generateSlots().filterNot { dailyBusy.contains(it) }
        availableSlots = slots
        selectedSlot = null
        renderSlots()
    }

    private fun renderSlots() {
        b.chipGroupSlots.removeAllViews()
        val selected = selectedSlot
        val chips = mutableListOf<Chip>()

        availableSlots.forEach { time ->
            val chip = Chip(this).apply {
                text = time
                isCheckable = true
                isClickable = true
                isChecked = (time == selected)
                isEnabled = true
                setOnClickListener {
                    selectedSlot = time
                    renderSlots()
                }
            }
            b.chipGroupSlots.addView(chip)
            chips.add(chip)
        }

        if (selected != null) {
            val selectedIndex = availableSlots.indexOf(selected)
            if (selectedIndex >= 0) {
                val blockCount = BookingMocks.blocksToHold(
                    intent.getIntExtra("service_duration", 0),
                    if (intent.getBooleanExtra("add_brows", false)) intent.getIntExtra("add_brows_duration", 0) else 0
                )
                val end = (selectedIndex + blockCount).coerceAtMost(availableSlots.lastIndex)
                for (i in selectedIndex + 1..end) {
                    val chip = chips[i]
                    chip.isEnabled = false
                    chip.alpha = 0.35f
                }
            }
        }
        b.btnConfirmSlot.isEnabled = selectedSlot != null
    }

    private fun holdSlot(dateKey: String, slot: String, serviceDuration: Int, addOnDuration: Int) {
        val blockCount = BookingMocks.blocksToHold(serviceDuration, addOnDuration)
        val idx = availableSlots.indexOf(slot)
        val toBlock = mutableListOf(slot)
        for (i in 1..blockCount) {
            val next = availableSlots.getOrNull(idx + i)
            if (next != null) toBlock.add(next)
        }
        BookingMocks.appendBusy(dateKey, toBlock)
    }

    private fun buildSummary(
        serviceTitle: String,
        servicePrice: Int,
        addBrows: Boolean,
        addBrowsPrice: Int
    ): String {
        val total = servicePrice + if (addBrows) addBrowsPrice else 0
        return if (addBrows) {
            "$serviceTitle + Brows · $$total"
        } else {
            "$serviceTitle · $$total"
        }
    }

    private fun dateKey(cal: Calendar): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cal.time)
    }
}
