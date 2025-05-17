package com.example.todolistapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolistapp.ui.viewmodel.TodoListViewModel
import com.example.todolistapp.ui.viewmodel.TodoUiState

@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel,
    onTodoClick: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is TodoUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is TodoUiState.Error -> {
            Text("Error loading todos.", Modifier.padding(16.dp))
        }
        is TodoUiState.Success -> {
            val todos = (state as TodoUiState.Success).todos
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(todos) { todo ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { onTodoClick(todo.id) },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = todo.title, style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = if (todo.isCompleted) "Completed" else "Pending",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
