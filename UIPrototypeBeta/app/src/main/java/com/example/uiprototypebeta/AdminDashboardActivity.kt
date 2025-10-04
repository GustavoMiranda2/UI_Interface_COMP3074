package com.example.uiprototypebeta

import android.os.Bundle

class AdminDashboardActivity : BaseDrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.content_admin)
    }
}

