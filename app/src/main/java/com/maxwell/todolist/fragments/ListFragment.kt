package com.maxwell.todolist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.maxwell.todolist.R
import com.maxwell.todolist.data.task.TaskAdapter
import com.maxwell.todolist.data.task.TasksViewModel


class ListFragment : Fragment() {
    private lateinit var tasksViewModel: TasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_list, container, false)

        val rvTaskList = v.findViewById<RecyclerView>(R.id.rvTaskList)
        val adapter = TaskAdapter()
        rvTaskList.adapter = adapter
        rvTaskList.layoutManager = LinearLayoutManager(requireContext())

        tasksViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)
        tasksViewModel.tasks.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        v.findViewById<FloatingActionButton>(R.id.fabAddTask).setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return v
    }
}