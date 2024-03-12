package com.example.milistadetareasv2.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.milistadetareasv2.R
import com.example.milistadetareasv2.adapters.TaskAdapter
import com.example.milistadetareasv2.data.entities.Task
import com.example.milistadetareasv2.data.providers.TaskDAO

class MainActivity : AppCompatActivity() {

    private lateinit var taskList: List<Task>
    private lateinit var taskDAO: TaskDAO

    private lateinit var adapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addTaskButton: Button

    private var taskToDeletePosition: Int = -1 // Inicializa la variable con un valor inicial

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskDAO = TaskDAO(this)

        addTaskButton = findViewById(R.id.add_task_button)

        addTaskButton.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.recycler_view)

        adapter = TaskAdapter(mutableListOf())

        adapter.onTaskClickListener = { position: Int ->
            // Actualiza la variable taskToDeletePosition con la posición del elemento seleccionado
            taskToDeletePosition = position
        }

        adapter.onCompleteClickListener = { position: Int ->
            val task = taskList[position]
            task.isCompleted = !task.isCompleted
            taskDAO.update(task)
            refreshData()
        }

        adapter.onEditClickListener = { position: Int ->
            val taskToEdit = taskList[position]
            // Abre la actividad para editar la tarea
            val intent = Intent(this, EditTaskActivity::class.java)
            intent.putExtra("taskId", taskToEdit.id)
            startActivity(intent)
        }

        adapter.onDeleteClickListener = {
            val taskToDelete = taskList[it]
            taskDAO.delete(taskToDelete) // Llama al método de eliminar con el objeto Task
            refreshData()
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        refreshData()
    }

    fun refreshData() {
        taskList = taskDAO.findAll()
        adapter.updateList(taskList)
    }
}
