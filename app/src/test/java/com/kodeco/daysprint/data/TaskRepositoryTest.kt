package com.kodeco.daysprint.data

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class TaskRepositoryTest {
    private val task1 = Task("Title1", description = "Some test", completed = true)
    private val task2 = Task("Title2", description = "Test two", completed = false)
    private val task3 = Task("Title3", description = "Test three", completed = false)
    private val localTasks = mutableListOf(task1, task2).sortedBy { it.id }
    private val newTask = mutableListOf(task3)

    private lateinit var taskLocalDataSource: FakeDataSource

    //class under test
    private lateinit var taskRepository: TaskRepository

    @Before
    fun createRepository() {
        taskLocalDataSource = FakeDataSource(
            localTasks.toMutableList()
        )
        taskRepository = TaskRepository(
            taskLocalDataSource
        )
    }

    @Test
    fun getTasks_GetAllTasksFromLocal() = runBlocking {
        val tasks = taskRepository.getTasks().first()
        assertEquals(tasks, localTasks)
    }

    @Test
    fun saveTask_SaveTaskToLocal() = runBlocking {
        taskRepository.insertTask(task3)
        val task = taskRepository.getTaskById(task3.id)
        assertEquals(newTask.first(), task)
    }

    @Test
    fun clearTasks_clearAllTasks() = runBlocking {
        //taskRepository.clearTasks()
        //assertEquals()
    }

    @Test
    fun deleteTask_deleteASpecificTask() = runBlocking {
        taskRepository.deleteTask(task3)

        //assertEquals()
    }

}