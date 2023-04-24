package com.kodeco.daysprint.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
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
fun TaskDetailItem(
    task: Task,
    onCheckChange: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            MaterialTheme.colors.background
        ),
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
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
            if (task.completed) {

                Column {
                    Text(
                        text = task.title ?: "",
                        style = androidx.compose.material3.MaterialTheme.typography.titleLarge + TextStyle(
                            textDecoration = TextDecoration.LineThrough
                        )
                    )
                    Text(
                        text = task.description ?: "",
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                Column {
                    Text(
                        text = task.title ?: "",
                        style = androidx.compose.material3.MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = task.description ?: "",
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}