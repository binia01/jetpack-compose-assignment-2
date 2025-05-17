package com.example.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.todolistapp.data.local.AppDatabase
import com.example.todolistapp.data.repository.ToDoRepository
import com.example.todolistapp.network.RetrofitInstance
import com.example.todolistapp.ui.navigation.AppNavigation
import com.example.todolistapp.ui.theme.ToDoListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "todo_db"
        ).build()

        val repository = ToDoRepository(RetrofitInstance.api, db.ToDoDao())

        setContent {
            ToDoListAppTheme {
                AppNavigation(repository)
            }
        }
    }
}


