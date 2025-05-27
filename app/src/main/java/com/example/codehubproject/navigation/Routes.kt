package com.example.codehubproject.navigation

sealed class Routes(val route: String) {
    data object LoginScreen : Routes("Login")
    data object CHBankScreen : Routes("CHBankScreen")
}