package com.example.testcodecon.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.testcodecon.data.Todo
import com.example.testcodecon.data.TodoDatabase
import com.example.testcodecon.data.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: TodoRepository
    val allTodos: LiveData<List<Todo>>
    val activeTodos: LiveData<List<Todo>>
    val completedTodos: LiveData<List<Todo>>
    
    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        allTodos = repository.allTodos
        activeTodos = repository.activeTodos
        completedTodos = repository.completedTodos
    }
    
    fun insertTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertTodo(todo)
    }
    
    fun updateTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateTodo(todo)
    }
    
    fun deleteTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTodo(todo)
    }
    
    fun deleteCompletedTodos() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteCompletedTodos()
    }
    
    fun toggleTodoStatus(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.toggleTodoStatus(todo)
    }
}
