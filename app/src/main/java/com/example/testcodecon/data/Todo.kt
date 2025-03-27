package com.example.testcodecon.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val isCompleted: Boolean = false,
    val priority: Priority = Priority.MEDIUM,
    val createdDate: Date = Date(),
    val dueDate: Date? = null
)

enum class Priority {
    LOW, MEDIUM, HIGH
}
