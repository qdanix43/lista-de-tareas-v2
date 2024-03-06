// MainActivity.kt
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.milistadetareasv2.adapters.TaskAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tasks = mutableListOf(
            Task(1, "Tarea 1", false),
            Task(2, "Tarea 2", true),
            Task(3, "Tarea 3", false)
        )

        recyclerView = findViewById(R.id.recycler_view)
        adapter = TaskAdapter(tasks)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}