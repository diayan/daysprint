package com.kodeco.daysprint

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kodeco.daysprint.common.*
import com.kodeco.daysprint.ui.addTask.AddTaskScreen
import com.kodeco.daysprint.ui.tasks.TaskListScreen
import com.kodeco.daysprint.ui.tasks.TaskViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun DaySpringNavHost() {
    val navController = rememberNavController()
    val taskViewModel: TaskViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = DaySpringRoutes.TaskList.route
    ) {

        composable(DaySpringRoutes.TaskList.route) {
            TaskListScreen(navController, taskViewModel)
        }

        composable(DaySpringRoutes.AddTask.route) {
            AddTaskScreen(navController)
        }
    }
}


enum class DaySpringRoutes(val route: String) {
    TaskList("task-list"),
    AddTask("add-task")
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackBarManager = SnackBarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(scaffoldState, navController, snackbarManager, resources, coroutineScope) {
    DaySprintAppState(scaffoldState, navController, snackbarManager, resources, coroutineScope)
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@ExperimentalMaterial3Api
fun NavGraphBuilder.DaySprintGraph(appState: DaySprintAppState) {
    composable(SPLASH_SCREEN) {
        TODO("Build Splash Screen")
        //SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(TASKS_SCREEN) {
        //TaskListScreen(openScreen = { route -> appState.navigate(route) })
    }

    composable(
        route = "$EDIT_TASK_SCREEN$TASK_ID_ARG",
        arguments = listOf(navArgument(TASK_ID) { defaultValue = TASK_DEFAULT_ID })
    ) {
//        AddTaskScreen(
//            popUpScreen = { appState.popUp() },
//            taskId = it.arguments?.getString(TASK_ID) ?: TASK_DEFAULT_ID
//        )
    }
}