package com.kodeco.daysprint.data

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun getTaskById(id: Int): Task?

    suspend fun update(task: Task)

    fun getTasks(): Flow<List<Task>>
}