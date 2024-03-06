package com.example.newstodo.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Category(
    var item:String?

){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}
