package com.example.w7_d5_timerapp_db

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.w7_d5_timerapp_db.adapters.TaskAdapter

class MainTask : AppCompatActivity() {

    private lateinit var rvNotes: RecyclerView
    private lateinit var tasksAdapter: TaskAdapter

    private lateinit var taskEditText: EditText
    private lateinit var submitBtn: Button
    private lateinit var deleteBtn: Button
    private lateinit var updateBtn: Button

    lateinit var taskViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_main)
        taskViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        taskViewModel.getTasks().observe(this, {
                tasks -> tasksAdapter.updateRecycleView(tasks)
        })

        taskEditText = findViewById(R.id.taskEditText)

        submitBtn = findViewById(R.id.saveButton)
        submitBtn.setOnClickListener {
            taskViewModel.addTask(0,taskEditText.text.toString(), "uncompleted",1)
            clearSetting()
        }

        updateBtn = findViewById(R.id.updateButton)
        updateBtn.setOnClickListener {
            taskViewModel.editTask(2,taskEditText.text.toString(), "completed",1)
            clearSetting()
        }

        deleteBtn = findViewById(R.id.deleteButton)
        deleteBtn.setOnClickListener {
            taskViewModel.deleteTask(4)
            clearSetting()
        }

        rvNotes = findViewById(R.id.recyclerView)
        tasksAdapter = TaskAdapter(this)
        rvNotes.adapter = tasksAdapter
        rvNotes.layoutManager = LinearLayoutManager(this)
    }

    fun clearSetting(){
        taskEditText.text.clear()
        taskEditText.clearFocus()
    }
}