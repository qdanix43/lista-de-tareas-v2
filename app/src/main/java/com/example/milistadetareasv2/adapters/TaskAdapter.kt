package com.example.milistadetareasv2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.milistadetareasv2.R
import com.example.milistadetareasv2.data.entities.Task // Asegúrate de importar la clase Task del paquete correcto

class TaskAdapter(private var tasks: MutableList<Task>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    lateinit var onEditClickListener: (Int) -> Unit
    lateinit var onTaskClickListener: (Int) -> Unit
    lateinit var onCompleteClickListener: (Int) -> Unit
    lateinit var onDeleteClickListener: (Int) -> Unit
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskName: TextView = itemView.findViewById(R.id.task_name)
        val taskCheckBox: CheckBox = itemView.findViewById(R.id.task_checkbox)
        val deleteTaskButton: ImageButton = itemView.findViewById(R.id.delete_task_button)

        init {
            // Define el listener para el clic en el CheckBox
            taskCheckBox.setOnCheckedChangeListener { checkbox, isChecked ->
                if (checkbox.isPressed) {
                    onCompleteClickListener(adapterPosition)
                }
            }
            // Define el listener para el clic largo en el elemento de la lista
            deleteTaskButton.setOnClickListener {
                onDeleteClickListener(adapterPosition)
            }

            itemView.setOnClickListener {
                onEditClickListener(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = tasks[position]
        holder.taskName.text = currentTask.name
        holder.taskCheckBox.isChecked = currentTask.isCompleted
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun updateList(list: List<Task>) {
        tasks = list.toMutableList()
        notifyDataSetChanged()
    }
}

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // ...

    init {
        // ...

        // Define el listener para el clic largo en el elemento de la lista
        itemView.setOnLongClickListener {
            onTaskLongClickListener(adapterPosition)
            true
        }
    }

    private fun onTaskLongClickListener(adapterPosition: Int) {

    }
}

