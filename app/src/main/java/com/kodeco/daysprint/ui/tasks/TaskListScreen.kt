package com.kodeco.daysprint.ui.tasks

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kodeco.daysprint.common.ActionToolbar
import com.kodeco.daysprint.data.Task
import com.kodeco.daysprint.ext.smallSpacer
import com.kodeco.daysprint.ext.toolbarActions
import com.kodeco.daysprint.R.drawable as AppIcon
import com.kodeco.daysprint.R.string as AppText


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = hiltViewModel()
) {
    androidx.compose.material.Scaffold(
        floatingActionButton = {
            androidx.compose.material.FloatingActionButton(
                onClick = { viewModel.onAddClick(openScreen) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                modifier = modifier.padding(8.dp)
            ) {
                androidx.compose.material.Icon(Icons.Filled.Add, "Add")
            }
        }
    ) {
        val tasks = viewModel.tasks.collectAsStateWithLifecycle(emptyList())
        Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            ActionToolbar(
                title = AppText.tasks,
                modifier = Modifier.toolbarActions(),
                endActionIcon = AppIcon.ic_more_vert,
                endAction = {
                    //viewModel.onSettingsClick(openScreen)
                }
            )

            Spacer(modifier = Modifier.smallSpacer())

            LazyColumn {
                items(tasks.value, key = { it.id ?: -1}) { taskItem ->
                    TaskItem(
                        task = taskItem,
                        onCheckChange = {
                            //viewModel.onTaskCheckChange(taskItem)
                                        },
                        onActionClick = { action ->
//                            viewModel.onTaskActionClick(
//                                openScreen,
//                                taskItem,
//                                action
//                            )
                        }
                    )
                }
            }
        }
    }
    LaunchedEffect(viewModel) {
       // viewModel.loadTaskOptions()
    }
}
