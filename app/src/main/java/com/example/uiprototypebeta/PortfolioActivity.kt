package com.example.uiprototypebeta

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

//Name: Gustavo Miranda
//Student ID: 101488574

class PortfolioActivity : BaseDrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.content_portfolio)
        setToolbarTitle("Portfolio")
        setCheckedDrawerItem(R.id.m_portfolio)

        val toggleButton = findViewById<Button>(R.id.btn_toggle_testimonials)
        val testimonialsLayout = findViewById<LinearLayout>(R.id.layout_testimonials)

        if (toggleButton != null && testimonialsLayout != null) {
            toggleButton.setOnClickListener {
                if (testimonialsLayout.visibility == View.GONE) {
                    testimonialsLayout.visibility = View.VISIBLE
                    toggleButton.text = "Hide Testimonials"
                } else {
                    testimonialsLayout.visibility = View.GONE
                    toggleButton.text = "View Testimonials"
                }
            }
        }
    }
}


