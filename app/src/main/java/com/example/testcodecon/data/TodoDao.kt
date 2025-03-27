package com.example.testcodecon.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos ORDER BY createdDate DESC")
    fun getAllTodos(): LiveData<List<Todo>>
    
    @Query("SELECT * FROM todos WHERE isCompleted = 0 ORDER BY priority DESC, createdDate DESC")
    fun getActiveTodos(): LiveData<List<Todo>>
    
    @Query("SELECT * FROM todos WHERE isCompleted = 1 ORDER BY createdDate DESC")
    fun getCompletedTodos(): LiveData<List<Todo>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo): Long
    
    @Update
    suspend fun updateTodo(todo: Todo)
    
    @Delete
    suspend fun deleteTodo(todo: Todo)
    
    @Query("DELETE FROM todos WHERE isCompleted = 1")
    suspend fun deleteCompletedTodos()
}
