package com.nimetatila.libraryapp.ui.navigation

//Sayfa routerlarımın tanımı
sealed class Screen (val route : String) {

    object Login : Screen("login")
    object Register : Screen("register")
    object Homepage : Screen("homepage")

    object Splash : Screen(route = "splash")

    object BorrowedBooks : Screen("borrowed_books")
}