package com.example.joshsapplications

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TodoViewModel : ViewModel() {
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> get() = _todos

    fun addTodo(name: String) {
        _todos.value += Todo(id = generateId(), name = name, isCompleted = false)
    }

    fun toggleTodo(todo: Todo) {
        _todos.value = _todos.value.map {
            if (it == todo) it.copy(isCompleted = !it.isCompleted) else it
        }
    }


    private fun generateId(): String {
        return _todos.value.size.toString()
    }
}
