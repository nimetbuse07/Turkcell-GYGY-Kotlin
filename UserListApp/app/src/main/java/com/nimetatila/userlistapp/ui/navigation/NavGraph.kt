package com.nimetatila.userlistapp.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nimetatila.userlistapp.ui.screen.UserDetailScreen
import com.nimetatila.userlistapp.ui.screen.UserListScreen

@Composable
fun NavGraph(onToggleTheme: () -> Unit) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.USER_LIST,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // ← eklendi
    ) {

        composable(Routes.USER_LIST) {
            UserListScreen(
                navController = navController,
                onToggleTheme = onToggleTheme
            )
        }

        composable(
            route = Routes.USER_DETAIL + "/{name}/{email}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val name = backStackEntry.arguments?.getString("name") ?: ""
            val email = backStackEntry.arguments?.getString("email") ?: ""

            UserDetailScreen(
                name = name,
                email = email,
                navController = navController
            )
        }
    }
}