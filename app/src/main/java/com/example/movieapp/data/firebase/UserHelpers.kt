package com.example.movieapp.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object UserHelpers {
    fun getCurrentUsername(
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
            ?: return onError("Not authenticated")

        FirebaseDatabase.getInstance()
            .reference
            .child("users")
            .child(uid)
            .child("username")
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    onSuccess(snapshot.value.toString())
                } else {
                    onError("Username not found")
                }
            }
            .addOnFailureListener { e ->
                onError(e.message ?: "Failed to load username")
            }
    }

    fun getCurrentUserEmail(
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
            ?: return onError("Not authenticated")

        FirebaseDatabase.getInstance()
            .reference
            .child("users")
            .child(uid)
            .child("email")
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    onSuccess(snapshot.value.toString())
                } else {
                    onError("Email not found")
                }
            }
            .addOnFailureListener { e ->
                onError(e.message ?: "Failed to load email")
            }
    }
}