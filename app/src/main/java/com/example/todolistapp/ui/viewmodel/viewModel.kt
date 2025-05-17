package com.example.todolistapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.model.TodoItem
import com.example.todolistapp.data.repository.ToDoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class TodoUiState {
    object Loading : TodoUiState()
    data class Success(val todos: List<TodoItem>) : TodoUiState()
    data class Error(val message: String) : TodoUiState()
}

class TodoListViewModel(
    private val repository: ToDoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<TodoUiState>(TodoUiState.Loading)
    val uiState: StateFlow<TodoUiState> = _uiState

    init {
        loadTodos()
    }

    fun loadTodos() {
        _uiState.value = TodoUiState.Loading
        viewModelScope.launch {
            try {
                val todos = repository.getTodos()
                _uiState.value = TodoUiState.Success(todos)
            } catch (e: Exception) {
                _uiState.value = TodoUiState.Error("Failed to load todos")
            }
        }
    }

    fun getTodoById(id: Int): TodoItem? {
        val currentState = _uiState.value
        return if (currentState is TodoUiState.Success) {
            currentState.todos.find { it.id == id }
        } else null
    }
}
