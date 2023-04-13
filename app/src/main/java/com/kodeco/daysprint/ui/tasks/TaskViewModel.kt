package com.kodeco.daysprint.ui.tasks

import androidx.lifecycle.ViewModel
import com.kodeco.daysprint.data.Task
import com.kodeco.daysprint.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
): ViewModel() {
    val tasks: Flow<List<Task>> get() = repository.getTasks()

    fun markTaskAsDone(task: Task) {
        // TODO: Mark task as done in repository
        // taskRepository.markTaskAsDone(task)
    }

    fun deleteTask(task: Task) {
        // TODO: Delete task from repository
        // taskRepository.deleteTask(task)
    }

}