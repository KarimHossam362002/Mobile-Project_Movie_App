package com.example.movieapp.data.firebase

import com.google.firebase.Firebase
import com.google.firebase.database.database

class MovieRepository {

    private val db = Firebase.database.reference.child("movies")

    fun getMovies(onResult: (List<MovieFB>) -> Unit) {
        db.get().addOnSuccessListener { snapshot ->
            val list = mutableListOf<MovieFB>()
            snapshot.children.forEach { child ->
                val movie = child.getValue(MovieFB::class.java)
                if (movie != null) list.add(movie)
            }
            onResult(list)
        }.addOnFailureListener {
            onResult(emptyList()) // in case of error
        }
    }
}

