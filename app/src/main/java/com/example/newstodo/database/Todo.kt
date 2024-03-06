package com.example.newstodo.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Todo(
    var item:String?,
    var catName:String?

){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}
