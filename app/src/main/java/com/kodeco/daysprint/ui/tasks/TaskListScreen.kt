/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.kodeco.daysprint.ui.tasks

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kodeco.daysprint.R
import com.kodeco.daysprint.common.ActionToolbar
import com.kodeco.daysprint.ext.smallSpacer
import com.kodeco.daysprint.ext.toolbarActions
import com.kodeco.daysprint.ui.theme.colorPrimaryDark
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
                backgroundColor = colorPrimaryDark,
                contentColor = MaterialTheme.colors.onPrimary,
                modifier = modifier.padding(8.dp)
            ) {
                androidx.compose.material.Icon(Icons.Filled.Add, "Add")
            }
        }
    ) {
        val tasks = viewModel.tasks.collectAsStateWithLifecycle(emptyList())
        val hasTask = viewModel.hasTasks().collectAsStateWithLifecycle(false)
        if (!hasTask.value) {
            EmptyStateScreen()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                ActionToolbar(
                    title = AppText.tasks,
                    modifier = Modifier.toolbarActions(),
                    endActionIcon = R.drawable.ic_delete,
                    endAction = {
                                viewModel.deleteAllTasks()
                    },
                    canNavigateBack = false
                )

                Spacer(modifier = Modifier.smallSpacer())

                LazyColumn {
                    items(tasks.value, key = { it.id ?: -1 }) { taskItem ->
                        TaskItem(
                            task = taskItem,
                            onCheckChange = { viewModel.markTaskAsDone(taskItem) },
                            onActionClick = { action ->
                                viewModel.onTaskActionClick(
                                    openScreen,
                                    taskItem
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyStateScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ActionToolbar(
            title = AppText.tasks,
            modifier = Modifier.toolbarActions(),
            endActionIcon = R.drawable.ic_delete,
            endAction = {},
            canNavigateBack = false
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_hourglass_empty),
                contentDescription = "Empty hourglass",
                modifier = Modifier.size(36.dp),
                tint = colorPrimaryDark
            )
            Spacer(modifier = Modifier.smallSpacer())
            Text(
                text = "No tasks found, add a new task",
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
            )
        }
    }
}
