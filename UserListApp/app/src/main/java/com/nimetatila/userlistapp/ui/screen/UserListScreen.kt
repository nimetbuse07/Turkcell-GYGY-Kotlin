package com.nimetatila.userlistapp.ui.screen

// Bunu import et
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nimetatila.userlistapp.ui.components.UserItem
import com.nimetatila.userlistapp.viewmodel.UserUiState
import com.nimetatila.userlistapp.viewmodel.UserViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun UserListScreen(
    viewModel: UserViewModel = viewModel(),
    navController: NavController,
    onToggleTheme: () -> Unit
) {
    val users by viewModel.filteredUsers.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kullanıcılar") },
                actions = {
                    Button(
                        onClick = onToggleTheme,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("🌙 Tema")
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // ← bu önemli, scaffold padding
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { viewModel.setSearch(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                placeholder = { Text("İsim veya email ara") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            when (uiState) {
                is UserUiState.Loading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is UserUiState.Error -> {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = (uiState as UserUiState.Error).message,
                                color = MaterialTheme.colorScheme.error
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(onClick = { viewModel.getUsers() }) {
                                Text("Tekrar Dene")
                            }
                        }
                    }
                }
                is UserUiState.Success -> {
                    if (users.isEmpty()) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Sonuç bulunamadı")
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(8.dp)
                        ) {
                            items(users) { user ->
                                UserItem(
                                    user = user,
                                    onClick = {
                                        navController.navigate("user_detail/${user.name}/${user.email}")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}