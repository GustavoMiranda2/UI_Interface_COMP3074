package com.example.uiprototypebeta

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout

class BlogActivity : BaseDrawerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.content_blog)
        setToolbarTitle("Beauty Blog")
        setCheckedDrawerItem(R.id.m_blog)

        // Dummy list of 3 posts (future: replace with real data)
        val posts = listOf(
            Post(
                id = 1,
                title = "5 Hair Care Tips for Summer",
                snippet = "Keep your hair healthy and vibrant during the hot summer months…",
                body = """
                    Summer can be tough on your hair. The combination of sun, heat, humidity, and pool chemicals can leave your hair dry, damaged, and dull. Here are our top 5 tips to keep your hair looking its best all summer long:
                    
                    1. Protect from UV Rays: Just like your skin, your hair needs protection from harmful UV rays...
                    2. Deep Condition Regularly: Summer heat can strip moisture from your hair...
                    3. Limit Heat Styling: Give your hair a break from blow dryers and hot tools...
                    4. Rinse After Swimming: Chlorine and salt water can be damaging to your hair...
                    5. Stay Hydrated: What you put in your body affects your hair health...
                    
                    Follow these tips and your hair will thank you!
                """.trimIndent(),
                tags = listOf("Hair Care", "Summer", "Tips"),
                date = "9/14/2024",
                author = "Sarah Johnson",
                readTime = "5 min read"
            ),
            Post(
                id = 2,
                title = "The Latest Hair Color Trends for Fall",
                snippet = "Discover the hottest hair color trends this fall season…",
                body = "A deeper dive into warm caramels, bold burgundies, and subtle copper blends...",
                tags = listOf("Hair Color", "Trends", "Fall"),
                date = "9/9/2024",
                author = "Team",
                readTime = "4 min read"
            ),
            Post(
                id = 3,
                title = "How to Choose the Perfect Haircut for Your Face Shape",
                snippet = "Learn how to select a haircut that flatters your unique face shape…",
                body = "Identify your face shape and match it with balancing lines and layers...",
                tags = listOf("Haircuts", "Face Shape", "Styling"),
                date = "9/4/2024",
                author = "Team",
                readTime = "6 min read"
            )
        )

        // Hook the 3 cards
        findViewById<LinearLayout>(R.id.cardPost1).setOnClickListener { openDetail(posts[0]) }
        findViewById<LinearLayout>(R.id.cardPost2).setOnClickListener { openDetail(posts[1]) }
        findViewById<LinearLayout>(R.id.cardPost3).setOnClickListener { openDetail(posts[2]) }
    }

    private fun openDetail(post: Post) {
        val i = Intent(this, BlogDetailActivity::class.java)
        i.putExtra("post", post)
        startActivity(i)
    }
}


