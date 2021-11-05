package com.example.w7_d5_timerapp_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.w7_d5_timerapp_db.adapters.GoalAdapter
import com.example.w7_d5_timerapp_db.adapters.TaskAdapter
import com.example.w7_d5_timerapp_db.database.Goal
import com.example.w7_d5_timerapp_db.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var taskViewModel: MainViewModel
    private  var selectedGoal: Goal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeBinding()
        initializeRecycler()

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getGoals().observe(this, {goals -> adapterGoal.updateRecycleView(goals)})

        taskViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.addGoal.setOnClickListener {
            Log.d("TAG MAIN", "ADD GOAL BUTTON PRESSED")
            mainViewModel.addGoal(binding.etGoal.text.toString(),"","", "uncompleted","00:00:00")
            binding.etGoal.text.clear()
            binding.etGoal.clearFocus()
        }

        binding.addTask.setOnClickListener {
            Log.d("TAG MAIN", "ADD TASK BUTTON PRESSED")
            if (selectedGoal != null){
                taskViewModel.addTask(0,binding.etTask.text.toString(), "uncompleted","00:00:00", selectedGoal!!.g_id)
            }
            else {
                Log.d("TAG MAIN", "ADD TASK BUTTON --> NO SELECTED GOAL")
            }
            binding.etTask.text.clear()
            binding.etTask.clearFocus()
        }

    }

    fun updateSelectedGoal(newGoal: Goal){
        selectedGoal = newGoal
        binding.tvGoal.text = newGoal.g_title
        getTasks(selectedGoal!!.g_id)
    }

    fun getTasks(goalId:Int){
        taskViewModel.getTasks(goalId).observe(this, { tasks -> adapterTask.updateRecycleView(tasks) })
    }

    private lateinit var binding: ActivityMainBinding
    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("TAG MAIN", "Binding INITIALIZED")
    }

    private lateinit var adapterGoal: GoalAdapter
    private lateinit var adapterTask: TaskAdapter
    private fun initializeRecycler() {
        adapterGoal = GoalAdapter(this)
        binding.rvGoal.adapter = adapterGoal
        binding.rvGoal.layoutManager = LinearLayoutManager(this)

        adapterTask = TaskAdapter()
        binding.rvTask.adapter = adapterTask
        binding.rvTask.layoutManager = LinearLayoutManager(this)

        Log.d("TAG MAIN", "RV INITIALIZED")
    }


}