package com.maxwell.todolist.task.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.maxwell.todolist.task.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY priority")
    fun getTasks(): LiveData<List<Task>>

    @Insert
    suspend fun addTask(task: Task)

    @Delete
    suspend fun removeTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)
}