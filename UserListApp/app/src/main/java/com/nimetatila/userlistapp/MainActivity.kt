package com.nimetatila.userlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.nimetatila.userlistapp.ui.navigation.NavGraph
import com.nimetatila.userlistapp.ui.screen.UserListScreen
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.nimetatila.userlistapp.ui.theme.UserListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val darkTheme = remember { mutableStateOf(false) }

            UserListAppTheme(
                darkTheme = darkTheme.value
            ) {
                NavGraph(  onToggleTheme = {
                    darkTheme.value = !darkTheme.value
                })
            }

        }
    }
}