package ca.gbc.comp3074.hairstylistbooking2

import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class BookActivity : BaseActivity(R.layout.activity_book) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<Button>(R.id.btnContinue).setOnClickListener {
            Toast.makeText(this, "Next step (placeholder)", Toast.LENGTH_SHORT).show()
        }
    }
}