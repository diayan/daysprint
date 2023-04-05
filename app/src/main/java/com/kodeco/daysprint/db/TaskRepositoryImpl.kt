package com.kodeco.daysprint.db

import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val taskDao: TaskDao
): TaskRepository {
    override suspend fun insertTask(task: Task) {
        taskDao.insertTodo(task = task)
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTodo(task)
    }

    override suspend fun getTaskById(id: Int): Task? {
        return taskDao.getTodoById(id)
    }

    override fun getTasks(): Flow<List<Task>> {
        return  taskDao.getTodos()
    }
}