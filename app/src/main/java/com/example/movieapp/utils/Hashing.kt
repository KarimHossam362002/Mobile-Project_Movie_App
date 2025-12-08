package com.example.movieapp.utils

import java.security.*

object Hashing {

    fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(bytes)

        return hash.joinToString("") { "%02x".format(it) }
    }
}
