package com.example.todolistapp.data.repository

import com.example.todolistapp.data.local.ToDoDao
import com.example.todolistapp.data.model.TodoEntity
import com.example.todolistapp.data.model.TodoItem
import com.example.todolistapp.data.model.toEntity
import com.example.todolistapp.data.model.toTodoItem
import com.example.todolistapp.data.remote.ToDoService
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ToDoRepository(
    private val api: ToDoService,
    private val dao: ToDoDao
) {
    fun getTodos(): Flow<List<TodoItem>> = dao.getAllTodos()
        .map { list -> list.map { it.toTodoItem() } }
        .onStart {
            try {
                val remoteTodos = api.getTodos()
                dao.insertTodos(remoteTodos.map { it.toEntity() })
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    fun getTodoById(id: Int): Flow<TodoItem?> {
        return dao.getAllTodos()
            .map { todos ->
                todos.firstOrNull { it.id == id }?.toTodoItem()
            }
    }

    suspend fun updateTodo(todo: TodoEntity) {
        withContext(Dispatchers.IO) {
            dao.updateTodo(todo)
        }
    }
}
