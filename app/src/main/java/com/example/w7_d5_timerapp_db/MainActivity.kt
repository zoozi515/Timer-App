package com.example.w7_d5_timerapp_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.w7_d5_timerapp_db.adapters.GoalAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var rvNotes: RecyclerView
    private lateinit var goalsAdapter: GoalAdapter

    private lateinit var goalEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var submitBtn: Button
    private lateinit var deleteBtn: Button
    private lateinit var updateBtn: Button

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getGoals().observe(this, {
                goals -> goalsAdapter.updateRecycleView(goals)
        })

        goalEditText = findViewById(R.id.goalEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)

        submitBtn = findViewById(R.id.saveButton)
        submitBtn.setOnClickListener {
            mainViewModel.addGoal(goalEditText.text.toString(),descriptionEditText.text.toString(),"no icon", "uncompleted")
            clearSetting()
        }

        updateBtn = findViewById(R.id.updateButton)
        updateBtn.setOnClickListener {
            mainViewModel.editGoal(2,goalEditText.text.toString(),descriptionEditText.text.toString(),"icon", "completed")
            clearSetting()
        }

        deleteBtn = findViewById(R.id.deleteButton)
        deleteBtn.setOnClickListener {
            mainViewModel.deleteGoal(4)
            clearSetting()
        }

        rvNotes = findViewById(R.id.recyclerView)
        goalsAdapter = GoalAdapter(this)
        rvNotes.adapter = goalsAdapter
        rvNotes.layoutManager = LinearLayoutManager(this)
    }

    fun clearSetting(){
        goalEditText.text.clear()
        goalEditText.clearFocus()
        descriptionEditText.text.clear()
        descriptionEditText.clearFocus()
    }
}