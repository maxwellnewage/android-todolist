package com.maxwell.todolist.data.task

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY priority")
    fun getTasks(): LiveData<List<Task>>

    @Insert
    fun addTask(task: Task)

    @Delete
    fun removeTask(task: Task)

    @Update
    fun updateTask(task: Task)
}