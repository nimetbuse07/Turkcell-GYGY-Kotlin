package com.nimetatila.libraryapp.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nimetatila.libraryapp.data.model.Book
import com.nimetatila.libraryapp.ui.viewmodel.AuthViewModel
import com.nimetatila.libraryapp.ui.viewmodel.BookViewModel
import com.nimetatila.libraryapp.ui.viewmodel.SessionState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    bookViewModel: BookViewModel,
    onNavigateToBorrowedBooks: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val books by bookViewModel.books.collectAsState()
    val userLoans by bookViewModel.userLoans.collectAsState()
    val isLoading by bookViewModel.isLoading.collectAsState()
    val error by bookViewModel.error.collectAsState()
    val profileState by authViewModel.profile.collectAsState()
    val sessionState by authViewModel.sessionState.collectAsState() // Oturum durumunu dinle
    val context = LocalContext.current

    var searchQuery by remember { mutableStateOf("") }

    val filteredBooks = books.filter { book ->
        book.title.contains(searchQuery, ignoreCase = true) ||
                book.author.contains(searchQuery, ignoreCase = true)
    }


    LaunchedEffect(sessionState) {
        if (sessionState is SessionState.Unauthenticated) {
            onNavigateToLogin()
        }
    }

    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(profileState?.userId) {
        profileState?.userId?.let { uid ->
            bookViewModel.fetchUserLoans(uid)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kütüphane") },
                actions = {
                    TextButton(onClick = onNavigateToBorrowedBooks) {
                        Text("Kiralamalarım", color = MaterialTheme.colorScheme.primary)
                    }

                    IconButton(onClick = { authViewModel.signOut() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Çıkış Yap",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            profileState?.let {
                Text(
                    text = "Hoş geldin, ${it.fullName}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text("Kitap adı veya yazar ara...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = "Temizle")
                        }
                    }
                },
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.Gray,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            when {
                isLoading && books.isEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                books.isEmpty() && !isLoading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Kütüphane şu an boş.")
                        Button(onClick = { bookViewModel.loadBooks() }) {
                            Text("Tekrar Dene")
                        }
                    }
                }
                filteredBooks.isEmpty() && searchQuery.isNotEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "'$searchQuery' için sonuç bulunamadı.")
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filteredBooks, key = { it.id }) { book ->
                            val isAlreadyBorrowed = userLoans.any { it.bookId == book.id && it.returnedAt == null }

                            BookItem(
                                book = book,
                                isAlreadyBorrowed = isAlreadyBorrowed,
                                onBorrowClick = { selectedBook ->
                                    if (profileState != null) {
                                        bookViewModel.borrowBook(selectedBook, profileState!!.userId)
                                    } else {
                                        Toast.makeText(context, "Profil bilgisi yüklenemedi!", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookItem(
    book: Book,
    isAlreadyBorrowed: Boolean,
    onBorrowClick: (Book) -> Unit
) {
    val isAvailable = book.availableCopies > 0

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Yazar: ${book.author}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Sayfa: ${book.pageCount}",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = "Stok: ${book.availableCopies}",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isAvailable) MaterialTheme.colorScheme.primary else Color.Red
                    )
                }

                when {
                    isAlreadyBorrowed -> {
                        Surface(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                text = "ZATEN SENDE",
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    isAvailable -> {
                        Button(
                            onClick = { onBorrowClick(book) },
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Text("ÖDÜNÇ AL", style = MaterialTheme.typography.labelMedium)
                        }
                    }
                    else -> {
                        Surface(
                            color = Color.Red.copy(alpha = 0.1f),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                text = "STOKTA YOK",
                                color = Color.Red,
                                style = MaterialTheme.typography.labelMedium,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}