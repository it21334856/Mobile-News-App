package com.example.newstodo

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView.Adapter
import android.view.ViewGroup
import android.widget.Toast
import com.example.newstodo.database.Category

import com.example.newstodo.database.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryAdapter (items:List<Category>, repository: TodoRepository
                       , viewModel:MainActivityData):Adapter<TodoCatViewHolder>() {

    var context: Context? = null
    val items = items
    val repository = repository
    val viewModel = viewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoCatViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_category, parent, false)
        context = parent.context

        return TodoCatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TodoCatViewHolder, position: Int) {
        holder.cbcategory.text = items[position].item
        val viewBtn = holder.viewBtn
        viewBtn.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {
                    viewModel.setCatName(items[position].item.toString())
                }

        }
        holder.ivCatDel.setOnClickListener {
            val isChecked = holder.cbcategory.isChecked
            if (isChecked) {
                CoroutineScope(Dispatchers.IO).launch {
                    repository.deletecat(items[position])
                    val data = repository.getAllTodoCatItems()
                    withContext(Dispatchers.Main) {
                        viewModel.setDataCat(data)
                    }
                }

                Toast.makeText(context, "Item Deleted", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(context, "Select the news to delete", Toast.LENGTH_LONG).show()
            }
        }
    }
}