package com.maxwell.todolist.task

import androidx.lifecycle.LiveData
import com.maxwell.todolist.task.data.TaskDao
import com.maxwell.todolist.task.model.Task

class TaskRepository(private val taskDao: TaskDao) {
    val tasks: LiveData<List<Task>> = taskDao.getTasks()

    suspend fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun removeTask(task: Task) {
        taskDao.removeTask(task)
    }

    suspend fun removeAllTasks() {
        taskDao.removeAllTasks()
    }
}