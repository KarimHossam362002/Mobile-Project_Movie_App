package com.example.movieapp.data.firebase

import com.google.firebase.Firebase
import com.google.firebase.database.database

class MovieRepository {

    private val db = Firebase.database.reference

    fun getMovies(onResult: (List<Movie>) -> Unit) {
        db.get().addOnSuccessListener { snapshot ->
            val list = mutableListOf<Movie>()
            snapshot.children.forEach { child ->
                val movie = child.getValue(Movie::class.java)
                if (movie != null) list.add(movie)
            }
            onResult(list)
        }.addOnFailureListener {
            onResult(emptyList()) // in case of error
        }
    }
}

