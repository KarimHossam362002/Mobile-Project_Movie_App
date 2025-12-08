package com.example.movieapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.movieapp.data.*
import com.example.movieapp.utils.*
import kotlinx.coroutines.launch

class RegisterViewModel(private val userDao: UserDao) : ViewModel() {

    fun registerUser(
        username: String,
        email: String,
        password: String,
        confirmPassword: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (username.isBlank() || email.isBlank() || password.isBlank()) {
            onError("All fields are required")
            return
        }

        if (!Validation.isValidEmail(email)) {
            onError("Invalid email format")
            return
        }

        if (!Validation.isValidPassword(password)) {
            onError("Password must be at least 6 characters")
            return
        }

        if (password != confirmPassword) {
            onError("Passwords do not match")
            return
        }

        val hashedPassword = Hashing.hashPassword(password)

        val user = User(
            username = username,
            email = email,
            password = hashedPassword
        )

        viewModelScope.launch {
            userDao.insertUser(user)
            onSuccess()
        }
    }
}

