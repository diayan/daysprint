package com.kodeco.daysprint.ui.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kodeco.daysprint.R
import com.kodeco.daysprint.common.ActionToolbar
import com.kodeco.daysprint.ext.smallSpacer
import com.kodeco.daysprint.ext.toolbarActions

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    popUpScreen: () -> Unit,
    taskId: String,
    viewModel: TaskDetailViewModel = hiltViewModel()
) {
    val task by viewModel.task

    LaunchedEffect(viewModel) {
        viewModel.initialize(taskId)
    }

    androidx.compose.material.Scaffold{
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            ActionToolbar(
                title = R.string.delete,
                modifier = Modifier.toolbarActions(),
                endActionIcon = R.drawable.ic_delete,
                endAction = {
                    viewModel.deleteTask(task, popUpScreen)
                },
                canNavigateBack = true,
                navigateUp = popUpScreen
            )

            Spacer(modifier = Modifier.smallSpacer())
            TaskDetailItem(
                task = task,
                onCheckChange = {
                    viewModel.markTaskAsDone(task)
                }
            )
        }
    }
}