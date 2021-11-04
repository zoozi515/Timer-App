package com.example.w7_d5_timerapp_db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.w7_d5_timerapp_db.database.Goal
import com.example.w7_d5_timerapp_db.database.Task
import com.example.w7_d5_timerapp_db.database.TimerDatabase
import com.example.w7_d5_timerapp_db.database.TimerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
        private val repository: TimerRepository
        private val goals: LiveData<List<Goal>>
        private val tasks: LiveData<List<Task>>

        init {
            val timerDao = TimerDatabase.getDatabase(application).timerDao()
            repository = TimerRepository(timerDao)
            goals = repository.getGoals
            tasks = repository.getTasks
        }

        fun getGoals(): LiveData<List<Goal>> {
            return goals
        }

        fun addGoal(goalTitle: String, goalDescription : String, goalIcon: String, goalState: String){
            CoroutineScope(Dispatchers.IO).launch {
                repository.addGoal(Goal(0,goalTitle, goalDescription, goalIcon, goalState)) //no need to specify the id bcz it's auto
            }
        }

        fun editGoal(goalID: Int, goalTitle: String, goalDescription : String, goalIcon: String, goalState: String){
            CoroutineScope(Dispatchers.IO).launch {
                repository.updateGoal(Goal(goalID,goalTitle, goalDescription, goalIcon, goalState))
            }
        }

        fun deleteGoal(goalID: Int){
            CoroutineScope(Dispatchers.IO).launch {
                repository.deleteGoal(Goal(goalID,"","","",""))
            }
        }

        fun getTasks(): LiveData<List<Task>> {
            return tasks
        }

        fun addTask(taskID: Int, taskTitle: String, taskState: String, goalID: Int){
            CoroutineScope(Dispatchers.IO).launch {
                repository.addTask(Task(0,taskTitle, taskState, goalID)) //no need to specify the id bcz it's auto
            }
        }

        fun editTask(taskID: Int, taskTitle: String, taskState: String, goalID: Int){
            CoroutineScope(Dispatchers.IO).launch {
                repository.updateTask(Task(taskID,taskTitle, taskState, goalID))
            }
        }

        fun deleteTask(taskID: Int){
            CoroutineScope(Dispatchers.IO).launch {
                repository.deleteTask(Task(taskID,"","",0))
            }
        }


}