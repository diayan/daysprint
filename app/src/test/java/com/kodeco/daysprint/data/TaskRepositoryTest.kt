/*
 * Copyright (c) 2020 Razeware LLC
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

package com.kodeco.daysprint.data

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class TaskRepositoryTest {
    //TODO: create test data
    private val task1 = Task("Title1", description = "Some test", completed = true)
    private val task2 = Task("Title2", description = "Test two", completed = false)
    private val task3 = Task("Title3", description = "Test three", completed = false)
    private val localTasks = mutableListOf(task1, task2).sortedBy { it.id }
    private val newTask = mutableListOf(task3)

    private lateinit var taskLocalDataSource: FakeDataSource
    //class under test
    private lateinit var taskRepository: TaskRepository

    //TODO: Setup system under test
    @Before
    fun createRepository() {
        taskLocalDataSource = FakeDataSource(
            localTasks.toMutableList()
        )
        taskRepository = TaskRepository(
            taskLocalDataSource
        )
    }

    //TODO: Test to check getting tests from data source
    @Test
    fun getTasks_GetAllTasksFromLocal() = runBlocking {
        val tasks = taskRepository.getTasks().first()
        assertEquals(tasks, localTasks)
    }

    //TODO: Test saving tasks to the db
    @Test
    fun saveTask_SaveTaskToLocal() = runBlocking {
        taskRepository.insertTask(task3)
        val task = taskRepository.getTaskById(task3.id)
        assertEquals(newTask.first(), task)
    }


    @Test
    fun deleteTask_deleteASpecificTask() = runBlocking {
        taskRepository.deleteTask(task3)

        //assertEquals()
    }

}