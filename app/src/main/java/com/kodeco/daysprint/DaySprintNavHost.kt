package com.kodeco.daysprint

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kodeco.daysprint.common.*
import com.kodeco.daysprint.ui.addTask.AddEditTaskScreen
import com.kodeco.daysprint.ui.details.TaskDetailScreen
import com.kodeco.daysprint.ui.splash.SplashScreen
import com.kodeco.daysprint.ui.tasks.TaskListScreen
import com.kodeco.daysprint.ui.theme.DaySprintTheme
import kotlinx.coroutines.CoroutineScope

@Composable
@ExperimentalMaterial3Api
fun DaySprintNavHost() {
    DaySprintTheme {
        val appState = rememberAppState()
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    modifier = Modifier.padding(8.dp),
                    snackbar = { snackBarData ->
                        Snackbar(snackBarData, contentColor = MaterialTheme.colors.onPrimary)
                    }
                )
            },
            scaffoldState = appState.scaffoldState
        ) { innerPaddingModifier ->
            NavHost(
                navController = appState.navController,
                startDestination = SPLASH_SCREEN,
                modifier = Modifier.padding(innerPaddingModifier)
            ) {
                daySprintGraph(appState)
            }
        }
    }
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackBarManager: SnackBarManager = SnackBarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(scaffoldState, navController, snackBarManager, resources, coroutineScope) {
    DaySprintAppState(scaffoldState, navController, snackBarManager, resources, coroutineScope)
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalMaterial3Api
fun NavGraphBuilder.daySprintGraph(appState: DaySprintAppState) {
    composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopUp = {
                route, popUp -> appState.navigateAndPopUp(route, popUp)
        })
    }

    composable(TASKS_SCREEN) {
        TaskListScreen(openScreen = { route -> appState.navigate(route) })
    }

    composable(
        route = "$EDIT_TASK_SCREEN$TASK_ID_ARG",
        arguments = listOf(navArgument(TASK_ID) {
            defaultValue = TASK_DEFAULT_ID }
        )
    ) {
        AddEditTaskScreen(
            popUpScreen = { appState.popUp() },
        )
    }

    composable(
        route = "$DELETE_TASK_SCREEN$TASK_ID_ARG",
        arguments = listOf(navArgument(TASK_ID) {
            defaultValue = TASK_DEFAULT_ID
        })
    ) {
        TaskDetailScreen(
            popUpScreen = { appState.popUp() },
            taskId = it.arguments?.getString(TASK_ID) ?: TASK_DEFAULT_ID
        )
    }
}