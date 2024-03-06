package com.example.newstodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newstodo.database.Category
import com.example.newstodo.database.Todo

class MainActivityData:ViewModel() {
    private val _data = MutableLiveData<List<Todo>>()
    private val _datacat = MutableLiveData<List<Category>>()
    private val _catName = MutableLiveData<String>()

    val data: LiveData<List<Todo>> = _data
    val datacat: LiveData<List<Category>> = _datacat
    val catName: LiveData<String> = _catName

    fun setData(data:List<Todo>){
        _data.value = data
    }
    fun setDataCat(datacat:List<Category>){
        _datacat.value = datacat
    }
    fun setCatName(catName:String){
        _catName.value = catName
    }


}