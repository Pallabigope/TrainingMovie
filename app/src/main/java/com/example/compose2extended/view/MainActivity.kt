package com.example.compose2extended.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose2extended.viewmodel.UserViewModel

import com.example.compose2extended.view.ProductListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val userViewModel:UserViewModel by viewModels<UserViewModel>()

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "sign_in") {


                composable("sign_in") {
                   // val userViewModel:UserViewModel by viewModels()
                    LoginScreen(navController, userViewModel)
                }
                composable("sign_up") {
                    SignUpPage(navController, 30)
                }
                    composable("ProductListScreen"){
                        ProductListScreen(navController,userViewModel)
                    }
                }
            }

        }
    }



