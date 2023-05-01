package com.kodeco.daysprint.data

import kotlinx.coroutines.flow.Flow

interface ITaskRepository : TaskDataSource {
    override suspend fun insertTask(task: Task)

    override suspend fun deleteTask(task: Task)

    override suspend fun getTaskById(id: String): Task?

    override suspend fun update(task: Task)

    override fun getTasks(): Flow<List<Task>>
}