package com.jubee.bookstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jubee.bookstore.databinding.BookListItemBinding
import com.jubee.bookstore.model.BookModel


public class BookAdapter(
    private val items: List<BookModel>,
    val clickListener: (BookModel) -> Unit
) :
    RecyclerView.Adapter<BookAdapter.BookItemViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BookListItemBinding.inflate(inflater, parent, false)
        return BookItemViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        val bookItem = items[position]
        holder.binding.book = bookItem
        Glide.with(holder.itemView).load(bookItem.photo).into(holder.binding.bookImage)
        holder.itemView.setOnClickListener { clickListener(bookItem) }
    }

    inner class BookItemViewHolder(v: View, val binding: BookListItemBinding) :
        RecyclerView.ViewHolder(v)
}