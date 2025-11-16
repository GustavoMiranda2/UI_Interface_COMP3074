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

        // Inflate your layout inside the drawer
        setContentLayout(R.layout.content_admin)
        setToolbarTitle("Admin Dashboard")
        setCheckedDrawerItem(R.id.m_admin)
        showLogoutOption(true)
        showLoginOption(false)

        // --- Find views ---
        val tvDate: TextView = findViewById(R.id.tvDate)
        val btnList: Button = findViewById(R.id.btnList)
        val btnCalendar: Button = findViewById(R.id.btnCalendar)
        val listContainer: ScrollView = findViewById(R.id.listContainer)
        val calendarContainer: LinearLayout = findViewById(R.id.calendarContainer)

        // --- Display current date ---
        val currentDate = SimpleDateFormat("EEEE, MMM dd yyyy", Locale.getDefault()).format(Date())
        tvDate.text = "Today: $currentDate"

        // --- Default: show List, hide Calendar ---
        listContainer.visibility = View.VISIBLE
        calendarContainer.visibility = View.GONE

        // --- Helper to style active button ---
        fun setActiveButton(active: Button, inactive: Button) {
            active.setBackgroundColor(Color.parseColor("#1A132F"))  // dark purple
            active.setTextColor(Color.WHITE)
            inactive.setBackgroundColor(Color.parseColor("#F2F2F2"))  // light gray
            inactive.setTextColor(Color.BLACK)
        }

        // --- Button listeners ---
        btnList.setOnClickListener {
            listContainer.visibility = View.VISIBLE
            calendarContainer.visibility = View.GONE
            setActiveButton(btnList, btnCalendar)
        }

        btnCalendar.setOnClickListener {
            listContainer.visibility = View.GONE
            calendarContainer.visibility = View.VISIBLE
            setActiveButton(btnCalendar, btnList)
        }

        // -- Initialize styles (List active by default) ---
        setActiveButton(btnList, btnCalendar)
    }
}
