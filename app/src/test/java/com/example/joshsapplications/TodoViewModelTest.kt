package com.example.joshsapplications

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class TodoViewModelTest {

    private lateinit var viewModel: TodoViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = TodoViewModel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `addTodo should add a new todo when name is valid`() = runBlocking {
        // Given
        val todoName = "Test Todo"

        viewModel.addTodo(todoName)

        val todos = viewModel.todos.first()
        assertEquals(1, todos.size)
        assertEquals(todoName, todos[0].name)
        assertEquals(false, todos[0].isCompleted)
    }

    @Test
    fun `toggleTodo should change completion state`() = runBlocking {
        val todoName = "Test Todo"
        viewModel.addTodo(todoName)
        val todo = viewModel.todos.first()[0]

        viewModel.toggleTodo(todo)

        val todos = viewModel.todos.first()
        assertEquals(1, todos.size)
        assertEquals(true, todos[0].isCompleted)
    }
}
