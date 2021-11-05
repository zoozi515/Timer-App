package com.example.w7_d5_timerapp_db.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.w7_d5_timerapp_db.MainActivity
import com.example.w7_d5_timerapp_db.database.Goal
import com.example.w7_d5_timerapp_db.databinding.GoalRowBinding

class GoalAdapter(val activity: MainActivity):
    RecyclerView.Adapter<GoalAdapter.ItemViewHolder>() {
    var goals = emptyList<Goal>()

    class ItemViewHolder(val binding: GoalRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalAdapter.ItemViewHolder {
        return ItemViewHolder(
            GoalRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: GoalAdapter.ItemViewHolder, position: Int) {
        val goal = goals[position]
        holder.binding.apply {
            goalTextView.text = goal.g_title //name of the entity column
            Log.d("TAG RV", "tv text is: ${goalTextView.text}")
            llGoalRV.setOnClickListener {
                activity.updateSelectedGoal(goal)
            }
        }
    }

    override fun getItemCount() = goals.size

    fun updateRecycleView(new_goals: List<Goal>) {
        Log.d("TAG RV", "UPDATING RV")
        Log.d("TAG RV", "NEW RV: \n${new_goals.toString()}")
        this.goals = new_goals
        this.notifyDataSetChanged()
    }
}