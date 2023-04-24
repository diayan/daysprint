package com.kodeco.daysprint.ui.tasks

import com.kodeco.daysprint.DELETE_TASK_SCREEN
import com.kodeco.daysprint.DaySprintViewModel
import com.kodeco.daysprint.EDIT_TASK_SCREEN
import com.kodeco.daysprint.TASK_ID
import com.kodeco.daysprint.data.Task
import com.kodeco.daysprint.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
): DaySprintViewModel() {
    val tasks: Flow<List<Task>> get() = repository.getTasks()

    fun markTaskAsDone(task: Task) {
        launchCatching {
            launchCatching { repository.update(task.copy(completed = !task.completed)) }
        }
    }

    fun deleteTask(task: Task) {
        launchCatching {
            repository.deleteTask(task)
        }
    }

    fun onAddClick(openScreen: (String) -> Unit) {
        return openScreen.invoke(EDIT_TASK_SCREEN)
    }

    fun onTaskActionClick(openScreen: (String) -> Unit, task: Task) {
        return openScreen.invoke("$DELETE_TASK_SCREEN?$TASK_ID={${task.id}}")
    }
}