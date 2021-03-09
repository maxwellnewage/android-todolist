package com.maxwell.todolist.task.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.maxwell.todolist.R
import com.maxwell.todolist.task.TaskAdapter
import com.maxwell.todolist.task.TasksViewModel


class ListFragment : Fragment() {
    private lateinit var tasksViewModel: TasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        val v = inflater.inflate(R.layout.fragment_list, container, false)

        val rvTaskList = v.findViewById<RecyclerView>(R.id.rvTaskList)
        val adapter = TaskAdapter()
        rvTaskList.adapter = adapter
        rvTaskList.layoutManager = LinearLayoutManager(requireContext())

        tasksViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)
        tasksViewModel.tasks.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)

            if(it.isEmpty()) {
                v.findViewById<View>(R.id.iEmptyList).visibility = View.VISIBLE
            }
        })

        v.findViewById<FloatingActionButton>(R.id.fabAddTask).setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return v
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            removeAllTasks()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun removeAllTasks() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            tasksViewModel.removeAllTasks()
            Toast.makeText(context, "All tasks removed!", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("No") { _, _ -> }

        builder.setTitle("Remove All Tasks")
        builder.setMessage("Are you sure you want to delete all tasks?")
        builder.show()
    }
}