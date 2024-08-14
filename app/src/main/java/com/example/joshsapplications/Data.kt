package com.example.joshsapplications

data class RegisterRequest(val name: String, val email: String, val password: String)
data class LoginRequest(val email: String, val password: String)
data class UserResponse(val token: String, val id: String)
data class TodoRequest(val name: String, val isCompleted: Boolean = false)
data class Todo(
    val id: String,
    val name: String,
    val isCompleted: Boolean
)
