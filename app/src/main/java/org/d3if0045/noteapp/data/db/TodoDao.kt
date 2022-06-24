package org.d3if0045.noteapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Insert
    fun insert(todo: org.d3if0045.noteapp.data.db.TodoEntity)

    @Query("SELECT * FROM todo ORDER BY id DESC")
    fun getDataTodo(): LiveData<List<org.d3if0045.noteapp.data.db.TodoEntity?>>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getOneDataTodo(id: Long): LiveData<org.d3if0045.noteapp.data.db.TodoEntity?>

    @Query("DELETE FROM todo")
    fun clearData()
}