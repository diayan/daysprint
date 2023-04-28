package com.kodeco.daysprint.data

import org.junit.Before

class TaskRepositoryImplTest {
    private val task = Task(title = "Title1", description = "description", completed = false)
    private val task1 = Task(title = "Title1", description = "description", completed = false)
    private val newTasks = listOf(task, task1).sortedBy { it.id }

    private lateinit var fakeTaskRepository: FakeTaskRepository
    private lateinit var taskRepositoryImpl: TaskRepositoryImpl

    @Before
    fun createRepository() {
        fakeTaskRepository = FakeTaskRepository(newTasks.toMutableList())
        //taskRepositoryImpl = TaskRepositoryImpl(fakeTaskRepository)
    }
}