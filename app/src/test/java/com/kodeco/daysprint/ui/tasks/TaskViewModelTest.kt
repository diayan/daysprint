package com.kodeco.daysprint.ui.tasks

import com.kodeco.daysprint.data.FakeTaskRepository
import org.junit.Before
import org.junit.Test

class TaskViewModelTest {
    private lateinit var repository: FakeTaskRepository

    private lateinit var viewModel: TaskViewModel

    //@get: Rule
    //var instantExecutorRule = InstantTaskExecutorRule

    @Before
    fun setupViewModel() {
      //  repository = TaskRepositoryImpl(repository)
    }

    @Test
    fun addNewTask_setsNewTaskEvent() {

    }
}