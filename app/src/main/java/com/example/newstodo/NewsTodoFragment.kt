package com.example.newstodo

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newstodo.database.Category
import com.example.newstodo.database.TodoDatabase
import com.example.newstodo.database.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsTodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsTodoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter: CategoryAdapter
    private lateinit var viewModel: MainActivityData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_news_todo, container, false)

        val recyclerView: RecyclerView = rootView.findViewById(R.id.rvNews)

        val repository = TodoRepository(TodoDatabase.getInstance(requireActivity()))

        viewModel = ViewModelProvider(requireActivity())[MainActivityData::class.java]
        viewModel.datacat.observe(requireActivity()) {
            adapter = CategoryAdapter(it, repository, viewModel)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        }
        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAllTodoCatItems()
            withContext(Dispatchers.Main) {
                viewModel.setDataCat(data)
            }
        }
        val addItem: ImageView = rootView.findViewById(R.id.ivAddCat)
        addItem.setOnClickListener {
            displayAlert(repository)
        }
        return rootView;
    }
    private fun displayAlert(repository: TodoRepository) {
        val builder = AlertDialog.Builder(requireActivity())

        builder.setTitle("Enter New Category")
        builder.setMessage("Enter the Category below:")


        val input = EditText(requireActivity())
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)


        builder.setPositiveButton("OK") { dialog, which ->


            val item = input.text.toString()
            if (item.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                repository.insertcat(Category(item))
                val data = repository.getAllTodoCatItems()
                withContext(Dispatchers.Main) {
                    viewModel.setDataCat(data)
                }
            }
        }else {
                Toast.makeText(context, "Category can not be Empty", Toast.LENGTH_LONG).show()
            }
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsTodoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsTodoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}