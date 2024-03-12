package com.example.milistadetareasv2.data.providers

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.milistadetareasv2.data.entities.Task
import com.example.milistadetareasv2.utils.DatabaseManager

class TaskDAO(context: Context) {

    private var databaseManager: DatabaseManager = DatabaseManager(context)

    fun insert(task: Task): Task {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Task.COLUMN_NAME, task.name)
        values.put(Task.COLUMN_COMPLETED, task.isCompleted)

        val newRowId = db.insert(Task.TABLE_NAME, null, values)
        Log.i("DATABASE", "New record id: $newRowId")

        db.close()

        task.id = newRowId.toInt()
        return task
    }

    fun update(task: Task) {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Task.COLUMN_NAME, task.name)
        values.put(Task.COLUMN_COMPLETED, task.isCompleted)

        val rowsUpdated = db.update(
            Task.TABLE_NAME,
            values,
            "${Task._ID} = ?",
            arrayOf(task.id.toString())
        )
        Log.i("DATABASE", "Updated record id: ${task.id}")

        db.close()
    }

    fun delete(task: Task) {
        val db = databaseManager.writableDatabase

        val deletedRows = db.delete(
            Task.TABLE_NAME,
            "${Task._ID} = ?",
            arrayOf(task.id.toString())
        )
        Log.i("DATABASE", "Deleted rows: $deletedRows")

        db.close()
    }

    @SuppressLint("Range")
    fun find(id: Int): Task? {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Task.TABLE_NAME,
            null,
            "${Task._ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        var task: Task? = null

        if (cursor.moveToNext()) {
            val taskId = cursor.getInt(cursor.getColumnIndex(Task._ID))
            val taskName = cursor.getString(cursor.getColumnIndex(Task.COLUMN_NAME))
            val done = cursor.getInt(cursor.getColumnIndex(Task.COLUMN_COMPLETED)) == 1

            task = Task(taskId, taskName, done)
        }

        cursor.close()
        db.close()

        return task
    }

    @SuppressLint("Range")
    fun findAll(): List<Task> {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Task.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val list: MutableList<Task> = mutableListOf()

        while (cursor.moveToNext()) {
            val taskId = cursor.getInt(cursor.getColumnIndex(Task._ID))
            val taskName = cursor.getString(cursor.getColumnIndex(Task.COLUMN_NAME))
            val done = cursor.getInt(cursor.getColumnIndex(Task.COLUMN_COMPLETED)) == 1

            val task = Task(taskId, taskName, done)
            list.add(task)
        }

        cursor.close()
        db.close()

        return list
    }
}
