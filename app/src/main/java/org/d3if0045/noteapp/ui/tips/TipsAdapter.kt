package org.d3if0045.noteapp.ui.tips

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import org.d3if0045.noteapp.R
import org.d3if0045.noteapp.data.model.Tips
import org.d3if0045.noteapp.data.network.TipsApi
import org.d3if0045.noteapp.databinding.ItemListTipsBinding

class TipsAdapter : RecyclerView.Adapter<TipsAdapter.TipsViewHolder>() {

    private val items = mutableListOf<Tips>()

    @SuppressLint("NotifyDataSetChanged")
    fun setListTipsData(data : List<Tips>){
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TipsViewHolder(
        ItemListTipsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: TipsViewHolder, position: Int) = with(holder) {
        bind(items[position])
    }

    override fun getItemCount() = items.size

    class TipsViewHolder(var binding: ItemListTipsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tips: Tips) = with(binding) {
            Glide.with(this.root)
                .load(TipsApi.getTipsUrl(tips.imageId))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(ivTips)
            tvTipsName.text = tips.name
            tvTipsDesc.text = tips.description

            root.setOnClickListener {

                Snackbar.make(
                    root,
                    tips.name,
                    Snackbar.LENGTH_SHORT,

                    ).show()
            }
        }
    }
}