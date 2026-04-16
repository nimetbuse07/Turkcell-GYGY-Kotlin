package com.nimetatila.userlistapp.viewmodel
import com.nimetatila.userlistapp.data.model.User

sealed class UserUiState {

    object Loading : UserUiState()

    data class Success(val users: List<User>) : UserUiState()

    data class Error(val message: String) : UserUiState()
}