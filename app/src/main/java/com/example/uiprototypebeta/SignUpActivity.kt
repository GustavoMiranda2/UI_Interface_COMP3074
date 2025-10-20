package com.example.uiprototypebeta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uiprototypebeta.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreateAccount.setOnClickListener {
            finish() // Placeholder: would submit registration in a future iteration
        }

        binding.toolbar.setNavigationOnClickListener { finish() }
    }
}
