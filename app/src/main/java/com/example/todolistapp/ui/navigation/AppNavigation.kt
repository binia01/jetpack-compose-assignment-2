package com.example.todolistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolistapp.data.repository.ToDoRepository
import com.example.todolistapp.ui.screen.TodoDetailScreen
import com.example.todolistapp.ui.screen.TodoListScreen
import com.example.todolistapp.ui.viewmodel.TodoListViewModel

@Composable
fun AppNavigation(repository: ToDoRepository) {
    val navController = rememberNavController()
    val viewModel = remember { TodoListViewModel(repository) }

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            TodoListScreen(viewModel) { todoId ->
                navController.navigate("detail/$todoId")
            }
        }
        composable("detail/{todoId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("todoId")?.toIntOrNull()
            val todo = id?.let { viewModel.getTodoById(it) }
            TodoDetailScreen(todo = todo) {
                navController.popBackStack()
            }
        }
    }
}