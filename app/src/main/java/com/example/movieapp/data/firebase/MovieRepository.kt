package com.example.movieapp.data.firebase

import android.util.Log
import com.google.firebase.database.FirebaseDatabase

class MovieRepository {

    private val db = FirebaseDatabase.getInstance().reference.child("movies")

    fun getMovies(onResult: (List<MovieFB>) -> Unit) {
        db.get().addOnSuccessListener { snapshot ->
            val list = mutableListOf<MovieFB>()
            if (snapshot.exists()) {
                Log.d("MovieRepository", "Snapshot exists, children count: ${snapshot.childrenCount}")
                snapshot.children.forEach { child ->
                    try {
                        val movie = child.getValue(MovieFB::class.java)
                        if (movie != null) {
                            list.add(movie)
                            Log.d("MovieRepository", "Loaded movie: ${movie.title}")
                        } else {
                            Log.w("MovieRepository", "Movie is null for child: ${child.key}")
                        }
                    } catch (e: Exception) {
                        Log.e("MovieRepository", "Error deserializing movie: ${e.message}", e)
                    }
                }
            } else {
                Log.w("MovieRepository", "Snapshot does not exist at 'movies' path")
            }
            Log.d("MovieRepository", "Total movies loaded: ${list.size}")
            onResult(list)
        }.addOnFailureListener { error ->
            Log.e("MovieRepository", "Failed to load movies: ${error.message}", error)
            onResult(emptyList())
        }
    }
}

