package com.kodeco.daysprint.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = TaskDao.TABLE_NAME)
data class Task(
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "completed")
    val completed: Boolean,
    @PrimaryKey @ColumnInfo(name = "taskid")
    val id: String = UUID.randomUUID().toString()
){
    companion object {
        val EMPTY = Task(
            title = "",
            description = "",
            completed = false
        )
    }
}