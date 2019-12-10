package com.jubee.bookstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
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
        return BookItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        val bookItem = items[position]
        holder.binding?.book = bookItem
        holder.itemView.setOnClickListener { clickListener(bookItem) }
    }

    inner class BookItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var binding: BookListItemBinding? = null

        init {
            binding = DataBindingUtil.bind(v)
        }
    }
}