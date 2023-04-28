package com.kodeco.daysprint.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TaskRepository(
    private val taskDao: TaskDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): TaskDataSource {
    override suspend fun insertTask(task: Task) =
        withContext(ioDispatcher) {
            taskDao.insertTask(task = task)
    }

    override suspend fun deleteTask(task: Task)  =
        withContext(ioDispatcher){
        taskDao.deleteTask(task)
    }

    override suspend fun getTaskById(id: String): Task? =
        withContext(ioDispatcher) {
            return@withContext taskDao.getTaskById(id)
    }

    override suspend fun update(task: Task) =
        withContext(ioDispatcher) {
        taskDao.insertTask(task)
    }

    override fun getTasks(): Flow<List<Task>> {
        return  taskDao.getTasks()
    }

}