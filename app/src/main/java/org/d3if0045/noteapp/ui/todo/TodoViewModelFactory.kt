package org.d3if0045.noteapp.ui.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TodoViewModelFactory (
    private val db: org.d3if0045.noteapp.data.db.TodoDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}