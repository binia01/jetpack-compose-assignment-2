package com.example.todolistapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolistapp.ui.viewmodel.TodoListViewModel
import com.example.todolistapp.ui.viewmodel.TodoUiState

@OptIn(ExperimentalMaterial3Api::class)
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
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Todo List") },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
                    )
                }
            ) { padding ->
                LazyColumn(modifier = Modifier.padding(padding).padding(16.dp).fillMaxSize()) {
                    items(todos) { todo ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable { onTodoClick(todo.id) },
                            elevation = CardDefaults.cardElevation(4.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Checkbox(
                                    checked = todo.isCompleted,
                                    onCheckedChange = {  }
                                )
                                Text(text = todo.title, style = MaterialTheme.typography.titleMedium)
                            }
                        }
                    }
                }
            }
        }
    }
}
