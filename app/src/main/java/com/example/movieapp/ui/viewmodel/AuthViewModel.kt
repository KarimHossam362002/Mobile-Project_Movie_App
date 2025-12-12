package com.example.movieapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movieapp.data.firebase.UserFB
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val database =
        FirebaseDatabase.getInstance().reference.child("users")

    private val _user = MutableStateFlow<UserFB?>(null)
    val user: StateFlow<UserFB?> = _user

    fun loadCurrentUser() {
        val uid = auth.currentUser?.uid ?: return

        database.child(uid)
            .get()
            .addOnSuccessListener { snapshot ->
                _user.value = snapshot.getValue(UserFB::class.java)
            }
    }

    fun logout() {
        auth.signOut()
        _user.value = null
    }
}
