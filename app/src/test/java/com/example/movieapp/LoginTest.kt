package com.example.movieapp

import com.example.movieapp.data.firebase.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Tasks
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.junit.Assert.*

class LoginTest {

    @Mock
    lateinit var firebaseAuth: FirebaseAuth

    @Mock
    lateinit var authResult: AuthResult

    private lateinit var authRepository: AuthRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        authRepository = AuthRepository(firebaseAuth)
    }

    @Test
    fun login_success_returnsTrue() {
        val email = "test@test.com"
        val password = "123456"

        `when`(
            firebaseAuth.signInWithEmailAndPassword(email, password)
        ).thenReturn(Tasks.forResult(authResult))

        var result = false

        authRepository.login(email, password) {
            result = it
        }

        assertTrue(result)
    }

    @Test
    fun login_failure_returnsFalse() {
        val email = "test@test.com"
        val password = "wrongpass"

        `when`(
            firebaseAuth.signInWithEmailAndPassword(email, password)
        ).thenReturn(
            Tasks.forException(Exception("Login failed"))
        )

        var result = true

        authRepository.login(email, password) {
            result = it
        }

        assertFalse(result)
    }
}
