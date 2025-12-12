package com.example.movieapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference.child("users")

    fun registerUser(
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            onError("All fields are required")
            return
        }

        if (password != confirmPassword) {
            onError("Passwords do not match")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid ?: return@addOnSuccessListener onError("Registration failed")

                val userData = mapOf(
                    "username" to username,
                    "email" to email
                )

                database.child(uid)
                    .setValue(userData)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { e -> onError(e.message ?: "Database error") }
            }
            .addOnFailureListener { e ->
                onError(e.message ?: "Authentication error")
            }
    }
}
