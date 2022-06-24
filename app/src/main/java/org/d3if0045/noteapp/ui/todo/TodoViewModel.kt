package org.d3if0045.noteapp.ui.todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.d3if0045.noteapp.data.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoViewModel (private val db: org.d3if0045.noteapp.data.db.TodoDao): ViewModel()  {

    private val hasilTodo = MutableLiveData<Todo?>()

    val data = db.getDataTodo()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }

    fun getHasilTodo(): LiveData<Todo?> = hasilTodo

    fun mulaiNavigasi() {
        hasilTodo.value = hasilTodo.value
    }
    fun selesaiNavigasi() {
        hasilTodo.value = null
    }
    fun getNavigasi() : LiveData<Todo?> = hasilTodo

}