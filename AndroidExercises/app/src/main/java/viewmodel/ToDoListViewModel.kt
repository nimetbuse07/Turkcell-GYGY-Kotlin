package com.example.androidexercies.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import model.ToDo
import kotlin.collections.emptyList

class ToDoListViewModel : ViewModel() {
    private val repository = TodoRepository()

    private val _todos = MutableStateFlow<List< ToDo>>(emptyList())
    val todos: StateFlow<List<ToDo>> = _todos.asStateFlow();

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow();

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow();

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val result = repository.getTodos()
                _todos.value = result
            } catch (e: Exception) {
                _error.value = e.message ?: "Bir hata oluştu."
            } finally {
  