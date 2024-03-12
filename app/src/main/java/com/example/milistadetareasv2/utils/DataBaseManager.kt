package com.example.milistadetareasv2.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.milistadetareasv2.data.entities.Task

class DatabaseManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "to_do_app.db"
        const val DATABASE_VERSION = 2 // Aumenta la versión de la base de datos para la actualización
        const val COLUMN_NAME_ID = "id"

        private const val SQL_CREATE_TABLE =
            "CREATE TABLE ${Task.TABLE_NAME} (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Task.COLUMN_NAME} TEXT," +
                    "${Task.COLUMN_COMPLETED} INTEGER)" // Cambia BOOLEAN a INTEGER

        private const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${Task.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion == 1 && newVersion == 2) { // Comprueba si la versión anterior es 1 y la nueva versión es 2
            db.execSQL(SQL_DELETE_TABLE) // Borra la tabla anterior
            onCreate(db) // Crea una nueva tabla con los cambios necesarios
        }
    }

    // Método para destruir la base de datos
    fun destroyDatabase() {
        val db = writableDatabase
        db.execSQL(SQL_DELETE_TABLE)
        onCreate(db)
    }
}
