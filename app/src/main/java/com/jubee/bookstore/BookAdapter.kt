package com.jubee.bookstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jubee.bookstore.databinding.BookListItemBinding
import com.jubee.bookstore.dto.BookDto


public class BookAdapter(
    val clickListener: (BookDto) -> Unit
) :
    RecyclerView.Adapter<BookAdapter.BookItemViewHolder>() {

    var data = mutableListOf<BookDto>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BookListItemBinding.inflate(inflater, parent, false)
        return BookItemViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        val bookItem = data[position]
        holder.binding.book = bookItem
        Glide.with(holder.itemView).load(bookItem.photo).into(holder.binding.bookImage)
        holder.itemView.setOnClickListener { clickListener(bookItem) }
    }

    inner class BookItemViewHolder(v: View, val binding: BookListItemBinding) :
        RecyclerView.ViewHolder(v)
}