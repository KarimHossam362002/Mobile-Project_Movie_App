package com.example.movieapp.data.firebase

import com.google.firebase.auth.FirebaseAuth

class AuthRepository(
    private val firebaseAuth: FirebaseAuth
) {

    fun login(
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                onResult(it.isSuccessful)
            }
    }

    fun register(
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                onResult(it.isSuccessful)
            }
    }
}