package com.nimetatila.libraryapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimetatila.libraryapp.data.model.Profile
import com.nimetatila.libraryapp.data.repository.AuthRepository
import com.nimetatila.libraryapp.data.supabase.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val role: String) : AuthState()
    data class Error(val message: String) : AuthState()
}

sealed class SessionState {
    object Initializing : SessionState()
    object Unauthenticated : SessionState()
    data class Authenticated(val role: String) : SessionState()
}

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    private val _sessionState = MutableStateFlow<SessionState>(SessionState.Initializing)
    val sessionState: StateFlow<SessionState> = _sessionState

    private val _profile = MutableStateFlow<Profile?>(null)
    val profile: StateFlow<Profile?> = _profile

    init {
        observeSessionStatus()
    }

    private fun observeSessionStatus() {
        viewModelScope.launch {
            supabase.auth.sessionStatus.collect { status ->
                println("DEBUG: Session Durumu: $status")
                when (status) {
                    is SessionStatus.Authenticated -> {
                        fetchUserProfile()
                    }
                    SessionStatus.Initializing -> {
                        _sessionState.value = SessionState.Initializing
                    }
                    else -> {
                        _profile.value = null
                        _sessionState.value = SessionState.Unauthenticated
                    }
                }
            }
        }
    }

    fun fetchUserProfile() {
        viewModelScope.launch {
            try {
                val userId = repository.getCurrentUserId()
                if (userId != null) {
                    val profile = repository.getProfile(userId)
                    _profile.value = profile
                    _sessionState.value = SessionState.Authenticated(profile?.role ?: "student")
                    println("DEBUG: Profil Yüklendi: ${profile?.fullName}")
                } else {
                    _profile.value = null
                    _sessionState.value = SessionState.Unauthenticated
                }
            } catch (e: Exception) {
                println("DEBUG: Profil çekilirken hata: ${e.message}")
            }
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            repository.signIn(email, password)
                .onSuccess {
                    val userId = repository.getCurrentUserId()
                    if (userId != null) {
                        fetchUserProfile()
                        _authState.value = AuthState.Success("student")
                    } else {
                        _authState.value = AuthState.Error("Kullanıcı ID alınamadı.")
                    }
                }
                .onFailure { ex ->
                    _authState.value = AuthState.Error(ex.message ?: "Giriş başarısız")
                }
        }
    }

    fun signUp(email: String, password: String, fullName: String, studentNo: String?) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            repository.signUp(email, password, fullName, studentNo)
                .onSuccess {

                    _authState.value = AuthState.Success("student")
                    println("DEBUG: Kayıt Başarılı, AuthState Success oldu.")
                }
                .onFailure { ex ->
                    _authState.value = AuthState.Error(ex.message ?: "Kayıt başarısız")
                }
        }
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }

    fun signOut() {
        viewModelScope.launch {
            repository.signOut()
            _profile.value = null
            _authState.value = AuthState.Idle
            _sessionState.value = SessionState.Unauthenticated
        }
    }
}