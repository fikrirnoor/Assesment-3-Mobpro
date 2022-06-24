package org.d3if0045.noteapp.ui.addtodo

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import org.d3if0045.noteapp.R
import org.d3if0045.noteapp.databinding.FragmentAddTodoBinding


class AddTodoFragment : Fragment() {

    private lateinit var binding : FragmentAddTodoBinding

    private val viewModel: AddTodoViewModel by lazy {
        val db = org.d3if0045.noteapp.data.db.TodoDb.getInstance(requireContext())
        val factory = AddTodoViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[AddTodoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddTodoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddTodo.setOnClickListener {
            addTodo(it)
            val toast = Toast.makeText(context, "Todo Berhasil ditambahkan", Toast.LENGTH_SHORT)
            toast.show()
            setView()
        }

    }

    private fun addTodo(it : View?){

        val todoTitle = binding.edtTodoTitle.text.toString()
        if (TextUtils.isEmpty(todoTitle)) {
            binding.edtTodoTitle.requestFocus()
            Toast.makeText(context, R.string.msg_title_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.addTodo(
            todoTitle,
            false
        )

        Navigation.findNavController(it!!).navigate(R.id.action_addTodoFragment_to_todoFragment)
    }

    private fun setView(){
        binding.apply {
            edtTodoTitle.text?.clear()
        }
    }


}