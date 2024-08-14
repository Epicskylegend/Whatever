package com.example.joshsapplications

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("/api/users/register")
    suspend fun registerUser(@Body request: RegisterRequest): UserResponse

    @POST("/api/users/login")
    suspend fun loginUser(@Body request: LoginRequest): UserResponse

    @GET("/api/users/{user_id}/todos")
    suspend fun getTodos(@Path("user_id") userId: String): List<Todo>

    @POST("/api/users/{user_id}/todos")
    suspend fun createTodo(@Path("user_id") userId: String, @Body request: TodoRequest): Todo

    @PUT("/api/users/{user_id}/todos/{id}")
    suspend fun updateTodo(@Path("user_id") userId: String, @Path("id") todoId: String, @Body request: TodoRequest): Todo
}
