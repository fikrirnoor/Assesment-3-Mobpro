package org.d3if0045.noteapp.ui.detailtodo

import androidx.lifecycle.ViewModel

class DetailTodoViewModel (private val db: org.d3if0045.noteapp.data.db.TodoDao, private val todoId : Long): ViewModel()  {

    val data = db.getOneDataTodo(todoId)

}