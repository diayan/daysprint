package com.kodeco.daysprint.ui.addTask

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kodeco.daysprint.common.ActionToolbar
import com.kodeco.daysprint.common.BasicField
import com.kodeco.daysprint.ext.fieldModifier
import com.kodeco.daysprint.ext.spacer
import com.kodeco.daysprint.ext.toolbarActions
import com.kodeco.daysprint.R.drawable as AppIcon
import com.kodeco.daysprint.R.string as AppText

@Composable
@ExperimentalMaterialApi
fun AddEditTaskScreen(
    popUpScreen: () -> Unit,
    taskId: String,
    modifier: Modifier = Modifier,
    viewModel: AddEditTaskViewModel = hiltViewModel()
) {
    val task by viewModel.task

   // LaunchedEffect(Unit) { viewModel.initialize(taskId) }

    Column(
        modifier = modifier.fillMaxWidth().fillMaxHeight().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionToolbar(
            title = AppText.edit_task,
            modifier = Modifier.toolbarActions(),
            endActionIcon = AppIcon.check_mark_24,
            endAction = {
                viewModel.onDoneClick(popUpScreen)
            }
        )

        Spacer(modifier = Modifier.spacer())

        val fieldModifier = Modifier.fieldModifier()
        BasicField(AppText.title, task.title, viewModel::onTitleChange, fieldModifier)
        BasicField(AppText.description, task.description, viewModel::onDescriptionChange, fieldModifier)

        Spacer(modifier = Modifier.spacer())
    }
}
