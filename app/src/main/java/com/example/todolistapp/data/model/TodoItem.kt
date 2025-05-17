package com.example.todolistapp.data.model

import androidx.room.PrimaryKey

data class TodoItem(
    val userId: Int,
    @PrimaryKey val id: Int,
    val title: String,
    val isCompleted: Boolean
)