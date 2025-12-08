package com.example.movieapp.data

import androidx.room.*

@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("""
    SELECT * FROM users 
    WHERE (username = :login OR email = :login) 
    AND password = :password
    LIMIT 1
""")
    suspend fun login(login: String, password: String): User?



    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>


}