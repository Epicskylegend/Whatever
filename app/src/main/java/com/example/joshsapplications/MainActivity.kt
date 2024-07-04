package com.example.joshsapplications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import com.example.joshsapplications.ui.theme.JoshsApplicationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JoshsApplicationsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    TodoScreen()
                }
            }
        }
    }

}
/* package com.example.joshsapplications

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


*/