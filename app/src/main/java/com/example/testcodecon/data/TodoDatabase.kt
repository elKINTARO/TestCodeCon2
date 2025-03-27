package com.example.testcodecon.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

@Database(entities = [Todo::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TodoDatabase : RoomDatabase() {
    
    abstract fun todoDao(): TodoDao
    
    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null
        
        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database"
                )
                .addCallback(TodoDatabaseCallback())
                .build()
                INSTANCE = instance
                instance
            }
        }
        
        private class TodoDatabaseCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        // Add sample data when the database is created
                        val todoDao = database.todoDao()
                        
                        // Sample todos
                        val todo1 = Todo(
                            title = "Complete Android Project",
                            description = "Finish the TODO app implementation",
                            priority = Priority.HIGH
                        )
                        
                        val todo2 = Todo(
                            title = "Buy groceries",
                            description = "Milk, eggs, bread, and vegetables",
                            priority = Priority.MEDIUM
                        )
                        
                        val todo3 = Todo(
                            title = "Go for a run",
                            description = "30 minutes jogging in the park",
                            priority = Priority.LOW
                        )
                        
                        todoDao.insertTodo(todo1)
                        todoDao.insertTodo(todo2)
                        todoDao.insertTodo(todo3)
                    }
                }
            }
        }
    }
}
