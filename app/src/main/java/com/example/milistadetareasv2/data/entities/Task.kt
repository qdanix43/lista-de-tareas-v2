package com.example.milistadetareasv2.data.entities

import com.example.milistadetareasv2.utils.DatabaseManager

data class Task(var id: Int, val name: String, var isCompleted: Boolean) {

    companion object {
        const val TABLE_NAME: String = "Task"
        const val _ID: String = DatabaseManager.COLUMN_NAME_ID
        const val COLUMN_COMPLETED: String = "completed"
        const val COLUMN_NAME: String = "name"
    }
}
