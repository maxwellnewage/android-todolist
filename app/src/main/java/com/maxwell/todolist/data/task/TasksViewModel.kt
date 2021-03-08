package com.maxwell.todolist.data.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxwell.todolist.data.TodoListDatabase
import com.maxwell.todolist.data.task.Task
import com.maxwell.todolist.data.task.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel(application: Application) : AndroidViewModel(application) {
    val tasks: LiveData<List<Task>>
    private val repository: TaskRepository

    init {
        val taskDao = TodoListDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(taskDao)
        tasks = repository.tasks
    }

    fun addTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }
}