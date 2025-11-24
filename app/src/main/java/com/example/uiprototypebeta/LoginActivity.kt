package com.example.uiprototypebeta

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uiprototypebeta.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val demoEmail = "user@example.com"
    private val demoPassword = "password123"

    private lateinit var b: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnSignIn.setOnClickListener {
            val email = b.etEmail.text?.toString()?.trim().orEmpty()
            val password = b.etPassword.text?.toString()?.trim().orEmpty()

            val isValid = email.equals(demoEmail, ignoreCase = true) && password == demoPassword

            if (isValid) {
                UserSession.isLoggedIn = true
                UserSession.displayName = email
                Toast.makeText(this, "Signed in as $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, UserDashboardActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show()
            }
        }

        b.btnGuest.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        b.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
