package com.maxwell.todolist.task.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maxwell.todolist.task.model.Task


@Database(entities = [Task::class], version = 1)
abstract class TodoListDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TodoListDatabase? = null

        fun getDatabase(context: Context): TodoListDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoListDatabase::class.java,
                    "todolist_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}