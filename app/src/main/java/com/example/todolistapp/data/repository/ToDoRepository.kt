package com.example.todolistapp.data.repository

import com.example.todolistapp.data.local.ToDoDao
import com.example.todolistapp.data.model.TodoItem
import com.example.todolistapp.data.model.toEntity
import com.example.todolistapp.data.model.toTodoItem
import com.example.todolistapp.data.remote.ToDoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ToDoRepository(
    private val api: ToDoService,
    private val dao: ToDoDao
){
    suspend fun getTodos(): List<TodoItem> = withContext(Dispatchers.IO) {
        try {
            val remoteTodos = api.getTodos()
            dao.insertTodos(remoteTodos.map { it.toEntity() })
            remoteTodos
        } catch (e: Exception) {
            // Fallback to cached data if network fails
            dao.getAllTodos().map { it.toTodoItem() }
        }
    }

    suspend fun getTodoById(id: Int): TodoItem? = withContext(Dispatchers.IO) {
        dao.getAllTodos().firstOrNull { it.id == id }?.toTodoItem()
    }
}