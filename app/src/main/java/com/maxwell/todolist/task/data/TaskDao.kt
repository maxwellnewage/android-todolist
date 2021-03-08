package com.maxwell.todolist.data.task

import androidx.lifecycle.LiveData
import androidx.room.*
import com.maxwell.todolist.data.task.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY priority")
    fun getTasks(): LiveData<List<Task>>

    @Insert
    suspend fun addTask(task: Task)

    @Delete
    fun removeTask(task: Task)

    @Update
    fun updateTask(task: Task)
}