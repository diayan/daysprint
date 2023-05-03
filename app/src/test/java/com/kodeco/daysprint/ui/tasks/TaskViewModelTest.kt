package com.kodeco.daysprint.ui.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.InvalidationTracker
import com.kodeco.daysprint.data.Task
import com.kodeco.daysprint.data.TaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class TaskViewModelTest {
    private lateinit var repository: TaskRepository
    private lateinit var viewModel: TaskViewModel

    val tasks = listOf(
        Task("Task 1", "Description 1", false),
        Task("Task 2", "Description 2", true)
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() = runBlocking {
       repository = Mockito.mock(TaskRepository::class.java)
        viewModel = TaskViewModel(repository)
    }

    @Test
    fun allTasks_checkNumberOfTasks() = runTest {
        val flow = flowOf(tasks)
        `when`(repository.getTasks()).thenReturn(flow)
        val tasksCount = flow.first().size
        assertEquals(tasksCount, 2)
    }

    @Test
    fun testMarkTaskAsDone() = runTest {
        val task = Task(
            "Task 1", "Description 1", false
        )
        viewModel.markTaskAsDone(task)
        verify(repository).update(task.copy(completed = true))
    }

}