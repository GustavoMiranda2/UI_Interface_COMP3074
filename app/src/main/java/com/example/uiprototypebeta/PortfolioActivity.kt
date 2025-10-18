package com.example.uiprototypebeta

import android.os.Bundle

class PortfolioActivity : BaseDrawerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.content_portfolio)
        setToolbarTitle("Portfolio")
        setCheckedDrawerItem(R.id.m_portfolio)
    }
}


