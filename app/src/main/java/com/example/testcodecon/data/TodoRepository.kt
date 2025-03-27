package com.example.testcodecon.data

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoDao) {
    
    val allTodos: LiveData<List<Todo>> = todoDao.getAllTodos()
    val activeTodos: LiveData<List<Todo>> = todoDao.getActiveTodos()
    val completedTodos: LiveData<List<Todo>> = todoDao.getCompletedTodos()
    
    suspend fun insertTodo(todo: Todo): Long {
        return todoDao.insertTodo(todo)
    }
    
    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }
    
    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }
    
    suspend fun deleteCompletedTodos() {
        todoDao.deleteCompletedTodos()
    }
    
    suspend fun toggleTodoStatus(todo: Todo) {
        val updatedTodo = todo.copy(isCompleted = !todo.isCompleted)
        todoDao.updateTodo(updatedTodo)
    }
}
