package com.example.w7_d5_timerapp_db.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TimerDao {

    @Query("SELECT * FROM GoalsTable ORDER BY g_id ASC")
    fun getGoals(): LiveData<List<Goal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGoal(goal: Goal)

    @Update
    suspend fun updateGoal(goal: Goal)

    @Delete
    suspend fun deleteGoal(goal: Goal)

    @Transaction
    @Query("SELECT * FROM TasksTable ORDER BY t_id ASC")
    fun getTasks(): LiveData<List<Task>>

    //@Transaction
    //@Query("SELECT * FROM TasksTable WHERE g_id = :goal_id ORDER BY t_id ASC")
    //fun getTasks(goal_id : Int): LiveData<List<Task>>

    //@Transaction
    //@Query("SELECT * FROM GoalsTable WHERE g_id = :goal_id ORDER BY g_id ASC")
    //fun getGoalWithTasks(goal_id : Int): LiveData<List<Goal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

}
