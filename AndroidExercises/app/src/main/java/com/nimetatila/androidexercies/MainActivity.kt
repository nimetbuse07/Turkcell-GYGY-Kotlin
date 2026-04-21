package com.nimetatila.androidexercies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidexercies.viewmodel.ToDoListViewModel
import model.ToDo

// Ekran tanımları
sealed class Screen(val route: String) {
    data object Register : Screen("register")
    data object HomePage : Screen("homePage")
    data object AddTodo : Screen("addTodo") // TODO 2: Yeni ekleme ekranı
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { paddingValues ->
                MyNavigatableApp(Modifier.padding(paddingValues))
            }
        }
    }
}

@Composable
fun MyNavigatableApp(modifier: Modifier) {
    val navController = rememberNavController()

    // ViewModel'i en üste alıyoruz — Kod 1'deki gibi tüm ekranlar paylaşır
    val todoViewModel: ToDoListViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.HomePage.route
    ) {
        composable(Screen.Register.route) {
            RegisterScreen(modifier, navController)
        }
        composable(Screen.HomePage.route) {
            Homepage(modifier, navController, todoViewModel)
        }
        // TODO 2: Ekleme ekranını navigation'a bağladık
        composable(Screen.AddTodo.route) {
            AddToDoScreen(modifier, navController, todoViewModel)
        }
    }
}

@Composable
fun Homepage(modifier: Modifier, navController: NavController, todoViewModel: ToDoListViewModel) {
    val todos by todoViewModel.todos.collectAsState()
    val isLoading by todoViewModel.isLoading.collectAsState()
    val error by todoViewModel.error.collectAsState()

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        when {
            isLoading -> { Text("Yükleniyor...") }
            error != null -> { Text("Hata: $error") }
            else -> {
                // TODO 1: onDelete artık index değil, todo.id gönderiyor
                ToDoList(todos, onDelete = { id -> todoViewModel.deleteTodo(id) })
            }
        }

        // TODO 2: Butona tıklayınca ekleme ekranına git
        Button(
            onClick = { navController.navigate(Screen.AddTodo.route) },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Yeni Görev Ekle")
        }
    }
}

// TODO 2: Yeni ekleme ekranı
@Composable
fun AddToDoScreen(modifier: Modifier, navController: NavController, todoViewModel: ToDoListViewModel) {
    val text = remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text("Yeni Görev Ekle")

        TextField(
            value = text.value,
            onValueChange = { newValue -> text.value = newValue },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            placeholder = { Text("Görev başlığı...") }
        )

        Button(
            onClick = {
                if (text.value.isNotBlank()) {
                    todoViewModel.addTodo(text.value) // Supabase'e ekle + yenile
                    text.value = ""                   // Kutuyu temizle
                    navController.popBackStack()       // Ana sayfaya dön
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kaydet")
        }
    }
}

// TODO 1: onDelete parametresi artık Int (id) alıyor, index değil
@Composable
fun ToDoList(toDoList: List<ToDo>, onDelete: (Int) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(toDoList) { _, todo ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(todo.id.toString())
                Text(todo.title)

                // Tamamlanma durumu ikonu — Kod 2'den korundu
                when {
                    todo.completed -> Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Tamamlandı"
                    )
                    else -> Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Tamamlanmadı"
                    )
                }

                // TODO 1: todo.id geçiriliyor, artık index değil
                IconButton(onClick = { onDelete(todo.id) }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Sil")
                }
            }
        }
    }
}