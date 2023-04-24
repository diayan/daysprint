package com.kodeco.daysprint.ui.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kodeco.daysprint.R
import com.kodeco.daysprint.common.ActionToolbar
import com.kodeco.daysprint.ext.smallSpacer
import com.kodeco.daysprint.ext.toolbarActions
import com.kodeco.daysprint.ui.tasks.TaskItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    popUpScreen: () -> Unit,
    modifier: Modifier = Modifier,
    taskId: String,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(viewModel) {
        viewModel.initialize(taskId)
    }

    val task by viewModel.task
    Log.i("retrievedTask: ", task.toString())
    androidx.compose.material.Scaffold(
        floatingActionButton = {
            androidx.compose.material.FloatingActionButton(
                onClick = {
                    //viewModel.onAddClick(openScreen)
                          },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                modifier = modifier.padding(8.dp)
            ) {
                androidx.compose.material.Icon(Icons.Filled.Add, "Delete")
            }
        }
    ) {
        //val tasks = viewModel.tasks.collectAsStateWithLifecycle(emptyList())
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            ActionToolbar(
                title = R.string.delete,
                modifier = Modifier.toolbarActions(),
                endActionIcon = R.drawable.ic_more_vert,
                endAction = {
                    //viewModel.onSettingsClick(openScreen)
                }
            )

            Spacer(modifier = Modifier.smallSpacer())
                    TaskItem(
                        task = task,
                        onCheckChange = {
                            //viewModel.markTaskAsDone(taskItem)
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