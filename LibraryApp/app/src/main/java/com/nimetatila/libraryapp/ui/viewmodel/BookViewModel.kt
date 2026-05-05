package com.nimetatila.libraryapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimetatila.libraryapp.data.model.Book
import com.nimetatila.libraryapp.data.model.BorrowRecord
import com.nimetatila.libraryapp.data.repository.BookRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID
import kotlin.collections.find

class BookViewModel : ViewModel() {
    private val repository = BookRepository()

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _userLoans = MutableStateFlow<List<BorrowRecord>>(emptyList())
    val userLoans: StateFlow<List<BorrowRecord>> = _userLoans

    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {
            _isLoading.value = true
            repository
                .getAllBooks()
                .onSuccess {
                    _books.value = emptyList()
                    _books.value = it
                }
                .onFailure { _error.value = it.message }
            _isLoading.value = false
        }
    }

    fun borrowBook(book: Book, userId: String) {
        // Stok kontrolü
        if (book.availableCopies <= 0) {
            _error.value = "Bu kitap şu an stokta kalmamış."
            return
        }

        viewModelScope.launch {
            try {
                _isLoading.value = true

                val formatter = DateTimeFormatter.ISO_DATE_TIME
                val now = LocalDateTime.now()
                val borrowedAt = now.format(formatter)
                val dueDate = now.plusDays(5).format(formatter)

                val record = BorrowRecord(
                    id = UUID.randomUUID().toString(),
                    studentId = userId,
                    bookId = book.id,
                    bookName = book.title, // Supabase'e ismini de gönderiyoruz
                    borrowedAt = borrowedAt,
                    dueDate = dueDate,
                    returnedAt = null
                )


                repository.createBorrowRecord(record)


                val newStockCount = book.availableCopies - 1
                repository.updateBookStock(book.id, newStockCount)

                println("DEBUG: Veritabanı güncellendi. Yeni beklenen stok: $newStockCount")


                delay(800)


                loadBooks()
                fetchUserLoans(userId)

                println("DEBUG: Tüm listeler tazelendi.")

            } catch (e: Exception) {
                _error.value = "Ödünç alma işlemi başarısız: ${e.message}"
                println("DEBUG: Hata: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchUserLoans(userId: String) {
        viewModelScope.launch {
            repository.getUserBorrowRecords(userId)
                .onSuccess { _userLoans.value = it }
                .onFailure { _error.value = it.message }
        }
    }

    fun returnBook(record: BorrowRecord, userId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true

                // Mevcut stoku bul (Listemizde güncel hali var)
                val book = _books.value.find { it.id == record.bookId }
                val currentStock = book?.availableCopies ?: 0


                repository.returnBook(record.id, record.bookId, currentStock)

                delay(1000)


                loadBooks()
                fetchUserLoans(userId)

                println("DEBUG: Kitap başarıyla iade edildi.")
            } catch (e: Exception) {
                _error.value = "İade işlemi sırasında hata: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

}