package com.example.newstodo

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class TodoCatViewHolder(view: View):ViewHolder(view) {
    val cbcategory:CheckBox = view.findViewById(R.id.cbcategory)
    val ivCatDel:ImageView = view.findViewById(R.id.ivCatDel)
    val viewBtn: Button = view.findViewById(R.id.viewbtn)

}