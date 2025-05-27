package com.example.codehubproject.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codehubproject.app.CHBankApp
import com.example.codehubproject.login.ui.LoginScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LoginScreen.route,
        builder = {
            composable(route = Routes.LoginScreen.route) {
                LoginScreen(navController = navController)
            }

            composable(route = Routes.CHBankScreen.route + "/{name}") {
                val name: String? = it.arguments?.getString("name")

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (name != null) {
                        CHBankApp(text = name, navController = navController)
                    }
                }
            }
        }
    )
}