package com.kodeco.daysprint.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Task(
    var title: String = "",
    val priority: String = "",
    val description: String = "",
    val completed: Boolean = false,
    @PrimaryKey @ColumnInfo(name = "taskid")
    var id: String = UUID.randomUUID().toString()

)