package com.example.uiprototypebeta

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UserDashboardActivity : BaseDrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentLayout(R.layout.content_user)
        setToolbarTitle("My Bookings")
        setCheckedDrawerItem(R.id.m_user)
        showLogoutOption(true)
        updateUserFooterLabel()

        val tvGreeting: TextView = findViewById(R.id.tvGreeting)
        val tvDate: TextView = findViewById(R.id.tvDate)
        val btnUpcoming: Button = findViewById(R.id.btnUpcoming)
        val btnPast: Button = findViewById(R.id.btnPast)
        val upcomingContainer: ScrollView = findViewById(R.id.upcomingContainer)
        val pastContainer: LinearLayout = findViewById(R.id.pastContainer)

        val name = if (UserSession.displayName.isNotBlank()) UserSession.displayName else "Guest"
        tvGreeting.text = "Hi, $name"

        val currentDate = SimpleDateFormat("EEEE, MMM dd yyyy", Locale.getDefault()).format(Date())
        tvDate.text = "Today: $currentDate"

        fun setActiveButton(active: Button, inactive: Button) {
            active.setBackgroundColor(android.graphics.Color.parseColor("#1A132F"))
            active.setTextColor(android.graphics.Color.WHITE)
            inactive.setBackgroundColor(android.graphics.Color.parseColor("#F2F2F2"))
            inactive.setTextColor(android.graphics.Color.BLACK)
        }

        btnUpcoming.setOnClickListener {
            upcomingContainer.visibility = View.VISIBLE
            pastContainer.visibility = View.GONE
            setActiveButton(btnUpcoming, btnPast)
        }

        btnPast.setOnClickListener {
            upcomingContainer.visibility = View.GONE
            pastContainer.visibility = View.VISIBLE
            setActiveButton(btnPast, btnUpcoming)
        }

        setActiveButton(btnUpcoming, btnPast)
    }
}
