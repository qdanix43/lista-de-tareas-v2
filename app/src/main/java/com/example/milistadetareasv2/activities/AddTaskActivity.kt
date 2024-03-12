package com.example.milistadetareasv2.activities // Declara el paquete al que pertenece la clase AddTaskActivity

import android.annotation.SuppressLint // Importa la anotación SuppressLint para controlar advertencias específicas del código
import androidx.appcompat.app.AppCompatActivity // Importa la clase base AppCompatActivity para actividades de la biblioteca de compatibilidad
import android.os.Bundle // Importa la clase Bundle para pasar datos entre actividades
import android.widget.Button // Importa la clase Button para manejar botones en la interfaz de usuario
import android.widget.EditText // Importa la clase EditText para manejar campos de entrada de texto en la interfaz de usuario
import com.example.milistadetareasv2.R // Importa el archivo de recursos R, que contiene referencias a los recursos de la aplicación
import com.example.milistadetareasv2.data.entities.Task
import com.example.milistadetareasv2.data.providers.TaskDAO


class AddTaskActivity: AppCompatActivity() { // Define la clase AddTaskActivity que extiende AppCompatActivity

    private lateinit var taskEditText: EditText // Declara una variable lateinit para el campo de texto de la tarea
    private lateinit var addTaskButton: Button // Declara una variable lateinit para el botón de agregar tarea

    private lateinit var taskDAO: TaskDAO

    @SuppressLint("MissingInflatedId") // Anota la función onCreate para suprimir advertencias de ID faltantes
    override fun onCreate(savedInstanceState: Bundle?) { // Sobrescribe el método onCreate para inicializar la actividad
        super.onCreate(savedInstanceState) // Llama al método onCreate de la clase base
        setContentView(R.layout.activity_add_task) // Establece el diseño de la actividad desde XML

        taskDAO = TaskDAO(this)

        taskEditText = findViewById(R.id.task_edit_text) // Inicializa el campo de texto de la tarea
        addTaskButton = findViewById(R.id.add_task_button) // Inicializa el botón de agregar tarea

        addTaskButton.setOnClickListener { // Establece un OnClickListener para el botón de agregar tarea
            val taskName = taskEditText.text.toString() // Obtiene el nombre de la tarea ingresado en el campo de texto
            if (taskName.isNotBlank()) { // Verifica si el nombre de la tarea no está en blanco
                // Aquí se puede agregar la lógica para crear una nueva tarea y agregarla a la base de datos o lista de tareas
                val task = Task(-1, taskName, false)
                taskDAO.insert(task)
                finish()
            }
        }
    }
}
