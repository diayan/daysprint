/*
 * Copyright (c) 2023 Kodeco Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.kodeco.daysprint.ui.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kodeco.daysprint.data.Task
import com.kodeco.daysprint.data.TaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class TaskViewModelTest {
    private lateinit var repository: TaskRepository
    private lateinit var viewModel: TaskViewModel

    private val tasks = listOf(
        Task("Task 1", "Description 1", false),
        Task("Task 2", "Description 2", true)
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    //TODO: Setup the system under test
    @Before
    fun setupViewModel() = runBlocking {
       repository = Mockito.mock(TaskRepository::class.java)
        viewModel = TaskViewModel(repository)
    }

    //TODO: Test that tasks list is not empty
    @Test
    fun allTasks_checkThatTasksIsNotEmpty() = runTest {
        val flow = flowOf(tasks)
        `when`(repository.getTasks()).thenReturn(flow)
        val tasksCount = flow.first().size
        assertNotEquals(tasksCount, 0)
    }

    //TODO: Test if a task is completed
    @Test
    fun completedTask_iSTaskMarkAsCompleted() = runTest {
        val task = Task(
            "Task 1", "Description 1", false
        )
        viewModel.markTaskAsDone(task)
        verify(repository).update(task.copy(completed = true))
    }



}