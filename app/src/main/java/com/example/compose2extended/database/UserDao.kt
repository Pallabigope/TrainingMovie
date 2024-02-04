package com.example.compose2extended.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao{
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)
    @Query("SELECT * FROM user WHERE email= :email AND password= :password")
    suspend fun getUserByEmailAndPassword(email:String,password:String):User?
    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>
    @Query("SELECT * FROM user")
    fun getUserFlow():LiveData<List<User>>
}