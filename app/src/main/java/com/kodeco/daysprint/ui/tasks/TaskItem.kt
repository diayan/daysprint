package com.kodeco.daysprint.ui.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodeco.daysprint.data.Task
import com.kodeco.daysprint.data.hasDueDate
import com.kodeco.daysprint.data.hasDueTime

@Composable
@ExperimentalMaterial3Api
fun TaskItem(
    task: Task,
    options: List<String>,
    onCheckChange: () -> Unit,
    onActionClick: (String) -> Unit
) {
    Card(
        colors  = CardDefaults.cardColors(backgroundColor),
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp),
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

            Column(modifier = Modifier.weight(1f)) {
                Text(text = task.title, style = MaterialTheme.typography.titleSmall)
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = getDueDateAndTime(task), fontSize = 12.sp)
                }
            }
//
//            if (task.flag) {
//                Icon(
//                    painter = painterResource(AppIcon.ic_flag),
//                    tint = DarkOrange,
//                    contentDescription = "Flag"
//                )
//            }
//
//            DropdownContextMenu(options, Modifier.contextMenu(), onActionClick)
        }
    }
}

private fun getDueDateAndTime(task: Task): String {
    val stringBuilder = StringBuilder("")

    if (task.hasDueDate()) {
        stringBuilder.append(task.dueDate)
        stringBuilder.append(" ")
    }

    if (task.hasDueTime()) {
        stringBuilder.append("at ")
        stringBuilder.append(task.dueTime)
    }

    return stringBuilder.toString()
}


//@Preview
//@Composable
//fun TodoCardPreview() {
//    Compose_PlaygroundTheme {
//        TaskItemCard(
//            toDo = todoList[1],
//            onDoneClick = { /*TODO*/ },
//            onDeleteClick = { /*TODO*/ })
//    }
//}

