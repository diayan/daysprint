package com.kodeco.daysprint

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import com.kodeco.daysprint.common.SnackBarManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class DaySprintAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val snackbarManager: SnackBarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launch {
//            snackbarManager.snackbarMessages.filterNotNull().collect { snackbarMessage ->
//                val text = snackbarMessage.toMessage(resources)
//                scaffoldState.snackbarHostState.showSnackbar(text)
//            }
        }
    }

    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}
