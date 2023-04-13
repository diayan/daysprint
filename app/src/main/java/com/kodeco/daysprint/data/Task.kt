package com.kodeco.daysprint.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    var title: String = "",
    val priority: String = "",
    val dueDate: String = "",
    val dueTime: String = "",
    val description: String = "",
    val completed: Boolean = false,

    @PrimaryKey
    val id: Int? = null //room will automatically generate it
)

fun Task?.hasDueDate(): Boolean {
    return this?.dueDate.orEmpty().isNotBlank()
}

fun Task?.hasDueTime(): Boolean {
    return this?.dueTime.orEmpty().isNotBlank()
}