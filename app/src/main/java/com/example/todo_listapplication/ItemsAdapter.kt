package com.example.todo_listapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter (private val group: Group,listenerContext: OnItemClickListener): RecyclerView.Adapter<ItemViewHolder>() {

    private var myInterface : OnItemClickListener = listenerContext
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item: Item = group.items[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            myInterface.itemClicked(position)
        }

        holder.itemView.setOnLongClickListener{
            myInterface.itemLongClicked(position)
            true
        }
    }

    override fun getItemCount(): Int = group.items.size
}
