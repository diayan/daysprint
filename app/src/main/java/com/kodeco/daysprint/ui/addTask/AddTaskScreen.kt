package com.kodeco.daysprint.ui.addTask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kodeco.daysprint.DaySpringRoutes
import com.kodeco.daysprint.data.Task
import com.kodeco.daysprint.ui.tasks.TasksList
import com.kodeco.daysprint.ui.tasks.goToTaskListScreen


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
    var task by remember { mutableStateOf<Task>(
        Task(
            title = "",
            description = "",
        ))
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        TextField(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            value = task.title,
            placeholder = { Text("Clean my room") },
            onValueChange = {
                task.title = it
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