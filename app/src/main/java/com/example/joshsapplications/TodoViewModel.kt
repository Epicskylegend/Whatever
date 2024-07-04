package com.example.joshsapplications

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Todo(
    val name: String,
    var isCompleted: Boolean = false
)

class TodoViewModel : ViewModel() {
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> get() = _todos

    fun addTodo(name: String) {
        _todos.value = _todos.value + Todo(name)
    }

    fun toggleTodo(todo: Todo) {
        _todos.value = _todos.value.map {
            if (it == todo) it.copy(isCompleted = !it.isCompleted) else it
        }
    }
}

