package com.example.todolistapp.data.remote

import com.example.todolistapp.data.model.TodoItem
import retrofit2.http.GET


interface ToDoService{
    @GET("/todos")
    suspend fun getTodos(): List<TodoItem>
}