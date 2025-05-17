package com.example.todolistapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class TodoEntity(
    val userId: Int,
    @PrimaryKey val id: Int,
    val title: String,
    val isCompleted: Boolean
)

fun TodoEntity.toTodoItem(): TodoItem = TodoItem(userId, id, title, isCompleted)
fun TodoItem.toEntity(): TodoEntity = TodoEntity(userId, id, title, isCompleted)