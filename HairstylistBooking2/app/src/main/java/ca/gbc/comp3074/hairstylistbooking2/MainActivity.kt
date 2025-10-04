package ca.gbc.comp3074.hairstylistbooking2

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity : BaseActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<Button>(R.id.btnBookNow).setOnClickListener {
            startActivity(Intent(this, BookActivity::class.java))
        }
    }
}