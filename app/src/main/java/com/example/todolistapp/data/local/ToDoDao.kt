package com.example.todolistapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todolistapp.data.model.TodoEntity
import com.example.todolistapp.data.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo_items")
    fun getAllTodos(): List<TodoEntity>

//    @Query("SELECT * FROM todo_items WHERE id = :id")
//    fun getById(id: Int): TodoItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodos(todos: List<TodoEntity>)
}