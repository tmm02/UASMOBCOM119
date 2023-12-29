package com.example.myapplication.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Buku
import com.example.myapplication.databinding.ItemBukuBinding

class BukuAdapter : ListAdapter<Buku, BukuAdapter.BukuViewHolder>(BukuDiffCallback()) {

    private var onItemClickListener: ((Buku) -> Unit)? = null

    fun setOnItemClickListener(listener: (Buku) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        val binding = ItemBukuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BukuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val buku = getItem(position)
        holder.bind(buku)
    }

    inner class BukuViewHolder(private val binding: ItemBukuBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(buku: Buku) {
            binding.buku = buku
            binding.executePendingBindings()
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val buku = getItem(position)
                onItemClickListener?.invoke(buku)
            }
        }
    }
}

class BukuDiffCallback : DiffUtil.ItemCallback<Buku>() {
    override fun areItemsTheSame(oldItem: Buku, newItem: Buku): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Buku, newItem: Buku): Boolean {
        return oldItem == newItem
    }
}
