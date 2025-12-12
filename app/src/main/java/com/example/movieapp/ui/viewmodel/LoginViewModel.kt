package com.example.movieapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movieapp.data.firebase.UserFB
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    fun login(
        username: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        database.child("users")
            .get()
            .addOnSuccessListener { snapshot ->

                var matchedEmail: String? = null

                for (userSnapshot in snapshot.children) {
                    val dbUsername =
                        userSnapshot.child("username").getValue(String::class.java)

                    if (dbUsername == username) {
                        matchedEmail =
                            userSnapshot.child("email").getValue(String::class.java)
                        break
                    }
                }

                if (matchedEmail == null) {
                    onResult(false, "Username not found")
                    return@addOnSuccessListener
                }

                auth.signInWithEmailAndPassword(matchedEmail, password)
                    .addOnSuccessListener {
                        onResult(true, null)
                    }
                    .addOnFailureListener { e ->
                        onResult(false, e.message)
                    }
            }
            .addOnFailureListener { e ->
                onResult(false, e.message)
            }
    }
}



