package com.kodeco.daysprint.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task : Task)

    @Delete
    suspend fun deleteTask(todo: Task)

    @Query("SELECT * FROM $TABLE_NAME WHERE taskid = :id")
    suspend fun getTaskById(id: String): Task?

    @Query("SELECT * FROM $TABLE_NAME")
    fun getTasks(): Flow<List<Task>>

//    @Query("SELECT * FROM task WHERE id = :id")
//    suspend fun update(id: Int): Task?

    companion object {
        const val TABLE_NAME = "task"
    }
}
