package com.chkan.testwork

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chkan.testwork.model.Titles
import com.chkan.testwork.utils.MenuAdapter


//автоматически обновляет ресайклер когда изменяется ливдата в моделвью
    @BindingAdapter("listData")
    fun bindRecyclerView(recyclerView: RecyclerView,
                         data: List<Titles>?) {
        val adapter = recyclerView.adapter as MenuAdapter
        adapter.submitList(data)
    }


