package com.example.todolistapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolistapp.data.model.TodoItem

@Database(entities = [TodoItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ToDoDao():ToDoDao
}