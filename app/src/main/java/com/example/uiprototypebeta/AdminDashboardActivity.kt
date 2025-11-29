package com.example.uiprototypebeta

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*

class AdminDashboardActivity : BaseDrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        
        setContentLayout(R.layout.content_admin)
        setToolbarTitle("Admin Dashboard")
        setCheckedDrawerItem(R.id.m_admin)
        showLogoutOption(true)

        
        val tvDate: TextView = findViewById(R.id.tvDate)
        val btnList: Button = findViewById(R.id.btnList)
        val btnCalendar: Button = findViewById(R.id.btnCalendar)
        val btnAnalytics: Button = findViewById(R.id.btnAnalytics)
        val listContainer: ScrollView = findViewById(R.id.listContainer)
        val calendarContainer: LinearLayout = findViewById(R.id.calendarContainer)
        val analyticsContainer: ScrollView = findViewById(R.id.analyticsContainer)

        
        val tvMonthBookings: TextView = findViewById(R.id.tvMonthBookings)
        val tvMonthRevenue: TextView = findViewById(R.id.tvMonthRevenue)
        val tvMonthAvgTicket: TextView = findViewById(R.id.tvMonthAvgTicket)
        val tvYearBookings: TextView = findViewById(R.id.tvYearBookings)
        val tvYearRevenue: TextView = findViewById(R.id.tvYearRevenue)
        val tvNoShowRate: TextView = findViewById(R.id.tvNoShowRate)
        val tvTopService1: TextView = findViewById(R.id.tvTopService1)
        val tvTopService2: TextView = findViewById(R.id.tvTopService2)
        val tvTopService3: TextView = findViewById(R.id.tvTopService3)
        val pbTopService1: ProgressBar = findViewById(R.id.pbTopService1)
        val pbTopService2: ProgressBar = findViewById(R.id.pbTopService2)
        val pbTopService3: ProgressBar = findViewById(R.id.pbTopService3)
        val tvBusiestDay: TextView = findViewById(R.id.tvBusiestDay)
        val tvBusiestHour: TextView = findViewById(R.id.tvBusiestHour)
        val tvNewClients: TextView = findViewById(R.id.tvNewClients)
        val tvReturningClients: TextView = findViewById(R.id.tvReturningClients)
        val tvRetentionRate: TextView = findViewById(R.id.tvRetentionRate)
        val tvCancelled: TextView = findViewById(R.id.tvCancelled)
        val tvNoShows: TextView = findViewById(R.id.tvNoShows)
        val tvInsight1: TextView = findViewById(R.id.tvInsight1)
        val tvInsight2: TextView = findViewById(R.id.tvInsight2)

        
        val currentDate = SimpleDateFormat("EEEE, MMM dd yyyy", Locale.getDefault()).format(Date())
        tvDate.text = "Today: $currentDate"

        
        listContainer.visibility = View.VISIBLE
        calendarContainer.visibility = View.GONE
        analyticsContainer.visibility = View.GONE

        
        fun setActiveButton(active: Button, vararg inactive: Button) {
            active.setBackgroundColor(Color.parseColor("#1A132F"))  // dark purple
            active.setTextColor(Color.WHITE)
            inactive.forEach {
                it.setBackgroundColor(Color.parseColor("#F2F2F2"))  // light gray
                it.setTextColor(Color.BLACK)
            }
        }

        
        btnList.setOnClickListener {
            listContainer.visibility = View.VISIBLE
            calendarContainer.visibility = View.GONE
            analyticsContainer.visibility = View.GONE
            setActiveButton(btnList, btnCalendar, btnAnalytics)
        }

        btnCalendar.setOnClickListener {
            listContainer.visibility = View.GONE
            calendarContainer.visibility = View.VISIBLE
            analyticsContainer.visibility = View.GONE
            setActiveButton(btnCalendar, btnList, btnAnalytics)
        }

        btnAnalytics.setOnClickListener {
            listContainer.visibility = View.GONE
            calendarContainer.visibility = View.GONE
            analyticsContainer.visibility = View.VISIBLE
            setActiveButton(btnAnalytics, btnList, btnCalendar)
            populateAnalytics(
                tvMonthBookings,
                tvMonthRevenue,
                tvMonthAvgTicket,
                tvYearBookings,
                tvYearRevenue,
                tvNoShowRate,
                tvTopService1,
                tvTopService2,
                tvTopService3,
                pbTopService1,
                pbTopService2,
                pbTopService3,
                tvBusiestDay,
                tvBusiestHour,
                tvNewClients,
                tvReturningClients,
                tvRetentionRate,
                tvCancelled,
                tvNoShows,
                tvInsight1,
                tvInsight2
            )
        }

        
        setActiveButton(btnList, btnCalendar, btnAnalytics)
    }

    private fun populateAnalytics(
        tvMonthBookings: TextView,
        tvMonthRevenue: TextView,
        tvMonthAvgTicket: TextView,
        tvYearBookings: TextView,
        tvYearRevenue: TextView,
        tvNoShowRate: TextView,
        tvTopService1: TextView,
        tvTopService2: TextView,
        tvTopService3: TextView,
        pbTopService1: ProgressBar,
        pbTopService2: ProgressBar,
        pbTopService3: ProgressBar,
        tvBusiestDay: TextView,
        tvBusiestHour: TextView,
        tvNewClients: TextView,
        tvReturningClients: TextView,
        tvRetentionRate: TextView,
        tvCancelled: TextView,
        tvNoShows: TextView,
        tvInsight1: TextView,
        tvInsight2: TextView
    ) {
        // --- MOCK ANALYTICS DATA START ---
        // These values are hardcoded for testing the Analytics tab UI. Remove or replace this block when wiring real data.
        val mockMonthBookings = 42
        val mockYearBookings = 268
        val mockMonthRevenue = 1725.0
        val mockYearRevenue = 10840.0
        val mockNoShowRate = 0.07
        val mockCancelled = 5
        val mockNoShows = 3
        val mockNewClients = 12
        val mockReturningClients = 30
        val topServices = listOf(
            Triple("Haircut & Beard", 16, 38),
            Triple("Haircut", 15, 36),
            Triple("Beard", 8, 19)
        )
        val busiestDay = "Thursday"
        val busiestHour = "5:00 PM \u2013 6:00 PM"
        val insight1 = "Thursdays late afternoon are the busiest slot; consider extending hours."
        val insight2 = "Haircut & Beard drives 42% of bookings; bundle promos could lift revenue."
        // --- MOCK ANALYTICS DATA END ---

        val avgTicket = if (mockMonthBookings > 0) mockMonthRevenue / mockMonthBookings else 0.0
        val retentionRate = if (mockNewClients + mockReturningClients > 0) {
            mockReturningClients.toDouble() / (mockNewClients + mockReturningClients)
        } else 0.0

        tvMonthBookings.text = "$mockMonthBookings bookings"
        tvMonthRevenue.text = String.format(Locale.getDefault(), "$%.0f revenue", mockMonthRevenue)
        tvMonthAvgTicket.text = String.format(Locale.getDefault(), "Avg ticket $%.0f", avgTicket)

        tvYearBookings.text = "$mockYearBookings bookings"
        tvYearRevenue.text = String.format(Locale.getDefault(), "$%.0f revenue", mockYearRevenue)
        tvNoShowRate.text = String.format(Locale.getDefault(), "No-show rate %.0f%%", mockNoShowRate * 100)

        tvTopService1.text = "1) ${topServices[0].first} \u2022 ${topServices[0].second} bookings (${topServices[0].third}%)"
        tvTopService2.text = "2) ${topServices[1].first} \u2022 ${topServices[1].second} bookings (${topServices[1].third}%)"
        tvTopService3.text = "3) ${topServices[2].first} \u2022 ${topServices[2].second} bookings (${topServices[2].third}%)"

        pbTopService1.progress = topServices[0].third
        pbTopService2.progress = topServices[1].third
        pbTopService3.progress = topServices[2].third

        tvBusiestDay.text = "Busiest day: $busiestDay"
        tvBusiestHour.text = "Busiest hour: $busiestHour"

        tvNewClients.text = "New: $mockNewClients"
        tvReturningClients.text = "Returning: $mockReturningClients"
        tvRetentionRate.text = String.format(Locale.getDefault(), "Retention: %.0f%%", retentionRate * 100)

        tvCancelled.text = "Cancelled: $mockCancelled"
        tvNoShows.text = "No-shows: $mockNoShows"

        tvInsight1.text = "\u2022 $insight1"
        tvInsight2.text = "\u2022 $insight2"
    }
}
