package com.example.todolistapp.data.repository

import com.example.todolistapp.data.local.ToDoDao
import com.example.todolistapp.data.model.TodoItem
import com.example.todolistapp.data.remote.ToDoService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ToDoRepository{
    fun getToDo(): Flow<List<TodoItem>>
    fun getToDoById(id: Long): TodoItem?
}

class ToDoRepositoryImpl @Inject constructor(
    private val toDoService: ToDoService,
    private val toDoDao: ToDoDao
): ToDoRepository{
    override fun getToDo(): Flow<List<TodoItem>> =
        toDoDao.getAll()
    override fun getToDoById(id: Long): TodoItem? =
        toDoDao.getById(id)
}