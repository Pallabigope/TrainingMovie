package com.example.compose2extended.repository

import com.example.compose2extended.database.User
import com.example.compose2extended.database.UserDao
import com.example.compose2extended.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(private val userDao: UserDao,private val userViewModel: UserViewModel) {
    private val coroutineScope= CoroutineScope(Dispatchers.Main)

    fun addUser(firstname:String,lastname:String,email:String,address:String,dob:String,password:String){
        coroutineScope.launch(Dispatchers.IO){
            val user =User(firstname=firstname,lastname= lastname, email = email, address = address,dob = dob,password=password)
            userDao.addUser(user)
            userViewModel.setUser(user)
        }
    }
}