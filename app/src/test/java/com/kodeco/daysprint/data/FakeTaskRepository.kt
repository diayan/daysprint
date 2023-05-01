package com.kodeco.daysprint.data

import kotlinx.coroutines.flow.Flow

class FakeTaskRepository(
    var tasks: MutableList<Task>? = mutableListOf()
): ITaskRepository {
    override suspend fun insertTask(task: Task) {
        tasks?.add(task)
    }

    override suspend fun deleteTask(task: Task) {
        tasks?.remove(task)
    }

    override suspend fun getTaskById(id: String): Task? {
        TODO("Not yet implemented")
    }

    override suspend fun update(task: Task) {
        TODO("Not yet implemented")
    }

    override fun getTasks(): Flow<List<Task>> {
        TODO("Not yet implemented")
    }

}