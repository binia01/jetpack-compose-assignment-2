package com.example.todolistapp.data.remote

import com.example.todolistapp.data.model.TodoItem
import retrofit2.http.GET
import retrofit2.http.PATCH

interface ToDoService{
    @GET("/todos")
    suspend fun getTodos(): List<TodoItem>

    @GET("/todos/{id}")
    suspend fun getTodoById(id: Long): TodoItem

    @PATCH("/todos/{id}")
    suspend fun updateTodo(id: Long, todoItem: TodoItem): TodoItem

}