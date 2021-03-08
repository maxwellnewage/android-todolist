package com.maxwell.todolist.task

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maxwell.todolist.R
import com.maxwell.todolist.task.model.Task

class TaskVH(v: View): RecyclerView.ViewHolder(v) {
    val tvTaskName: TextView = v.findViewById(R.id.tvTaskName)
    val tvTaskDesc: TextView = v.findViewById(R.id.tvTaskDesc)
    val tvPriority: TextView = v.findViewById(R.id.tvPriority)
}

class TaskAdapter: RecyclerView.Adapter<TaskVH>() {

    private var taskList = emptyList<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH {
        return TaskVH(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_task, parent, false))
    }

    override fun onBindViewHolder(holder: TaskVH, position: Int) {
        val task = taskList[position]
        holder.tvTaskName.text = task.name


        if(task.description.isNullOrEmpty()) {
            holder.tvTaskDesc.visibility = GONE
        } else {
            holder.tvTaskDesc.text = task.description
        }

        if(task.priority > 2) {
            holder.tvPriority.text = "Priority is high"
        } else {
            holder.tvPriority.text = "Priority is low"
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(taskList: List<Task>) {
        this.taskList = taskList
        notifyDataSetChanged()
    }
}