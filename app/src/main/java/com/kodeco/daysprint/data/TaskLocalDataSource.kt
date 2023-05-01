package com.kodeco.daysprint.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskLocalDataSource @Inject constructor(
    private val taskDao: TaskDao,
) : TaskDataSource {
    override suspend fun insertTask(task: Task) =
        taskDao.insertTask(task = task)


    override suspend fun deleteTask(task: Task) =
        taskDao.deleteTask(task)

    override suspend fun getTaskById(id: String): Task? = taskDao.getTaskById(id)


    override suspend fun update(task: Task) =
        taskDao.insertTask(task)


    override fun getTasks(): Flow<List<Task>> = taskDao.getTasks()

}