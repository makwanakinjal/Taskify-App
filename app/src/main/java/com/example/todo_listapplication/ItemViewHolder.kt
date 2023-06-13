package com.example.todo_listapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder(inflater: LayoutInflater,parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_row,parent,false)){

    private var itemNameTextView: TextView? = null
    private var itemCheckBox: CheckBox?= null

    init {
        itemNameTextView = itemView.findViewById(R.id.itemNameTextView)
        itemCheckBox = itemView.findViewById(R.id.itemCheckBox)
    }
    fun bind(item: Item){

        itemNameTextView!!.text = item.name
        itemCheckBox!!.isChecked = item.completed

    }
}