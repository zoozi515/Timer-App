package com.example.w7_d5_timerapp_db.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.w7_d5_timerapp_db.MainActivity
import com.example.w7_d5_timerapp_db.MainTask
import com.example.w7_d5_timerapp_db.database.Goal
import com.example.w7_d5_timerapp_db.database.Task
import com.example.w7_d5_timerapp_db.databinding.GoalRowBinding
import com.example.w7_d5_timerapp_db.databinding.TaskRowBinding

class TaskAdapter (private val activity: MainTask):
    RecyclerView.Adapter<TaskAdapter.ItemViewHolder>() {
    private var tasks = emptyList<Task>()

    class ItemViewHolder(val binding: TaskRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.ItemViewHolder {
        return ItemViewHolder(
            TaskRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TaskAdapter.ItemViewHolder, position: Int) {
        val task = tasks[position]

        holder.binding.apply {
            taskTextView.text = task.t_title +" "+ task.t_state +" "+ task.g_id //name of the entity column
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

    override fun getItemCount() = tasks.size

    fun updateRecycleView(new_tasks: List<Task>) {
        this.tasks = new_tasks
        notifyDataSetChanged()
    }
}