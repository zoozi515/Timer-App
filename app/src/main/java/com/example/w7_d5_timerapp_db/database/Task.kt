package com.example.w7_d5_timerapp_db.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TasksTable")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val t_id: Int,
    val t_title: String,
    val t_state: String, //todo turn to boolean
    val t_time: String, //todo or date
    val goal_id: Int)