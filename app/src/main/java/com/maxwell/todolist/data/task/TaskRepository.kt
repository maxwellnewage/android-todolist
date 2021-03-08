package com.maxwell.todolist.data.task

import android.app.Application
import androidx.lifecycle.LiveData
import com.maxwell.todolist.data.TodoListDatabase

class TaskRepository(private val taskDao: TaskDao) {
    val tasks: LiveData<List<Task>> = taskDao.getTasks()

    suspend fun addTask(task: Task) {
        taskDao.addTask(task)
    }
}