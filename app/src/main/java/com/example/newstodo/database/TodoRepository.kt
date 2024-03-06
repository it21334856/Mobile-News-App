package com.example.newstodo.database

class TodoRepository (
    private val db:TodoDatabase
) {
    suspend fun insert(todo:Todo) = db.getTodoDao().insert(todo)
    suspend fun delete(todo:Todo) = db.getTodoDao().delete(todo)
    suspend fun insertcat(category: Category) = db.getCategoryDao().insert(category)
    suspend fun deletecat(category: Category) = db.getCategoryDao().delete(category)

    fun getCatTodoItems(cat: String?):List<Todo> = db.getTodoDao().getCatTodoItems(cat)
    fun getAllTodoCatItems():List<Category> = db.getCategoryDao().getAllCategoryItems()

}