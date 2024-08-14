package com.example.joshsapplications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateAccountViewModel(mockApiService: ApiService) : ViewModel() {

    private val _createAccountState = MutableStateFlow<CreateAccountState>(CreateAccountState.Idle)
    val createAccountState: StateFlow<CreateAccountState> get() = _createAccountState

    fun createAccount(name: String, email: String, password: String) {
        viewModelScope.launch {
            _createAccountState.value = CreateAccountState.Loading
            try {
                val response = RetrofitInstance.api.registerUser(RegisterRequest(name, email, password))
                _createAccountState.value = CreateAccountState.Success(response)
            } catch (e: Exception) {
                _createAccountState.value = CreateAccountState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class CreateAccountState {
    object Idle : CreateAccountState()
    object Loading : CreateAccountState()
    data class Success(val response: Any) : CreateAccountState()
    data class Error(val message: String) : CreateAccountState()
}
