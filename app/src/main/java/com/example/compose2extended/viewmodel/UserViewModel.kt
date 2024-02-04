package com.example.compose2extended.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.compose2extended.repository.UserRepository
import com.example.compose2extended.database.AppDatabase
import com.example.compose2extended.database.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = AppDatabase.getDatabase(application).userDao()
    private val userRepository: UserRepository
    private var currentUser: User? = null

    init {
        userRepository = UserRepository(userDao,this)
    }

    fun addUser(
        firstname: String,
        lastname: String,
        email: String,
        address: String,
        dob: String,
        password: String,

        ) {
        userRepository.addUser(firstname, lastname, email, address, dob, password)
    }

    suspend fun checkCredentials(email: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            userDao.getUserByEmailAndPassword(email, password) != null
        }
    }
    fun setUser(user: User) {
        currentUser = user
    }

    fun getCurrentUser(): User? {
        return currentUser
    }
}



    /*private val _productsState = MutableStateFlow<List<ProductX>>(emptyList())
    val productsState: MutableStateFlow<List<ProductX>> = _productsState
init{
    fetchProducts()
}
    // Fetching data from the API using Retrofit and emit it as a Flow
  private  fun fetchProducts() {
        viewModelScope.launch {
            try {
                RetrofitInstance.productService.getProducts().collect { response ->
                    _productsState.value = response.products
                }
            } catch (e: Exception) {
                // Handle error

            }
        }
    }*/
