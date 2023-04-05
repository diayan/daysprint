package com.kodeco.daysprint

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kodeco.daysprint.ui.AddTaskScreen
import com.kodeco.daysprint.ui.TaskListScreen

@Composable
fun DaySpringNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DaySpringRoutes.TaskList.route
    ) {

        composable(DaySpringRoutes.TaskList.route){
            TaskListScreen(navController)
        }

        composable(DaySpringRoutes.AddTask.route){
            AddTaskScreen(navController)
        }
    }
}


enum class DaySpringRoutes(val route: String) {
    TaskList("task-list"),
    AddTask("add-task")
}