package com.kodeco.daysprint.ui.addTask

import androidx.compose.runtime.mutableStateOf
import com.kodeco.daysprint.DaySprintViewModel
import com.kodeco.daysprint.TASK_DEFAULT_ID
import com.kodeco.daysprint.data.Task
import com.kodeco.daysprint.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val repository: TaskRepository
): DaySprintViewModel() {
    val task = mutableStateOf(Task())

    fun initialize(taskId: String) {
        launchCatching {
            if (taskId != TASK_DEFAULT_ID) {
                //task.value = repository.getTaskById(taskId) ?: Task()
            }
        }
    }

    fun onTitleChange(newValue: String) {
        task.value = task.value.copy(title = newValue)
    }

    fun onDescriptionChange(newValue: String) {
        task.value = task.value.copy(description = newValue)
    }


    fun onDoneClick(popUpScreen: () -> Unit) {
        launchCatching {
            val editedTask = task.value
//            if (editedTask.id.isBlank()) {
//                repository.insertTask(editedTask)
//            } else {
//                //storageService.update(editedTask)
//            }

            repository.insertTask(editedTask)
            popUpScreen()
        }
    }
}