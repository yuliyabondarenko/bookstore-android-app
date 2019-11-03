package com.jubee.bookstore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jubee.bookstore.model.BookModel
import kotlinx.android.synthetic.main.book_list_item.view.*

public class BookAdapter(private val items: List<BookModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.book_list_item,
                parent,
                false
            )
        ) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.bookName?.text = items[position].name
        holder.itemView.bookPrice?.text = items[position].price.toString()
        holder.itemView.bookAbsent?.text = items[position].absent.toString()
    }
}

