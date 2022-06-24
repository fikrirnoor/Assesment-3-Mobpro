package org.d3if0045.noteapp.data.model

import org.d3if0045.noteapp.data.db.TodoEntity

fun TodoEntity.inputTodo(): Todo {
    val title = title
    return Todo(title)
}