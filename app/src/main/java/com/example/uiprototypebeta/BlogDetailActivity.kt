package com.example.uiprototypebeta

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class BlogDetailActivity : BaseDrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.content_blog_detail)
        setToolbarTitle("Hairstylist Booking") // top toolbar

        val post = intent.getSerializableExtra("post") as? Post
        if (post != null) {
            findViewById<TextView>(R.id.tvTitle).text = post.title
            findViewById<TextView>(R.id.tvMeta).text =
                "${post.date} • ${post.readTime} • By ${post.author}"
            findViewById<TextView>(R.id.tvBody).text = post.body

            val tagsContainer = findViewById<LinearLayout>(R.id.llTags)
            tagsContainer.removeAllViews()
            post.tags.forEach { tag ->
                val chip = TextView(this).apply {
                    text = tag
                    setPadding(16, 8, 16, 8)
                    setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, 12f)
                    setBackgroundResource(R.drawable.bg_chip)
                    val lp = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    lp.setMargins(0, 0, 12, 0)
                    layoutParams = lp
                }
                tagsContainer.addView(chip)
            }
        }

        findViewById<LinearLayout>(R.id.btnBack).setOnClickListener { finish() }
    }
}
