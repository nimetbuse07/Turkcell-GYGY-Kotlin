package com.nimetatila.userlistapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimetatila.userlistapp.data.model.User
import com.nimetatila.userlistapp.data.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val repository = UserRepository()

    // UI State
    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val uiState: StateFlow<UserUiState> = _uiState

    // Search query (encapsulated)
    val searchQuery = MutableStateFlow("")

    // Filtered users (FINAL LIST USED IN UI)
    val filteredUsers: StateFlow<List<User>> = combine(
        searchQuery,
        uiState
    ) { query, state ->

        if (state is UserUiState.Success) {
            val list = state.users

            if (query.isEmpty()) {
                list
            } else {
                list.filter { user ->
                    user.name.contains(query, ignoreCase = true) ||
                            user.email.contains(query, ignoreCase = true)
                }
            }

        } else {
            emptyList()
        }

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        getUsers()
    }

    // API CALL
    fun getUsers() {
        viewModelScope.launch {
            _uiState.value = UserUiState.Loading

            try {
                val users = repository.getUsers()
                _uiState.value = UserUiState.Success(users)
            } catch (e: Exception) {
                _uiState.value = UserUiState.Error(
                    e.localizedMessage ?: "Bilinmeyen hata"
                )
            }
        }
    }

    // SEARCH UPDATE
    fun setSearch(query: String) {
        searchQuery.value = query
    }
}