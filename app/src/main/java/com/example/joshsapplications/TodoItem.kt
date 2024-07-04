package com.example.joshsapplications

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class TodoItem(val name: String, val isCompleted: Boolean)

@Composable
fun TodoItem(todo: Todo, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(todo.name, modifier = Modifier.weight(1f))
        Checkbox(
            checked = todo.isCompleted,
            onCheckedChange = { isChecked -> onCheckedChange(isChecked) }
        )
    }
}
