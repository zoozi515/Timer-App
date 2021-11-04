package com.example.w7_d5_timerapp_db.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.w7_d5_timerapp_db.MainActivity
import com.example.w7_d5_timerapp_db.database.Goal
import com.example.w7_d5_timerapp_db.databinding.GoalRowBinding

class GoalAdapter(private val activity: MainActivity):
    RecyclerView.Adapter<GoalAdapter.ItemViewHolder>() {
    private var goals = emptyList<Goal>()

    class ItemViewHolder(val binding: GoalRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalAdapter.ItemViewHolder {
        return ItemViewHolder(
            GoalRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: GoalAdapter.ItemViewHolder, position: Int) {
        val goal = goals[position]

        holder.binding.apply {
            goalTextView.text = goal.g_title +" "+ goal.g_description +" "+  goal.g_icon +" "+  goal.g_state //name of the entity column
            if (position % 2 == 0) {
                holderLinearLayout.setBackgroundColor(Color.GRAY)
            }
            /*
            delete.setOnClickListener {
                activity.mainViewModel.deleteGoal(goal.g_id)
            }
            ibDeleteNote.setOnClickListener {
                activity.mainViewModel.deleteNote(note.id)
            }*/
        }
    }

    override fun getItemCount() = goals.size

    fun updateRecycleView(new_goals: List<Goal>) {
        this.goals = new_goals
        notifyDataSetChanged()
    }
    // goals = items
}