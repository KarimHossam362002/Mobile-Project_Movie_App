package com.example.movieapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.*
import kotlinx.coroutines.launch

class LoginViewModel(private val userDao: UserDao) : ViewModel() {

    fun login(
        username: String,
        password: String,
        onSuccess: (User) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val user = userDao.login(username, password)
                Log.d("LoginViewModel", "User: ${user?.username}")
                if (user != null) {
                    onSuccess(user)
                } else {
                    Log.d("LoginViewModel", "Invalid username or password")
                    onError("Invalid username or password")
                }
            } catch (e: Exception) {
                onError("Login failed: ${e.message}")
            }

        }
    }
}
