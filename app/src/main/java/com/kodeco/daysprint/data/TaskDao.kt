package com.kodeco.daysprint.data

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.room.*
import com.kodeco.daysprint.DaySprintViewModel
import com.kodeco.daysprint.TASK_DEFAULT_ID
import com.kodeco.daysprint.ui.details.TaskDetailViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


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
