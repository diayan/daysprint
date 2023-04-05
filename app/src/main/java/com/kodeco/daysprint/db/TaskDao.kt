package com.kodeco.daysprint.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(task : Task)

    @Delete
    suspend fun deleteTodo(todo: Task)

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTodoById(id: Int): Task?

    @Query("SELECT * FROM task")
    fun getTodos(): Flow<List<Task>>
}
