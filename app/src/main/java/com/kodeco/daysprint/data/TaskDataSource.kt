package com.kodeco.daysprint.data

import dagger.Provides
import kotlinx.coroutines.flow.Flow

interface TaskDataSource {
    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun getTaskById(id: String): Task?

    suspend fun update(task: Task)

    fun getTasks(): Flow<List<Task>>
}