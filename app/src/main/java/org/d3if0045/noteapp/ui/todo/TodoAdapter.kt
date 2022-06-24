package org.d3if0045.noteapp.ui.todo

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0045.noteapp.data.db.TodoEntity
import org.d3if0045.noteapp.databinding.ItemListTodoBinding


class TodoAdapter(private val clickListener: OnClickListener): ListAdapter<TodoEntity, TodoAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TodoEntity>() {
            override fun areItemsTheSame(oldData: TodoEntity, newData: TodoEntity): Boolean {
                return oldData.id == newData.id
            }
            override fun areContentsTheSame(oldData: TodoEntity, newData: TodoEntity): Boolean {
                return oldData == newData
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListTodoBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemListTodoBinding,
        private val clickListener: OnClickListener
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(todos: TodoEntity) {
           binding.apply {
               tvTodoTitle.text = todos.title
               cbDone.isChecked = todos.isChecked

               toggleStrikeThrough(tvTodoTitle, todos.isChecked)

               cbDone.setOnCheckedChangeListener { _, isChecked ->
                   toggleStrikeThrough(binding.tvTodoTitle, isChecked)
                   todos.isChecked = !todos.isChecked
               }
               ivDetail.setOnClickListener{

                   clickListener.goToDetailTodo(todos.id)

               }
           }
//            btn.setOnClickListener {
//                clickListener.onItemClick(anime.id)
//            }
//            detailButton.setOnClickListener {
//                clickListener.goToDetailAnime(anime.mal_id)
//            }
        }
    }

    fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }


    interface OnClickListener {
        fun onItemClick(id: Long)
        fun goToDetailTodo(todoId: Long)
    }
}