package com.example.todolistapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class TodoItem(
    val userId: Int,
    @PrimaryKey val id: Long = 0,
    val title: String,
    val isCompleted: Boolean = false
)