package org.d3if0045.noteapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [org.d3if0045.noteapp.data.db.TodoEntity::class], version = 1, exportSchema = false)
abstract class TodoDb : RoomDatabase() {
    abstract val dao: org.d3if0045.noteapp.data.db.TodoDao
    companion object {
        @Volatile
        private var INSTANCE: org.d3if0045.noteapp.data.db.TodoDb? = null
        fun getInstance(context: Context): org.d3if0045.noteapp.data.db.TodoDb {
            synchronized(this) {
                var instance = org.d3if0045.noteapp.data.db.TodoDb.Companion.INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        org.d3if0045.noteapp.data.db.TodoDb::class.java,
                        "todo.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    org.d3if0045.noteapp.data.db.TodoDb.Companion.INSTANCE = instance
                }
                return instance
            }
        }
    }
}
