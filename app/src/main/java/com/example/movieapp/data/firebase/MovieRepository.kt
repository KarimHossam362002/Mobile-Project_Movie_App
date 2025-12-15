package com.example.movieapp.data.firebase

import MovieFB
import com.google.firebase.database.FirebaseDatabase

class MovieRepository {

    private val db = FirebaseDatabase.getInstance().reference.child("movies")

    fun getMovies(onResult: (List<MovieFB>) -> Unit) {
        db.get().addOnSuccessListener { snapshot ->
            val list = mutableListOf<MovieFB>()
            if (snapshot.exists()) {
                snapshot.children.forEach { child ->
                    try {
                        val movie = child.getValue(MovieFB::class.java)
                        if (movie != null) list.add(movie)
                    } catch (e: Exception) {
                        // Skip movies that can't be deserialized
                    }
                }
            }
            onResult(list)
        }.addOnFailureListener {
            onResult(emptyList()) // in case of error
        }
    }
}

