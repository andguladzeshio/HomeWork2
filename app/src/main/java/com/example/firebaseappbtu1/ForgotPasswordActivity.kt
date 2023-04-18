package com.example.firebaseappbtu1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var sendEmailButton: Button

    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        initViews()
        setListeners()
    }

    private fun initViews() {
        emailEditText = findViewById(R.id.resetEmailEditText)
        sendEmailButton = findViewById(R.id.sendResetLinkButton)
    }

    private fun setListeners() {
        sendEmailButton.setOnClickListener {
            val email = emailEditText.text.toString()

            if (email.isNotBlank() && !email.contains(" ")) {

                auth.sendPasswordResetEmail(email).addOnSuccessListener {
                    Toast.makeText(this, "Reset email sent", Toast.LENGTH_SHORT).show()
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

            } else Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show()
        }
    }
}