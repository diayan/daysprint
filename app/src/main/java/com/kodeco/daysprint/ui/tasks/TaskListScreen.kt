package com.kodeco.daysprint.ui.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kodeco.daysprint.DaySpringRoutes
import com.kodeco.daysprint.data.Task
import com.kodeco.daysprint.ui.addTask.goToAddTaskScreen


fun NavController.goToTaskListScreen() {
    this.navigate(DaySpringRoutes.TaskList.route)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(navController: NavController, taskViewModel: TaskViewModel) {
    val tasks by taskViewModel.tasks.collectAsState(initial = emptyList())

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { navController.goToAddTaskScreen() }) {
            Text(
                text = "New Task",
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }) {

//
//        LazyColumn {
//            items(tasks.size, key = { it }) { taskItem ->
//                TaskItem(
//                    task = taskItem,
//                    options = options,
//                    onCheckChange = { viewModel.onTaskCheckChange(taskItem) },
//                    onActionClick = { action -> viewModel.onTaskActionClick(openScreen, taskItem, action) }
//                )
//            }
//        }

        LazyVerticalGrid(
            modifier = Modifier.padding(it),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tasks.size) { index ->
                val task = tasks[index]
                TaskItem(
                    task = task,
                    options = listOf(),
                    onCheckChange =
                    { /*TODO*/ },
                    onActionClick = {}
                )
            }
        }
    }
}


@Composable
fun TaskCard(task: String, modifier: Modifier = Modifier) {
    Card(modifier, shape = RoundedCornerShape(12.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text(text = task)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskCardPreview() {
    TaskCard(task = "Clean My Room")
}


object TasksList {
    val items = mutableStateListOf<Task>()
}