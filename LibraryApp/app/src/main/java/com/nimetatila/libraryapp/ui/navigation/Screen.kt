package com.nimetatila.libraryapp.ui.navigation

sealed class Screen (val route: String){
    object Login : Screen("Login")
    object Register : Screen("register")
}