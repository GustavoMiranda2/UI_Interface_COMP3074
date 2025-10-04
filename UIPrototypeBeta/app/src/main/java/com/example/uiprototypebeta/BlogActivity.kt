package com.example.uiprototypebeta

import android.os.Bundle

class BlogActivity : BaseDrawerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.content_blog)
        setToolbarTitle("Beauty Blog")
        setCheckedDrawerItem(R.id.m_blog)
    }
}

