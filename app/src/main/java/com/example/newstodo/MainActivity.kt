package com.example.newstodo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.newstodo.database.TodoDatabase
import com.example.newstodo.database.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    var categoryFragment = NewsTodoFragment()
    var homeFragment = HomeFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadCategory()
        val webbtn:Button = findViewById(R.id.webbtn)
        var ivHomeBtn:ImageView = findViewById(R.id.ivHomeBtn)
        val repository = TodoRepository(TodoDatabase.getInstance(this))
        val viewModel = ViewModelProvider(this)[MainActivityData::class.java]
        viewModel.catName.observe(this) {

            Log.d("message",it)
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            loadItems()
            CoroutineScope(Dispatchers.IO).launch {
                var data = repository.getCatTodoItems(viewModel.catName.value)
                withContext(Dispatchers.Main) {
                    viewModel.setData(data)
                }
            }
        }
        webbtn.setOnClickListener{
            val url = "https://www.dailynews.lk/"
            val uri = Uri.parse(url)
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = uri
            startActivity(intent)
        }

        ivHomeBtn.setOnClickListener {
            loadCategory()
        }

    }


    fun loadItems(){
        var fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)

        if(fragment == null){
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView,homeFragment).commit()
        }
        else{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,homeFragment).commit()
        }
    }

    fun loadCategory(){
        var fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)

        if(fragment == null){
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView,categoryFragment).commit()
        }
        else{
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,categoryFragment).commit()
        }
    }

}