package com.example.w7_d5_timerapp_db.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GoalsTable")
data class Goal(
    @PrimaryKey(autoGenerate = true)
    val g_id: Int,
    val g_title: String,
    val g_description: String, //todo make optional
    val g_icon: String, //bitmap
    val g_state: String, //completed - un (todo turn to boolean)
    val g_time: String) //todo make state and total time default

// what if we added tasks as a list