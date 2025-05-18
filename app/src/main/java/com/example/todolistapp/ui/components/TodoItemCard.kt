package com.example.todolistapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolistapp.data.model.TodoEntity
import com.example.todolistapp.data.model.TodoItem
import com.example.todolistapp.data.model.toEntity

@Composable
fun TodoItemCard(
    todo: TodoItem,
    onTodoClick: (Int) -> Unit,
    onToggleCompletion: (TodoEntity) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()

            .clickable { onTodoClick(todo.id) },
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (todo.isCompleted) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Checkbox(
                checked = todo.isCompleted,
                onCheckedChange = { isChecked ->

                    onToggleCompletion(todo.toEntity().copy(isCompleted = isChecked))
                },

                colors = CheckboxDefaults.colors(
                    checkedColor = if (todo.isCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary
                )
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = todo.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
