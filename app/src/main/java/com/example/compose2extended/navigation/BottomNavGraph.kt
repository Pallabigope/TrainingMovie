package com.example.compose2extended.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose2extended.view.HomeScreen
import com.example.compose2extended.view.ProfileScreen
import com.example.compose2extended.viewmodel.UserViewModel

@Composable
fun BottomNavGraph(navController: NavHostController,userViewModel: UserViewModel) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(userViewModel = userViewModel, navController =navController )
        }
       /* composable(route = BottomBarScreen.Cart.route) {
            Cart()
        }*/
    }
}