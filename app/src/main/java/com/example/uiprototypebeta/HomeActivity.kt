package com.example.uiprototypebeta

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class HomeActivity : BaseDrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.content_home)
        val btnBookNow: Button? = findViewById(R.id.btnBookNow)
        btnBookNow?.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }
    }
}


