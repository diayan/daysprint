package com.kodeco.daysprint.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kodeco.daysprint.DaySpringRoutes


fun NavController.goToTaskListScreen() {
    this.navigate(DaySpringRoutes.TaskList.route)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.goToAddTaskScreen() }
            ) {
                Text(text = "New Task")
            }
        }
    ) {

        LazyColumn(modifier = Modifier.padding(it)) {
            items(TasksList.items) {
                TaskCard(task = it)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCard(task: String, modifier: Modifier = Modifier) {
    Card(modifier) {
        Text(text = task)
    }
}


fun NavController.goToAddTaskScreen() {
    this.navigate(DaySpringRoutes.AddTask.route)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navController: NavController) {
    Scaffold() {
        AddTaskContent(
            onAddTask = { navController.goToTaskListScreen() },
            Modifier.padding(it),
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddTaskContent(
    onAddTask: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var task by remember { mutableStateOf(" ") }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        TextField(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            value = task,
            placeholder = { Text("Clean my room") },
            onValueChange = {
                task = it
            }
        )

        Button(
            onClick = {
                TasksList.items.add(task)
                onAddTask()
            }
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "ADD TASK"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddTaskPreview() {
    AddTaskContent(onAddTask = {})
}


object TasksList {
    val items = mutableStateListOf<String>()
}