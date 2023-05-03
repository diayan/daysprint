package com.kodeco.daysprint.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDataSource(
    var tasks: MutableList<Task>? = mutableListOf()
): TaskDataSource {
    override suspend fun insertTask(task: Task) {
        tasks?.add(task)
    }

    override suspend fun deleteTask(task: Task) {
        tasks?.remove(task)
    }

    override suspend fun getTaskById(id: String): Task? {
        return tasks?.find { it.id == id }
    }

    override suspend fun update(task: Task) {
        TODO("Not yet implemented")
    }

    override fun getTasks(): Flow<List<Task>> {
        return flow {
            emit(tasks ?: emptyList())
        }
    }
}