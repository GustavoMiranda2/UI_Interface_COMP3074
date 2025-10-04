package com.example.uiprototypebeta

import android.os.Bundle

class BookingActivity : BaseDrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.content_booking)
        setToolbarTitle("Book Appointment")
        setCheckedDrawerItem(R.id.m_book)
    }
}


