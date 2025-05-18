package com.example.todolistapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todolistapp.data.model.TodoEntity
import com.example.todolistapp.data.model.TodoItem
import com.example.todolistapp.data.model.toEntity
import com.example.todolistapp.ui.components.TodoItemCard
import com.example.todolistapp.ui.viewmodel.TodoListViewModel
import com.example.todolistapp.ui.viewmodel.TodoUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel,
    onTodoClick: (Int) -> Unit,
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Todos") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is TodoUiState.Loading -> {
                    LoadingState()
                }
                is TodoUiState.Error -> {
                    ErrorState(
                        message = (state as TodoUiState.Error).message,
                        onRetryClick = { viewModel.loadTodos()}
                    )}
                is TodoUiState.Success -> {
                    val todos = (state as TodoUiState.Success).todos
                    TodoList(
                        todos = todos,
                        onTodoClick = onTodoClick,
                        onToggleCompletion = { updatedTodoEntity ->
                            viewModel.toggleTodoCompletion(updatedTodoEntity)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingState() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CircularProgressIndicator()
        Text("Loading your tasks...")
    }
}

@Composable
fun ErrorState(message: String, onRetryClick : () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Error: $message",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
         Button(
             onClick = { onRetryClick }) {
             Text("Retry")
         }
    }
}



@Composable
fun TodoList(
    todos: List<TodoItem>,
    onTodoClick: (Int) -> Unit,
    onToggleCompletion: (TodoEntity) -> Unit
) {
    LazyColumn(

        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = todos,
            key = { it.id }
        ) { todo ->
            TodoItemCard(
                todo = todo,
                onTodoClick = onTodoClick,
                onToggleCompletion = onToggleCompletion
            )
        }
    }
}


