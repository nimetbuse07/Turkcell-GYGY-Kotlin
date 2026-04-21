package com.nimetatila.androidexercies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun RegisterScreen(
    modifier: Modifier,
    navController: NavController){
    Column(modifier = modifier.fillMaxSize()) {
        Text("merhaba")
        Button(
            onClick = {
            navController.navigate(Screen.HomePage.route)
        }) {
            Text("anasayfaya dön")
        }
    }
}