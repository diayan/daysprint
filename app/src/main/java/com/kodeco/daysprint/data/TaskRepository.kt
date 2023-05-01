package com.kodeco.daysprint.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TaskRepository constructor(
    private val tasksLocalDataSource: TaskDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ITaskRepository {

    override suspend fun insertTask(task: Task) =
        withContext(ioDispatcher) {
            tasksLocalDataSource.insertTask(task = task)
        }

    override suspend fun deleteTask(task: Task) =
        withContext(ioDispatcher) {
            tasksLocalDataSource.deleteTask(task)
        }

    override suspend fun getTaskById(id: String): Task? =
        withContext(ioDispatcher) {
            return@withContext tasksLocalDataSource.getTaskById(id)
        }

    override suspend fun update(task: Task) =
        withContext(ioDispatcher) {
            tasksLocalDataSource.insertTask(task)
        }

    override fun getTasks(): Flow<List<Task>> {
        return tasksLocalDataSource.getTasks()
    }

}