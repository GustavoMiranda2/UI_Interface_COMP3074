package com.example.uiprototypebeta

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
// Import statement for view binding - THIS WAS THE BROKEN LINE
import com.example.uiprototypebeta.databinding.ActivityAdminLoginBinding

// Class definition starts here, on a NEW LINE
class AdminLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // This is for the back/close button
        binding.toolbar.setNavigationOnClickListener { finish() }

        // --- LOGIN BUTTON LOGIC ---
        binding.btnAdminLogin.setOnClickListener {
            // Get text using the CORRECT IDs from your XML file
            val username = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            // Hardcoded check for "admin" and "123"
            if (username == "admin" && password == "123") {
                // If correct, show success and navigate
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                AdminSession.isLoggedIn = true
                val intent = Intent(this, AdminDashboardActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // If wrong, show an error
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_LONG).show()
            }
        }

        // This button logic is correct
        binding.btnSwitchToUser.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
