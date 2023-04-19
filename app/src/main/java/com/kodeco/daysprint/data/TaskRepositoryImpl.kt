package com.kodeco.daysprint.data

import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val taskDao: TaskDao
): TaskRepository {
    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(task = task)
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    override suspend fun getTaskById(id: Int): Task? {
        return taskDao.getTaskById(id)
    }

    override suspend fun update(task: Task) {
        taskDao.insertTask(task)
    }

    override fun getTasks(): Flow<List<Task>> {
        return  taskDao.getTasks()
    }
}