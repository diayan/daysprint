package com.kodeco.daysprint.ui.addTask

import androidx.compose.runtime.mutableStateOf
import com.kodeco.daysprint.DaySprintViewModel
import com.kodeco.daysprint.data.Task
import com.kodeco.daysprint.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : DaySprintViewModel() {
    val task = mutableStateOf(Task.EMPTY)

    fun onTitleChange(newValue: String) {
        task.value = task.value.copy(title = newValue)
    }

    fun onDescriptionChange(newValue: String) {
        task.value = task.value.copy(description = newValue)
    }

    fun onDoneClick(popUpScreen: () -> Unit) {
        launchCatching {
            if (task.value.id.isNotBlank()) {
                repository.insertTask(task.value)
            }
            popUpScreen()
        }
    }
}