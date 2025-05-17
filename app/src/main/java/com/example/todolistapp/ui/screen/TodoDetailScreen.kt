package com.example.todolistapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolistapp.data.model.TodoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(
    todo: TodoItem?,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo Detail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (todo != null) {
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                Text(text = "ID: ${todo.id}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Title: ${todo.title}", style = MaterialTheme.typography.titleLarge)
                Text(
                    text = "Status: ${if (todo.isCompleted) "Completed" else "Pending"}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("Todo not found.")
            }
        }
    }
}
