package com.example.uiprototypebeta

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uiprototypebeta.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var b: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(b.root)

        b.btnSignIn.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
            finish()
        }

        b.btnGuest.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        b.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}
