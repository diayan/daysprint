package com.kodeco.daysprint.ui.details

import androidx.compose.runtime.mutableStateOf
import com.kodeco.daysprint.DaySprintViewModel
import com.kodeco.daysprint.TASK_DEFAULT_ID
import com.kodeco.daysprint.data.Task
import com.kodeco.daysprint.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val repository: TaskRepository
) : DaySprintViewModel() {
    val task = mutableStateOf(Task.EMPTY)

    fun initialize(taskId: String) {
        launchCatching {
            if (taskId != TASK_DEFAULT_ID) {
                task.value = repository.getTaskById(taskId) ?: Task.EMPTY
            }
        }
    }

    fun markTaskAsDone(task: Task) {
        launchCatching { repository.update(task.copy(completed = !task.completed)) }
    }

    fun deleteTask(task: Task, popUpScreen: () -> Unit) {
        launchCatching(dispatcher = Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                repository.deleteTask(task)
            }
            popUpScreen()
        }
    }
}