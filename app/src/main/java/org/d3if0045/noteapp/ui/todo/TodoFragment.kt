package org.d3if0045.noteapp.ui.todo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.d3if0045.noteapp.R
import org.d3if0045.noteapp.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {

    private lateinit var binding : FragmentTodoBinding
    private lateinit var todoAdapter: TodoAdapter

    private val viewModel: TodoViewModel by lazy {
        val db = org.d3if0045.noteapp.data.db.TodoDb.getInstance(requireContext())
        val factory = TodoViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[TodoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTodoBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.tipsFragment -> {
                findNavController().navigate(
                    R.id.action_todoFragment_to_tipsFragment
                )
                return true
            }

            R.id.addTodoFragment -> {
                findNavController().navigate(
                    R.id.action_todoFragment_to_addTodoFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        todoAdapter = TodoAdapter( object : TodoAdapter.OnClickListener {
            override fun onItemClick(id: Long) {}

            override fun goToDetailTodo(todoId: Long) {
                findNavController().navigate(
                    TodoFragmentDirections.actionTodoFragmentToDetailTodoFragment(todoId)
                )
            }

        })


        binding.btnDeleteTodo.setOnClickListener {
            hapusData()
        }

        with(binding.rvTodoItems) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = todoAdapter
            setHasFixedSize(true)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            binding.emptyTextView.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            todoAdapter.submitList(it)

        }

    }


    private fun hapusData() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Konfirmasi penghapusan")
            .setMessage("Yakin ingin menghapus task ini ?")
            .setNegativeButton("Batal") { _, _ ->
            }
            .setPositiveButton("Hapus") { _, _ ->
                viewModel.hapusData()
            }.show()
    }


}