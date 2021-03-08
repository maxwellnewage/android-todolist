package com.maxwell.todolist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.maxwell.todolist.R
import com.maxwell.todolist.data.task.model.Task
import com.maxwell.todolist.data.task.TasksViewModel

class AddFragment : Fragment() {

    private lateinit var tasksViewModel: TasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_add, container, false)

        tasksViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)

        val etName = v.findViewById<EditText>(R.id.etName)
        val etDescription = v.findViewById<EditText>(R.id.etDescription)
        val etPriority = v.findViewById<EditText>(R.id.etPriority)

        v.findViewById<Button>(R.id.btAddTask).setOnClickListener {
            val name = etName.text.toString()
            val desc = etDescription.text?.toString()
            val priority = etPriority.text.toString()

            if(name.isNotEmpty() && priority.isNotEmpty()) {
                insertTasktoDB(name, desc, priority.toInt())
            } else {
                Toast.makeText(context, "Fill the required data!", Toast.LENGTH_SHORT).show()
            }

        }

        return v
    }

    private fun insertTasktoDB(name: String, desc: String?, priority: Int) {
        val task = Task(0, name, desc, priority)
        tasksViewModel.addTask(task)
        Toast.makeText(context, "Task added!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }
}