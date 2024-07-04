package com.example.joshsapplications

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoScreen(todoViewModel: TodoViewModel = viewModel()) {
    val todos by todoViewModel.todos.collectAsState()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(BottomSheetValue.Collapsed)
    val (todoText, setTodoText) = remember { mutableStateOf("") }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = todoText,
                    onValueChange = setTodoText,
                    label = { Text("New Todo") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        if (todoText.isNotBlank()) {
                            todoViewModel.addTodo(todoText)
                            setTodoText("")
                            scope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
                        } else {
                            scope.launch {
                                //scaffoldState.snackbarHostState.showSnackbar("Todo cannot be empty")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Save")
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = { scope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() } },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancel")
                }
            }
        },
        sheetPeekHeight = 0.dp,
        content = {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(title = { Text("Todo") })
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() } }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Todo")
                    }
                },
                content = {
                    Column(modifier = Modifier.padding(16.dp)) {
                        LazyColumn {
                            items(todos) { todo ->
                                TodoItem(
                                    todo = todo,
                                    onCheckedChange = { todoViewModel.toggleTodo(todo) }
                                )
                            }
                        }
                    }
                }
            )
        }
    )

}


fun rememberScaffoldState() {

}

/* package com.example.joshsapplications

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoScreen(todoViewModel: TodoViewModel = viewModel()) {
    val todos by todoViewModel.todos.collectAsState()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(BottomSheetValue.Collapsed)
    val (todoText, setTodoText) = remember { mutableStateOf("") }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = todoText,
                    onValueChange = setTodoText,
                    label = { Text("New Todo") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        if (todoText.isNotBlank()) {
                            todoViewModel.addTodo(todoText)
                            setTodoText("")
                            scope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() }
                        } else {
                            scope.launch {
                                //scaffoldState.snackbarHostState.showSnackbar("Todo cannot be empty")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Save")
                }
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = { scope.launch { bottomSheetScaffoldState.bottomSheetState.collapse() } },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancel")
                }
            }
        },
        sheetPeekHeight = 0.dp,
        content = {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(title = { Text("Todo") })
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { scope.launch { bottomSheetScaffoldState.bottomSheetState.expand() } }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Todo")
                    }
                },
                content = {
                    Column(modifier = Modifier.padding(16.dp)) {
                        LazyColumn {
                            items(todos) { todo ->
                                TodoItem(
                                    todo = todo,
                                    onCheckedChange = { todoViewModel.toggleTodo(todo) }
                                )
                            }
                        }
                    }
                }
            )
        }
    )

}


fun rememberScaffoldState() {

}


*/
