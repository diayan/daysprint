package com.kodeco.daysprint.ui.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.kodeco.daysprint.data.Task

@Composable
@ExperimentalMaterial3Api
fun TaskItem(
    task: Task,
    onCheckChange: () -> Unit,
    onActionClick: (String) -> Unit
) {
    Card(
        colors  = CardDefaults.cardColors(
            androidx.compose.material.MaterialTheme.colors.background
        ),
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
        onClick = { onActionClick(task.id) }

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Checkbox(
                checked = task.completed,
                onCheckedChange = { onCheckChange() },
                modifier = Modifier.padding(8.dp, 0.dp)
            )
            if(task.completed) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleLarge + TextStyle(
                        textDecoration = TextDecoration.LineThrough
                    )
                )
            }else {
                Text(text = task.title,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}


