package com.maxwell.todolist.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.maxwell.todolist.task.data.TodoListDatabase
import com.maxwell.todolist.task.model.Task
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

    fun updateTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }

    fun removeTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeTask(task)
        }
    }

    fun removeAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeAllTasks()
        }
    }
}