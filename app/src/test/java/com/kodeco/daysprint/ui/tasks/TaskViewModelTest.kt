package com.kodeco.daysprint.ui.tasks

import androidx.hilt.navigation.compose.hiltViewModel
import com.kodeco.daysprint.data.TaskRepository
import com.kodeco.daysprint.data.TaskRepositoryImpl
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TaskViewModelTest {
    private lateinit var repository: TaskRepository

    private lateinit var viewModel: TaskViewModel

    //@get: Rule
    //var instantExecutorRule = InstantTaskExecutorRule

    @Before
    fun setupViewModel() {
        //repository = TaskRepositoryImpl
    }

    @Test
    fun addNewTask_setsNewTaskEvent() {

    }
}