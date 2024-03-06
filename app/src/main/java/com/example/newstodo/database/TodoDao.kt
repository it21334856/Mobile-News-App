package com.example.newstodo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TodoDao {
    @Insert
    suspend fun insert(todo:Todo)

    @Delete
    suspend fun delete(todo:Todo)

    @Query("SELECT * FROM Todo WHERE catName=:cat")
    fun getCatTodoItems(cat: String?):List<Todo>

    @Query("SELECT * FROM Todo WHERE id=:id")
    fun getOne(id:Int):Todo
}