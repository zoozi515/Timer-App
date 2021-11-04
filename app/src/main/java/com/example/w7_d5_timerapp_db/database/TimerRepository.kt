package com.example.w7_d5_timerapp_db.database

import androidx.lifecycle.LiveData

class TimerRepository(private val timerDao: TimerDao) {

    val getGoals: LiveData<List<Goal>> = timerDao.getGoals()

    suspend fun addGoal(goal: Goal){
        timerDao.addGoal(goal)
    }

    suspend fun updateGoal(goal: Goal){
        timerDao.updateGoal(goal)
    }

    suspend fun deleteGoal(goal : Goal){
        timerDao.deleteGoal(goal)
    }

    val getTasks: LiveData<List<Task>> = timerDao.getTasks()

    suspend fun addTask(task: Task){
        timerDao.addTask(task)
    }

    suspend fun updateTask(task: Task){
        timerDao.updateTask(task)
    }

    suspend fun deleteTask(task : Task){
        timerDao.deleteTask(task)
    }
}
