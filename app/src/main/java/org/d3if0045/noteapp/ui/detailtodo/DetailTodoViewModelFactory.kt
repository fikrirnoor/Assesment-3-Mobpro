package org.d3if0045.noteapp.ui.detailtodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DetailTodoViewModelFactory (
    private val db: org.d3if0045.noteapp.data.db.TodoDao,
    private val todoId : Long
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailTodoViewModel::class.java)) {
            return DetailTodoViewModel(db, todoId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}