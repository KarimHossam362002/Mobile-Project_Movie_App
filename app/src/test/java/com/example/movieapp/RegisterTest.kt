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

class RegisterTest {

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
    fun register_success_returnsTrue() {
        val email = "new@test.com"
        val password = "123456"

        `when`(
            firebaseAuth.createUserWithEmailAndPassword(email, password)
        ).thenReturn(Tasks.forResult(authResult))

        var result = false

        authRepository.register(email, password) {
            result = it
        }

        assertTrue(result)
    }

    @Test
    fun register_failure_returnsFalse() {
        val email = "bad@test.com"
        val password = "123"

        `when`(
            firebaseAuth.createUserWithEmailAndPassword(email, password)
        ).thenReturn(
            Tasks.forException(Exception("Register failed"))
        )

        var result = true

        authRepository.register(email, password) {
            result = it
        }

        assertFalse(result)
    }
}
