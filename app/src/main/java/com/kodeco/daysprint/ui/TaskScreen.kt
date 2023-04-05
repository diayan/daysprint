package com.kodeco.daysprint.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TaskListScreen() {
//    Scaffold() {
//
//    }
}


@Composable
fun TaskCard() {

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen() {
    Scaffold() {
        AddTaskContent(
            Modifier
                .padding(it)
        )
    }
}

@Composable
fun AddTaskContent(modifier: Modifier = Modifier) {
    var task by remember { mutableStateOf(" ") }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        TextField(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            value = task,
            placeholder = { Text("Clean my room") },
            onValueChange = {
                task = it
            }
        )

        Button(onClick = { /*TODO*/ }) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "ADD TASK"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddTaskPreview() {
    AddTaskContent()
}