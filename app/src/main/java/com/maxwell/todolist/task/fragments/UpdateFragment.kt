package com.maxwell.todolist.task.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.maxwell.todolist.R
import com.maxwell.todolist.task.TasksViewModel
import com.maxwell.todolist.task.model.Task

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var tasksViewModel: TasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        val v = inflater.inflate(R.layout.fragment_update, container, false)

        tasksViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)

        val etName = v.findViewById<EditText>(R.id.etName)
        etName.setText(args.task.name)

        val etDescription = v.findViewById<EditText>(R.id.etDescription)
        etDescription.setText(args.task.description)

        val etPriority = v.findViewById<EditText>(R.id.etPriority)
        etPriority.setText(args.task.priority.toString())

        v.findViewById<Button>(R.id.btUpdateTask).setOnClickListener {
            val name = etName.text.toString()
            val desc = etDescription.text?.toString()
            val priority = etPriority.text.toString()

            if(name.isNotEmpty() && priority.isNotEmpty()) {
                updateTask(name, desc, priority.toInt())
            } else {
                Toast.makeText(context, "Fill the required data!", Toast.LENGTH_SHORT).show()
            }
        }

        return v
    }

    private fun updateTask(name: String, desc: String?, priority: Int) {
        val updatedTask = Task(args.task.uid, name, desc, priority)
        tasksViewModel.updateTask(updatedTask)
        Toast.makeText(context, "Task updated!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            removeTask()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun removeTask() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            tasksViewModel.removeTask(args.task)
            Toast.makeText(context, "Task ${args.task.name} removed!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        builder.setNegativeButton("No") { _, _ -> }

        builder.setTitle("Delete ${args.task.name}")
        builder.setMessage("Are you sure you want to delete ${args.task.name}?")
        builder.show()
    }
}