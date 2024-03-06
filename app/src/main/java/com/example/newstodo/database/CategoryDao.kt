package com.example.newstodo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("SELECT * FROM Category ")
    fun getAllCategoryItems():List<Category>

    @Query("SELECT * FROM Category WHERE id=:id")
    fun getOne(id:Int):Category
}