package com.example.w7_d5_timerapp_db.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GoalsTable")
data class Goal(
    @PrimaryKey(autoGenerate = true)
    val g_id: Int,
    val g_title: String,
    val g_description: String,
    val g_icon: String,
    val g_state: String)