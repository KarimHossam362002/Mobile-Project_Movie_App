package com.example.movieapp

import com.example.movieapp.data.firebase.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class LoginTest {

    @Mock
    lateinit var firebaseAuth: FirebaseAuth

    @Mock
    lateinit var authTask: Task<AuthResult>

    private lateinit var authRepository: AuthRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        authRepository = AuthRepository(firebaseAuth)
    }

    @Test
    fun login_success_returnsTrue() {
        val email = "karim@gmail.com"
        val password = "123456"

        `when`(
            firebaseAuth.signInWithEmailAndPassword(email, password)
        ).thenReturn(authTask)

        `when`(authTask.addOnSuccessListener(any())).thenAnswer {
            val listener = it.arguments[0] as OnSuccessListener<AuthResult>
            listener.onSuccess(mock(AuthResult::class.java))
            authTask
        }

        var result = false

        authRepository.login(email, password) {
            result = it
        }

        assertTrue(result)
    }
}
