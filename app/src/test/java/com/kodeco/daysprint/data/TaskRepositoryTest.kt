package com.kodeco.daysprint.data

class TaskRepositoryTest {
    private val task1 = Task("Title1", description = "Some test", completed = true)
    private val task2 = Task("Title2", description = "Test two", completed = false)
    private val task3 = Task("Title3", description = "Test three", completed = false)

    private val localTasks = mutableListOf(task1, task2).sortedBy { it.id }
    private val newTask = mutableListOf(task3)

    private lateinit var taskLocalDataSource: FakeTaskRepository

}